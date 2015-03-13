package br.ufes.scap.nucleo.aplicacao;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.AsyncMailer;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.nucleo.persistencia.RelatorDAO;

public class AplRelatorImp implements AplRelator{//ESSA CLASSE FOI CONSIDERADA DESNECESSÁRIA EM UMA REAVALIAÇÃO DO DIAGRAMA DE CLASSES, SEUS MÉTODOS DEVEM SER MOVIDOS PARA 
											//A CLASSE AplAfastamentoImp DESTE PACOTE

	private final AsyncMailer mailer;
	
	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Inject
	private RelatorDAO relatorDAO;
	
	@Inject
	public AplRelatorImp(AsyncMailer mailer) {
		this.mailer = mailer;
	}
	
	@Override
	public void salvar(Relator relator, Afastamento afastamento) {
		
		relator.setAfastamento(afastamento);
		
		SituacaoSolic situacao = SituacaoSolic.LIBERADO;
		afastamento.setSituacaoSolicitacao(situacao);
		
		afastamentoDAO.merge(afastamento);
		
		Email email = new SimpleEmail();
        email.setSubject("SCAP - Você foi escolhido como Relator de um Afastamento");
        try {
			email.addTo(relator.getRelator().getEmail());
			email.setMsg("Você foi escolhido como Relator do afastamento de ID: " + afastamento.getId_afastamento().toString());
			mailer.asyncSend(email);
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		relatorDAO.salvar(relator);
	}

}

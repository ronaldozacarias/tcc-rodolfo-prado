package br.ufes.scap.nucleo.aplicacao;

import java.text.DateFormat;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.AsyncMailer;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.secretaria.persistencia.PessoaDAO;

public class AplAfastamentoImp implements AplAfastamento{

	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Inject
	private PessoaDAO pessoaDAO;
	
	private final AsyncMailer mailer;
	
	@Inject
	public AplAfastamentoImp(AsyncMailer mailer){
		this.mailer = mailer;
	}
	
	@Override
	public void salvar(Afastamento novoAfastamento,Pessoa solicitante,TipoAfastamento tipo,
			Onus onusAfastamento) {
		novoAfastamento.setSolicitante(solicitante);
		novoAfastamento.setTipoAfastamento(tipo);
		novoAfastamento.setOnus(onusAfastamento);
		if(tipo.getTipoAfastamento().equals("NACIONAL")){
			SituacaoSolic situacao = SituacaoSolic.LIBERADO;
			novoAfastamento.setSituacaoSolicitacao(situacao);
		}else{
			SituacaoSolic situacao = SituacaoSolic.INICIADO;
			novoAfastamento.setSituacaoSolicitacao(situacao);
		}
		afastamentoDAO.salvar(novoAfastamento);
		
		List<Pessoa> listaProfessores = pessoaDAO.listaProfessores();
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
		
		for(int i=0;i<listaProfessores.size();i++){
			Email email = new SimpleEmail();
	        email.setSubject("SCAP - Um novo Afastamento foi solicitado");
	        try {
				email.addTo(listaProfessores.get(i).getEmail());
				email.setMsg("O Professor: "+novoAfastamento.getSolicitante().getNome()+" solicitou um Afastamento a partir de: "+
				f.format(novoAfastamento.getData_iniAfast().getTime())+". Até: "+f.format(novoAfastamento.getData_fimAfast().getTime())+".");
				mailer.asyncSend(email);
				
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Afastamento> listaAfastamentos() {
		return afastamentoDAO.listaAfastamentos();
	}

	@Override
	public Afastamento buscaId(String id_afastamento) {
		return afastamentoDAO.buscaId(id_afastamento);
	}

	@Override
	public void mudarStatus(Afastamento afastamento, SituacaoSolic novoStatus,Pessoa logado) {
		afastamento.setSituacaoSolicitacao(novoStatus);
		afastamentoDAO.merge(afastamento);
		Email email = new SimpleEmail();
        email.setSubject("SCAP - O status da sua Solicitação de Afastamento foi alterado.");
        try {
			email.addTo(afastamento.getSolicitante().getEmail());
			email.setMsg("O status de sua Solicitação de Afastamento ID: "+afastamento.getId_afastamento().toString()+" foi alterado para "
							+novoStatus.getStatusAfastamento()+" por: "+logado.getNome()+" "+logado.getSobreNome()+".");
			mailer.asyncSend(email);
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}

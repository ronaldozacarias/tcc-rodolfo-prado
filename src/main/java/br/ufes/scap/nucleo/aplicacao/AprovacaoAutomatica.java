package br.ufes.scap.nucleo.aplicacao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import br.com.caelum.vraptor.simplemail.AsyncMailer;
import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.secretaria.persistencia.PessoaDAO;




@Scheduled(fixedRate = 30000)
public class AprovacaoAutomatica implements Task {

	@Inject
	AfastamentoDAO afastamentoDAO;
	
	@Inject
	PessoaDAO pessoaDAO;
	
	private final AsyncMailer mailer;
	
	protected AprovacaoAutomatica(){
		this(null);
	}
	
	@Inject
	public AprovacaoAutomatica(AsyncMailer mailer) {
		this.mailer = mailer;
	}
	
	@Override
	public void execute() {
		List<Afastamento> lista = afastamentoDAO.listaAfastamentos();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,-10);
		Integer tam = lista.size();
		Integer count = 0;
		
		while(tam >= count){
			if(cal.compareTo(lista.get(count).getData_criacao()) >= 0){
				if(lista.get(count).getTipoAfastamento().getTipoAfastamento().equals("NACIONAL") &&
						lista.get(count).getSituacaoSolicitacao().getStatusAfastamento().equals("LIBERADO")){
					
					SituacaoSolic situacao = SituacaoSolic.APROVADO_CT;
					lista.get(count).setSituacaoSolicitacao(situacao);
					Pessoa pessoa = pessoaDAO.buscaId(lista.get(count).getSolicitante().getId());
					afastamentoDAO.merge(lista.get(count));	
					Email email = new SimpleEmail();
			        email.setSubject("SCAP - A sua Solicitação de Afastamento foi APROVADA");
			        try {
						email.addTo(pessoa.getEmail());
						email.setMsg("O status de sua Solicitação de Afastamento ID: "+lista.get(count).getId_afastamento().toString()+
			        		" foi alterado para APROVADO_CT");
						mailer.asyncSend(email);
					} catch (EmailException e) {
						e.printStackTrace();
					}
			        
				}
			}
			count = count + 1;
		}
	}

}

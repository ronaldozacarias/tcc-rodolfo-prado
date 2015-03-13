package br.ufes.scap.nucleo.aplicacao;

import java.util.List;

import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoParecer;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.nucleo.persistencia.ParecerDAO;
import br.ufes.scap.nucleo.persistencia.RelatorDAO;
import br.ufes.scap.secretaria.persistencia.MandatoDAO;

public class AplParecerImp implements AplParecer{

	@Inject
	private ParecerDAO parecerDAO;
	
	@Inject
	private RelatorDAO relatorDAO;
	
	@Inject
	private MandatoDAO mandatoDAO;
	
	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Override
	public void salvar(Parecer parecer,Afastamento afastamento,Pessoa usuario,TipoParecer tipoParecer) {
		parecerDAO.salvar(parecer);
		if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL") && !(mandatoDAO.checaMandato(usuario.getId().toString()))){
			Relator relator = relatorDAO.buscaPorAfastamento(afastamento.getId_afastamento().toString());
			if(relator != null){
				if(relator.getRelator().equals(usuario)){
					if(tipoParecer.get().equals("FAVORAVEL")){
						SituacaoSolic situacao = SituacaoSolic.APROVADO_CT;
						afastamento.setSituacaoSolicitacao(situacao);
						}else{
						SituacaoSolic situacao = SituacaoSolic.REPROVADO;
						afastamento.setSituacaoSolicitacao(situacao);
						}
					afastamentoDAO.merge(afastamento);
					}
				}
		}
	}

	@Override
	public List<Parecer> buscaPorAfastamento(String id_afastamento) {
		return parecerDAO.buscaPorAfastamento(id_afastamento);
	}

}

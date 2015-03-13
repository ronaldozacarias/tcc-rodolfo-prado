package br.ufes.scap.secretaria.aplicacao;

import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Mandato;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.persistencia.MandatoDAO;
import br.ufes.scap.secretaria.persistencia.PessoaDAO;

public class AplMandatoImp implements AplMandato{

	@Inject
	private PessoaDAO pessoaDAO;
	
	@Inject 
	private MandatoDAO mandatoDAO;
	
	@Override
	public void salvar(Mandato novoMandato, String matricula) {
		Pessoa chefeDepatamento;
		chefeDepatamento = pessoaDAO.buscaMatricula(matricula);
		novoMandato.setPessoa(chefeDepatamento);
		mandatoDAO.salvar(novoMandato);
	}

	@Override
	public boolean checaMandato(String id_pessoa) {
		return mandatoDAO.checaMandato(id_pessoa);
	}

}

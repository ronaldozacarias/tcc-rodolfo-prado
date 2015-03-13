package br.ufes.scap.secretaria.controle;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.SecretarioRestricted;
import br.ufes.scap.nucleo.controle.IndexController;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Controller
public class PessoaController {
	
	@Inject
	private AplPessoa aplPessoa;
	
	private final Result result;
	
	protected PessoaController() {
		this(null);
	}
	
	@Inject
	public PessoaController(Result result) {
		this.result = result;
	}
	
	@SecretarioRestricted
	public void formulario(){
		
	}
	
	public void lista(){}
	
	@SecretarioRestricted
	public void salva(Pessoa novoUsuario,HttpSession session){
		aplPessoa.salvar(novoUsuario);
		result.redirectTo(IndexController.class).index(session);
	}
	
	public void busca(){
	}
	
	public List<Pessoa> listar(String nome,String sobreNome){
		
		List<Pessoa> lista = aplPessoa.buscaNome(nome, sobreNome);
		return lista;
	}

}

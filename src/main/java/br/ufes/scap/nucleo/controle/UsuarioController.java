package br.ufes.scap.nucleo.controle;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Stateful
@Controller
public class UsuarioController {
	
	@Inject
	private Result result;
	
	@Inject
	private Usuario usuarioWeb;
	
	@Inject
	private AplPessoa aplPessoa;
	
	public void login(){
		
	}
	
	public void logar(Pessoa usuario,HttpSession session){
		Pessoa pessoa = new Pessoa();
		pessoa = aplPessoa.buscaMatricula(usuario.getMatricula());
		if(pessoa!=null){
			if(pessoa.getPassword().equals(usuario.getPassword())){
				usuarioWeb.login(pessoa);
				session.setAttribute("usuarioWeb", usuarioWeb);
				session.getAttribute("usuarioWeb");
				result.redirectTo(AfastamentoController.class).busca(session);
			}else {
				result.include("variable", "Matricula ou Senha Incorreta");
				result.redirectTo(UsuarioController.class).login();
			}
		}else{
			result.include("variable", "Matricula ou Senha Incorreta");
			result.redirectTo(UsuarioController.class).login();
		}
	}
	
	public void logout(HttpSession session){
		Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		usuarioWeb.logout();
		session.removeAttribute("usuarioWeb");
		result.redirectTo(IndexController.class).index(session);
	}
	
	public void naoAutorizado(){
	}
	
	protected UsuarioController() {
		this(null);
	}
	
	@Inject
	public UsuarioController(Result result) {
		this.result = result;
	}
	
}

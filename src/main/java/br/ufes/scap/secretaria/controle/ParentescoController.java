package br.ufes.scap.secretaria.controle;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.SecretarioRestricted;
import br.ufes.scap.nucleo.controle.IndexController;
import br.ufes.scap.nucleo.dominio.TipoParentesco;
import br.ufes.scap.secretaria.aplicacao.AplParentesco;

@Controller
public class ParentescoController {
	
	@Inject
	AplParentesco aplParentesco;
	
	private final Result result;
	
	protected ParentescoController() {
		this(null);
	}
	
	@Inject
	public ParentescoController(Result result) {
		this.result = result;
	}
	
	@SecretarioRestricted
	public void formulario(String matricula1){
		result.include("matricula1",matricula1);
	}
	
	public void lista(){}
	
	@SecretarioRestricted
	public void salva(String matricula1,String matricula2,TipoParentesco tipo,HttpSession session){
		aplParentesco.salvar(matricula1,matricula2,tipo);
		result.redirectTo(IndexController.class).index(session);
	}
	
}

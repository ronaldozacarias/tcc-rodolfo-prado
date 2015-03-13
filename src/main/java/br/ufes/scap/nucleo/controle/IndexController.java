package br.ufes.scap.nucleo.controle;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;


@Controller
public class IndexController {

	private final Result result;

	/**
	 * @deprecated CDI eyes only
	 */
	protected IndexController() {
		this(null);
	}
	
	@Inject
	public IndexController(Result result) {
		this.result = result;
	}
	
	//@Path(value="/",priority=1)
	public void index(HttpSession session) {
		result.redirectTo(AfastamentoController.class).busca(session);
		/*Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		if(usuarioWeb.getLogado().getTipoPessoa().equals("1")){
			result.include("secretario",false);
		}else{
			result.include("secretario",true);
		}*/
	}
	
	public void msgErro(String msg){
		result.include("msg",msg);
	}
	
	public void teste(){
		
	}
	
}
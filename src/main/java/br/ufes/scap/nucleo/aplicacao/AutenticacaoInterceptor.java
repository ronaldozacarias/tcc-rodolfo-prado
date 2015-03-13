package br.ufes.scap.nucleo.aplicacao;

import javax.inject.Inject;




import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.ufes.scap.nucleo.controle.UsuarioController;

@Intercepts
public class AutenticacaoInterceptor implements Interceptor{

	private Result result;
	private Usuario usuario;
	
	
	@Inject
	public AutenticacaoInterceptor (Result result,Usuario usuario){
		this.result = result;
		this.usuario = usuario;
	}
	@Deprecated AutenticacaoInterceptor(){}
	
	@Override
	public boolean accepts(ControllerMethod arg0) {
		// TODO Auto-generated method stub
		if(arg0.getController().getType().equals(UsuarioController.class)){
			return false;
		}else{
			if(arg0.getController().getType().equals(AprovacaoAutomatica.class)){
				return false;
			}else {
				return true;
			}
		}
	}

	@Override
	public void intercept(InterceptorStack arg0, ControllerMethod arg1,
			Object arg2) throws InterceptionException {
		// TODO Auto-generated method stub
		if(usuario.isLogado()){
			arg0.next(arg1,arg2);
		}else{
			result.redirectTo(UsuarioController.class).login();
		}
	}
}

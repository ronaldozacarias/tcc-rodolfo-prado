package br.ufes.scap.nucleo.aplicacao;

import javax.inject.Inject;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.ufes.scap.nucleo.controle.IndexController;
import br.ufes.scap.nucleo.controle.UsuarioController;
import br.ufes.scap.secretaria.persistencia.MandatoDAO;

@Intercepts(after = AutenticacaoInterceptor.class)
public class AutorizacaoInterceptor implements Interceptor{

	private Result result;
	private Usuario usuario;
	
	@Inject
	private MandatoDAO mandatoDAO;
	
	@Inject
	public AutorizacaoInterceptor (Result result,Usuario usuario){
		this.result = result;
		this.usuario = usuario;
	}
	@Deprecated AutorizacaoInterceptor(){}
	
	@Override
	public boolean accepts(ControllerMethod arg0) {
		
		return !arg0.getController().getType().equals(UsuarioController.class);
		
	}

	@Override
	public void intercept(InterceptorStack arg0, ControllerMethod arg1,
			Object arg2) throws InterceptionException {
		
		if(usuario.getLogado().getTipoPessoa().equals("1")){
			result.include("secretario",false);
			result.include("professor",true);
		}else{
			result.include("secretario",true);
			result.include("professor", false);
		}
		
		//se o metodo possui alguma restrição entra no if
		if(arg1.containsAnnotation(SecretarioRestricted.class) || arg1.containsAnnotation(ProfessorRestricted.class) || arg1.containsAnnotation(ChefeRestricted.class)){
			//se é um metodo de secretario e o usuario logado é secretario entra no if
			if(usuario.getLogado().getTipoPessoa().equals("2") && arg1.containsAnnotation(SecretarioRestricted.class)){
				arg0.next(arg1,arg2);
			}else {
				//se é um metodo de Professor e o usuario logado é Professor entra no if
				if(usuario.getLogado().getTipoPessoa().equals("1") && arg1.containsAnnotation(ProfessorRestricted.class)){
					arg0.next(arg1,arg2);
				}else{
					//se é um metodo de Chefe do Departamento e o usuario logado esta com o mandato vigente entra no if
					if(mandatoDAO.checaMandato(usuario.getLogado().getId().toString()) && arg1.containsAnnotation(ChefeRestricted.class)){
						arg0.next(arg1,arg2);
					}else{
						result.redirectTo(IndexController.class).msgErro("Seu login não tem autorização para realizar esta tarefa!");
					}
				}
			}
		}else {
			arg0.next(arg1,arg2);
		}
	}
}

package br.ufes.scap.secretaria.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.SecretarioRestricted;
import br.ufes.scap.nucleo.controle.IndexController;
import br.ufes.scap.nucleo.dominio.Mandato;
import br.ufes.scap.secretaria.aplicacao.AplMandato;

@Controller
public class MandatoController {
	
	@Inject 
	private AplMandato	aplMandato;

	private final Result result;
	
	protected MandatoController() {
		this(null);
	}
	
	@Inject
	public MandatoController(Result result) {
		this.result = result;
	}
	
	@SecretarioRestricted
	public void formulario(){
		
	}
	
	public void lista(){}
	
	@SecretarioRestricted
	public void salva(Mandato novoMandato,String matricula,String data_iniMandato,String data_fimMandato,HttpSession session ) throws ParseException{
		
		SimpleDateFormat formatada = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.setTime(formatada.parse(data_iniMandato.replaceAll("-","/")));
		novoMandato.setData_inicio(cal);
		cal2.setTime(formatada.parse(data_fimMandato.replaceAll("-","/")));
		novoMandato.setData_fim(cal2);
		
		aplMandato.salvar(novoMandato,matricula);
		result.redirectTo(IndexController.class).index(session);
	}
	
}

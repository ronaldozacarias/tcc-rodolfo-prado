package br.ufes.scap.nucleo.controle;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.AplRelator;
import br.ufes.scap.nucleo.aplicacao.ChefeRestricted;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.secretaria.aplicacao.AplParentesco;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;


@Controller
public class RelatorController { //ESSA CLASSE FOI CONSIDERADA DESNECESSÁRIA EM UMA REAVALIAÇÃO DO DIAGRAMA DE CLASSES, SEUS MÉTODOS DEVEM SER MOVIDOS PARA 
								//A CLASSES AfastamentoController DESTE PACOTE
	@Inject
	private AplRelator aplRelator;
	
	@Inject
	private AplAfastamento aplAfastamento;
	
	@Inject
	private AplPessoa aplPessoa;
	
	@Inject
	private AplParentesco aplParentesco;
	
	private final Result result;
	
	protected RelatorController(){
		this(null);
	}
	
	@Inject
	public RelatorController(Result result) {
		this.result = result;
	}
	
	@ChefeRestricted
	public void formulario(String msg){
		result.include("msg",msg);
	}
	
	@ChefeRestricted
	public void porAfastamento(String msg,String matricula){
		result.include("msg",msg);
		result.include("matricula",matricula);
	}
	
	@ChefeRestricted
	public void salvaPorAfastamento(Relator relator, String matricula,String id_afastamento,HttpSession session){
		Afastamento afastamento = aplAfastamento.buscaId(id_afastamento);
		relator.setRelator(aplPessoa.buscaMatricula(matricula));
		if(aplParentesco.checaParentesco(afastamento.getSolicitante().getId_pessoa().toString(),relator.getRelator().getId_pessoa().toString())){
			result.redirectTo(RelatorController.class).porAfastamento("Erro: Não é possível cadastrar essa pessoa como relatora porque existe um parentesco entre ela e o solicitante do afastamento!",matricula);
			return;
		}
		
		aplRelator.salvar(relator,afastamento);

		result.redirectTo(IndexController.class).index(session);
	}
	
	@ChefeRestricted
	public void salva(Relator relator,String matricula,HttpSession session){
		Afastamento afastamento = new Afastamento();
		afastamento = (Afastamento)session.getAttribute("afastamento");
		relator.setRelator(aplPessoa.buscaMatricula(matricula));
		if(aplParentesco.checaParentesco(afastamento.getSolicitante().getId_pessoa().toString(),relator.getRelator().getId_pessoa().toString())){
			result.redirectTo(RelatorController.class).formulario("Erro: Não é possível cadastrar essa pessoa como relatora porque existe um parentesco entre ela e o solicitante do afastamento!");
			return;
		}
		aplRelator.salvar(relator,afastamento);
		
		result.redirectTo(IndexController.class).index(session);
	}

	
}

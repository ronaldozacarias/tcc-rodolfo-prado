package br.ufes.scap.nucleo.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.AplParecer;
import br.ufes.scap.nucleo.aplicacao.ProfessorRestricted;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.TipoParecer;

@Controller
public class ParecerController {

	@Inject
	private AplParecer	aplParecer;
	
	private final Result result;
	
	protected ParecerController(){
		this(null);
	}
	
	@Inject
	public ParecerController(Result result) {
		this.result = result;
	}
	
	@ProfessorRestricted
	public void formulario(){
		
	}
	
	@ProfessorRestricted
	public void salvar(Parecer parecer,TipoParecer tipoParecer, HttpSession session){
		Afastamento afastamento = new Afastamento();
		afastamento = (Afastamento)session.getAttribute("afastamento");
		
		if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("LIBERADO")){
			result.redirectTo(IndexController.class).msgErro("Afastamendo não consta como Liberado!");
			return;
		}
		
		Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		Calendar cal = Calendar.getInstance();
		
		parecer.setRelator(usuarioWeb.getLogado());
		parecer.setAfastamento(afastamento);
		parecer.setData_parecer(cal);
		parecer.setJulgamento(tipoParecer);
		
		aplParecer.salvar(parecer,afastamento,usuarioWeb.getLogado(),tipoParecer);
		result.redirectTo(IndexController.class).index(session);
	}
	
	public List<ParecerLista> listar(HttpSession session){
		Afastamento afastamento = new Afastamento();
		afastamento = (Afastamento)session.getAttribute("afastamento");
		
		List<Parecer> listaDAO = aplParecer.buscaPorAfastamento(afastamento.getId_afastamento().toString());
		List<ParecerLista> tabela = new ArrayList<ParecerLista>();
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		for(Integer i=0;i<listaDAO.size();i++){
			ParecerLista elemento = new ParecerLista();
			elemento.setNomeCriador(listaDAO.get(i).getRelator().getNome()+" "+listaDAO.get(i).getRelator().getSobreNome());
			elemento.setData(formatada.format(listaDAO.get(i).getData_parecer().getTime()));
			elemento.setJulgamento(listaDAO.get(i).getJulgamento().get());
			elemento.setMotivo(listaDAO.get(i).getMotivoIndeferimento());
			tabela.add(elemento);
		}
		return tabela;
	}
	
}

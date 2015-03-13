package br.ufes.scap.nucleo.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.ProfessorRestricted;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;
import br.ufes.scap.secretaria.aplicacao.AplDocumento;
import br.ufes.scap.secretaria.aplicacao.AplMandato;


@Controller
public class AfastamentoController {
	
	@Inject
	private AplAfastamento aplAfastamento;
	
	@Inject
	private AplDocumento aplDocumento;
	
	@Inject
	private AplMandato aplMandato;
	
	private final Result result;
	
	protected AfastamentoController(){
		this(null);
	}
	
	@Inject
	public AfastamentoController(Result result) {
		this.result = result;
	}
	
	@ProfessorRestricted
	public void formulario(){
		
	}
	
	@ProfessorRestricted
	public void salva(Afastamento novoAfastamento,TipoAfastamento tipo,Onus onusAfastamento, HttpSession session,String data_iniAfast,String data_fimAfast,String data_iniEvento,String data_fimEvento) throws ParseException {
		Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		SimpleDateFormat formatada = new SimpleDateFormat("yyyy/MM/dd");
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		Calendar cal4 = Calendar.getInstance();
		Calendar cal5 = Calendar.getInstance();

		novoAfastamento.setData_criacao(cal);
		cal2.setTime(formatada.parse(data_iniAfast.replaceAll("-","/")));
		novoAfastamento.setData_iniAfast(cal2);
		cal3.setTime(formatada.parse(data_fimAfast.replaceAll("-","/")));
		novoAfastamento.setData_fimAfast(cal3);
		cal4.setTime(formatada.parse(data_iniEvento.replaceAll("-","/")));
		novoAfastamento.setData_iniEvento(cal4);
		cal5.setTime(formatada.parse(data_fimEvento.replaceAll("-","/")));
		novoAfastamento.setData_fimEvento(cal5);
		aplAfastamento.salvar(novoAfastamento,usuarioWeb.getLogado(),tipo,onusAfastamento);
		result.redirectTo(IndexController.class).index(session);
	}
	
	@Path(value="/",priority=1)
	public List<AfastamentoLista> busca(HttpSession session){
		
		List<Afastamento> listaDAO = aplAfastamento.listaAfastamentos();
		List<AfastamentoLista> tabela = new ArrayList<AfastamentoLista>();
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
	
		
		for(Integer i=0;i<listaDAO.size();i++){
			if(!listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento().equals("CANCELADO") &&
					!listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento().equals("ARQUIVADO")
					){
				AfastamentoLista elemento = new AfastamentoLista();
				elemento.setId_afastamento(listaDAO.get(i).getId_afastamento().toString());
				elemento.setNome_pessoa(listaDAO.get(i).getSolicitante().getNome() +" "+ listaDAO.get(i).getSolicitante().getSobreNome());
				elemento.setNome_cidade(listaDAO.get(i).getNome_cidade());
				elemento.setNome_evento(listaDAO.get(i).getNome_evento());
				elemento.setSituacaoSolicitacao(listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento());
				elemento.setTipoAfastamento(listaDAO.get(i).getTipoAfastamento().getTipoAfastamento());
				elemento.setData_iniAfast(formatada.format(listaDAO.get(i).getData_iniAfast().getTime()));
				elemento.setData_fimAfast(formatada.format(listaDAO.get(i).getData_fimAfast().getTime()));
				tabela.add(elemento);
			}
		}
		return tabela;
	}
	
	public List<DocumentoLista> mostrar(String id_afastamento,HttpSession session){
		
		Afastamento afastamento = aplAfastamento.buscaId(id_afastamento);
		if(afastamento == null){
			result.redirectTo(IndexController.class).msgErro("Id de Afastamendo não encontrada!");
			return null;
		}
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
		session.setAttribute("afastamento", afastamento);
		List<DocumentoLista> tabela = new ArrayList<DocumentoLista>();
		List<Documento> listaDAO = aplDocumento.buscaPorAfastamento(afastamento.getId_afastamento().toString());
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Integer i=0;i<listaDAO.size();i++){
			DocumentoLista elemento = new DocumentoLista();
			elemento.setId(listaDAO.get(i).getId_documento().toString());
			elemento.setTitulo(listaDAO.get(i).getTituloDocumento());
			elemento.setArquivo(listaDAO.get(i).getNomeArquivo());
			elemento.setJuntada(formatada.format(listaDAO.get(i).getData_juntada().getTime()));
			elemento.setContent(listaDAO.get(i).getContent());
			tabela.add(elemento);
		}
		
		Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		if(aplMandato.checaMandato(usuarioWeb.getLogado().getId().toString())){
			result.include("chefe",true);
		}
		
		result.include("SolicitanteNome",afastamento.getSolicitante().getNome()+" "+afastamento.getSolicitante().getSobreNome());
		result.include("SolicitanteMatricula",afastamento.getSolicitante().getMatricula());
		result.include("SolicitanteEmail",afastamento.getSolicitante().getEmail());
		result.include("SolicitanteTel",afastamento.getSolicitante().getTelefone());
		result.include("afastamentonomeCidade",afastamento.getNome_cidade());
		result.include("afastamentonomeEvento",afastamento.getNome_evento());
		result.include("afastamentotipo",afastamento.getTipoAfastamento().getTipoAfastamento());
		result.include("afastamentoonus",afastamento.getOnus().getOnus());
		result.include("afastamentostatus",afastamento.getSituacaoSolicitacao().getStatusAfastamento());
		result.include("afastamentoData_iniAfasta",f.format(afastamento.getData_iniAfast().getTime()));
		result.include("afastamentoData_fimAfasta",f.format(afastamento.getData_fimAfast().getTime()));
		result.include("afastamentoData_iniEvento",f.format(afastamento.getData_iniEvento().getTime()));
		result.include("afastamentoData_fimEvento",f.format(afastamento.getData_fimEvento().getTime()));
		result.include("id_afastamento",afastamento.getId_afastamento().toString());
		
		return tabela;

	}
	
	public void mudarStatusForm(String msg){
		result.include("msg",msg);
	}
	
	public void mudarStatus(HttpSession session,SituacaoSolic novoStatus){
		Afastamento afastamento = new Afastamento();
		Usuario usuarioWeb = (Usuario)session.getAttribute("usuarioWeb");
		afastamento = (Afastamento)session.getAttribute("afastamento");
		if(novoStatus.getStatusAfastamento().equals("CANCELADO")){
			if(!(usuarioWeb.getLogado().equals(afastamento.getSolicitante())) && !(aplMandato.checaMandato(usuarioWeb.getLogado().getId().toString()))){
				result.redirectTo(AfastamentoController.class).mudarStatusForm("Somente o solicitante do Afastamento ou o Chefe do Departamento pode Cancela-lo!");
				return;
			}
		}
		aplAfastamento.mudarStatus(afastamento,novoStatus,usuarioWeb.getLogado());
		
		result.redirectTo(IndexController.class).index(session);
	}
	
	
}

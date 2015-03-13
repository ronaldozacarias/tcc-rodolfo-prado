package br.ufes.scap.secretaria.controle;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.com.caelum.vraptor.observer.upload.*;
import br.ufes.scap.nucleo.controle.IndexController;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.secretaria.aplicacao.AplDocumento;

import java.io.ByteArrayInputStream;

import com.google.common.io.ByteStreams;

@Controller
public class DocumentoController {

	private final Result result;
	
	@Inject
	private AplDocumento aplDocumento;
	
	protected DocumentoController() {
		this(null);
	}
	
	
	@Inject
	public DocumentoController(Result result) {
		this.result = result;
	}
	
	public void formulario(){
		
	}
	
	@UploadSizeLimit(fileSizeLimit=50 * 1024 * 1024)
	public void salva(Documento documento,UploadedFile file,HttpSession session) throws IOException, ParseException{
		
		Afastamento afastamento = new Afastamento();
		afastamento = (Afastamento)session.getAttribute("afastamento");
		Calendar cal = Calendar.getInstance();
		
		documento.setData_juntada(cal);
		documento.setAfastamento(afastamento);
		documento.setNomeArquivo(file.getFileName());
		
		byte[] bytes = ByteStreams.toByteArray(file.getFile());
		documento.setContent(bytes);
		
		aplDocumento.salvar(documento);
		result.redirectTo(IndexController.class).index(session);
		
	}
	
	public void download(){
		
	}
	
	public InputStreamDownload downloadArquivo(String id_documento){
		Documento documento = aplDocumento.buscaId(id_documento);
		if(documento!=null){
			result.include("teste"," não nulo");
			String contentType = "doc/pdf";
			InputStream aux = new ByteArrayInputStream(documento.getContent());
			InputStreamDownload stream = new InputStreamDownload(aux,contentType,documento.getNomeArquivo());
			return stream;
		}else{
		result.include("teste","nulo");
		return null;
		}
	}
	



	public void lista(){}
	
}

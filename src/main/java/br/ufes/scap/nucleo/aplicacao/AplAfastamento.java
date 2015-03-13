package br.ufes.scap.nucleo.aplicacao;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;

public interface AplAfastamento {
	
	public void salvar(Afastamento novoAfastamento,Pessoa solicitante,TipoAfastamento tipo,Onus onusAfastamento);
	
	public List<Afastamento> listaAfastamentos();
	
	public Afastamento buscaId(String id_afastamento);
	
	public void mudarStatus(Afastamento afastamento,SituacaoSolic novoStatus,Pessoa logado);
	
}

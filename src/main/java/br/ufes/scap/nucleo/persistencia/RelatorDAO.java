package br.ufes.scap.nucleo.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Relator;

public interface RelatorDAO extends BaseDAO<Relator> {//ESSA CLASSE FOI CONSIDERADA DESNECESSÁRIA EM UMA REAVALIAÇÃO DO DIAGRAMA DE CLASSES, SEUS MÉTODOS DEVEM SER MOVIDOS PARA 
	//A CLASSE AfastamentoDAO DESTE PACOTE
	
	public Relator buscaId(String id_relator);
	
	public Relator buscaPorAfastamento(String id_afastamento);
	
	public List<Relator> listaRelatores();
}

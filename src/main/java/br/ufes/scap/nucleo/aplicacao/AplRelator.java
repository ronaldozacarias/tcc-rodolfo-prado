package br.ufes.scap.nucleo.aplicacao;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Relator;

public interface AplRelator {//ESSA CLASSE FOI CONSIDERADA DESNECESSÁRIA EM UMA REAVALIAÇÃO DO DIAGRAMA DE CLASSES, SEUS MÉTODOS DEVEM SER MOVIDOS PARA 
							//A AplAfastamento DESTE PACOTE
	
	public void salvar(Relator relator,Afastamento afastamento);
	
}

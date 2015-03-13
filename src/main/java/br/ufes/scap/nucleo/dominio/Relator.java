package br.ufes.scap.nucleo.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relator")
public class Relator {  //ESSA CLASSE FOI CONSIDERADA DESNECESSÁRIA EM UMA REAVALIAÇÃO DO DIAGRAMA DE CLASSES, DEVE SER SUBSTITUIDA POR APENAS UM CAMPO
						// NA CLASSE AFASTAMENTO COM O ID DE UM PROFESSOR
	@Id
	@GeneratedValue
	@Column(name="id_relator")
	private Long id_relator;	
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa relator;
	
	@ManyToOne
	@JoinColumn(name="id_afastamento")
	private Afastamento afastamento;

	public Long getId_relator() {
		return id_relator;
	}

	public void setId_relator(Long id_relator) {
		this.id_relator = id_relator;
	}

	public Pessoa getRelator() {
		return relator;
	}

	public void setRelator(Pessoa relator) {
		this.relator = relator;
	}

	public Afastamento getAfastamento() {
		return afastamento;
	}

	public void setAfastamento(Afastamento afastamento) {
		this.afastamento = afastamento;
	}
}

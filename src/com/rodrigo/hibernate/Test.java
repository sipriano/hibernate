package com.rodrigo.hibernate;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="test")
//@Table(name="test")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "finalizacao")
	private boolean finalizacao;
	
	@Column(name = "dataFinalizacao")
	@Temporal(TemporalType.DATE)
	private Calendar dataFinalizacao;
	
	public Integer getId() {
		return this.id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean getFinalizacao() {
		return this.finalizacao;
	}
	
	public Calendar getDataFinalizacao() {
		return this.dataFinalizacao;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setFinalizacao(boolean finalizacao) {
		this.finalizacao = finalizacao;
	}
	
	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	@Override
	public String toString() {
		String data = "ID: " +this.id + " Descrição: " + this.getDescricao() + " Finalizaçao: " + this.getFinalizacao()
		+ " dataFinalização: " + this.getDataFinalizacao();
		
		return data;
	}
}

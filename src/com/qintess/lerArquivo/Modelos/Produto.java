package com.qintess.lerArquivo.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Produto {

	private String nome;
	private List<Operacoes> listaOperacoes = new ArrayList<Operacoes>();
	double resultado = 0;
	
	public Produto(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
		
	public double getResultado() {
		return resultado;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Operacoes> getListaOperacoes() {
		return listaOperacoes;
	}

	public void cadastrarOperacoes(Operacoes operacao){
		listaOperacoes.add(operacao);
	}
	
	public void cadastrarResultado(double quantidade, double preco) {
		resultado += quantidade * preco;
	}
}

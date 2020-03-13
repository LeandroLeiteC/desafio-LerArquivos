package com.qintess.lerArquivo.Modelos;

public class DadosMercado {

	int idPreco;
	long diasCorridos;
	double preco;
	
	public DadosMercado(int idPreco, long diasCorridos, double preco) {
		this.idPreco = idPreco;
		this.diasCorridos = diasCorridos;
		this.preco = preco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPreco;
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
		DadosMercado other = (DadosMercado) obj;
		if (idPreco != other.idPreco)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DadosMercado [idPreco=" + idPreco + ", diasCorridos=" + diasCorridos + ", preco=" + preco + "]";
	}
	public int getIdPreco() {
		return idPreco;
	}
	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}
	public long getDiasCorridos() {
		return diasCorridos;
	}
	public void setDiasCorridos(int diasCorridos) {
		this.diasCorridos = diasCorridos;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}


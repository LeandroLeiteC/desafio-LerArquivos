package com.qintess.lerArquivo.Modelos;

public class Operacoes implements Comparable<Operacoes> {

	int idPreco;
	long diasCorridos;
	double quantidade;
	
	public Operacoes(int idPreco, long diasCorridos, double quantidade) {
		this.idPreco = idPreco;
		this.diasCorridos = diasCorridos;
		this.quantidade = quantidade;
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
		Operacoes other = (Operacoes) obj;
		if (idPreco != other.idPreco)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Operacoes [idPreco=" + idPreco + ", diasCorridos=" + diasCorridos + ", quantidade=" + quantidade + "]";
	}
	
@Override
	public int compareTo(Operacoes o) {
		
		return 0;
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

	public double getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}

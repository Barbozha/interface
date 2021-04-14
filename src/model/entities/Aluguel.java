package model.entities;

import java.util.Date;

public class Aluguel {
	private Date inicio;
	private Date termino;
	
	private Veiculo veiculo;
	private Fatura fatura;
	
	public Aluguel() {
		
	}

	public Aluguel(Date inicio, Date termino, Veiculo veiculo) {
		this.inicio = inicio;
		this.termino = termino;
		this.veiculo = veiculo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
}

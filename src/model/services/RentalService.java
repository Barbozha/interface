package model.services;

import model.entities.Aluguel;
import model.entities.Fatura;

public class RentalService {
	private Double precoHora;
	private Double precoDia;
	
	private TaxService taxaServico;

	public RentalService(Double precoHora, Double precoDia, TaxService taxaServico) {
		this.precoHora = precoHora;
		this.precoDia = precoDia;
		this.taxaServico = taxaServico;
	}
	
	
	public void processarFatura(Aluguel aluguel) {
		long t1 = aluguel.getInicio().getTime();
		long t2 = aluguel.getTermino().getTime();
		// tenho milisegundos e divido por 1000 e converto para segundos
		// Agora tenho segundo e divido por 60 para ter minutos
		// Agora tenho minutos e divido por 60 para ter horas.
		double horas = (double)(t2 - t1) / 1000 / 60 / 60;
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * precoHora;
		}else {
			pagamentoBasico = Math.ceil(horas /24) * precoDia;
		}
		double taxa = taxaServico.tax(pagamentoBasico);
		aluguel.setFatura(new Fatura(pagamentoBasico, taxa));
		
	}
	
	
	
	
}

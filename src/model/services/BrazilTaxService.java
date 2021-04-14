package model.services;

public class BrazilTaxService implements TaxService{
	
	public double tax(double valor) {
		if(valor <=100.00) {
			return valor*=0.2;
		}else {
			return valor*=0.15;
		}
	}
}

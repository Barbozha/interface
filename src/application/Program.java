package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Aluguel;
import model.entities.Veiculo;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		// Uma locadora brasileira de carros cobra um valor por hora para loca��es de at�
		// 12 horas. Por�m, se a dura��o da loca��o ultrapassar 12 horas, a loca��o ser�
		// cobrada com base em um valor di�rio. Al�m do valor da loca��o, � acrescido no
		// pre�o o valor do imposto conforme regras do pa�s que, no caso do Brasil, � 20%
		// para valores at� 100.00, ou 15% para valores acima de 100.00. Fazer um
		// programa que l� os dados da loca��o (modelo do carro, instante inicial e final da
		// loca��o), bem como o valor por hora e o valor di�rio de loca��o. O programa
		// deve ent�o gerar a nota de pagamento (contendo valor da loca��o, valor do
		// imposto e valor total do pagamento) e informar os dados na tela.
		
		Locale.setDefault(Locale.US);;
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String modelo = sc.nextLine();
		try {
			System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
			Date dataRetirada = sdf.parse(sc.nextLine());
			System.out.print("Return (dd/MM/yyyy hh:ss): ");
			Date dataEntregue = sdf.parse(sc.nextLine());
			//Instanciando a classe aluguel para guardar os dados do aluguel
			Aluguel aluguel = new Aluguel(dataRetirada, dataEntregue, new Veiculo(modelo));
			
			System.out.print("Enter price per hour: ");
			double precoHora = sc.nextDouble();
			System.out.print("Enter price per day: ");
			double precoDia = sc.nextDouble();
			//Instanciando o servi�o que calcula o pre�o por hora ou por dia e instancio
			// tamb�m o meu servi�o que calcula a taxa conforme regras do Brasil.
			RentalService rental = new RentalService(precoHora, precoDia, new BrazilTaxService());
			
			// Acesso o meu servi�o de aluguel e chamo a opera��o ProcessarFatura
			// Passando como argumento o meu argumento, aluguel.
			rental.processarFatura(aluguel);
			
			// Imprimindo os dados 
			System.out.println("FATURA:");
			System.out.println("Basic payment: "+String.format("%.2f",aluguel.getFatura().getPagamentoBasico()));
			System.out.println("Tax: "+String.format("%.2f", aluguel.getFatura().getTaxa()));
			System.out.println("Total payment: "+String.format("%.2f", aluguel.getFatura().getTotalPagamento()));
			
			
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		sc.close();
	}

}

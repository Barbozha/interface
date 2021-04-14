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
		// Uma locadora brasileira de carros cobra um valor por hora para locações de até
		// 12 horas. Porém, se a duração da locação ultrapassar 12 horas, a locação será
		// cobrada com base em um valor diário. Além do valor da locação, é acrescido no
		// preço o valor do imposto conforme regras do país que, no caso do Brasil, é 20%
		// para valores até 100.00, ou 15% para valores acima de 100.00. Fazer um
		// programa que lê os dados da locação (modelo do carro, instante inicial e final da
		// locação), bem como o valor por hora e o valor diário de locação. O programa
		// deve então gerar a nota de pagamento (contendo valor da locação, valor do
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
			//Instanciando o serviço que calcula o preço por hora ou por dia e instancio
			// também o meu serviço que calcula a taxa conforme regras do Brasil.
			RentalService rental = new RentalService(precoHora, precoDia, new BrazilTaxService());
			
			// Acesso o meu serviço de aluguel e chamo a operação ProcessarFatura
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

package projeto;

import java.util.Scanner;

public class GrupoD extends Veiculos {

	public GrupoD(String fabricante, String placa, String modelo, int ano, int qntd_lugares, 
	         boolean blindado, boolean ar_condicionado, boolean automatico,
	         boolean adaptavel, boolean disponivel) {
			 super(fabricante, placa, modelo, ano, qntd_lugares, blindado, ar_condicionado, automatico, adaptavel, disponivel);
			 this.setCategoria('D');
	}
	
	@Override
	public double calculaTotal() {
		return getQntd_dias()*250;
	}
	
	@Override
	public double calculaSeguro() {
		if(!isSeguro()) {
			return 0;
		}
		else {
			return calculaTotal()*0.15;
		}
	}
	
	public static GrupoD criaObj() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o fabricante do veículo: ");
		String fabricante = scanner.nextLine();
		
		System.out.print("Digite a placa do veículo: ");
		 String placa  = scanner.nextLine();
		
		System.out.print("Digite o modelo do veículo: ");
		String modelo = scanner.nextLine();
		
		System.out.print("Digite o ano de fabricação do veículo: ");
		int ano = scanner.nextInt();
		
		System.out.print("Digite a quantidade de lugares do veículo: ");
		int qntd_lugares = scanner.nextInt();
		
		return new GrupoD(fabricante, placa, modelo, ano, qntd_lugares, false, true, true, true, true);
	}
	
}

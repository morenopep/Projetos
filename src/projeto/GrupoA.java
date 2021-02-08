package projeto;

import java.util.Scanner;
public class GrupoA extends Veiculos {

	public GrupoA(String fabricante, String placa, String modelo, int ano, int qntd_lugares, 
	         boolean blindado, boolean ar_condicionado, boolean automatico,
	         boolean adaptavel, boolean disponivel) {
			 super(fabricante, placa, modelo, ano, qntd_lugares, blindado, ar_condicionado, automatico, adaptavel, disponivel);
			 this.setCategoria('A');
	}
	
	@Override
	public double calculaTotal() {
		return getQntd_dias()*1159.58;
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

	public static GrupoA criaObj() {
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
		
		return new GrupoA(fabricante, placa, modelo, ano, qntd_lugares, true, true, true, false, true);
	}
	
}

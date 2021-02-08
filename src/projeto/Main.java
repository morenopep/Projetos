package projeto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static ArrayList<Veiculos> listaCarros = new ArrayList<Veiculos>();

	static Scanner scanner = new Scanner(System.in);
	static Scanner scanner2 = new Scanner(System.in);

	public static void alugar(char caso) {
		System.err.println("Para cancelar o aluguel digite -99 em qualquer momento do código.\n");
		System.out.println("\nCarros disponíveis:");
		int contCarro = 0;
		for(Veiculos v: listaCarros) 
		{
			if(v.getCategoria() == caso && v.isDisponivel()) 
			{
				System.out.print(" " + v.getModelo() + " |");
				contCarro++;
			}
		}
		
		if(contCarro == 0) 
		{
			System.err.println("Neste momento não há carros disponíveis nessa categoria, pedimos desculpas pelo inconveniente.");
			return;
		}
		
		System.out.print("\n\nEscolha o carro desejado: ");
		String escolhaCarro = scanner2.nextLine();
		if(escolhaCarro.trim().equals("-99")) 
		{
			System.out.println("Aluguel cancelado.");
			return;
		}
		
		Veiculos vAux = null;
		
		for(Veiculos v: listaCarros) 
		{
			if(v.getModelo().equalsIgnoreCase(escolhaCarro.trim()) && v.getCategoria() == caso) 
			{
				vAux = v;
			}
		}
			
		int dias, opcaoSeg;
		String localRet, localEnt;
		if(vAux != null) 
		{
			if(!vAux.isDisponivel()) 
			{
				System.err.print("\nEste carro não está disponível no momento.");
				return;
			}			
			System.out.println("\n" + vAux);	
			
			int confirmar = 0;
			do 
			{
			System.out.print("\nInforme por quantos dias irá alugar o veículo:  ");
			dias = scanner.nextInt();
			if(dias == -99) 
			{
				System.out.println("Aluguel cancelado.");
				return;
			}
			
			System.out.print("\nInforme o local de retirada: ");
			localRet = scanner2.nextLine();
			if(localRet.trim().equals("-99")) 
			{
				System.out.println("Aluguel cancelado.");
				return;
			}
			
			System.out.print("\nAgora, informe o local de entrega: ");
			localEnt = scanner2.nextLine();
			if(localEnt.trim().equals("-99")) 
			{
				System.out.println("Aluguel cancelado.");
				return;
			}
			
			System.out.print("\nDeseja adicionar o seguro? 0 para não e 1 para sim. ");
			opcaoSeg = scanner.nextInt();
			if(opcaoSeg == -99) 
			{
				System.out.println("Aluguel cancelado.");
				return;
			}
			while(opcaoSeg < 0 || opcaoSeg > 1) 
			{
				System.err.println("\nPor favor digite uma opção válida.");
				System.out.print("\nDeseja adicionar o seguro? 0 para não e 1 para sim. ");
				opcaoSeg = scanner.nextInt();
				if(opcaoSeg == -99) 
				{
					System.out.println("Aluguel cancelado.");
					return;
				}
			}
			
			
				System.out.println("\nDias que ficará com o carro: " + dias + "\nLocal de retirada: " + localRet 
						+ "\nLocal de entrega: " + localEnt + "\nSeguro: " + (opcaoSeg == 0 ? "Não" : "Sim"));
				System.out.print("\nConfirma os dados? Digite 1 para sim e 0 caso queria editá-los: ");
				confirmar = scanner.nextInt();
				if(confirmar == -99) 
				{
					System.out.println("Aluguel cancelado.");
					return;
				}
				while(confirmar != 0 && confirmar != 1) 
				{
					System.err.println("\nPor favor, digite dados válidos.");
					System.out.println("Dias que ficará com o carro: " + dias + "\nLocal de retirada: " + localRet + "\nLocal de entrega: " + localEnt);
					System.out.print("\nConfirma os dados? Digite 1 para sim e 0 caso queria editá-los: ");
					confirmar = scanner.nextInt();
					if(confirmar == -99) 
					{
						System.out.println("Aluguel cancelado.");
						return;
					}
				}
			} while(confirmar != 1);
			
			for(Veiculos v: listaCarros) 
			{
				if(vAux == v) 
				{
					v.setQntd_dias(dias);
					v.setLocal_retirada(localRet);
					v.setLocal_entrega(localEnt);
					if(opcaoSeg == 0) 
					{
						v.setSeguro(false);								
					}
					else 
					{
						v.setSeguro(true);
					}
				}
			}
		}
		else 
		{
			System.err.print("\nEsse carro não existe nessa categoria!");
			return;
		}
	
		System.out.println("\nReserva concluída!");
		for(Veiculos v: listaCarros) 
		{
			if(vAux == v) 
			{
				System.out.println("Você alugou o/a " + v.getFabricante() + " " + v.getModelo() + " por " + v.getQntd_dias()
				+ " dias. A retirada do veículo será em " + v.getLocal_retirada() + " e a devolução em " + v.getLocal_entrega()
				+ ".\nTotal seguro: " + formataMoeda(v.calculaSeguro()) + "\nTotal taxa: " + formataMoeda(v.calculaTaxa())
				+ "\nValor final: " + formataMoeda(v.calculaTotalTotal()));
				v.setDisponivel(false);
			}
		}
		System.out.println("\nObrigado por utilizar nosso sistema! :)");
		return;
	}

	public static String formataMoeda(double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMinimumFractionDigits(2);
		return nf.format(valor);
	}

	public static void bdCarros() {

		listaCarros.add(new GrupoA("Mercedes Benz", "GEN1A11", "AMG", 2020, 5, true, true, true, false, true));
        listaCarros.add(new GrupoA("Audi", "GEN8A88", "R8", 2021, 2, true, true, true, false, true));

        listaCarros.add(new GrupoB("Toyota", "GEN2B22", "Hilux", 2020, 5, false, true, true, false, true));
        listaCarros.add(new GrupoB("Volkswagen", "GEN4B44", "Tiguan", 2020, 7, false, true, true, false, true));
        listaCarros.add(new GrupoB("BMW", "GEN3B45", "X1", 2020, 7, false, true, true, false, true));

        listaCarros.add(new GrupoC("Chevrolet", "GEN3C33", "Onix", 2019, 5, false, true, false, false, false));
        listaCarros.add(new GrupoC("Fiat", "GEN5C55", "Uno", 2020, 5, false, true, false, false, true));
        listaCarros.add(new GrupoC("Hyundai", "GEN7C77", "HB20", 2019, 5, false, true, false, false, true));
        listaCarros.add(new GrupoC("Chevrolet", "GEN9C99", "Spin", 2017, 7, false, true, false, false, true));

        listaCarros.add(new GrupoD("Nissan", "GEN1D22", "Kicks", 2021, 5, false, true, true, true, true));
        listaCarros.add(new GrupoD("Renault", "GEN6D66", "Fluence", 2021, 5, false, true, true, true, true));
	}

	public static void menuIni() {
		System.out.println("\n           Entre com seu perfil: ");
		System.out.println("----------------------------------------");
		System.out.println("(1)Adminstrador\n(2)Cliente\n(0)Sair");
		System.out.println("----------------------------------------");
		System.out.print("Digite sua escolha: ");
	}

	public static void VerificaDisp() {
		char cat = 65;
		System.out.println("\n--------------------------------------------");
		for (int i = 0; i < 4; i++) {
			System.out.println("Categoria " + cat + ":");
			for (int j = 0; j < listaCarros.size(); j++) {
				if (listaCarros.get(j).getCategoria() == cat && listaCarros.get(j).isDisponivel()) {
					System.out.println("\t" + listaCarros.get(j).getFabricante() + " " + listaCarros.get(j).getModelo()
							+ ", disponível");
				}
			}

			for (int k = 0; k < listaCarros.size(); k++) {
				if (listaCarros.get(k).getCategoria() == cat && !listaCarros.get(k).isDisponivel()) {
					System.out.println("\t" + listaCarros.get(k).getFabricante() + " " + listaCarros.get(k).getModelo()
							+ ", indisponível");
				}
			}
			System.out.println("--------------------------------------------");

			cat++;
		}
		System.out.println();
	}

	public static void appCliente() {
		int entrada;
	
		System.out.println("*** ALUGUEL CARROS GENERATION ***\n");
		try {
			do {
				System.out.println(
						"Menu:\n\t0 - Sair do programa\n\t1 - Exibir categorias de carro\n\t2 - Verificar disponibilidade de carros"
								+ "\n\t3 - Alugar carro\n");
				System.out.print("Escolha sua opção: ");
				entrada = scanner.nextInt();

				switch (entrada) {
				case 0:
					System.out.println("\nPrograma finalizado.");
					break;

				case 1:
					System.out.println(
							"\nCategoria A - Carros de luxo, carros blindados, com ar-condicionado, câmbio automático e tudo que a mais nova tecnologia pode te trazer."
									+ "\nCategoria B - Carros premium, carros automáticos, com ar-condicionado, com opção de até 7 lugares, proporcionando toda comodidade para o seu passeio."
									+ "\nCategoria C - Carros standard, carros hatch e econômicos, com ar-condicionado e são perfeitos para situações cotidianas."
									+ "\nCategoria D - Carros adaptados para PCD, com ar-condicionado, transmissão automática, extremamente confortáveis e seguros (é preciso ter uma CNH de categoria H).\n");
					break;

				case 2:
					VerificaDisp();
					break;

				case 3:
					System.out.println("\nCategorias disponíveis:\n(A)-Luxo\n(B)-Premium\n(C)-Standard\n(D)-PCD");
					System.out.print("Escolha a categoria: ");
					char ent3 = scanner.next().toUpperCase().trim().charAt(0);
					System.out.println();

					switch (ent3) {
					case 'A':
						alugar('A');
						System.out.println();
						break;

					case 'B':
						alugar('B');
						System.out.println();
						break;

					case 'C':
						alugar('C');
						System.out.println();
						break;

					case 'D':
						alugar('D');
						System.out.println();
						break;

					default:
						System.out.println("Opção inválida.");
						break;
					}

					System.out.println();
					break;

				default:
					System.err.println("\nSelecione uma opção válida.\n");
					break;

				}

			} while (entrada != 0);

		} catch (InputMismatchException err1) {
			System.out.println("\nDigite apenas opções válidas para o funcionamento correto do programa.");
			System.err.println("Erro: " + err1);
		} catch (Exception err2) {
			System.err.println("Erro: " + err2);
		}
	}

	public static void appAdm() {
		int escolha;
		do {

			System.out.println("\n           Digite uma opção: ");
			System.out.println("----------------------------------------");
			System.out.println(
					"(0)Encerrar programa\n(1)Adicionar Veículo\n(2)Atualizar Veículo\n(3)Remover veículo\n(4)Mostrar todos os veículos"
					 + "\n(5)Atualizar disponibilidade do veículo");
			System.out.println("----------------------------------------");
			System.out.print("Digite sua escolha: ");
			escolha = scanner.nextInt();
			System.out.println("----------------------------------------");

			switch (escolha) {
			case 0:
				System.out.println("Obrigado por utilizar nosso app! :)");
				break;
			case 1:
				int escolha1 = 0;
				while (escolha1 < 1 || escolha1 > 4) {
					System.out.println(
							"Você deseja adicionar qual tipo de véiculo:\n(1)-Luxo\n(2)-Premium\n(3)-Standard\n(4)-PCD");
					System.out.println("----------------------------------------");
					System.out.print("Digite sua escolha: ");
					escolha1 = scanner.nextInt();
					System.out.println("----------------------------------------");
				}
				String fabricante;
				String placa;
				String modelo;
				int ano;
				int qntd_lugares;
				String nome;
				switch (escolha1) {
				case 1:
					listaCarros.add(GrupoA.criaObj());
					System.out.println("----------------------------------------");
					System.out.println("Veículo adicionado com sucesso! ");
					System.out.println("----------------------------------------");
					break;
				case 2:
					listaCarros.add(GrupoB.criaObj());
					System.out.println("----------------------------------------");
					System.out.println("Veículo adicionado com sucesso! ");
					System.out.println("----------------------------------------");
					break;
				case 3:
					listaCarros.add(GrupoC.criaObj());
					System.out.println("----------------------------------------");
					System.out.println("Veículo adicionado com sucesso! ");
					System.out.println("----------------------------------------");
					break;
				case 4:
					listaCarros.add(GrupoD.criaObj());
					System.out.println("----------------------------------------");
					System.out.println("Veículo adicionado com sucesso! ");
					System.out.println("----------------------------------------");
					break;
				default:
					System.out.println("----------------------------------------");
					System.out.println("Selecione uma opção válida! ");
					System.out.println("----------------------------------------");
					break;
				}
				break;
			case 2:
				scanner.nextLine();
				System.out.print("Digite o modelo do veículo que você deseja atualizar: ");
				nome = scanner.nextLine();
				int cont=0;
				for (Veiculos v : listaCarros) {
					if (nome.equalsIgnoreCase(v.getModelo())) {
						cont++;
						char categoria = v.getCategoria();
						System.out.println();
						System.out.println(v);
						listaCarros.remove(listaCarros.indexOf(v));
						
						if(categoria == 'A') {
							listaCarros.add(GrupoA.criaObj());
						}
						else if(categoria == 'B') {
							listaCarros.add(GrupoB.criaObj());
						}
						else if(categoria == 'C') {
							listaCarros.add(GrupoC.criaObj());
						}
						else {
							listaCarros.add(GrupoD.criaObj());
						}
						break;
					}
				}
				if(cont==0) {
					System.out.println("AVISO: Não foi encontrado nenhum veículo nessa categoria...");

				}
				else {
					System.out.println("----------------------------------------");
					System.out.println("     VEÍCULO ATUALIZADO COM SUCESSO!    ");
					System.out.println("----------------------------------------");
				}
				break;
			case 3:
				scanner.nextLine();
				System.out.print("Digite o modelo do veículo que você deseja excluir: ");
				nome = scanner.nextLine();
				int cont2=0;
				for(int i=0;i<listaCarros.size();i++) {
					if(listaCarros.get(i).getModelo().equalsIgnoreCase(nome)) {
						listaCarros.remove(i);
						cont2++;
					}
					
				}
				if(cont2==0) {
					System.out.println("AVISO: Não foi encontrado nenhum veículo com este Modelo!");
				}
				else {
					System.out.println("----------------------------------------");
					System.out.println("     VEÍCULO REMOVIDO COM SUCESSO!      ");
					System.out.println("----------------------------------------");
				}
				
				break;
			case 4:
				VerificaDisp();
				break;
			case 5:
				int cont5 = 0;
				scanner.nextLine();
				System.out.println("Digite o modelo de veículo que deseja atualizar: ");
				String modeloAtualiza = scanner.nextLine();
				
				for(Veiculos v : listaCarros) {
					if(modeloAtualiza.equalsIgnoreCase(v.getModelo())) {
						System.out.println("Escolha 0 para indisponível ou 1 para disponível");
						int escolha5 = scanner.nextInt();
						
						while(escolha5 < 0 || escolha5 > 1) {
							System.out.println("Opção inválida! Tente novamente...");
							escolha5 = scanner.nextInt();
						}
							if(escolha5 == 0) {
							v.setDisponivel(false);
							}
							else {
							v.setDisponivel(true);
							}
						cont5++;
						
						System.out.println("----------------------------------------");
						System.out.println("     VEÍCULO ATUALIZADO COM SUCESSO!    ");
						System.out.println("----------------------------------------");
					}					
				}
				break;
			}

		} while (escolha != 0);
	}
	
	public static void main(String[] args) {
		int op;
		bdCarros();
		try {
			do {
				menuIni();
				op = scanner.nextInt();
				System.out.println("----------------------------------------");
				while (op < 0 || op > 2) {
					System.out.println("AVISO: DIGITE UMA OPÇÃO VÁLIDA");
					System.out.println("----------------------------------------");
					menuIni();
					op = scanner.nextInt();
				}
	
				if (op == 1) {
					scanner.nextLine();
					System.out.print("Digite a senha para acessar o perfil de ADM: ");
					String senha = scanner.nextLine();
	
					if (senha.equals("ADM123")) {
						System.out.println("Acesso liberado...");
						appAdm();
					} 
					else {
						System.out.println("Senha Incorreta");
					}
				} else if (op == 2) {
					scanner.nextLine();
					appCliente();
				}
	
				else {
					System.out.println("Obrigado por utilizar nosso app! :)");
				}
	
			} while (op != 0);
		}catch(InputMismatchException err1) {
			System.out.println("\nDigite apenas opções válidas para o funcionamento correto do programa.");             
			System.err.println("Erro: " + err1);
		}
		catch(Exception err2) 
        {
            System.err.println("Erro: " + err2);
        }
	}
	
}
	

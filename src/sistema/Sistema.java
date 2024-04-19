package sistema;

import java.util.Scanner;

import service.HandleMenu;
import service.HandleMenuProduto;

public class Sistema {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int opcao = 0;
		int opcaoCrud = 0;

		do {
			System.out.println("Qual CRUD voce quer acessar: ");
			System.out.println("1 - Usuarios \n2 - Produtos");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1: {
				HandleMenu hu = new HandleMenu();
				System.out.println("---------------CRUD USUARIO---------------");
				do {

					System.out.print(
							"1 - Criar \n2 - Editar \n3 - Deletar \n4 - Listar \n5 - ListarId \n6 - Login  \n7 - Trocar Senha \n9 - Sair\n");
					opcaoCrud = sc.nextInt();

					switch (opcaoCrud) {
					case 1: {
						hu.criar();
						break;
					}
					case 2: {
						hu.editar();
						break;
					}
					case 3: {
						hu.deletar();
						break;
					}
					case 4: {
						hu.listar();
						break;
					}
					case 5: {
						hu.listarId();
						break;
					}
					case 6: {
						hu.login();
						break;
					}
					case 7: {
						hu.trocarSenha();
						break;
					}
					case 9: {
						break;
					}
					default: {
						System.out.println("Opcao Invalida");
						break;
					}
					}

				} while (opcaoCrud != 9);

				break;
			}
			case 2: {

				HandleMenuProduto hm = new HandleMenuProduto();
				System.out.println("---------------CRUD PRODUTO---------------");
				do {

					System.out.print(
							"1 - Criar \n2 - Editar \n3 - Deletar \n4 - Listar \n5 - ListarId \n6 - Somar Preços \n7 - Contar Produtos  \n9 - Sair\n");
					opcaoCrud = sc.nextInt();

					switch (opcaoCrud) {
					case 1: {
						hm.criar();
						break;
					}
					case 2: {
						hm.editar();
						break;
					}
					case 3: {
						hm.deletar();
						break;
					}
					case 4: {
						hm.listar();
						break;
					}
					case 5: {
						hm.listarId();
						break;
					}
					case 6: {
						hm.somarPreco();
						break;
					}
					case 7: {
						hm.contarProduto();
						break;
					}
					case 9: {
						break;
					}
					default: {
						System.out.println("Opcao Invalida");
						break;
					}
					}

				} while (opcaoCrud != 9);

				break;

			}
			}

		} while (opcao != 9);
	}
}

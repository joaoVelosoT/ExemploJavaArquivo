package service;

import java.util.List;
import java.util.Scanner;

import models.Produto;
import utils.GerenciadorDeProduto;

public class HandleMenuProduto {
	Scanner sc = new Scanner(System.in);

	GerenciadorDeProduto gp = new GerenciadorDeProduto();

	public HandleMenuProduto() {
		gp.verificaECria("produtos.txt");
	}

	public void criar() {
		String nome = "";
		// Inicio do input do nome, e da verificação
		boolean nomeVazio = false;
		while (nomeVazio == false) {
			System.out.println("Digite o nome");
			nome = sc.nextLine();
			if (nome.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				nomeVazio = true;
			}
		}
		
		double preco = 0;
		boolean precoPositivo = false;
		while (precoPositivo == false) {
			System.out.println("Digite o preco");
			try {
				preco = sc.nextDouble();
				if (preco < 1) {
					System.err.println("Erro - Digite um valor maior que 0 R$");
				} else {
					precoPositivo = true;
				}
			} catch (Exception e) {
				System.err.println("Erro - Digite um valor numerico");
			}
			sc.nextLine();
		}

		int qtd = 0;
		// Inicio do input dos dependentes, e da verificação
		boolean qtdPositivo = false;
		while (qtdPositivo == false) {
			System.out.println("Digite a quantidade de produtos");
			try {
				qtd = sc.nextInt();
				if (qtd < 0) {
					System.err.println("Erro - Não e possivel ter menos de 0");
				} else {
					qtdPositivo = true;
				}
			} catch (Exception e) {
				System.err.println("Erro - Digite um valor numerico");
			}
			sc.nextLine();
		}


		long id = getNextId();
		
		Produto p = new Produto(id, nome, preco, qtd);
		
		gp.adicionarProduto(p);
	}
	
	public void editar() {
		
		long id = 0;
		// Inicio do input dos dependentes, e da verificação
		boolean idPositivo = false;
		while (idPositivo == false) {
			System.out.println("Digite o ID que deseja modificar");
			try {
				id = sc.nextLong();
				if (id < 0) {
					System.err.println("Erro - Não e possivel ter um ID negativo");
				} else {
					idPositivo = true;
				}
			} catch (Exception e) {
				System.err.println("Erro - Digite um valor numerico");
			}
			sc.nextLine();
		}
		
		System.out.println("Digite o novo nome");
		String nome = sc.next();
		
		System.out.println("Digite o novo preço");
		double preco = sc.nextDouble();
		
		System.out.println("Digite a nova quantidade");
		int qtd = sc.nextInt();
		
		gp.editarProduto(id, nome, preco, qtd);
	}
	
	public void deletar() {
		System.out.println("Digite o ID do uproduto a ser deletado: ");
		long id = sc.nextLong();
		gp.deletarProduto(id);
	}
	
	public void listar() {
		gp.listarProdutos();
	}
	
	public void listarId() {
		System.out.println("Digite o ID do produto a ser lido");
		long id = sc.nextLong();
		
		gp.listarProdutoId(id);
	}
	
	public void somarPreco() {
		System.out.println("Preço total "+ gp.somarPreco());
	}
	
	public void contarProduto() {
		System.out.println("Total Produto "+ gp.contarProduto());
	}

	private long getNextId() {
		List<Produto> produtos = gp.lerProdutos();
		long maxId = 0;

		for (Produto produto : produtos) {
			long id = produto.getId();
			if(id > maxId) {
				maxId = id;
			}
		}
		return maxId+1;
	}
}

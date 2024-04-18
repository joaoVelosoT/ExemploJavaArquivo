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
		System.out.println("Digite o nome do produto: ");
		String nome = sc.next();

		System.out.println("Digite o preço do produto: ");
		double preco = sc.nextDouble();

		System.out.println("Digite a quantidade de produto:");
		int qtd = sc.nextInt();

		long id = getNextId();
		
		Produto p = new Produto(id, nome, preco, qtd);
		
		gp.adicionarProduto(p);
	}
	
	public void editar() {
		System.out.println("Digite o ID do produto a ser modificado");
		long id = sc.nextLong();
		
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

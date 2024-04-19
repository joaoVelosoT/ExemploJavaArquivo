package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class GerenciadorDeProduto {

	private static final String NOME_ARQUIVO_PRODUTO = "produtos.txt";

	public void verificaECria(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		if (arquivo.exists()) {
			System.out.println("Banco funcionando !!");
		} else {
			try {
				arquivo.createNewFile();
				System.out.println("Arquivo Criado Com Sucesso");
			} catch (IOException e) {
				System.out.println("Erro ao criar arquivo");
			}
		}
	}

	public void adicionarProduto(Produto produto) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO_PRODUTO, true))) {
			bw.write(produto.toString());
			bw.newLine();
			System.out.println("Produto adicionado com sucesso!");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
		}
	}

	public List<Produto> lerProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO_PRODUTO))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");
				produtos.add(new Produto(Long.parseLong(partes[0]), partes[1], Double.valueOf(partes[2]),
						Integer.parseInt(partes[3])));
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
		return produtos;
	}

	public void deletarProduto(long id) {
		List<Produto> produtos = lerProdutos();

		if (produtos.removeIf(produto -> produto.getId() == id)) {

			reescreverArquivo(produtos);
			System.out.println("produto deletado com sucesso");
		} else {
			System.out.println("produto nao encontrado");
		}
	}

	public void reescreverArquivo(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO_PRODUTO))) {
			for (Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo" + e.getMessage());
		}
	}

	public void listarProdutos() {
		List<Produto> produtos = lerProdutos();
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
		} else {
			System.out.println("Lista de produtos");
			for (Produto produto : produtos) {
				System.out.println("ID: " + produto.getId() + ", Nome: " + "" + produto.getNome() + ", Preço: "
						+ produto.getPreco() + ", Quantidade: " + produto.getQuantidade());
			}
		}
	}

	public void listarProdutoId(long id) {
		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
		}
		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				System.out.println("ID: " + produto.getId() + ", Nome: " + "" + produto.getNome() + ", Preço: "
						+ produto.getPreco() + ", Quantidade: " + produto.getQuantidade());
				encontrado = true;
			}

		}
		if (!encontrado) {
			System.out.println("Usuario nao encontrado");
		}
	}

	public void editarProduto(long id, String newNome, double newPreco, int newQuantidade) {
		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				produto.setNome(newNome);
				produto.setPreco(newPreco);
				produto.setQuantidade(newQuantidade);
				encontrado = true;
				break;
			}
		}

		if (encontrado) {
			reescreverArquivo(produtos);
			System.out.println("Produto editado com sucesso!");
		} else {
			System.out.println("Produto nao encontrado");
		}
	}

	public double somarPreco() {
		List<Produto> produtos = lerProdutos();
		double precoTotal = 0;

		for (Produto produto : produtos) {
			precoTotal += produto.getPreco() * produto.getQuantidade();

		}
		return precoTotal;
	}

	public int contarProduto() {
		List<Produto> produtos = lerProdutos();
		int produtoTotal = 0;
		for (Produto produto : produtos) {
			produtoTotal += produto.getQuantidade();
		}
		return produtoTotal;
	}
}

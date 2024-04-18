package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class GerenciadorDeUsuario {

	private static final String NOME_ARQUIVO = "usuarios.txt";

	// Verificar a existencia do nosso banco e criar caso nao exista

	public void verificaECria(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		if (arquivo.exists()) {
			System.out.println("Banco Funcionando!");
		} else {
			// tente criar, caso de erro, exibe o erro
			try {
				// Criar o novo arquivo
				arquivo.createNewFile();
				System.out.println("Arquivo Criado com sucesso!");
			} catch (IOException e) {
				System.out.println("Erro ao criar arquivo" + e.getMessage());
			}
		}
	}

	public void adicionarUsuario(Usuario usuario) {
		// BuffereWriter, FileWriter
		// BufferedWriter, proporciona uma eficiente escrita
		// FileWriter, escreve dentro de arquivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(usuario.toString());
			bw.newLine();
			System.out.println("Usuario adicionado com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
		}
	}

	public List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;
			// Percorrer todas as linhas enquanto seja diferente de vazio
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); // Dividir em tres partes
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
		return usuarios;
	}

	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();

		if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
			// Reescrevendo arquivos com novos usuarios e alteracoes
			reescreverArquivo(usuarios);
			System.out.println("Usuario deletado com sucesso");
		} else {
			System.out.println("Usuario nao encontrado");
		}
	}

	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo" + e.getMessage());
		}
	}

	public void listarUsuarios() {
		List<Usuario> usuarios = lerUsuarios();
		// erros
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado");
		} else {
			System.out.println("Lista de usuarios");
			for (Usuario usuario : usuarios) {
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: "
						+ usuario.getSenha());
			}
		}

	}

	public void listarUsuarioId(int id) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado");
		}
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: "
						+ usuario.getSenha());
			} else {
				System.out.println("Nao existe esse usuario no sistema");
				break;
			}
		}
	}

	public void editarUsuario(int id, String newNome, String newSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;

		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				usuario.setNome(newNome);
				usuario.setSenha(newSenha);
				encontrado = true;
				break;
			}
		}
		if (encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Usuario editado com sucesso!");
		} else {
			System.out.println("Usuario nao encontrado");
		}

	}

	public void login(String login, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean acesso = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(login) && usuario.getSenha().equals(senha)) {
				acesso = true;
			} else {
				acesso = false;
			}
		}
		if (acesso == true) {
			System.out.println("Acesso permitido");
		} else {
			System.out.println("Acesso negado");
		}

	}
}

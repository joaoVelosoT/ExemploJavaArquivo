package service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import models.Usuario;
import utils.GerenciadorDeUsuario;

public class HandleMenu {
	Scanner sc = new Scanner(System.in);

	// Gerenciador
	GerenciadorDeUsuario gs = new GerenciadorDeUsuario();

	public HandleMenu() {
		// toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
		gs.verificaECria("usuarios.txt");
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

		String senha = "";
		// Inicio do input do nome, e da verificação
		boolean senhaVazio = false;
		while (senhaVazio == false) {
			System.out.println("Digite a senha");
			senha = sc.nextLine();
			if (senha.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				senhaVazio = true;
			}
		}

		int id = getNextId();

		Usuario u = new Usuario(id, nome, senha);

		gs.adicionarUsuario(u);
	}

	public void editar() {
		int id = 0;
		// Inicio do input dos dependentes, e da verificação
		boolean idPositivo = false;
		while (idPositivo == false) {
			System.out.println("Digite o ID que deseja modificar");
			try {
				id = sc.nextInt();
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

		String newNome = "";
		// Inicio do input do nome, e da verificação
		boolean nomeVazioNew = false;
		while (nomeVazioNew == false) {
			System.out.println("Digite o nome");
			newNome = sc.nextLine();
			if (newNome.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				nomeVazioNew = true;
			}
		}

		String newSenha = "";
		// Inicio do input do nome, e da verificação
		boolean senhaVazioNew = false;
		while (senhaVazioNew == false) {
			System.out.println("Digite a senha");
			newSenha = sc.nextLine();
			if (newSenha.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				senhaVazioNew = true;
			}
		}

		gs.editarUsuario(id, newNome, newSenha);
	}

	public void deletar() {
		int id = 0;
		// Inicio do input dos dependentes, e da verificação
		boolean idPositivo = false;
		while (idPositivo == false) {
			System.out.println("Digite o ID que deseja deletar");
			try {
				id = sc.nextInt();
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
		gs.deletarUsuario(id);
	}

	public void listar() {
		gs.listarUsuarios();
	}

	public void listarId() {
		int id = 0;
		// Inicio do input dos dependentes, e da verificação
		boolean idPositivo = false;
		while (idPositivo == false) {
			System.out.println("Digite o ID que deseja visualizar");
			try {
				id = sc.nextInt();
				if (id < 0) {
					System.err.println("Erro - Não e possivel ter um ID negativo");
				} else {
					idPositivo = true;
				}
			} catch (Exception e) {
				System.err.println("Erro - Digite um valor numerico");
			}
			sc.nextLine();
			gs.listarUsuarioId(id);
		}
		
	}

	public void login() {
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

		String senha = "";
		// Inicio do input do nome, e da verificação
		boolean senhaVazio = false;
		while (senhaVazio == false) {
			System.out.println("Digite a senha");
			senha = sc.nextLine();
			if (senha.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				senhaVazio = true;
			}
		}

		gs.login(nome, senha);
	}

	public void trocarSenha() {

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

		String senha = "";
		// Inicio do input do nome, e da verificação
		boolean senhaVazio = false;
		while (senhaVazio == false) {
			System.out.println("Digite a senha");
			senha = sc.nextLine();
			if (senha.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				senhaVazio = true;
			}
		}
		
		
		
		String newSenha = "";
		// Inicio do input do nome, e da verificação
		boolean newSenhaVazio = false;
		while (newSenhaVazio == false) {
			System.out.println("Digite a nova senha");
			newSenha = sc.nextLine();
			if (newSenha.length() == 0) {
				System.err.println("Não deixe espaços vazios");

			} else {
				newSenhaVazio = true;
			}
		}

		gs.trocarSenha(nome, senha, newSenha);

	}

	private int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		int maxId = 0;
		// Unico usuario: Lista com todos
		for (Usuario usuario : usuarios) {
			int id = usuario.getId();
			if (id > maxId) {
				// Logica para descobrir ultimo id
				maxId = id;
			}
		}
		return maxId + 1;
	}

}

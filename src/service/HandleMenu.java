package service;

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

		System.out.println("Digite o nome:");
		String nome = sc.next();

		System.out.println("Digite a senha:");
		String senha = sc.next();

		int id = getNextId();

		Usuario u = new Usuario(id, nome, senha);

		gs.adicionarUsuario(u);
	}

	public void editar() {
		System.out.println("Digite o ID do usuario a ser modificado");
		int id = sc.nextInt();
		
		System.out.println("Digite o novo nome: ");
		String newNome = sc.next();
				
		System.out.println("Digite a nova senha: ");
		String newSenha = sc.next();
	
		gs.editarUsuario(id, newNome, newSenha);
	}

	public void deletar() {
		System.out.println("Digite o ID do usuario a ser deletado: ");
		int id = sc.nextInt();
		gs.deletarUsuario(id);
	}

	public void listar() {
		gs.listarUsuarios();
	}
	
	public void listarId() {
		System.out.println("Digite o ID do usuario a ser lido: ");
		int id = sc.nextInt();
		gs.listarUsuarioId(id);
	}
	
	public void login() {
		System.out.println("Digite o seu login");
		String login = sc.next();
		
		System.out.println("Digite sua senha");
		String senha = sc.next();
		
		gs.login(login, senha);
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
		return maxId+1;
	}

}
	
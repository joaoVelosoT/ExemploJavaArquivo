package models;

public class Produto {
	
	private long idp;
	private String nome;
	private double preco;
	private int quantidade;
	
	
	
	
	public Produto(long id, String nome, double preco, int quantidade) {
		super();
		this.idp = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public long getId() {
		return idp;
	}
	public void setId(long id) {
		this.idp = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override 
	public String toString() {
		return idp + ";" + nome + ";" + preco + ";" + quantidade;
	}
	
}

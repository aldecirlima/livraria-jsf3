package br.com.caelum.livraria.modelo;

public class Venda {

	private Livro livro;
	private Integer quantidade;

	public Venda(Livro livro, Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Venda() {

	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}

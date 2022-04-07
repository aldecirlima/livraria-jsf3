package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Livro {

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String isbn;
	private Double preco;
	private String dataLancamento;

	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();
	
	
	

	public Livro(Integer id, String titulo, String isbn, Double preco, String dataLancamento) {
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.preco = preco;
		this.dataLancamento = dataLancamento;
	}

	public Livro() {
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
package com.example.esdraskhan.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="tb_emprestimo")
public class Emprestimo extends PanacheEntityBase {

	@Id
	private Long id;

	@NotBlank(message="O campo livro é obrigatório!")
	@Column(name = "livro")
	private String livro;

	@ManyToOne
	private Cliente cliente;

	@NotBlank(message="A data de inicio é obrigatória!")
	@Column(name = "data_inicio")
	private LocalDate dataEmprestimo;

	@Column(name = "data_entrega")
	private LocalDate dataDevolucao;

	@OneToMany(mappedBy = "emprestimo", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("livros")
	private List<Livros> livros;


	public Emprestimo() {
	}

	public Emprestimo(String livro, Cliente cliente,LocalDate dataEmprestimo, LocalDate dataDevolucao) {
		this.livro = livro;
		this.cliente = cliente;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}

	public List<Livros> getLivros() {
		return livros;
	}

	public void setLivros(List<Livros> livros) {
		this.livros = livros;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}



}

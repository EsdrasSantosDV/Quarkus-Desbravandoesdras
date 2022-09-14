package com.example.esdraskhan.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="tb_autor")
public class Autor extends PanacheEntityBase{
	
	
	@Id
	@NotBlank(message="O campo isni é obrigatório!")
	private String isni;
	
	@NotBlank(message="O campo nome é obrigatório!")
	@Size(max = 50, message = "o nome deve conter no maximo 50 caracteres")
	@Column(name = "nome")
	private String nome;
		
	@Schema(example = "email@email.com")
	@NotBlank(message="O campo e-mail é obrigatório!")
	@Email(message="O email esta invalido")
	@Column(name = "email")
	private String email;

	@Column(name = "data_nascimento",nullable = false,updatable = false)
	private LocalDate dataNascimento;

	@NotBlank(message="O campo biografia é obrigatório!")
	@Size(max = 200, message = "A biografia deve possuir no maximo 200 caracteres")
	@Column(name = "biografia")
	private String biografia;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("autor")
	private List<Livros> livros;

	
	public Autor() {
	}

	public Autor( String nome, String isni, String email, LocalDate dataNascimento, String biografia){
	
		this.nome = nome;
		this.isni = isni;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.biografia = biografia;
	}

	public static Autor findbyISNI(String isni) {
		return find("isni",isni).firstResult();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIsni() {
		return isni;
	}

	public void setIsni(String iSNI) {
		isni = iSNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public List<Livros> getLivros() {
		return livros;
	}

	public void setLivros(List<Livros> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return "Autor{" +
				"isni='" + isni + '\'' +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", dataNascimento=" + dataNascimento +
				", biografia='" + biografia + '\'' +
				'}';
	}
}


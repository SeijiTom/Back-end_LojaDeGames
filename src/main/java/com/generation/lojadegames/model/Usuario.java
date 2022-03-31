package com.generation.lojadegames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome_usuario é obrigatório e não pode utilizar "
			+ "espaços em branco.")
	@Size(min = 8, max = 100, message = "O atributo nome_usuario deve conter no mínimo "
			+ "08 a 100 caracteres.")
	private String nomeUsuario;
	
	@NotBlank(message = "O atributo usuario é obrigatório e não pode utilizar "
			+ "espaços em branco.")
	@Size(min = 2, max = 16, message = "O atributo usuario deve conter no mínimo "
			+ "02 a 16 caracteres.")
	private String usuario;
	
	@NotNull(message = "O atributo senha é obrigatório!")
	@Size(min = 6, max = 20, message = "O atributo nome_usuario deve conter no mínimo "
			+ "06 a 20 caracteres.")
	private String senha;
	
	private String fotoUsuario;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
}
package br.inf.fs.ufgmemo.vo;

import java.util.ArrayList;

public class ClienteVO {

	private Integer id;
	private String nome;
	private String email;
	private String matricula;
	private String senha;
	private ArrayList<String> possiveisRemetentes;

	public ClienteVO() {

	}

	public ClienteVO(Integer id, String nome, String email, String matricula,
			String senha, ArrayList<String> possiveisRemetentes) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
		this.senha = senha;
		this.possiveisRemetentes = possiveisRemetentes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<String> getPossiveisRemetentes() {
		return possiveisRemetentes;
	}

	public void setPossiveisRemetentes(ArrayList<String> possiveisRemetentes) {
		this.possiveisRemetentes = possiveisRemetentes;
	}
}

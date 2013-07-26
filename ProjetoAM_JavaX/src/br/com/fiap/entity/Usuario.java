package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_USUARIO")
@SequenceGenerator(name="seqUsuario", sequenceName="SEQ_AM_USUARIO", allocationSize=1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqUsuario")
	private int codUsuario;

	@Column(nullable = false, length = 60)
	private String email;

	@Column(nullable = false, length = 25)
	private String senha;

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario(){
	}


	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}

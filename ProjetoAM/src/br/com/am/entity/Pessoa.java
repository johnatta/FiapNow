package br.com.am.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AM_PESSOA")
@SequenceGenerator(name="seqPessoa", sequenceName="SEQ_AM_PESSOA", allocationSize=1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqPessoa")
	@Column(nullable = false)
	private int codPessoa;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 50)
	private String sobrenome;
	
	@Column(name="DATA_NASCIMENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private  Calendar dt_nasc;
	
	
	@Column(nullable = false, length = 40)
	private String apelido;
	
	
	@Column(name="TELEFONE_RESIDENCIAL", length = 12)
	private String telRes;
	
	@Column(name="TELEFONE_CELULAR", length = 12)
	private String cel;
	
	@Column(name="IMAGEM_PERFIL", nullable = false)
	@Lob
	private byte [] imgPerfil;
	
	@Column(name="IMAGEM_BACK_GROUND", nullable = false)
	@Lob
	private byte [] imgBackGround;
	
	@Column
	private Usuario codUsuario;
	
	@Column
	private Endereco codEndereco;

	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Calendar getDt_nascimento() {
		return dt_nasc;
	}

	public void setDt_nascimento(Calendar dt_nascimento) {
		this.dt_nasc = dt_nascimento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getTelRes() {
		return telRes;
	}

	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public byte[] getImgPerfil() {
		return imgPerfil;
	}

	public void setImgPerfil(byte[] imgPerfil) {
		this.imgPerfil = imgPerfil;
	}

	public byte[] getImgBackGround() {
		return imgBackGround;
	}

	public void setImgBackGround(byte[] imgBackGround) {
		this.imgBackGround = imgBackGround;
	}

	public Usuario getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Usuario codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Endereco getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Endereco codEndereco) {
		this.codEndereco = codEndereco;
	}
	

	

}

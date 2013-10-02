package br.com.fiap.rc;

import java.util.Calendar;

public class ComentarioGrupoRC {
	
	private int codComentario;
	private int codPessoa;
	private String apelido;
	private byte[] imgPerfil;
	private String comentario;
	private Calendar dataHora;
	
	public ComentarioGrupoRC(int codComentario, int codPessoa, String apelido,
			byte[] imgPerfil, String comentario, Calendar dataHora){
		this.codComentario = codComentario;
		this.codPessoa = codPessoa;
		this.apelido = apelido;
		this.imgPerfil = imgPerfil;
		this.comentario = comentario;
		this.dataHora = dataHora;
	}

	public int getCodComentario() {
		return codComentario;
	}

	public void setCodComentario(int codComentario) {
		this.codComentario = codComentario;
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public byte[] getImgPerfil() {
		return imgPerfil;
	}

	public void setImgPerfil(byte[] imgPerfil) {
		this.imgPerfil = imgPerfil;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

}

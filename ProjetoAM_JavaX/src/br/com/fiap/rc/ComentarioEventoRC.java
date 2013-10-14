package br.com.fiap.rc;

import java.util.Calendar;

public class ComentarioEventoRC {
	
	private int codPessoa;
	private String apelido;
	private byte[] imgPerfil;
	private String comentario;
	private Calendar dataHora;
	
	/**
	 * Contrutor padr�o para o ComentarioEventoRC
	 *  
	 * @param codPessoa C�digo da pessoa que realizou o coment�rio
	 * @param apelido Apelido da pessoa que realizou o coment�rio
	 * @param imgPerfil Imagem de perfil de quem realizou o coment�rio
	 * @param comentario Descri��o do coment�rio
	 * @param dataHora Data e Hora do coment�rio
	 *  
	 */
	public ComentarioEventoRC(int codPessoa, String apelido,
			byte[] imgPerfil, String comentario, Calendar dataHora){
		this.codPessoa = codPessoa;
		this.apelido = apelido;
		this.imgPerfil = imgPerfil;
		this.comentario = comentario;
		this.dataHora = dataHora;
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

package br.com.fiap.rc;

import java.util.Calendar;

public class ComentarioEventoRC {
	
	public int codPessoa;
	public String apelido;
	public byte[] imgPerfil;
	public String comentario;
	public Calendar dataHora;
	
	public ComentarioEventoRC(int codPessoa, String apelido,
			byte[] imgPerfil, String comentario, Calendar dataHora){
		this.codPessoa = codPessoa;
		this.apelido = apelido;
		this.imgPerfil = imgPerfil;
		this.comentario = comentario;
		this.dataHora = dataHora;
	}

}

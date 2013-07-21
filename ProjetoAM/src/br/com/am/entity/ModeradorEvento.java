package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_MODERADOR_EVENTO")
@SequenceGenerator(name="seqModeradorEvento", sequenceName="SEQ_AM_MODERADOR_EVENTO", allocationSize=1)
public class ModeradorEvento implements Serializable {

	private static final long serialVersionUID = 1902409748279894396L;
	
	private Evento codEvento;
	
	private Pessoa codPessoa;

	public Evento getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Evento codEvento) {
		this.codEvento = codEvento;
	}

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}

}

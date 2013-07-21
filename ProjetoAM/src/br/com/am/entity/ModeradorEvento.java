package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_MODERADOR_EVENTO")
@SequenceGenerator(name="seqModeradorEvento", sequenceName="SEQ_AM_MODERADOR_EVENTO", allocationSize=1)
public class ModeradorEvento implements Serializable {

	private static final long serialVersionUID = 1L;
     
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqModeradorEvento")
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Evento codEvento;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
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

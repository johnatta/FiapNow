package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AM_EVENTO_GRUPO")
public class EventoGrupo implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name="cod_evento")
	private Evento evento;
	
	@Id
	@ManyToOne
	@JoinColumn(name="cod_grupo")
	private Grupo grupo;

	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
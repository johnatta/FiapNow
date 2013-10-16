package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@Column(name="cod_pessoa")
	private int codPessoa;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, length = 50)
	private String sobrenome;

	@Column(name="DATA_NASCIMENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private  Calendar dtNasc;

	@Column(nullable = false, length = 40)
	private String apelido;

	@Column(name="TELEFONE_RESIDENCIAL", length = 12)
	private String telRes;

	@Column(name="TELEFONE_CELULAR", length = 12)
	private String cel;

	@Column(name="IMAGEM_PERFIL", nullable = false)
	@Lob
	private byte [] imgPerfil;

	@Column(name="IMAGEM_BACK_GROUND")
	@Lob
	private byte [] imgBackGround;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_usuario")
	private Usuario usuario;	

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_endereco")
	private Endereco endereco;

	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="AM_PESSOA_ESPORTE",
	joinColumns={@JoinColumn(name="COD_PESSOA")},
	inverseJoinColumns={@JoinColumn(name="COD_ESPORTE")})
	private List<Esporte> esportes;
	
	@OneToMany(mappedBy="adm",cascade=CascadeType.ALL)
	private List<Grupo> gruposAdministrados;

	@ManyToMany(mappedBy="membros",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Grupo> gruposParticipantes;

	@OneToMany(mappedBy="adm",cascade=CascadeType.ALL)
	private List<Evento> eventosAdministrados;

	@ManyToMany(mappedBy="membros",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Evento> eventosParticipantes;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<ComentarioEvento> comentariosEvento;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<ComentarioGrupo> comentariosGrupo;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<PedidoEvento> pedidosEvento;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<PedidoGrupo> pedidosGrupo;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<MensagemEvento> mensagensEvento;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<MensagemGrupo> mensagensGrupo;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<ConviteEvento> convitesEvento;
	
	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	private List<ConviteGrupo> convitesGrupo;

	public Pessoa(String nome, String sobrenome, Calendar dtNasc,
			String apelido, String telRes, String cel, byte[] imgPerfil,
			byte[] imgBackGround, Usuario codUsuario, Endereco endereco,
			List<Esporte> esportes, List<Grupo> gruposParticipantes, List<Evento> eventosParticipantes) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dtNasc = dtNasc;
		this.apelido = apelido;
		this.telRes = telRes;
		this.cel = cel;
		this.imgPerfil = imgPerfil;
		this.imgBackGround = imgBackGround;
		this.usuario = codUsuario;
		this.endereco = endereco;
		this.esportes = esportes;
		this.gruposParticipantes = gruposParticipantes;
		this.eventosParticipantes = eventosParticipantes;
	}

	public Pessoa() {
	}

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

	public Calendar getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Calendar dtNasc) {
		this.dtNasc = dtNasc;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Esporte> getEsportes() {
		return esportes;
	}

	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}

	public List<Grupo> getGruposParticipantes() {
		return gruposParticipantes;
	}

	public void setGruposParticipantes(List<Grupo> gruposParticipantes) {
		this.gruposParticipantes = gruposParticipantes;
	}

	public List<Grupo> getGruposAdministrados() {
		return gruposAdministrados;
	}

	public void setGruposAdministrados(List<Grupo> gruposAdministrados) {
		this.gruposAdministrados = gruposAdministrados;
	}

	public List<ComentarioEvento> getComentariosEvento() {
		return comentariosEvento;
	}

	public void setComentariosEvento(List<ComentarioEvento> comentariosEvento) {
		this.comentariosEvento = comentariosEvento;
	}

	public List<ComentarioGrupo> getComentariosGrupo() {
		return comentariosGrupo;
	}

	public void setComentariosGrupo(List<ComentarioGrupo> comentariosGrupo) {
		this.comentariosGrupo = comentariosGrupo;
	}

	public List<PedidoEvento> getPedidosEvento() {
		return pedidosEvento;
	}

	public void setPedidosEvento(List<PedidoEvento> pedidosEvento) {
		this.pedidosEvento = pedidosEvento;
	}

	public List<PedidoGrupo> getPedidosGrupo() {
		return pedidosGrupo;
	}

	public void setPedidosGrupo(List<PedidoGrupo> pedidosGrupo) {
		this.pedidosGrupo = pedidosGrupo;
	}

	public List<MensagemEvento> getMensagensEvento() {
		return mensagensEvento;
	}

	public void setMensagensEvento(List<MensagemEvento> mensagensEvento) {
		this.mensagensEvento = mensagensEvento;
	}

	public List<MensagemGrupo> getMensagensGrupo() {
		return mensagensGrupo;
	}

	public void setMensagensGrupo(List<MensagemGrupo> mensagensGrupo) {
		this.mensagensGrupo = mensagensGrupo;
	}

	public List<ConviteEvento> getConvitesEvento() {
		return convitesEvento;
	}

	public void setConvitesEvento(List<ConviteEvento> convitesEvento) {
		this.convitesEvento = convitesEvento;
	}

	public List<ConviteGrupo> getConvitesGrupo() {
		return convitesGrupo;
	}

	public void setConvitesGrupo(List<ConviteGrupo> convitesGrupo) {
		this.convitesGrupo = convitesGrupo;
	}

	public List<Evento> getEventosAdministrados() {
		return eventosAdministrados;
	}

	public void setEventosAdministrados(List<Evento> eventosAdministrados) {
		this.eventosAdministrados = eventosAdministrados;
	}

	public List<Evento> getEventosParticipantes() {
		return eventosParticipantes;
	}

	public void setEventosParticipantes(List<Evento> eventosParticipantes) {
		this.eventosParticipantes = eventosParticipantes;
	}

}

package br.com.fiescfrotas.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FF_AGENDAMENTO")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_AGENDAMENTO")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_USUARIO", insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "COD_VEICULO", insertable = false, updatable = false)
	private Veiculo veiculo;

	@Column(name = "PREVISAO_SAIDA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoSaida;

	@Column(name = "PREVISAO_ENTRADA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoEntrada;

	@Column(name = "DATA_CRIACAO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Column(name = "STATUS", nullable = false)
	private int status;

	@Column(name = "COD_USUARIO", nullable = false)
	private long codUsuario;

	@Column(name = "COD_VEICULO", nullable = false)
	private long codVeiculo;

	@Column(name = "OBSERVACAO")
	private String observacao;

	@Column(name = "LOCAL")
	private String local;

	@Column(name = "MOTIVO")
	private String motivo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getPrevisaoSaida() {
		return previsaoSaida;
	}

	public void setPrevisaoSaida(Date previsaoSaida) {
		this.previsaoSaida = previsaoSaida;
	}

	public Date getPrevisaoEntrada() {
		return previsaoEntrada;
	}

	public void setPrevisaoEntrada(Date previsaoEntrada) {
		this.previsaoEntrada = previsaoEntrada;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public long getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public long getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(long codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
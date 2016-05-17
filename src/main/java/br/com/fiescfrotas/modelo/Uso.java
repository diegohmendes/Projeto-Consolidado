package br.com.fiescfrotas.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FF_USO")
public class Uso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_USO")
	private long codUso;
	
	@OneToOne
	@JoinColumn(name="COD_AGENDAMENTO", insertable=false, updatable=false)
	private Agendamento agendamento;
	
	@Column(name="KM_SAIDA", nullable=false)
	private float kmSaida;
	
	@Column(name="KM_ENTRADA", nullable=false)
	private float kmEntrada;
	
	@Column(name="STATUS", nullable=false)
	private int status;
	
	@Column(name="DATA_SAIDA", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;
	
	@Column(name="DATA_ENTRADA", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	@Column(name="OBSERVACAO", nullable=false)
	private String observacao;
	
	@Column(name="INCIDENTE", nullable=false)
	private boolean	incidente;
	
	@Column(name="ABASTECIMENTO", nullable=false)
	private boolean abasteciemento;
	
	@Column(name="COD_AGENDAMENTO", nullable=false)
	private long codAgendamento;

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public long getCodUso() {
		return codUso;
	}

	public void setCodUso(long codUso) {
		this.codUso = codUso;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public float getKmSaida() {
		return kmSaida;
	}

	public void setKmSaida(float kmSaida) {
		this.kmSaida = kmSaida;
	}

	public float getKmEntrada() {
		return kmEntrada;
	}

	public void setKmEntrada(float kmEntrada) {
		this.kmEntrada = kmEntrada;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean getIncidente() {
		return incidente;
	}

	public void setIncidente(boolean incidente) {
		this.incidente = incidente;
	}

	public boolean getAbasteciemento() {
		return abasteciemento;
	}

	public void setAbasteciemento(boolean abasteciemento) {
		this.abasteciemento = abasteciemento;
	}

	public long getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(long codAgendamento) {
		this.codAgendamento = codAgendamento;
	}
	
	
}
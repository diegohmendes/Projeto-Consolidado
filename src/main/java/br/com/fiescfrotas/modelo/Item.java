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
@Table(name = "FF_ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_ITEM")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_MANUTENCAO", insertable = false, updatable = false)
	private Manutencao manutencao;

	@Column(name = "PREVISAO_TROCA_DATA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoTrocaPorData;

	@Column(name = "PREVISAO_TROCA_KM")
	private float previsaoTrocaPorKM;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "TIPO_MANUTENCAO", nullable = false)
	private int tipoDeManutencao;

	@Column(name = "COD_MANUTENCAO", nullable = false)
	private long codManutencao;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getPrevisaoTrocaPorData() {
		return previsaoTrocaPorData;
	}

	public void setPrevisaoTrocaPorData(Date previsaoTrocaPorData) {
		this.previsaoTrocaPorData = previsaoTrocaPorData;
	}

	public float getPrevisaoTrocaPorKM() {
		return previsaoTrocaPorKM;
	}

	public void setPrevisaoTrocaPorKM(float previsaoTrocaPorKM) {
		this.previsaoTrocaPorKM = previsaoTrocaPorKM;
	}

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getTipoDeManutencao() {
		return tipoDeManutencao;
	}

	public void setTipoDeManutencao(int tipoDeManutencao) {
		this.tipoDeManutencao = tipoDeManutencao;
	}

	public long getCodManutencao() {
		return codManutencao;
	}

	public void setCodManutencao(long codManutencao) {
		this.codManutencao = codManutencao;
	}
}

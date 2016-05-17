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
@Table(name = "FF_MANUTENCAO")
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_MANUTENCAO")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_USUARIO", insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "COD_VEICULO", insertable = false, updatable = false)
	private Veiculo veiculo;

	@Column(name = "DATA_SAIDA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;

	@Column(name = "KM_SAIDA")
	private float kmSaida;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ENTRADA")
	private Date dataEntrada;

	@Column(name = "KM_ENTRADA")
	private float kmEntrada;

	@Column(name = "COD_USUARIO")
	private long codUsuario;

	@Column(name = "COD_VEICULO")
	private long codVeiculo;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Usuario getUsuario() {
		return usuario;
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

	public float getKmSaida() {
		return kmSaida;
	}

	public void setKmSaida(float kmSaida) {
		this.kmSaida = kmSaida;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public float getKmEntrada() {
		return kmEntrada;
	}

	public void setKmEntrada(float kmEntrada) {
		this.kmEntrada = kmEntrada;
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
}
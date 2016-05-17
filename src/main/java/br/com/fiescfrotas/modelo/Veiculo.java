package br.com.fiescfrotas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FF_VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_VEICULO")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_UNIDADE", insertable = false, updatable = false)
	private Unidade unidade;

	@Column(name = "MODELO", nullable=false)
	private String modelo;

	@Column(name = "MARCA", nullable=false)
	private String marca;

	@Column(name = "TIPO", nullable=false)
	private int tipo;

	@Column(name = "PLACA", nullable=false)
	private String placa;

	@Column(name = "KM_ATUAL", nullable=false)
	private float kmAtual;

	@Column(name = "STATUS", nullable=false)
	private int status;

	@Column(name = "MOTORIZACAO", nullable=false)
	private float motorizacao;

	@Column(name = "ANO", nullable=false)
	private int ano;

	@Column(name = "PATRIMONIO", nullable=false)
	private int patrimonio;

	@Column(name = "COMBUSTIVEL", nullable=false)
	private int combustivel;

	@Column(name = "COD_UNIDADE", nullable=false)
	private long codUnidade;

	public Unidade getUnidade() {
		return unidade;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public float getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(float kmAtual) {
		this.kmAtual = kmAtual;
	}

	public float getMotorizacao() {
		return motorizacao;
	}

	public void setMotorizacao(float motorizacao) {
		this.motorizacao = motorizacao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(int patrimonio) {
		this.patrimonio = patrimonio;
	}

	public int getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(int combustivel) {
		this.combustivel = combustivel;
	}

	public long getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(long codUnidade) {
		this.codUnidade = codUnidade;
	}

	@Override
	public String toString() {
		return "Veiculo [codigo=" + codigo + ", unidade=" + unidade + ", modelo=" + modelo + ", marca=" + marca
				+ ", tipo=" + tipo + ", placa=" + placa + ", kmAtual=" + kmAtual + ", status=" + status
				+ ", motorizacao=" + motorizacao + ", ano=" + ano + ", patrimonio=" + patrimonio + ", combustivel="
				+ combustivel + ", codUnidade=" + codUnidade + "]";
	}
}

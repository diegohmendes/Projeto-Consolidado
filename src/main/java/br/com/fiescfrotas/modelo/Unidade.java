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
@Table(name = "FF_UNIDADE")
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_UNIDADE")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_INSTITUICAO", insertable = false, updatable = false)
	private Instituicao instituicao;

	@Column(name = "NOME_UNIDADE", nullable = false)
	private String nome;

	@Column(name = "COD_INSTITUICAO", nullable = false)
	private long codInstituicao;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCodInstituicao() {
		return codInstituicao;
	}

	public void setCodInstituicao(long codInstituicao) {
		this.codInstituicao = codInstituicao;
	}
}

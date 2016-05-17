
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
@Table(name = "FF_USUARIO")
public class Usuario {
	
	/**
	 * RN01 Os campos nome, email, senha e CNH, tipo de CNH, validade da CNH, Setor, matricula, Unidade, login deverão ser válidos e obrigatórios.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_USUARIO")
	private long codigo;

	@ManyToOne
	@JoinColumn(name = "COD_UNIDADE", insertable = false, updatable = false)
	private Unidade unidade;
	
	@Column(name="LOGIN", nullable=false, unique=true)
	private String login;

	@Column(name = "NOME", nullable=false)
	private String nome;

	@Column(name = "EMAIL", nullable=false, unique=true)
	private String email;

	@Column(name = "SENHA", nullable=false)
	private String senha;

	@Column(name = "CNH", nullable=false, unique=true)
	private long cnh;

	@Column(name = "TIPO_CNH", nullable=false)
	private int tipoCNH;

	@Column(name = "VALIDADE_CNH", nullable =false)
	@Temporal(TemporalType.DATE)
	private Date validadeCNH;
	
	@Column(name = "COD_UNIDADE", nullable=false)
	private long codUnidade;
	
	@Column(name = "SETOR", nullable=false)
	private String setor;

	@Column(name = "MATRICULA", nullable=false, unique=true)
	private int matricula;

	@ManyToOne
	@JoinColumn(name = "COD_PERFIL", insertable = false, updatable = false)
	private Perfil perfil;
	
	@Column(name = "STATUS", nullable=false)
	private int status;
	
	@Column(name = "COD_PERFIL", nullable=false)
	private long codPerfil;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getCnh() {
		return cnh;
	}

	public void setCnh(long cnh) {
		this.cnh = cnh;
	}

	public int getTipoCNH() {
		return tipoCNH;
	}

	public void setTipoCNH(int tipoCNH) {
		this.tipoCNH = tipoCNH;
	}

	public Date getValidadeCNH() {
		return validadeCNH;
	}

	public void setValidadeCNH(Date validadeCNH) {
		this.validadeCNH = validadeCNH;
	}

	public long getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(long codUnidade) {
		this.codUnidade = codUnidade;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(long codPerfil) {
		this.codPerfil = codPerfil;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", unidade=" + unidade + ", login=" + login + ", nome=" + nome + ", email="
				+ email + ", senha=" + senha + ", cnh=" + cnh + ", tipoCNH=" + tipoCNH + ", validadeCNH=" + validadeCNH
				+ ", codUnidade=" + codUnidade + ", setor=" + setor + ", matricula=" + matricula + ", perfil=" + perfil
				+ ", status=" + status + ", codPerfil=" + codPerfil + "]";
	}
	
}
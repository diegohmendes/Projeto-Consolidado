package br.com.fiescfrotas.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FF_PERFIL")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PERFIL")
	private long codigo;
	
	@Column(name = "TIPO_PERFIL")
	private int tipoPerfil;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "FF_PERFIL_PAGINAS", joinColumns = {@JoinColumn(name = "codPerfil")}, inverseJoinColumns = {@JoinColumn(name = "codPagina")})
	private List<Paginas> paginas;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(int tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
}
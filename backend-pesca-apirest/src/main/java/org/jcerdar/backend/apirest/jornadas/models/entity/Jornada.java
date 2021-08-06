package org.jcerdar.backend.apirest.jornadas.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="jornadas")
public class Jornada implements Serializable {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column
	private String modalidad;
	
	@Column(name="fecha_salida")
	@Temporal(TemporalType.DATE)
	private Date fechaSalida;
	
	@Column(name="dir_viento")	
	private String dirViento;
	
	@Column(name="fuerza_viento")
	private String fuerzaViento;
	
	@Column(name="fase_luna")
	private String faseLuna;
	
	@Column
	private String observaciones;
	
	@Column
	private String resultado;
	
	@Column
	private Integer duracion;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date(); 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getDirViento() {
		return dirViento;
	}

	public void setDirViento(String dirViento) {
		this.dirViento = dirViento;
	}

	public String getFuerzaViento() {
		return fuerzaViento;
	}

	public void setFuerzaViento(String fuerzaViento) {
		this.fuerzaViento = fuerzaViento;
	}

	public String getFaseLuna() {
		return faseLuna;
	}

	public void setFaseLuna(String faseLuna) {
		this.faseLuna = faseLuna;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

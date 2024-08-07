package com.registro.usuarios.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidoP")
	private String apellidoP;

	private String email;
	private String password;
	private boolean activo;
	private boolean darkmode;
	private Long id_universidad;
	private String lang;
	private String foto_perfil;
	private LocalDateTime fechaDesbloqueo;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "fk_rol", referencedColumnName = "id_rol")
			)
	private Collection<Rol> roles;

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isDarkmode() {
		return darkmode;
	}

	public void setDarkmode(boolean darkmode) {
		this.darkmode = darkmode;
	}

	public void addRol(Rol rol) {
        this.roles.add(rol);
    }

	public void removeRol(Long idRol) {
        this.roles.removeIf(rol -> rol.getId_rol() == idRol);
    }

	
	public Long getId_universidad() {
		return id_universidad;
	}

	public void setId_universidad(Long id_universidad) {
		this.id_universidad = id_universidad;
	}

	@ManyToMany
    @JoinTable(
        name = "usuario_carreras_favoritas",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    private Set<Carrera> carrerasFavoritas = new HashSet<>();
	

	public Usuario(Long id_usuario, String nombre, String apellidoP, String email, String password, Collection<Rol> roles, boolean activo, boolean darkmode, String lang,Long id_universidad, String foto_perfil) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.activo = activo;
		this.darkmode = darkmode;
		this.lang = lang;
		this.id_universidad =  id_universidad;
		this.foto_perfil = foto_perfil;
	}

	public Usuario(String nombre, String apellidoP, String email, String password, Collection<Rol> roles, boolean activo, boolean darkmode, String lang, Long id_universidad, String foto_perfil) {
		super();
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.activo = activo;
		this.darkmode = darkmode;
		this.lang = lang;
		this.id_universidad = id_universidad;
		this.foto_perfil = foto_perfil;
	}

	public Usuario() {
		
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Set<Carrera> getCarrerasFavoritas() {
		return carrerasFavoritas;
	}

	public void setCarrerasFavoritas(Set<Carrera> carrerasFavoritas) {
		this.carrerasFavoritas = carrerasFavoritas;
	}

	public String getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	public LocalDateTime getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}

	public void setFechaDesbloqueo(LocalDateTime fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}



	

}
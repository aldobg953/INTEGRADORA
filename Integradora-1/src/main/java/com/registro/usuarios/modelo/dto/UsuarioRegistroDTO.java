package com.registro.usuarios.modelo.dto;

public class UsuarioRegistroDTO {

	private Long id_usuario;
	private String nombre;
	private String apellidoP;
	private String email;
	private String password;
	private boolean superUsuario;
	private boolean usuarioAdmin;
	private Long idUniversidad;
	private String lang;
	private boolean activo;

	
	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId(Long id_usuario) {
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

	
	
	public UsuarioRegistroDTO(Long id_usuario, String nombre, String apellidoP, String email, String password,
			boolean superUsuario, boolean usuarioAdmin,Long id_universidad, String lang, boolean activo) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.email = email;
		this.password = password;
		this.superUsuario = superUsuario;
		this.usuarioAdmin = usuarioAdmin;
		this.idUniversidad = id_universidad;
		this.lang = lang;
		this.activo = activo;
	}

	public UsuarioRegistroDTO(String nombre, String apellidoP, String email, String password, Long id_universidad, String lang, boolean activo) {
		super();
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.email = email;
		this.password = password;
		this.lang = lang;
		this.idUniversidad = id_universidad;
		this.activo = activo;
	}

	public UsuarioRegistroDTO() {

	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public boolean isSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		this.superUsuario = superUsuario;
	}

	public boolean isUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(boolean usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}

	public Long getIdUniversidad() {
		return idUniversidad;
	}

	public void setIdUniversidad(Long idUniversidad) {
		this.idUniversidad = idUniversidad;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	

}

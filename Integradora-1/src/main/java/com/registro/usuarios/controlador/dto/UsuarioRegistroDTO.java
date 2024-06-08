package com.registro.usuarios.controlador.dto;

public class UsuarioRegistroDTO {

	private Long id_usuario;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String email;
	private String password;

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

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
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


	public UsuarioRegistroDTO(String nombre, String apellidoP, String apellidoM, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.email = email;
		this.password = password;
	}

	public UsuarioRegistroDTO() {

	}

}

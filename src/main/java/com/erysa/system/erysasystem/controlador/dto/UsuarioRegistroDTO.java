package com.erysa.system.erysasystem.controlador.dto;

public class UsuarioRegistroDTO {

    private Long id_Usuario;
    private String nombre;
    private String apellido;
    private String numero_celular;
    private String direccion;
    private String username;
    private String email;
    private String password;

    public Long getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Long id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(String numero_celular) {
        this.numero_celular = numero_celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public UsuarioRegistroDTO(String nombre, String apellido, String numero_celular, String direccion,String username,
    		String email,String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_celular = numero_celular;
        this.direccion = direccion;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDTO() {
    }



}

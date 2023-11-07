package com.erysa.system.erysasystem.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Rol;
    private String Nombre_Rol;
    
    //Getters and Setters
    public Long getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(Long id_Rol) {
        this.id_Rol = id_Rol;
    }

    public String getNombre_Rol() {
        return Nombre_Rol;
    }

    public void setNombre_Rol(String nombre_Rol) {
        Nombre_Rol = nombre_Rol;
    }
    //Getters and Setters
    
    //Construstor
    public Rol(Long id_Rol, String nombre_Rol) {
        this.id_Rol = id_Rol;
        Nombre_Rol = nombre_Rol;
    }

    public Rol(String nombre_Rol) {
        Nombre_Rol = nombre_Rol;
    }

    public Rol() {
    }
  //Construstor
}

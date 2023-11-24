/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

import java.util.Objects;

/**
 *
 * @author dam
 */
public class Cliente {
    private int ID;
    
    private String nombre;
    
    private String contraseña;
    
    public Cliente(){
        
    }
    
    public Cliente(String nombreCliente, String contraseñaCliente){
        this.nombre = nombreCliente;
        this.contraseña = contraseñaCliente;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setID(int IDNuevo){
        this.ID = IDNuevo;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombreNuevo){
        this.nombre = nombreNuevo;
    }
    
    public String getContraseña(){
        return this.contraseña;
    }
    
    public void setContraseña(String contraseñaNueva){
        this.contraseña = contraseñaNueva;
    }
    
        @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.contraseña, other.contraseña);
    }

    @Override
    public String toString() {
        return "Cliente{" + "ID=" + ID + ", nombre=" + nombre + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAOs.DAOClientes;
import Recursos.Cliente;
import Vista.PanelInicioSesion;

/**
 *
 * @author dam
 */
public class ControllerInicioSesion {
    public static boolean iniciarSesion(PanelInicioSesion panelPrincipal){
        String nombreUsuario = panelPrincipal.getUsuario();
        String contraseña = panelPrincipal.getContraseña();
        
        Cliente clienteIniciado = new Cliente(nombreUsuario, contraseña);
        
        if(DAOClientes.getInstance().iniciarSesion(clienteIniciado)!= 0){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean registro(PanelInicioSesion panelPrincipal){
        String nombreUsuario = panelPrincipal.getUsuario();
        String contraseña = panelPrincipal.getContraseña();
        
        Cliente clienteIniciado = new Cliente(nombreUsuario, contraseña);
        
        if(DAOClientes.getInstance().insertarCliente(clienteIniciado)!=0){
            return true;
        }else{
            return false;
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Recursos.Cliente;
import java.util.List;

/**
 *
 * @author dam
 */
public interface IDAOClientes {
        public int iniciarSesion(Cliente clienteIniciar);  
        public int insertarCliente(Cliente clienteNuevo);
	public int eliminarClienteIniciado();
	public Cliente getCliente(int ID);
	public  List<Cliente> getClientes();
        public int comprobarAdmin();
        public int getClienteIDporNombre(String nombreCliente);
        public String getClienteNombreporID(int IDCliente);
}

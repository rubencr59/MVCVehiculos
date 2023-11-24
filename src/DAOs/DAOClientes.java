package DAOs;

import Recursos.Cliente;
import Recursos.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dam
 */
public class DAOClientes implements IDAOClientes{
    
    private List<Cliente> BDClientes;
    private static IDAOClientes dao=null; 
    private static Cliente clienteIniciado;
    private Cliente admin = new Cliente("admin", "admin");  

    public DAOClientes(){
        getClientesBD();
    }
    
    public static Cliente getClienteIniciado(){
        return clienteIniciado;
    }
    
    public void getClientesBD(){
          List<Cliente> clientes = new ArrayList<>();

            try (Connection connection = ConexionBD.getConnection()) {
                String sql = "SELECT * FROM clientes"; 

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet != null){
                            while (resultSet.next()) {
                                int ID = resultSet.getInt("idcliente");
                                String username = resultSet.getString("nombre");
                                String password = resultSet.getString("contraseña");

                                Cliente cliente = new Cliente(username, password);
                                cliente.setID(ID);
                                clientes.add(cliente);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.BDClientes = clientes;
    }
    

    @Override
    public int insertarCliente(Cliente clienteNuevo) {
            try (Connection connection = ConexionBD.getConnection()) {
                String sql = "INSERT INTO clientes(nombre, contraseña) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, clienteNuevo.getNombre());
                preparedStatement.setString(2, clienteNuevo.getContraseña());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa");
                    getClientesBD();
                    
                    for(Cliente cadaCliente: BDClientes){
                        if(cadaCliente.equals(clienteNuevo)){
                            clienteIniciado = cadaCliente;
                        }
                    }
                    return 1;

                } else {
                    System.out.println("No se pudo insertar el vehículo");
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }

    @Override
    public int eliminarClienteIniciado() {
            int exito = 0;
            if(comprobarAdmin()==0){
                try(Connection connection = ConexionBD.getConnection()){
                    String sql = "DELETE FROM clientes WHERE idcliente = ?";
                
                    try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                        preparedStatement.setInt(1, this.clienteIniciado.getID());
                        DAOVehiculoImpl.getInstance().eliminarVehiculosCliente(this.clienteIniciado.getID());

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Eliminación exitosa");
                            getClientesBD();
                            exito = 1;
                        } else {
                            System.out.println("No se pudo eliminar el vehículo");
                        }
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("NO SE PUEDE BORRAR ADMIN");
            }
            
            return exito;
    }

    @Override
    public Cliente getCliente(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> getClientes() {
        return this.BDClientes;
    }
    
	public static IDAOClientes getInstance() {
	  if (dao== null) dao =new DAOClientes();
	  
		return dao;
	}    

    @Override
    public int iniciarSesion(Cliente clienteIniciar) {
        for(Cliente cadaCliente: BDClientes){
            if(clienteIniciar.equals(cadaCliente)){
                System.out.println("Cliente iniciado con éxito");
                clienteIniciado = clienteIniciar;
                clienteIniciado.setID(cadaCliente.getID());
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int comprobarAdmin(){
        if(clienteIniciado.equals(admin)){
                System.out.println("Bienvenido Admin");
                return 1;
        }else{
            return 0;
        }
    }
    
    @Override
    public int getClienteIDporNombre(String nombreCliente){
        int idCliente = 0;
        if(nombreCliente.equals("admin")){
            return 0;
        }
        for(Cliente cadacliente: this.BDClientes){
            if(cadacliente.getNombre().equals(nombreCliente)){
                idCliente = cadacliente.getID();
            }
        }
        
        return idCliente;
    }
    
    @Override
    public String getClienteNombreporID(int IDCliente){
        String nombre = "";
        for(Cliente cadacliente: this.BDClientes){
            if(cadacliente.getID() == IDCliente){
                nombre = cadacliente.getNombre();
            }
        }
        return nombre;
    }
    
    
}

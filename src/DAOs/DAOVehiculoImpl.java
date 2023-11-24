package DAOs;


import Recursos.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import Recursos.Vehiculo;

public class DAOVehiculoImpl implements IDAOVehiculo {
	
	private List<Vehiculo> BD;
	private static IDAOVehiculo dao=null; 

	private DAOVehiculoImpl() {
		super();
                getVehiculosBD();
	}
        
        
        public void getVehiculosBD(){
             List<Vehiculo> vehiculos = new ArrayList<>();

            try (Connection connection = ConexionBD.getConnection()) {
                String sql = "SELECT * FROM vehiculos WHERE idclienteasociado = ? "; 

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, DAOClientes.getClienteIniciado().getID());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet != null){
                            while (resultSet.next()) {
                                String matricula = resultSet.getString("matricula");
                                String modelo = resultSet.getString("modelo");
                                String marca = resultSet.getString("marca");
                                int idCliente = resultSet.getInt("idclienteasociado");
                                Vehiculo vehiculo = new Vehiculo(matricula, modelo, marca, idCliente );
                                vehiculos.add(vehiculo);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.BD = vehiculos;
        }
        
        public void getTodosVehiculos(){
            List<Vehiculo> vehiculos = new ArrayList<>();
            try (Connection connection = ConexionBD.getConnection()) {
                String sql = "SELECT * FROM vehiculos"; 
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet != null){
                            while (resultSet.next()) {
                                String matricula = resultSet.getString("matricula");
                                String modelo = resultSet.getString("modelo");
                                String marca = resultSet.getString("marca");
                                int idCliente = resultSet.getInt("idclienteasociado");
                                Vehiculo vehiculo = new Vehiculo(matricula, modelo, marca, idCliente );
                                vehiculos.add(vehiculo);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.BD = vehiculos;
        }
        
        @Override
        public void getVehiculosAdmin(String nombreCliente){
            int IdCliente = DAOClientes.getInstance().getClienteIDporNombre(nombreCliente);
            
            
            if(IdCliente == 0){
                getTodosVehiculos();
            }else{
                List<Vehiculo> vehiculos = new ArrayList<>();
                try (Connection connection = ConexionBD.getConnection()) {
                    String sql = "SELECT * FROM vehiculos WHERE idclienteasociado = ? "; 
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, IdCliente);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if(resultSet != null){
                                while (resultSet.next()) {
                                    String matricula = resultSet.getString("matricula");
                                    String modelo = resultSet.getString("modelo");
                                    String marca = resultSet.getString("marca");
                                    int idCliente = resultSet.getInt("idclienteasociado");
                                    Vehiculo vehiculo = new Vehiculo(matricula, modelo, marca, idCliente );
                                    vehiculos.add(vehiculo);
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.BD = vehiculos;
            }
            
        }

	@Override
	public int insertarVehiculo(Vehiculo vehiculo) {         
            try (Connection connection = ConexionBD.getConnection()) {
                String sql = "INSERT INTO vehiculos(matricula, modelo, marca, idclienteasociado) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, vehiculo.getMatricula());
                preparedStatement.setString(2, vehiculo.getModelo());
                preparedStatement.setString(3, vehiculo.getMarca());
                preparedStatement.setInt(4, vehiculo.getIDCliente());
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa");
                    getVehiculosBD();
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
	public int eliminarVehiculo(int posicionCoche) {
            
            try(Connection connection = ConexionBD.getConnection()){
                String sql = "DELETE FROM vehiculos WHERE matricula = ?";
                
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, this.BD.get(posicionCoche).getMatricula());
                
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa");
                    getVehiculosBD();
                    return 1;
                } else {
                    System.out.println("No se pudo eliminar el vehículo");
                }
            }
            }catch(SQLException e){
                e.printStackTrace();
            }


		return 0;
	}

	@Override
	public int eliminarVehiculos(List<Vehiculo> lstVehiculos) {
            for(Vehiculo vehiculo:lstVehiculos){
                BD.remove(vehiculo);
            }
            return 0;
	}
        
        @Override
        public void eliminarVehiculosCliente(int idcliente){
            for(Vehiculo cadaVehiculo:BD){
                if(cadaVehiculo.getIDCliente() == idcliente ){
                    eliminarVehiculo(cadaVehiculo);
                }
            }
        }

	@Override
	public Vehiculo getVehiculo(String matricula) {
            for(Vehiculo vehiculo:BD){
                if(vehiculo.getMatricula().equals(matricula)){
                    return vehiculo;
                }
            }
            return null;
	}

	@Override
	public List<Vehiculo> getVehiculos() {
		return this.BD;
	}

	
	public static IDAOVehiculo getInstance() {
	  if (dao== null) dao =new DAOVehiculoImpl();
	  
		return dao;
	}

	@Override
	public int eliminarVehiculo(Vehiculo vehiculo) {
            try(Connection connection = ConexionBD.getConnection()){
                String sql = "DELETE FROM vehiculos WHERE matricula = ?";
                
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, vehiculo.getMatricula());
                
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa");
                    getVehiculosBD();
                    return 1;
                } else {
                    System.out.println("No se pudo eliminar el vehículo");
                }
            }
            }catch(SQLException e){
                e.printStackTrace();
            }


		return 0;
	}

}

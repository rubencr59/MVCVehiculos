package DAOs;


import java.util.List;

import Recursos.Vehiculo;



	public interface IDAOVehiculo 
	{ public int insertarVehiculo(Vehiculo vehiculo);
	  public int eliminarVehiculo(Vehiculo vehiculo);
	  public int eliminarVehiculo(int posicionCoche);
	  public int eliminarVehiculos(List<Vehiculo> lstVehiculos);
	  public Vehiculo getVehiculo(String matricula);
	  public  List<Vehiculo> getVehiculos();
          public void getVehiculosAdmin(String nombreCliente);
          public void eliminarVehiculosCliente(int IDCliente);
 


  
	
	

}

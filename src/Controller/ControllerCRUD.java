package Controller;




import DAOs.DAOClientes;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAOs.DAOVehiculoImpl;
import Recursos.Cliente;
import Recursos.Vehiculo;
import Vista.PanelCRUD;
import java.util.ArrayList;
import javax.swing.JComboBox;





public class ControllerCRUD
{
	
        public static Cliente getClienteConectado(){
            return DAOClientes.getClienteIniciado();
        }

        public static ArrayList<String> getNombresClientes(){
            ArrayList<String> listaNombres = new ArrayList<String>();
            List<Cliente> clientes = DAOClientes.getInstance().getClientes();
            for(Cliente cadaCliente: clientes){
                listaNombres.add(cadaCliente.getNombre());
            }
            return listaNombres;
        }
        
        public static boolean comprobarAdmin(){
            if(DAOClientes.getInstance().comprobarAdmin()!=0){
                return true;
            }else{
                return false;
            }
        }
        
        public static void cargarTablaAdmin(String cliente, JTable tablaVehiculos){
            DAOVehiculoImpl.getInstance().getVehiculosAdmin(cliente );
            cargarTabla(tablaVehiculos);
        }
    
	public static void cargarTabla( JTable tablaVehiculos)
	{ //DefaultTableModel modeloDeDatosTabla = (DefaultTableModel) tablaVehiculos.getModel();
            List<Vehiculo> lstVehiculos = DAOVehiculoImpl.getInstance().getVehiculos();
		
            DefaultTableModel modelo=new DefaultTableModel();
	 
	 
            modelo.addColumn("Marca");

            modelo.addColumn("Modelo");

            modelo.addColumn("Matricula");
            
            modelo.addColumn("Propietario");

	
    
   
	 Object[] registroLeido = new Object [4];
	 
	 for(Vehiculo vehiculo:lstVehiculos)

	 {	 

			registroLeido[0]= vehiculo.getMarca();

			registroLeido[1]= vehiculo.getModelo();

			registroLeido[2]=  vehiculo.getMatricula();
	 
                        registroLeido[3]= DAOClientes.getInstance().getClienteNombreporID(vehiculo.getIDCliente());



		 modelo.addRow(registroLeido);

	 }
	 
	 tablaVehiculos.setModel(modelo);
	}
	
	
	public static boolean insertarVehiculo( PanelCRUD frmVehiculo, JTable tablaVehiculos)
        {   boolean insertado=false;
            Vehiculo vehiculo=new Vehiculo();



             vehiculo.setMarca(frmVehiculo.getTxtMarca().getText());

             vehiculo.setModelo(frmVehiculo.getTxtModelo().getText());

             vehiculo.setMatricula(frmVehiculo.getTxtMatricula().getText());
             
             vehiculo.setIDCliente(DAOClientes.getClienteIniciado().getID());

                if (DAOVehiculoImpl.getInstance().insertarVehiculo(vehiculo)!=0)
                {   insertado=true;
                    cargarTabla( tablaVehiculos);
                }
               return insertado;		
	}
        
        public static boolean modificarVehiculo(PanelCRUD frmVehiculo, JTable tablaVehiculos){
            boolean modificado = false;
            if(tablaVehiculos.getSelectedRow() > -1){
               if(borrarVehiculo(frmVehiculo, tablaVehiculos) && insertarVehiculo(frmVehiculo, tablaVehiculos)){
                   modificado = true;
               };
            }
            return modificado;
        }
        
        
        public static boolean borrarVehiculo(PanelCRUD frmVehiculo, JTable tablaVehiculos)
        {
            boolean borrado=false;
            if(tablaVehiculos.getSelectedRow() > -1){
                if(DAOVehiculoImpl.getInstance().eliminarVehiculo(tablaVehiculos.getSelectedRow()) != 0){
                borrado = true;
                cargarTabla(tablaVehiculos);
                };
            }
          
            return borrado;
            
        }
	
	
	

}

package TestVehiculos;

import Vista.PanelInicioSesion;
import java.awt.EventQueue;


public class TestVehiculos {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelInicioSesion ventana = new PanelInicioSesion();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	

	}

}

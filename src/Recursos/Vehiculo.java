package Recursos;



public class Vehiculo {


private String marca;

private String modelo;

private String matricula;

private int IDCliente;

public Vehiculo() {
	

}

public Vehiculo(String matricula, String modelo, String marca, int IDClienteAsociado ) {
        this.matricula = matricula;
	this.modelo = modelo;
        this.marca = marca;
        this.IDCliente = IDClienteAsociado;
}

public String getMarca() {
	return marca;
}

public void setMarca(String marca) {
	this.marca = marca;
}

public String getModelo() {
	return modelo;
}

public void setModelo(String modelo) {
	this.modelo = modelo;
}

public String getMatricula() {
	return matricula;
}

public void setMatricula(String matricula) {
	this.matricula = matricula;
}

public int getIDCliente(){
    return this.IDCliente;
}

public void setIDCliente(int IDClientenuevo){
    this.IDCliente = IDClientenuevo;
}


@Override
public String toString() {
	return "Vehiculo [marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", idCliente=" + IDCliente +"]";
}






}
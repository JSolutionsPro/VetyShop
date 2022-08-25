package com.apolosolutions.Apolo;

import com.apolosolutions.Apolo.Modelos.Empleado;
import com.apolosolutions.Apolo.Modelos.MovimientosDinero;
import com.apolosolutions.Apolo.Modelos.enums.RolEmpleado;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApoloApplication {

	public static void main(String[] args) {
		Empresa empresa1= new Empresa();
		Empleado empleado1= new Empleado("Daniel Soto","DanielSC@gamail.com", empresa1, RolEmpleado.ADMINISTRADOR);
		MovimientosDinero movimiento1= new MovimientosDinero(-23150,"Comida para mascotas",empleado1);

		System.out.println(empleado1.toString());
		System.out.println(movimiento1.toString());

		empleado1.setNombre("Jose Castro");
		empleado1.setRol(RolEmpleado.OPERATIVO);
		System.out.println(empleado1.toString());
		movimiento1.setMonto(50000);
		movimiento1.setConcepto("Pago boleta");
		System.out.println(movimiento1.toString());
	}

}
/*comentario para ver si funciona el pull
#comentario */
//comentario de prueba

package com.apolosolutions.Apolo;

import com.apolosolutions.Apolo.Modelos.Usuario;
import com.apolosolutions.Apolo.Modelos.Empresa;
import com.apolosolutions.Apolo.Modelos.MovimientoDinero;
import com.apolosolutions.Apolo.Modelos.enums.RolEmpleado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class ApoloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApoloApplication.class, args);
	}
	/*@GetMapping("/test")
	public String test(){
		// Instancia de las clases
 		Empresa empresa1= new Empresa("Apolo solutions", "Cll encuentrela", "311-2465513", "231-54697321");
		Usuario usuario1 = new Usuario("Daniel Soto","DanielSC@gmail.com", empresa1, RolEmpleado.ADMINISTRADOR);
		MovimientoDinero movimiento1= new MovimientoDinero(-23150,"Comida para mascotas", usuario1);

		System.out.println("----------ESTADO ORIGINAL------------ ");
		// Impresion de los objetos
		System.out.println(empresa1.toString());
		System.out.println(usuario1.toString());
		System.out.println(movimiento1.toString());

		System.out.println(" ----------ESTADO MODIFICADO--------- ");
		// Modificacion del objeto Empresa
		empresa1.setNombre("Riopaila SA");
		empresa1.setDireccion("La paila - Zarzal");
		empresa1.setNIT("132-645798-5");
		empresa1.setTelefono("57+3002525");
		System.out.println(empresa1.toString());

		// Modificacion del objeto Empleado
		usuario1.setNombre("Camilo Castro");
		usuario1.setCorreo("Kamiloc@gmail.com");
		Empresa empresa2= new Empresa("Rappi", "Avenida Siempreviva", "378-589745", "200-545454-8");
		usuario1.setEmpresa(empresa2);
		usuario1.setRol(RolEmpleado.OPERATIVO);
		System.out.println(usuario1.toString());

		// Modificacion del objeto MovimientoDinero
		movimiento1.setMonto(50000);
		movimiento1.setConcepto("Pago boleta");
		Usuario usuario2 = new Usuario("Laura Marina","LaMa@gmail.com", empresa2, RolEmpleado.OPERATIVO);
		movimiento1.setEmpleado(usuario2);
		System.out.println(movimiento1.toString());

		return empresa1.toString()+"<br>\n"+ usuario1.toString()+"<br>\n"+movimiento1.toString();
	}*/

}

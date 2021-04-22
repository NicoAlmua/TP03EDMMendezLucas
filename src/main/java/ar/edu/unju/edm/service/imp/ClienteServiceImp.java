package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;
import ar.edu.unju.edm.util.ListadoClientes;

@Service
@Qualifier("unImp")
public class ClienteServiceImp implements IClienteService{
	
	private List<Cliente> listadoClientes = ListadoClientes.clientes;

	@Autowired
	Cliente unCliente;
	
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		LocalDate nacimiento = unCliente.getFechaNacimiento();
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(nacimiento, hoy);
		unCliente.setEdad(periodo.getYears());
		
		LocalDate ultimaCompra = unCliente.getFechaUltimaCompra();
        Period periodo1 = Period.between(ultimaCompra, hoy);
        unCliente.setTiempoTranscurrido("; T.ult.compra " + periodo1.getDays()+ "/" + periodo1.getMonths()+"/" + periodo1.getYears());
		
        LocalDate cumple = nacimiento.withYear(hoy.getYear());
        if (cumple.isBefore(hoy) || cumple.isEqual(hoy)) {
            cumple = cumple.plusYears(1);
        }
        Period periodo2 = Period.between(hoy, cumple);
        unCliente.setHastaCumple("Faltan " +periodo2.getDays() + " días " + "y " +periodo2.getMonths()+" meses");
		
		listadoClientes.add(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return listadoClientes;
	}
	
}

package agenda.negocio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agenda.modelo.Contacto;
import agenda.persistencia.ContactoDao;

@Service("agenda")
public class AgendaImpl implements Agenda{

	@Autowired

	private ContactoDao dao;
	
	@Override
	public void insertarContacto(Contacto c) {
		dao.save(c);
	}

	@Override
	public Contacto eliminar(int idContacto) {
		Contacto c = dao.findByIdEager(idContacto);
		dao.delete(idContacto);
		return c;
	}

	@Override
	public boolean eliminar(Contacto c) {
		dao.delete(c);
		return true;	// esto es un espanto!!
	}

	@Override
	public void modificar(Contacto c) {
		dao.save(c);
	}

	@Override
	public Set<Contacto> buscarTodos() {
		Set<Contacto> todos = new TreeSet<>(dao.findAll());
		return todos;
	}

	@Override
	public Set<Contacto> buscarContactoPorNombre(String nombre) {
		return dao.findByValue(nombre);
	}

	@Override
	public int importarCSV(String fichero) throws IOException {

		List<String> csv = leerFichero(fichero);
		for(String linea : csv) {
			String[] datos = linea.split(";");
			Contacto nuevo = creaContacto(datos);
			dao.save(nuevo);
		}
		return csv.size();
	}

	private List<String> leerFichero(String fichero) throws IOException {
		List<String> datos = new LinkedList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(fichero))){
			String linea;
			while((linea = br.readLine()) != null) {
				datos.add(linea);
			}
		}
		return datos;
	}

	private Contacto creaContacto(String[] datos) {
		Contacto c = new Contacto();
		c.setNombre(datos[0]);
		c.setApellidos(datos[1]);
		c.setApodo(datos[2]);
		c.getDom().setTipoVia(datos[3]);
		c.getDom().setVia(datos[4]);
		c.getDom().setNumero(Integer.parseInt(datos[5]));
		c.getDom().setPiso(Integer.parseInt(datos[6]));
		c.getDom().setPuerta(datos[7]);
		c.getDom().setCodigoPostal(datos[8]);
		c.getDom().setProvincia(datos[9]);
		c.getDom().setCiudad(datos[10]);
		c.addTelefonos(datos[11].split("/"));
		c.addCorreos(datos[12].split("/"));

		return c;
	}

	@Override
	public Contacto buscar(int idContacto) {
		return dao.findByIdEager(idContacto);
	}
}



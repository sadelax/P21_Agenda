package agenda.negocio;

import java.io.IOException;
import java.util.Set;

import agenda.modelo.Contacto;

public interface Agenda {
	
	void insertarContacto(Contacto c);
	
	Contacto eliminar(int idContacto);
	
	boolean eliminar(Contacto c);
	
	public void modificar(Contacto c);
	
	// Debe retornar los contactos ordenados por apodo
	public Set<Contacto> buscarTodos();
	
	Set<Contacto> buscarContactoPorNombre(String nombre);
	
	int importarCSV(String fichero) throws IOException;

	Contacto buscar(int idContacto);
}

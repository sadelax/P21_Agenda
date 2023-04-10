package agenda.persistencia;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import agenda.modelo.Contacto;

public interface ContactoDao extends JpaRepository<Contacto, Integer>, ContactoDaoCustom {
	
	@Query("SELECT c FROM Contacto c LEFT JOIN FETCH c.correos LEFT JOIN FETCH c.telefonos WHERE c.idContacto = ?1")
	Contacto findByIdEager(int idContacto);
	
	@Query("SELECT c FROM Contacto c WHERE c.apellidos LIKE %?1% OR c.nombre LIKE %?1% OR c.apodo LIKE %?1%")
	Set<Contacto> findByValue(String nom);	
	
}

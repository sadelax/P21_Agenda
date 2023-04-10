package agenda.persistencia;

import org.springframework.beans.factory.annotation.Autowired;

public class ContactoDaoAgenda implements ContactoDaoCustom {

	@Autowired
	ContactoDao dao;
	
	@Override
	public void delete(int idContacto) {
		dao.deleteById(idContacto);
	}
}

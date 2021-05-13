package isil.proyecto.intapli.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import isil.proyecto.intapli.persistence.entity.Aviso;
import java.util.List;

public interface NoticeCrudRepository extends CrudRepository<Aviso, Integer> {
	
	List<Aviso> findByIdUsuarioOrderByContenidoAsc(int idUsuario);

}

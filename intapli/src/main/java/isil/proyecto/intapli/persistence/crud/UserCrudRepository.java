package isil.proyecto.intapli.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import isil.proyecto.intapli.persistence.entity.Usuario;


public interface UserCrudRepository extends CrudRepository<Usuario, Integer>  {
	

}

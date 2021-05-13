package isil.proyecto.intapli.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import isil.proyecto.intapli.persistence.entity.Producto;

public interface ProductCrudRepository extends CrudRepository<Producto, Integer> {

}

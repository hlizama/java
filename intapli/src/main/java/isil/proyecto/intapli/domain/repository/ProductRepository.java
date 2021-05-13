package isil.proyecto.intapli.domain.repository;

import isil.proyecto.intapli.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();

    Optional<Product> getProduct(int productId);

    Product save(Product product);

    void delete(int productId);
}

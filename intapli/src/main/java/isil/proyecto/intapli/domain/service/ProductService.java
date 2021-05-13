package isil.proyecto.intapli.domain.service;

import isil.proyecto.intapli.domain.Product;
import isil.proyecto.intapli.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int idproducto) {
        return productRepository.getProduct(idproducto);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int idproducto) {

        return getProduct(idproducto)
                .map(product -> {
                    productRepository.delete(idproducto);
                    return true;
                }).orElse(false);
    }


}

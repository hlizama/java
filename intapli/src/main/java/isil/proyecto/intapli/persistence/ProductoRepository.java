package isil.proyecto.intapli.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import isil.proyecto.intapli.domain.Product;
import isil.proyecto.intapli.domain.repository.ProductRepository;
import isil.proyecto.intapli.persistence.crud.ProductCrudRepository;
import isil.proyecto.intapli.persistence.entity.Producto;
import isil.proyecto.intapli.persistence.mapper.ProductoMapper;


import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository{
	
	@Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productCrudRepository.findAll();
        return productoMapper.toProducts(productos);
    }


    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId)
                .map(producto -> productoMapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = productoMapper.toProducto(product);
        return productoMapper.toProduct(productCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
	

}

package isil.proyecto.intapli.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import isil.proyecto.intapli.domain.Product;
import isil.proyecto.intapli.persistence.entity.Producto;


@Mapper(componentModel = "spring")
public interface ProductoMapper {
	
	@Mappings({
		@Mapping(source = "idProducto", target = "productId"),
		@Mapping(source = "nombreproducto", target = "name"),
		@Mapping(source = "precio", target = "price")
	})
	
	Product toProduct(Producto producto);
	List<Product> toProducts(List<Producto> productos);
	
	@InheritInverseConfiguration
	Producto toProducto(Product product);
	
}

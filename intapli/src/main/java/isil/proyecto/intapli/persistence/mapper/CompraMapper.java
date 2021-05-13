package isil.proyecto.intapli.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import isil.proyecto.intapli.domain.Purchase;
import isil.proyecto.intapli.persistence.entity.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {
	
	@Mappings({
		@Mapping(source = "idCompra", target = "idPurchase"),
		@Mapping(source = "monto", target = "amount"),
		@Mapping(source = "fechacompra", target = "purchaseDate"),
		@Mapping(source = "idProducto", target = "productId"),
		@Mapping(source = "idUsuario", target = "idusuario")
	})
	
	Purchase toUser(Compra compra);
	List<Purchase> toPurchases(List<Compra> compras);
	
	@InheritInverseConfiguration
	Compra toCompra(Purchase purchase);
	
}

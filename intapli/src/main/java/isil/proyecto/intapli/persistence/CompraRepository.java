package isil.proyecto.intapli.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import isil.proyecto.intapli.domain.Purchase;
import isil.proyecto.intapli.domain.repository.PurchaseRepository;
import isil.proyecto.intapli.persistence.crud.PurchaseCrudRepository;
import isil.proyecto.intapli.persistence.entity.Compra;
import isil.proyecto.intapli.persistence.mapper.CompraMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository{
	
	@Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private CompraMapper compraMapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) purchaseCrudRepository.findAll();
        return compraMapper.toPurchases(compras);
    }


    @Override
    public Optional<Purchase> getPurchase(int userId) {
        return purchaseCrudRepository.findById(userId)
                .map(compra -> compraMapper.toUser(compra));
    }

    @Override
    public Purchase save(Purchase user) {
        Compra usuario = compraMapper.toCompra(user);
        return compraMapper.toUser(purchaseCrudRepository.save(usuario));
    }

    @Override
    public void delete(int userId) {
        purchaseCrudRepository.deleteById(userId);
    }

}

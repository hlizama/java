package isil.proyecto.intapli.domain.repository;

import java.util.List;
import java.util.Optional;

import isil.proyecto.intapli.domain.Purchase;

public interface PurchaseRepository {
	
	List<Purchase> getAll();

    Optional<Purchase> getPurchase(int idpurchase);

    Purchase save(Purchase purchase);

    void delete(int idpurchase);

}

package isil.proyecto.intapli.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isil.proyecto.intapli.domain.Purchase;
import isil.proyecto.intapli.domain.repository.PurchaseRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
	
	 private final ClientEventService clientEventService;

	 public PurchaseService(ClientEventService clientEventService) {
			this.clientEventService = clientEventService;
	 }  
	
	 @Autowired
	    private PurchaseRepository purchaseRepository;

	    public List<Purchase> getAll() {
	        return purchaseRepository.getAll();
	    }

	    public Optional<Purchase> getPurchase(int idpurchase) {
	        return purchaseRepository.getPurchase(idpurchase);
	    }

	    public Purchase save(Purchase purchase) {
			Purchase result = purchaseRepository.save(purchase);
			clientEventService.sendEvent(result);
	        return result;
	    }

	    public boolean delete(int idpurchase) {

	        return getPurchase(idpurchase)
	                .map(purchase -> {
	                	purchaseRepository.delete(idpurchase);
	                    return true;
	                }).orElse(false);
	    }
}

package isil.proyecto.intapli.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import isil.proyecto.intapli.domain.Purchase;
import isil.proyecto.intapli.domain.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
	
	@Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
	@ApiOperation(value = "Get all purchase", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
	@ApiOperation(value = "Get purchase by id", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<Purchase> getPurchase(@PathVariable int id){

        return purchaseService.getPurchase(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/save")
	@ApiOperation(value = "Save purchase", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<Purchase>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
	@ApiOperation(value = "Delete purchase", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity delete(@PathVariable int id){

        if(purchaseService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}

package isil.proyecto.intapli.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import isil.proyecto.intapli.domain.Product;
import isil.proyecto.intapli.domain.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @GetMapping("/all")
	@ApiOperation(value = "Get all product", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
	@ApiOperation(value = "Get notice by id", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<Product> getProduct(@PathVariable int id){

        return productService.getProduct(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/save")
	@ApiOperation(value = "Save notice", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
	@ApiOperation(value = "Delete notice", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity delete(@PathVariable int id){

        if(productService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}

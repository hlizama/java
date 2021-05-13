package isil.proyecto.intapli.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import isil.proyecto.intapli.domain.User;
import isil.proyecto.intapli.domain.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;

    @GetMapping("/all")
	@ApiOperation(value = "Get all user", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
	@ApiOperation(value = "Get user by id", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<User> getUser(@PathVariable int id){

        return userService.getUser(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/save")
	@ApiOperation(value = "Save user", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<User> save(@RequestBody User user){
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
	@ApiOperation(value = "Delete user", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity delete(@PathVariable int id){

        if(userService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
	
	
	
}
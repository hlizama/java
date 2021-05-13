package isil.proyecto.intapli.domain.repository;

import isil.proyecto.intapli.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll();

    Optional<User> getUser(int idusuario);

    User save(User user);

    void delete(int idusuario);

}

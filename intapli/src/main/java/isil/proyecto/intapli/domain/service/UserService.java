package isil.proyecto.intapli.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isil.proyecto.intapli.domain.User;
import isil.proyecto.intapli.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(int idusuario) {
        return userRepository.getUser(idusuario);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int idusuario) {

        return getUser(idusuario)
                .map(usuario -> {
                    userRepository.delete(idusuario);
                    return true;
                }).orElse(false);
    }

}

package isil.proyecto.intapli.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import isil.proyecto.intapli.domain.User;
import isil.proyecto.intapli.domain.repository.UserRepository;
import isil.proyecto.intapli.persistence.crud.UserCrudRepository;
import isil.proyecto.intapli.persistence.entity.Usuario;
import isil.proyecto.intapli.persistence.mapper.UsuarioMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
	
	    @Autowired
	    private UserCrudRepository userCrudRepository;

	    @Autowired
	    private UsuarioMapper usuarioMapper;

	    @Override
	    public List<User> getAll() {
	        List<Usuario> usuarios = (List<Usuario>) userCrudRepository.findAll();
	        return usuarioMapper.toUsers(usuarios);
	    }


	    @Override
	    public Optional<User> getUser(int userId) {
	        return userCrudRepository.findById(userId)
	                .map(usuario -> usuarioMapper.toUser(usuario));
	    }

	    @Override
	    public User save(User user) {
	        Usuario usuario = usuarioMapper.toUsuario(user);
	        return usuarioMapper.toUser(userCrudRepository.save(usuario));
	    }

	    @Override
	    public void delete(int userId) {
	        userCrudRepository.deleteById(userId);
	    }
	

}

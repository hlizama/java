package isil.proyecto.intapli.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IsilUserDetailsService implements UserDetailsService {
	
	private static List<User> users = new ArrayList<>();

    public IsilUserDetailsService() {
        users.add(new User("isil", "{noop}isil2020", new ArrayList<>()));
        users.add(new User("jsventura", "{noop}123456", new ArrayList<>()));
        users.add(new User("user", "{noop}user", new ArrayList<>()));
    }
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();

        if(!user.isPresent()){
            throw new UsernameNotFoundException("User not found by name: "+ username);
        }

        return user.get();
    }
	
}

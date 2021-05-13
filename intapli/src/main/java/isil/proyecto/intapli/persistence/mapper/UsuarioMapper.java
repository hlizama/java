package isil.proyecto.intapli.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import isil.proyecto.intapli.domain.User;
import isil.proyecto.intapli.persistence.entity.Usuario;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	@Mappings({
		@Mapping(source = "idUsuario", target = "idusuario"),
		@Mapping(source = "nombre", target = "nomusuario"),
		@Mapping(source = "correo", target = "email"),
		@Mapping(source = "contrasenia", target = "password"),
		@Mapping(source = "direccion", target = "direccion"),
		@Mapping(source = "telefono", target = "telefono"),
		@Mapping(source = "empresa", target = "empresa"),
		@Mapping(source = "ruc", target = "ruc"),
		@Mapping(source = "tipo", target = "tipo")
	})
	
	User toUser(Usuario usuario);
	List<User> toUsers(List<Usuario> usuarios);
	
	@InheritInverseConfiguration
	@Mapping(target = "avisos", ignore = true)
	Usuario toUsuario(User user);

}

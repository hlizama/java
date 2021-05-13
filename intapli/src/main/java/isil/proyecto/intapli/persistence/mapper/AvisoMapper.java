package isil.proyecto.intapli.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import isil.proyecto.intapli.domain.Notice;
import isil.proyecto.intapli.persistence.entity.Aviso;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface AvisoMapper {
	
	@Mappings({
		@Mapping(source = "idAviso", target = "idmaviso"),
		@Mapping(source = "contenido", target = "contenidoaviso"),
		@Mapping(source = "idUsuario", target = "idusuario"),
		@Mapping(source = "usuario", target = "user")
	})
	
	Notice toNotice(Aviso aviso);
	List<Notice> toNotices(List<Aviso> avisos);
	
	@InheritInverseConfiguration
	
	Aviso toAviso(Notice notice);

}

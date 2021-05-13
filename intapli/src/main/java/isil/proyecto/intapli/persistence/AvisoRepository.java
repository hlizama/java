package isil.proyecto.intapli.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import isil.proyecto.intapli.domain.Notice;
import isil.proyecto.intapli.domain.repository.NoticeRepository;
import isil.proyecto.intapli.persistence.crud.NoticeCrudRepository;
import isil.proyecto.intapli.persistence.entity.Aviso;
import isil.proyecto.intapli.persistence.mapper.AvisoMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class AvisoRepository implements NoticeRepository {
	
	    @Autowired
	    private NoticeCrudRepository noticeCrudRepository;

	    @Autowired
	    private AvisoMapper noticeMapper;

	    @Override
	    public List<Notice> getAll() {
	        List<Aviso> avisos = (List<Aviso>) noticeCrudRepository.findAll();
	        return noticeMapper.toNotices(avisos);
	    }

	    @Override
	    public Optional<List<Notice>> getByUser(int idUsuario) {
	        List<Aviso> avisos = noticeCrudRepository.findByIdUsuarioOrderByContenidoAsc(idUsuario);
	        return Optional.of(noticeMapper.toNotices(avisos));
	    }

	    @Override
	    public Optional<Notice> getNotice(int noticeId ) {
	        return noticeCrudRepository.findById(noticeId)
	                .map(aviso -> noticeMapper.toNotice(aviso));
	    }

	    @Override
	    public Notice save(Notice notice) {
	        Aviso aviso = noticeMapper.toAviso(notice);
	        return noticeMapper.toNotice(noticeCrudRepository.save(aviso));
	    }

	    @Override
	    public void delete(int noticeId) {
	        noticeCrudRepository.deleteById(noticeId);
	    }
	

}

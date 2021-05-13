package isil.proyecto.intapli.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isil.proyecto.intapli.domain.Notice;
import isil.proyecto.intapli.domain.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getAll() {
        return noticeRepository.getAll();
    }
    
    public Optional<List<Notice>> getByNotices(int userId){
        return noticeRepository.getByUser(userId);
    }

    public Optional<Notice> getNotice(int idaviso) {
        return noticeRepository.getNotice(idaviso);
    }

    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    public boolean delete(int idaviso) {

        return getNotice(idaviso)
                .map(notice -> {
                    noticeRepository.delete(idaviso);
                    return true;
                }).orElse(false);
    }

}

package isil.proyecto.intapli.domain.repository;

import isil.proyecto.intapli.domain.Notice;


import java.util.List;
import java.util.Optional;

public interface NoticeRepository {

    List<Notice> getAll(); 
    Optional<List<Notice>> getByUser(int userId);
    Optional<Notice> getNotice(int idaviso);
    Notice save(Notice notice);
    void delete(int idaviso);

}

package de.funkeengineering.urlaubsapp.repository;

import de.funkeengineering.urlaubsapp.domain.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface StatusRepository extends CrudRepository<Status, Long> {

    List<Status> findAll();
}

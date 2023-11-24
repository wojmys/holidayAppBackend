package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import de.funkeengineering.urlaubsapp.error.EmployeeNotFoundException;
import de.funkeengineering.urlaubsapp.error.StatusNotFoundException;
import de.funkeengineering.urlaubsapp.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusDbService {

    private StatusRepository statusRepository;
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElseThrow(() -> new StatusNotFoundException(id));
    }

    public void deleteStatusById(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new StatusNotFoundException(id);
        }
        statusRepository.deleteById(id);
    }

    public Status updateStatus(Long statusId, Status updatedStatus) {
        Status existingStatus = getStatusById(statusId);

        existingStatus.setName(updatedStatus.getName());

        return saveStatus(existingStatus);
    }
}

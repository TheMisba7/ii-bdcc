package org.example.springmvcthymeleaf.app;

import org.example.springmvcthymeleaf.dao.PatientDao;
import org.example.springmvcthymeleaf.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PatientApp extends AbstractApp<PatientDao, Patient>{
    private final PatientDao patientDao;
    protected PatientApp(PatientDao repository) {
        super(repository);
        this.patientDao = repository;
    }

    public Page<Patient> getPatients(final String name,
                                      final int page,
                                      final int size) {
        if (name == null || name.isBlank()) {
            return super.getPage(page, size);
        }
        return patientDao.findPatientByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    }

}

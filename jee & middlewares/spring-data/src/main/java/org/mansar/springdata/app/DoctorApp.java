package org.mansar.springdata.app;

import org.mansar.springdata.dao.DoctorRepository;
import org.mansar.springdata.model.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorApp extends AbstractApp<DoctorRepository, Doctor>{
    public DoctorApp(DoctorRepository repository) {
        super(repository);
    }

    public Doctor update(long id, Doctor doctor) {
        return null;
    }
}

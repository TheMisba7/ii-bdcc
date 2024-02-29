package org.mansar.springdata.app;

import lombok.extern.slf4j.Slf4j;
import org.mansar.springdata.dao.PatientRepository;
import org.mansar.springdata.model.Patient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientApp extends AbstractApp<PatientRepository, Patient>{
    public PatientApp(PatientRepository repository) {
        super(repository);
    }

    public Patient update(final long id, final Patient patient) {
        Patient old = get(id);
        if (old ==  null) {
            log.error("no patient found with id " + id);
            //todo generate exception and add exception handling
            return null;
        }

        //todo use mapstruct or alternatives +_+
        if (patient.getName() != null)
            old.setName(patient.getName());
        if (patient.getSick() != null)
            old.setSick(patient.getSick());
        if (patient.getBirthday() != null)
            old.setBirthday(patient.getBirthday());
        if (patient.getScore() != null)
            old.setScore(patient.getScore());

        return repository.save(old);
    }
}

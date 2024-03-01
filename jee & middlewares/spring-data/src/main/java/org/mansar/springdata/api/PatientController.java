package org.mansar.springdata.api;

import org.mansar.springdata.app.PatientApp;
import org.mansar.springdata.model.Patient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientApp patientApp;

    public PatientController(PatientApp patientApp) {
        this.patientApp = patientApp;
    }

    @PostMapping("")
    public void post(@RequestBody final Patient patient) {
        patientApp.add(patient);
    }

    @GetMapping
    public List<Patient> get() {
        return patientApp.getAll();
    }
    @GetMapping(path = "/{id}")
    public Patient get(@PathVariable(name = "id") final long id) {
        return patientApp.get(id);
    }

    @PutMapping(path = "/{id}")
    public Patient update(@PathVariable(name = "id") final long id,
                          @RequestBody final Patient patient) {
        return patientApp.update(id, patient);
    }
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final long id) {
         patientApp.delete(id);
    }
}

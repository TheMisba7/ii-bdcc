package org.mansar.springdata.api;

import org.mansar.springdata.app.DoctorApp;
import org.mansar.springdata.model.Doctor;
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
@RequestMapping(path = "/api/doctor")
public class DoctorController {
    private final DoctorApp doctorApp;
    public DoctorController(DoctorApp doctorApp) {
        this.doctorApp = doctorApp;
    }

    @PostMapping("")
    public void post(@RequestBody final Doctor Doctor) {
        doctorApp.add(Doctor);
    }

    @GetMapping
    public List<Doctor> get() {
        return doctorApp.getAll();
    }
    @GetMapping(path = "/{id}")
    public Doctor get(@PathVariable(name = "id") final long id) {
        return doctorApp.get(id);
    }

    @PutMapping(path = "/{id}")
    public Doctor update(@PathVariable(name = "id") final long id,
                          @RequestBody final Doctor Doctor) {
        return doctorApp.update(id, Doctor);
    }
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final long id) {
        doctorApp.delete(id);
    }
}

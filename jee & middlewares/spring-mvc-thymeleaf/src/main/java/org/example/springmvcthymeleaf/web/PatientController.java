package org.example.springmvcthymeleaf.web;

import jakarta.validation.Valid;
import org.example.springmvcthymeleaf.app.PatientApp;
import org.example.springmvcthymeleaf.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientApp patientApp;

    public PatientController(PatientApp patientApp) {
        this.patientApp = patientApp;
    }
    @PostMapping("")
    public String post(@Valid @ModelAttribute("patient") final Patient patient, final BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "create-patient";
        patientApp.add(patient);
        return "redirect:/patients";
    }

    @GetMapping
    public String get(final Model model,
                      @RequestParam(name = "keyword" , required = false) String name,
                      @RequestParam(name = "page", defaultValue = "0") int page,
                      @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Patient> patients = patientApp.getPatients(name, page, size);
        model.addAttribute("patientsPage", patients);
        model.addAttribute("keyword", name);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        System.out.println(patients.getTotalPages());
        return "patients";
    }
    @GetMapping(path = "/{id}")
    public Patient get(@PathVariable(name = "id") final long id) {
        return patientApp.getOrElseThrow(id, "Could find patient with id:" + id);
    }

    @GetMapping("/new")
    public String newPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        return "create-patient";
    }

    @GetMapping("/update")
    public String update(final Model model, @RequestParam("id") long id) {
        model.addAttribute("patient", patientApp.getOrElseThrow(id, "Could not find patient with id:" + id));
        return "create-patient";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(final Model model,
                         @PathVariable(name = "id") final long id,
                         @RequestParam(name = "keyword" , required = false) String name,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size) {
        patientApp.delete(id);
        Page<Patient> patients = patientApp.getPatients(name, page, size);
        model.addAttribute("patientsPage", patients);
        model.addAttribute("keyword", name);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        System.out.println(patients.getTotalPages());
        return "patients :: patientsFragment";
    }
}

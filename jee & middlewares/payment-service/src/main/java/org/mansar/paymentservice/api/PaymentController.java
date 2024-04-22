package org.mansar.paymentservice.api;

import org.mansar.paymentservice.dao.PaymentDao;
import org.mansar.paymentservice.model.Payment;
import org.mansar.paymentservice.model.PaymentStatus;
import org.mansar.paymentservice.model.PaymentType;
import org.mansar.paymentservice.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentDao paymentDao;

    public PaymentController(PaymentService paymentService, PaymentDao paymentDao) {
        this.paymentService = paymentService;
        this.paymentDao = paymentDao;
    }

    @GetMapping
    public List<Payment> get() {
        List<Payment> all = paymentDao.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Payment get(@PathVariable("id") final Long id) {
        return paymentService.getById(id);
    }

    @GetMapping("/type/{type}")
    public List<Payment> getByType(@PathVariable("type") final String type) {
        return paymentService.getByType(type);
    }

    @GetMapping("/status/{status}")
    public List<Payment> getByStatus(@PathVariable("status") final String status) {
        return paymentService.getByStatus(status);
    }

    @GetMapping("/students/{studentCode}")
    public List<Payment> getByStudent(@PathVariable("studentCode") final String studentCode) {
        return paymentService.getByStudent(studentCode);
    }

    @GetMapping("/major/{majorId}")
    public List<Payment> getByMajor(@PathVariable("majorId") final String majorId) {
        return paymentService.getByMajor(majorId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment newPayment(@RequestParam MultipartFile file, LocalDate paidAt,
                              double amount, PaymentType paymentType,
                              String studentCode) {
        return paymentService.newPayment(file, amount, studentCode, paidAt, paymentType);
    }

    @GetMapping(value = "/{paymentId}/receipt", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getReceipt(@PathVariable final long paymentId) {
        return paymentService.getReceipt(paymentId);
    }

    @PutMapping("/{paymentId}/status")
    public Payment updateStatus(@PathVariable final long paymentId,
                                @RequestParam(name = "newStatus") final PaymentStatus status) {
        return paymentService.updateStatus(paymentId, status);
    }
}

package org.mansar.paymentservice.service;

import org.mansar.paymentservice.dao.PaymentDao;
import org.mansar.paymentservice.dao.StudentDao;
import org.mansar.paymentservice.model.Payment;
import org.mansar.paymentservice.model.PaymentStatus;
import org.mansar.paymentservice.model.PaymentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService extends AbstractService<Payment, PaymentDao> {
    private final PaymentDao paymentDao;
    private final StudentDao studentDao;
    private final StorageProvider storageProvider;
    protected PaymentService(PaymentDao dao, StudentDao studentDao, StorageProvider storageProvider) {
        super(dao);
        this.paymentDao = dao;
        this.studentDao = studentDao;
        this.storageProvider = storageProvider;
    }

    public Payment newPayment(MultipartFile file, Double amount,
                              String studentCode, LocalDate paidAt,
                              PaymentType paymentType, PaymentStatus status) {
        try {
            String filename = storageProvider.save(file.getInputStream());
            return paymentDao.save(
                    Payment.builder()
                            .status(status)
                            .receipt(filename)
                            .paidAt(paidAt)
                            .amount(amount)
                            .student(studentDao.findByCode(studentCode))
                            .type(paymentType)
                            .status(PaymentStatus.CREATED)
                            .build()
            );

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Payment> getByType(String type) {
        return paymentDao.findByType(PaymentType.valueOf(type));
    }

    public List<Payment> getByStatus(String status) {
        return paymentDao.findByStatus(PaymentStatus.valueOf(status));
    }

    public List<Payment> getByStudent(String studentCode) {
        return paymentDao.findByStudentCode(studentCode);
    }

    public List<Payment> getByMajor(String majorId) {
        return paymentDao.findByStudentMajorId(majorId);
    }

    public byte[] getReceipt(long paymentId) {
        Payment  payment = paymentDao.findById(paymentId).orElseThrow(() -> new RuntimeException("not found"));
        if (payment.getReceipt() != null) {
         return storageProvider.download(payment.getReceipt());
        }
        return new byte[0];
    }

    public Payment updateStatus(long paymentId, PaymentStatus status) {
        Payment payment = paymentDao.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("not found"));

        payment.setStatus(status);
        return paymentDao.save(payment);
    }
}

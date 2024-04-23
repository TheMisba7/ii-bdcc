package org.mansar.paymentservice.service;

import org.mansar.paymentservice.dao.PaymentDao;
import org.mansar.paymentservice.dao.StudentDao;
import org.mansar.paymentservice.dto.PaymentThinDTO;
import org.mansar.paymentservice.dto.StudentDashboard;
import org.mansar.paymentservice.dto.mapper.PaymentMapper;
import org.mansar.paymentservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends AbstractService<Student, StudentDao> {
    private final StudentDao studentDao;
    private final PaymentDao paymentDao;
    private final PaymentMapper paymentMapper;
    protected StudentService(StudentDao dao, PaymentDao paymentDao, PaymentMapper paymentMapper) {
        super(dao);
        this.studentDao = dao;
        this.paymentDao = paymentDao;
        this.paymentMapper = paymentMapper;
    }

    public List<Student> getByMajor(String majorId) {
        return studentDao.findByMajorId(majorId);
    }

    public Student getByCode(String code) {
        return studentDao.findByCode(code);
    }

    public StudentDashboard getStudentDashboard(String code) {
        List<PaymentThinDTO> paymentThinDTOList =
                paymentMapper.toThinDTO(paymentDao.findByStudentCode(code));
        return new StudentDashboard(
                studentDao.findByCode(code),
                paymentThinDTOList,
                paymentThinDTOList.stream()
                        .mapToDouble(PaymentThinDTO::amount).sum()
                );
    }
}

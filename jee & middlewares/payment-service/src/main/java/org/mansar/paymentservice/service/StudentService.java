package org.mansar.paymentservice.service;

import org.mansar.paymentservice.dao.StudentDao;
import org.mansar.paymentservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends AbstractService<Student, StudentDao> {
    private final StudentDao studentDao;
    protected StudentService(StudentDao dao) {
        super(dao);
        this.studentDao = dao;
    }

    public List<Student> getByMajor(String majorId) {
        return studentDao.findByMajorId(majorId);
    }

    public Student getByCode(String code) {
        return studentDao.findByCode(code);
    }
}

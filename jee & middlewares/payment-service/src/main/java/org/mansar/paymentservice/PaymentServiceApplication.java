package org.mansar.paymentservice;

import org.mansar.paymentservice.dao.PaymentDao;
import org.mansar.paymentservice.dao.StudentDao;
import org.mansar.paymentservice.model.Payment;
import org.mansar.paymentservice.model.PaymentStatus;
import org.mansar.paymentservice.model.PaymentType;
import org.mansar.paymentservice.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao, PaymentDao paymentDao) {
		return args -> {
			paymentDao.deleteAll();
			studentDao.deleteAll();
			studentDao.save(Student.builder()
							.code("1234566")
							.email("a.mansar@nuitee.com")
							.firstname("Abdeddaim")
							.lastname("Mansar")
							.majorId("java")
					.build());
			studentDao.save(Student.builder()
					.code("572983")
					.email("m.lken@nuitee.com")
					.firstname("Mouad")
					.lastname("lken")
					.majorId("java")
					.build());
			studentDao.save(Student.builder()
					.code("761728")
					.email("y.mansar@nuitee.com")
					.firstname("Youssef")
					.lastname("Mansar")
					.majorId("java")
					.build());
			studentDao.save(Student.builder()
					.code("1615276")
					.email("o.kader@nuitee.com")
					.firstname("Omar")
					.lastname("Kader")
					.majorId("java")
					.build());
			studentDao.save(Student.builder()
					.code("9826618")
					.email("a.chabab@nuitee.com")
					.firstname("Ayoub")
					.lastname("Chabab")
					.majorId("java")
					.build());
			PaymentType[] paymentTypes = PaymentType.values();
			PaymentStatus[] paymentStatuses = PaymentStatus.values();
			Random random = new Random();
			studentDao.findAll()
					.forEach(student -> {
						for (int i = 0; i < 10; i++) {
							paymentDao.save(
									Payment.builder()
											.code(UUID.randomUUID().toString())
											.type(paymentTypes[random.nextInt(paymentTypes.length)])
											.status(paymentStatuses[random.nextInt(paymentStatuses.length)])
											.student(student)
											.amount(random.nextDouble(1000, 10000))
											.build());
						}
					});
		};
	}
}

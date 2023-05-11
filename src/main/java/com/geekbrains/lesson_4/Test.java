package com.geekbrains.lesson_4;

import com.github.javafaker.Faker;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            BigDecimal mark = BigDecimal.valueOf(Math.random() * 5).setScale(1, RoundingMode.CEILING);
            Student student = new Student(null, faker.funnyName().name(), mark);
            student = studentDao.saveOrUpdate(student);
            System.out.println(student);
        }
        System.out.println(studentDao.findById(1000));
        System.out.println(studentDao.saveOrUpdate(new Student(1000, "new Name", BigDecimal.ZERO)));
        System.out.println(studentDao.deleteById(1000));
//        System.out.println(studentDao.findAll());

    }

}

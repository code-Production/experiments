package com.geekbrains.lesson_4;

import java.util.List;

public interface StudentDao {
    Student findById(int id);
    List<Student> findAll();
    boolean deleteById(int id);
    Student saveOrUpdate(Student student);
}

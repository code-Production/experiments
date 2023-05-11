package com.geekbrains.lesson_4;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDaoImpl implements StudentDao{

    private final SessionFactory sessionFactory;

    public StudentDaoImpl() {
        sessionFactory = Hibernate.getSessionFactory();
    }

    @Override
    public Student findById(int id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.createNamedQuery("Student.findById", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return student;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentList = session.createNamedQuery("Student.findAll", Student.class)
                    .getResultList();
            session.getTransaction().commit();
            return studentList;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            int affected = session.createNamedMutationQuery("Student.deleteById")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return affected > 0;
        }
    }

    @Override
    public Student saveOrUpdate(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student mergedStudent = session.merge(student);
            session.getTransaction().commit();
            return mergedStudent;
        }
    }
}

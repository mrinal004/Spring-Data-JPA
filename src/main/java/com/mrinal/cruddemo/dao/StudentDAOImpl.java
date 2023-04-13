package com.mrinal.cruddemo.dao;

import com.mrinal.cruddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define entity manager member for injection
    private EntityManager entityManager;

    //constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
         return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student",Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String surName) {
        //create query by using JPQL named parameters
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student WHERE lastName =: data",Student.class);

        // set query parameter
        query.setParameter("data",surName);

        // return result as LIST
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // get student object with required id
        Student deleteStudent = entityManager.find(Student.class,id);

        entityManager.remove(deleteStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int recordsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return recordsDeleted;
    }
}

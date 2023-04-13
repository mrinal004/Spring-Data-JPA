package com.mrinal.cruddemo.dao;

import com.mrinal.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    // save a student object
    public void save(Student student);

    // find a student record from table by primary key
    public Student findById(Integer id);

    // find list of students from database
    public List<Student> findAll();

    // find student by last name
    public List<Student> findByLastName(String surName);

    // update Student data
    public void update(Student student);

    // delete a Student
    public void delete(Integer id);

    // delete all students
    public int deleteAll();
}

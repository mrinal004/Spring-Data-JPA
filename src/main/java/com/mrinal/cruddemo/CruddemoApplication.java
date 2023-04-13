package com.mrinal.cruddemo;

import com.mrinal.cruddemo.dao.StudentDAO;
import com.mrinal.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryStudentByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleting rows....");
		System.out.println("Deleted rows : " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 3;

		System.out.println("Deleting student");
		studentDAO.delete(id);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// get a student by id
		int id = 1;
		Student findStudent = studentDAO.findById(id);

		System.out.println("Updating student");
		findStudent.setFirstName("Sunny");
		studentDAO.update(findStudent);

		System.out.println("Updated student : " + findStudent );

	}

	private void queryStudentByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Mohan");

		for (Student student: students ) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for (Student student: students) {
			System.out.println(student);
		}
	}

	private void createStudent(StudentDAO studentDAO) {
		// create Student object
		System.out.println(" Creating student object");
		Student tempStudent = new Student("Mrinal","Mohan","mrinalmohan111@gmail.com");

		// save the object using DAO save method
		System.out.println("saving object");
		studentDAO.save(tempStudent);

		// display the object by it's id
		System.out.println("Fetching created record in DB by id : "+ tempStudent.getId());
		System.out.println(tempStudent);
	}

	private void readStudent(StudentDAO studentDAO) {
		// create Student object
		System.out.println(" Creating student object");
		Student tempStudent = new Student("Mrinal","Mohan","mrinalmohan111@gmail.com");

		// save the object using DAO save method
		System.out.println("saving object");
		studentDAO.save(tempStudent);

		// display the object by it's id
		System.out.println("Fetching created record in DB by id : "+ tempStudent.getId());

		// find student object by primary key - id
		System.out.println("Finding student by id");
		Student findStudent = studentDAO.findById(tempStudent.getId());
		System.out.println(findStudent);
	}

}

package com.simplilearn.A;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simplilearn.C.Student;
import com.simplilearn.B.Database;

public class StudentD {

	public Student getStudent(int id) {
		Transaction transaction = null;
		Student student = null;

		try (Session session = Database.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			student = session.get(Student.class, id);
	transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public Student saveStudent(Student student) {
		Transaction transaction = null;
		Student createdStudent = null;
		Session session = null;
		try {
			session = Database.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(student);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return createdStudent;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		Transaction transaction = null;
		List<Student> listOfStudents = null;
		try (Session session = Database.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an student object
			listOfStudents = session.createQuery("from Student").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfStudents;
	}
}
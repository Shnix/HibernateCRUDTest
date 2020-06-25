package com.krayale.hibernate.demo;

import com.krayale.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@SuppressWarnings("all")
public class QueryStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student").list();
            displayStudents(students);

            students = session.createQuery("from Student s where s.lastName='Doe'").list();
            System.out.println("Only last name = Doe");
            displayStudents(students);

            students = session.createQuery("from Student s where " +
                    "s.lastName='Doe' OR s.firstName='Daffy'").list();
            System.out.println("last name Doe or first name Duffy");
            displayStudents(students);

            students = session.createQuery("from Student s where " +
                    "s.email LIKE '%mail.ru'").list();
            System.out.println("email ends with mail.ru");
            displayStudents(students);

            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        if (students.size()==0){
            System.out.println("list is empty");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

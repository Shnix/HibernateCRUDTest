package com.krayale.hibernate.demo;

import com.krayale.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("all")
public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            int studentId = 1;
            session.beginTransaction();
            Student student = session.get(Student.class,studentId);
            student.setFirstName("Scooby");
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Update email");
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
        }
    }
}

package com.krayale.hibernate.demo;

import com.krayale.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("all")
public class DeleteStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            int studentId = 3;
            session.beginTransaction();
            Student student = session.get(Student.class,studentId);
            session.delete(student);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Student where id=2").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
        }
    }
}

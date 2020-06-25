package com.krayale.hibernate.demo;

import com.krayale.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            Student student = new Student("Daffy","Duck","daffyD@yandex.ru");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println(student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with id: " + student.getId());
            Student newStudent = session.get(Student.class,student.getId());
            System.out.println("Get complete: " + newStudent);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            session.close();
        }
    }
}

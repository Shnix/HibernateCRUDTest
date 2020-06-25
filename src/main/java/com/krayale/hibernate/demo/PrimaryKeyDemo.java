package com.krayale.hibernate.demo;

import com.krayale.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            Student student = new Student("John","Doe","JD@yandex.ru");
            Student student2 = new Student("Mary","Poppins","Marmary@yandex.ru");
            Student student3 = new Student("Brandon","Stark","threeeyedravenD@yandex.ru");
            session.beginTransaction();
            session.save(student);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
}

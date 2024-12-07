package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
     
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Client.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
           
            session.beginTransaction();
            Client client1 = new Client("Alice", "Female", 25, "New York", "alice@example.com", "1234567890");
            Client client2 = new Client("Bob", "Male", 30, "Los Angeles", "bob@example.com", "0987654321");
            session.persist(client1);
            session.persist(client2);
            session.getTransaction().commit();

            
            session.beginTransaction();
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            List<Client> clients = query.getResultList();
            for (Client client : clients) {
                System.out.println(client);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}


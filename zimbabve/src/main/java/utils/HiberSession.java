package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HiberSession {
    public static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null) {
            try {
                Configuration conf = new Configuration().configure("hibernate.cfg.xml");
                conf.addAnnotatedClass(Role.class);
                conf.addAnnotatedClass(Question.class);
                conf.addAnnotatedClass(QuestionItem.class);
                conf.addAnnotatedClass(User.class);
                conf.addAnnotatedClass(UserRole.class);
                conf.addAnnotatedClass(Category.class);
                conf.addAnnotatedClass(Product.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties());
                sessionFactory = conf.buildSessionFactory();
            } catch(Exception ex) {
                System.out.println("Помилка "+ex.getMessage());
            }
        }
        return sessionFactory;
    }
}

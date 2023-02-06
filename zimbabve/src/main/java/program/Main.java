package program;

import models.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HiberSession;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Session context = HiberSession.getSessionFactory().openSession())
        {
//            Scanner in = new Scanner(System.in);
//            System.out.println("Вкажіть назву ролі");
//            String name = in.nextLine();
//            Role role = new Role();
//            role.setName(name);
//            context.save(role);

            Query query = context.createQuery("FROM Role");
            List<Role> roles = query.list();
            for (Role role :  roles) {
                System.out.println("Name = "+role.getName());
            }
        }
    }
}

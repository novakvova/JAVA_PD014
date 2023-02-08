package program;

import models.Question;
import models.QuestionItem;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HiberSession;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //testRole();
        //addQuestion();
        showQuestions();
    }
    private static void addQuestion() {
        try(Session context = HiberSession.getSessionFactory().openSession()) {
            Scanner in = new Scanner(System.in);
            Transaction tx = context.beginTransaction();
            System.out.println("Вкажіть назву питання:");
            String questionName = in.nextLine();
            Question q = new Question();
            q.setName(questionName);
            context.save(q);
            String action = "";
            do {
                System.out.println("Вкажіть відповідь:");
                String text = in.nextLine();
                System.out.println("1-Правильно, 0-Неправильно");
                boolean isTrue = Byte.parseByte(in.nextLine())==1 ? true : false;
                QuestionItem qi = new QuestionItem();
                qi.setText(text);
                qi.setTrue(isTrue);
                qi.setQuestion(q);
                context.save(qi);
                System.out.println("0. Вихід");
                System.out.println("1. Додати наступний варіант");
                System.out.print("->_");
                action=in.nextLine();
            }while(!action.equals("0"));
            tx.commit();
            context.close();
        }
    }

    private static void showQuestions() {
        try(Session context = HiberSession.getSessionFactory().openSession()) {
            Query q = context.createQuery("FROM Question");
            List<Question> list = q.list();
            for (Question ques : list) {
                System.out.println(ques.toString());
            }
        }
    }
    private static void testRole() {
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

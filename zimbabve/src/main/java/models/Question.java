package models;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    @OneToMany(mappedBy = "question")
    private List<QuestionItem> questionItems;
    public Question() {
        questionItems = new ArrayList<>();
    }
    @Override
    public String toString() {
        String str=name;
        str+="\n";
        int i=1;
        for (QuestionItem item : questionItems) {
            str+=i+". "+item.getText()+"\n";
            i++;
        }
        return str;
    }
}

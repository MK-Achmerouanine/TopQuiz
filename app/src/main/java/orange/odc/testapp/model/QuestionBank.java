package orange.odc.testapp.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questionList;
    private int idCurrentQuestion; //1

    public QuestionBank(List<Question> questionList) {
        this.questionList = questionList;
        // Modifier l'ordre des questions
        Collections.shuffle(this.questionList);
    }
    public Question getCurrentQuestion(){
        return this.questionList.get(idCurrentQuestion); // .get(2)
    }

    public Question getNextQuestion(){
        idCurrentQuestion++; // 2
        return this.getCurrentQuestion();
    }

}

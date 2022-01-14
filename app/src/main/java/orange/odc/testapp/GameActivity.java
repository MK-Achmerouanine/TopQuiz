package orange.odc.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import orange.odc.testapp.model.Question;
import orange.odc.testapp.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private TextView questionTextview;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private QuestionBank modelQuestionBank;
    private int remainingQuestionsCount;

    private int score = 0;

    private QuestionBank questionBankSeed() {
        Question question1 = new Question(
                "Question 1 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                1
        );
        Question question2 = new Question(
                "Question 2 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                2
        );
        Question question3 = new Question(
                "Question 3 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );
        Question question4 = new Question(
                "Question 4 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );
        Question question5 = new Question(
                "Question 5 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );
        Question question6 = new Question(
                "Question 6 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );
        Question question7 = new Question(
                "Question 7 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );
        Question question8 = new Question(
                "Question 8 ?",
                Arrays.asList(
                        "Choix 1",
                        "Choix 2",
                        "Choix 3",
                        "Choix 4"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8
        ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Question
        questionTextview = findViewById(R.id.game_textview_question);
        // Réponses
        answerButton1 = findViewById(R.id.game_button_answer1);
        answerButton2 = findViewById(R.id.game_button_answer2);
        answerButton3 = findViewById(R.id.game_button_answer3);
        answerButton4 = findViewById(R.id.game_button_answer4);


        modelQuestionBank = questionBankSeed();

        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);

        remainingQuestionsCount = 4;
        displayQuestion(modelQuestionBank.getCurrentQuestion());

    }


    private void displayQuestion(final Question question){
        // Mis à jour du textview
        questionTextview.setText(question.getIntitule());
        answerButton1.setText(question.getChoix().get(0));
        answerButton2.setText(question.getChoix().get(1));
        answerButton3.setText(question.getChoix().get(2));
        answerButton4.setText(question.getChoix().get(3));
    }

    private void endQuiz() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Bien joué !")
                .setMessage("Ton score est de "+score)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
    @Override
    public void onClick(View view) {
        // Détection de l'element clické
        int idClickedElement;
        if(view == answerButton1) idClickedElement = 0;
        else if(view == answerButton2) idClickedElement = 1;
        else if(view == answerButton3) idClickedElement = 2;
        else if(view == answerButton4) idClickedElement = 3;
        else throw new IllegalStateException("Unknown clicked view : "+ view);
        if (idClickedElement == 0 || idClickedElement == 1 || idClickedElement == 2 || idClickedElement == 3) {
            remainingQuestionsCount --;
            if(idClickedElement == modelQuestionBank.getCurrentQuestion().getIdBonneReponse()){
                Toast.makeText(this, "Correct !", Toast.LENGTH_LONG).show();
                score++;
            } else {

                Toast.makeText(this, "Incorrect !", Toast.LENGTH_SHORT).show();
            }
            if(remainingQuestionsCount>0){
                displayQuestion(questionBankSeed().getNextQuestion());
            }else {
                // Finir le Quiz
                endQuiz();
            }
        }
    }
}
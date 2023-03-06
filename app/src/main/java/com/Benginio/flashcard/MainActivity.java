package com.Benginio.flashcard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btnNext;

private TextView btnOptionA;
private TextView btnOptionB;
private TextView btnOptionC;
private TextView tvCount;
String  Status = " ";
int score = 0;
String selectAnswer;
private TextView tvQuestion;
int totalQuestion = Quiz.question.length;
int currentQuestion = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate (convert xml to java code)
        btnNext=findViewById(R.id.btnNext);
        tvCount=findViewById(R.id.tvCount);
        tvQuestion=findViewById(R.id.tvQuestion);
        btnOptionA=findViewById(R.id.optionA);
        btnOptionB=findViewById(R.id.optionB);
        btnOptionC=findViewById(R.id.optionC);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Benginio", "entered onCLick method");

                Toast.makeText(MainActivity.this, "I CLICKED THE QUESTION!", Toast.LENGTH_SHORT).show();

            }
        });
        btnOptionA.setOnClickListener(this);
        btnOptionB.setOnClickListener(this);
        btnOptionC.setOnClickListener( this);
        btnNext.setOnClickListener(this);



        loadQuestion();

    }



    private void loadQuestion() {
        if(currentQuestion == totalQuestion){
            if(score > 1)
            {
                Status = "Success!";
            }else {

                Status = "Failed!";
            }
            messageScore();
            return;
        }
        tvCount.setText(""+(currentQuestion+1));
        btnOptionA.setBackgroundColor(Color.BLUE);
        btnOptionA.setTextColor(Color.WHITE);
        btnOptionB.setBackgroundColor(Color.BLUE);
        btnOptionB.setTextColor(Color.WHITE);
        btnOptionC.setBackgroundColor(Color.BLUE);
        btnOptionC.setTextColor(Color.WHITE);

        tvQuestion.setText(Quiz.question[currentQuestion]);
        btnOptionA.setText(Quiz.choix[currentQuestion][0]);
        btnOptionB.setText(Quiz.choix[currentQuestion][1]);
        btnOptionC.setText(Quiz.choix[currentQuestion][2]);
    }

    public void onClick(View view) {

        Button clickButton = (Button) view;

        btnOptionA.setBackgroundColor(Color.WHITE);
        btnOptionB.setBackgroundColor(Color.WHITE);
        btnOptionC.setBackgroundColor(Color.WHITE);

        if(clickButton.getId() == R.id.btnNext){

            if(selectAnswer.equals(Quiz.reponse[currentQuestion]))
            {
                score++;
            }
            currentQuestion++;
            loadQuestion();

        }else {

            selectAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.WHITE);
            clickButton.setTextColor(Color.BLACK);
        }

    }

    //show a message like a poppup(alert)
    private void messageScore() {
        new AlertDialog.Builder(this)
                .setTitle(Status)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Play again", (dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
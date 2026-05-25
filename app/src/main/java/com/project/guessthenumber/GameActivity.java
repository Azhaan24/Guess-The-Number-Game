package com.project.guessthenumber;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewRight, textViewHint;
    private EditText editTextViewGuess;
    private Button buttonConfirm;
    Boolean twoDigits, threeDigits, fourDigits;
    Random r = new Random();
    int random;
    int remainigRight=10;
    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttempts=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewHint=findViewById(R.id.textViewHint);
        textViewLast=findViewById(R.id.textViewLast);
        textViewRight=findViewById(R.id.textViewRight);

        editTextViewGuess=findViewById(R.id.editTextNumber);

        buttonConfirm=findViewById(R.id.buttonConfirm);

        twoDigits=getIntent().getBooleanExtra("two",false);
        threeDigits=getIntent().getBooleanExtra("three",false);
        fourDigits=getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            random=r.nextInt(90)+10;
        }
        if(threeDigits){
            random=r.nextInt(900)+100;
        }
        if(fourDigits){
            random=r.nextInt(9000)+1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editTextViewGuess.getText().toString();
                if(guess.equals("")){
                    Toast.makeText(GameActivity.this,"Please enter a number",Toast.LENGTH_LONG).show();
                }
                else{
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);

                    remainigRight--;
                    userAttempts++;

                    int userGuess=Integer.parseInt(guess);
                    guessList.add(userGuess);
                    textViewLast.setText("Your Last Guess was: "+guess);
                    textViewRight.setText("Your Remaining Right: "+remainigRight);

                    if(random==userGuess){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Game Over");
                        builder.setCancelable(false);
                        builder.setMessage("Cogratulations! You guessed it Correctly! \n\nMy number was: "+random+"\n\nYou got my number in "+userAttempts+" attempts."+"\n\nYour Guesses: "+guessList+"\n\nWould You Like to Play Again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                        return;
                    }
                    if(random>userGuess){
                        textViewHint.setText("Guess a Larger Number");
                    }
                    if(random<userGuess){
                        textViewHint.setText("Guess a Smaller Number");
                    }
                    if(remainigRight==0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Game Over");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry! Your Right to Guess is Over! \n\nMy number was: "+random+"\n\nYour Guesses: "+guessList+"\n\nWould You Like to Play Again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }
                    editTextViewGuess.setText("");
                }
            }
        });
    }
}
package com.project.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart;
    private RadioButton radio2, radio3, radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom);
            return insets;
        });

        buttonStart=findViewById(R.id.buttonStart);
        radio2=findViewById(R.id.radio2);
        radio3=findViewById(R.id.radio3);
        radio4=findViewById(R.id.radio4);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                if(!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked()){
                    Snackbar.make(v,"Please select a number of digit",Snackbar.LENGTH_LONG).show();
                }
                else{
                    if (radio2.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if (radio3.isChecked()){
                        intent.putExtra("three",true);
                    }
                    if (radio4.isChecked()){
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);
                }
            }
        });

    }
}
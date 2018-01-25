package com.example.dell.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Score;
    ImageView img;
    Button roll, hold, reset;

    int TurnScore, CompScore, YourScore, curDiceNo;
    boolean chance;
    int images[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Score = (TextView) findViewById(R.id.Score);
        roll = (Button) findViewById(R.id.RollB);
        hold = (Button) findViewById(R.id.HoldB);
        reset = (Button) findViewById(R.id.ResetB);
        img = (ImageView) findViewById(R.id.DiceFace);

    }

    public void Roll(View v) {
        curDiceNo = new Random().nextInt(6) + 1;
        if (curDiceNo == 1) {
            TurnScore = 0;
            Hold(null);
        } else
            TurnScore += curDiceNo;

        UpdateUI();
    }

    public void Reset(View v) {
        TurnScore = CompScore = YourScore = 0;
        chance = true;
        UpdateUI();
    }

    public void Hold(View v) {
        if (chance) {
            YourScore += TurnScore;
            chance = false;
            TurnScore =0;
            CompTurn();
        } else {
            CompScore += TurnScore;
            chance = true;
            TurnScore =0;
        }
    }

    public void UpdateUI() {
        Score.setText("Your Score:" + YourScore + "\nComputer Score =" + CompScore + "\nTurn Score =" + TurnScore);
        img.setImageResource(images[curDiceNo - 1]);
    }

    public void CompTurn(){
        if(!chance) {
            // if its computer's turn
            if(TurnScore<20) {
                Roll(null);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CompTurn();
                    }
                }, 2000);
            }

            else{
                Hold(null);
            }
        }
    }
}



package com.example.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String message;

        private Weapon(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    }


    Random rand = new Random();
    private Weapon userWeapon;
    private Weapon computerWeapon;

    private int playerLives;
    private int computerLives;

    private String weaponResult;
    private String roundResult;


    public void updateAll() {


        compChoice();
        decideWinner();

        updateScoreTextView();
        updateComputerWeaponText();
        updatePlayerWeaponText();
        updateRoundResultText();



    }


    public Weapon compChoice() {

        int weaponType = rand.nextInt(3);

        switch (weaponType) {
            case 0:
                computerWeapon = Weapon.ROCK;
                break;

            case 1:
                computerWeapon = Weapon.PAPER;
                break;

            case 2:
                computerWeapon = Weapon.SCISSORS;
                break;
        }

        return computerWeapon;
    }



    public void decideWinner() {

        //draw

        if (userWeapon == computerWeapon) {
            roundResult = "It's a draw... ";
            weaponResult = "";
        }


        //rock
        if (userWeapon == Weapon.ROCK) {
            if (computerWeapon == Weapon.SCISSORS) {
                computerLives--;
                roundResult = "Player wins... ";
                weaponResult = "Rock blunts scissors!";

            } else if (computerWeapon == Weapon.PAPER) {
                playerLives--;
                roundResult = "Computer wins... ";
                weaponResult = "Paper covers rock!";
            }
        }

        //paper
        if (userWeapon == Weapon.PAPER) {
            if (computerWeapon == Weapon.ROCK) {
                computerLives--;
                roundResult = "Player wins... ";
                weaponResult = "Paper covers rock!";

            } else if (computerWeapon == Weapon.SCISSORS) {
                playerLives--;
                roundResult = "Computer wins... ";
                weaponResult = "Scissors cuts paper!";

            }

        }

        //scissors
        if (userWeapon == Weapon.SCISSORS) {
            if (computerWeapon == Weapon.PAPER) {
                computerLives--;
                roundResult = "Player wins... ";
                weaponResult = "Scissors cuts paper!";

            } else if (computerWeapon == Weapon.ROCK) {
                playerLives--;
                roundResult = "Computer wins... ";
                weaponResult = "Rock blunts scissors!";

            }
        }


        if (computerLives == 0) {
            roundResult = "Player wins the game!";
        }

        if (playerLives == 0) {
            roundResult = "Computer wins the game!";
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        playerLives = 3;
        computerLives = 3;


    }

    public void rockButtonClicked(View v) {
        userWeapon = Weapon.ROCK;
        updateAll();
    }

    public void paperButtonClicked(View v) {
        userWeapon = Weapon.PAPER;
        updateAll();
    }

    public void scissorsButtonClicked(View v) {
        userWeapon = Weapon.SCISSORS;
        updateAll();
    }


    // display correct text views

    private void updateScoreTextView() {
        TextView scoreTextView = findViewById(R.id.scoreboardText);
        String scoreString = "Player: " + playerLives + ". " + "Computer: " + computerLives;
        scoreTextView.setText(scoreString);

    }


    private void updatePlayerWeaponText() {
        TextView playerTextView = findViewById(R.id.playerWeaponText);
        String playerWeaponText = "Player's Weapon: " + userWeapon;
        playerTextView.setText(playerWeaponText);
    }


    private void updateComputerWeaponText() {
        TextView computerTextView = findViewById(R.id.computerWeaponText);
        String computerString = "Computer's Weapon: " + computerWeapon;
        computerTextView.setText(computerString);
    }


    private void updateRoundResultText() {
        TextView resultTextView = findViewById(R.id.roundResultText);
        String resultString = roundResult + "" + weaponResult;
        resultTextView.setText(resultString);
    }



        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    }

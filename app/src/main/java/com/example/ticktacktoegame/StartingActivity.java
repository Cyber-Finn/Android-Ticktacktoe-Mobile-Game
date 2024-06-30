package com.example.ticktacktoegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartingActivity extends AppCompatActivity
{
    private Button btnStartGame = null; //just don't want to assign it as null

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);

        controllerMethod();
    }
    private void controllerMethod()
    {
        showLayout();
        getReferenceToWidgets();
        createOnClickForButton();
    }

    private void showLayout()
    {
        setContentView(R.layout.home_screen);
    }
    private void getReferenceToWidgets()
    {
        btnStartGame = findViewById(R.id.btnStartGame);
    }
    private void createOnClickForButton()
    {
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass intent to the game loop
                Intent newInt = new Intent(StartingActivity.this, GameLoopActivity.class);
                StartingActivity.this.startActivity(newInt);
                StartingActivity.this.finish();
            }
        });
    }
}

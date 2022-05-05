package com.example.rpn_calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String equation = "";
    private TextView showEquation;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setupToolbar();
        setupEFAB();
        initializeViews();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        showEquation = findViewById(R.id.equation);
    }

    private void setupEFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEFABClick();
            }
        });
    }

    private void handleEFABClick() {
        //do the calculation
        //if its correct,
            //display it in the result
        //else
            //go to the instructions activity
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void addToEquation(View view) {
        Button currentButton = (Button) view;
        String currentButtonText = currentButton.getText().toString();
        equation += currentButtonText;
        showEquation.setText(equation);
    }

    public void addSpaceToEquation(View view) {
        Button currentButton = (Button) view;
        String currentButtonText = " ";
        equation += currentButtonText;
        showEquation.setText(equation);
    }
}
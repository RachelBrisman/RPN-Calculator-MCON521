package com.example.rpn_calculator;

import static lib.Utils.showInfoDialog;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import models.PA3;

public class MainActivity extends AppCompatActivity {

    private String equation = "";
    private TextView showEquation;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupCalcEFAB();
        setupNewCalcEFAB();
        initializeViews();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        showEquation = findViewById(R.id.equation);
        button = findViewById(R.id.calc_fab);
    }

    private void setupCalcEFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.calc_fab);
        fab.setOnClickListener(view -> handleCalcEFABClick(fab));
    }

    private void handleCalcEFABClick(View view) {
        equation = showEquation.getText().toString();
        String result = PA3.evaluate(equation);
        if (!result.equals("Invalid input")) {
            showEquation.setText(result);
        } else {
            Snackbar.make(view, "Invalid Equation", Snackbar.LENGTH_LONG)
                    .setAction("Show Instructions...",
                            view1 -> showInstructions())
                    .show();
        }
    }

    private void setupNewCalcEFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.new_fab);
        fab.setOnClickListener(view -> handleNewCalcEFABClick(fab));
    }

    public void handleNewCalcEFABClick(View view) {
        equation = "";
        showEquation.setText(equation);
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
        } else if (id == R.id.action_instructions) {
            showInstructions();
        } else if (id == R.id.action_toggle_auto_save) {
            toggleMenuItem(item);
            //need to figure out how to do it
        } else if (id == R.id.action_about) {
            showAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleMenuItem(MenuItem item)
    {
        item.setChecked(!item.isChecked());
    }

    private void showInstructions() {
        Intent intent = new Intent(getApplicationContext(), InstructionsActivity.class);
        startActivity(intent);
    }

    private void showAbout() {
        showInfoDialog(MainActivity.this, "About This RPN Calculator",
                "Solve RPN equations easily\n" +
                        "\nBy Talia Yahav and Rachel Brisman");
    }

    public void addToEquation(View view) {
        Button currentButton = (Button) view;
        String currentButtonText = currentButton.getText().toString();
        equation += currentButtonText;
        showEquation.setText(equation);
    }

    public void addSpaceToEquation(View view) {
        equation += " ";
        showEquation.setText(equation);
    }

    public void backspaceEquation(View view) {
        if (equation.length() != 0) {
            equation = equation.substring(0, equation.length() - 1);
        }
        showEquation.setText(equation);
    }
}
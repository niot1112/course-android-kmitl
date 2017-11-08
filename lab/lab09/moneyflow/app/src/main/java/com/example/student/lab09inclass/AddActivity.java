package com.example.student.lab09inclass;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private EditText amount, task;
    private RadioButton payment, income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        amount = findViewById(R.id.Amount);
        task = findViewById(R.id.taskBox);
        payment = findViewById(R.id.payment);
        income = findViewById(R.id.income);


    }

    public void onAdd(View view) {
        String type;
        if(income.isChecked()){
            type = "+";
        }else {
            type = "-";
        }
        String str_task = task.getText().toString();
        String str_amount = amount.getText().toString();
        Intent data = new Intent();
        data.putExtra("task", str_task);
        data.putExtra("amount", str_amount);
        data.putExtra("type", type);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancel(View view) {
        finish();
    }
}

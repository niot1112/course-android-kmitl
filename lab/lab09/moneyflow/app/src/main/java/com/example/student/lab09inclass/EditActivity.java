package com.example.student.lab09inclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditActivity extends AppCompatActivity {

    private EditText amount, task;
    private RadioButton payment, income;
    private String delete = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        amount = findViewById(R.id.Amount);
        task = findViewById(R.id.taskBox);
        payment = findViewById(R.id.payment);
        income = findViewById(R.id.income);

        Intent intent = getIntent();
        amount.setText(intent.getStringExtra("money"));
        task.setText(intent.getStringExtra("task"));
        String type = intent.getStringExtra("type");
        if(type.equals("+")){
            income.setChecked(true);
        }else{
            payment.setChecked(true);
        }
    }

    public void onUpdate(View view) {
        String type;
        if(income.isChecked()){
            type = "+";
        }else {
            type = "-";
        }
        String str_task = task.getText().toString();
        String str_amount = amount.getText().toString();
        Intent data = new Intent();
        data.putExtra("delete", delete);
        data.putExtra("task", str_task);
        data.putExtra("amount", str_amount);
        data.putExtra("type", type);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onCancel(View view) {
        delete = "1";
        Intent data = new Intent();
        data.putExtra("delete", delete);
        setResult(RESULT_OK,data);
        finish();
    }
}

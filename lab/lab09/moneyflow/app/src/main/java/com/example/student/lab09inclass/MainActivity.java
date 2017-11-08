package com.example.student.lab09inclass;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AccountDB accountDB;
    private ListView messageList;
    private List<AccountInfo> infoList;

    private TextView sum_amount;

    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    private String item_id;
    private String item_type;
    private String item_task;
    private String item_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sum_amount = findViewById(R.id.sum_money);

        messageList = findViewById(R.id.AccountInfoList);

        accountDB = Room.databaseBuilder(this, AccountDB.class, "MESSAGE").build();

        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AccountInfo accountInfo = infoList.get(position);
                item_id = String.valueOf(accountInfo.getId());
                item_type = accountInfo.getType();
                item_task = accountInfo.getText();
                item_money = String.valueOf(accountInfo.getMoney());
                editTask();
            }
        });

        showList();
    }

    private void editTask() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("type", item_type);
        intent.putExtra("task", item_task);
        intent.putExtra("money", item_money);
        startActivityForResult(intent, EDIT_REQUEST);
    }

    public void onAdd(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, ADD_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_REQUEST) {
            if (resultCode == RESULT_OK) {

                String type = data.getStringExtra("type");
                String task = data.getStringExtra("task");
                int amount = Integer.parseInt(data.getStringExtra("amount"));

                addtoDB(type, task, amount);
                showList();
            }
        }
        if (requestCode == EDIT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String delete = data.getStringExtra("delete");
                if(delete.equals("1")){
                    Delete();
                    showList();
                }else {
                    String type = data.getStringExtra("type");
                    String task = data.getStringExtra("task");
                    int amount = Integer.parseInt(data.getStringExtra("amount"));
                    int id = Integer.parseInt(item_id);

                    updateDB(type, task, amount, id);
                    showList();
                }
            }
        }
    }

    private void Delete() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                accountDB.getAccountDAO().Delete(Integer.parseInt(item_id));

                return null;
            }
        }.execute();
    }

    private void updateDB(final String type, final String task, final int amount, final int item_id) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                accountDB.getAccountDAO().Update(type, task, amount, item_id);

                return null;
            }
        }.execute();
    }

    public void addtoDB(final String type, final String task, final int amount) {
        new AsyncTask<Void, Void, AccountInfo>() {

            @Override
            protected AccountInfo doInBackground(Void... voids) {
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setType(type);
                accountInfo.setText(task);
                accountInfo.setMoney(amount);

                accountDB.getAccountDAO().insert(accountInfo);

                return null;
            }
        }.execute();
    }

    public void showList() {
        new AsyncTask<Void, Void, List<AccountInfo>>() {

            @Override
            protected List<AccountInfo> doInBackground(Void... voids) {
                List<AccountInfo> result = accountDB.getAccountDAO().findAll();
                infoList = result;

                return result;
            }

            @Override
            protected void onPostExecute(List<AccountInfo> accountInfos) {
                ArrayAdapter<AccountInfo> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, accountInfos);

                messageList = findViewById(R.id.AccountInfoList);
                messageList.setAdapter(adapter);
                if(adapter.getCount()!=0){
                    getIncome();
                }
            }
        }.execute();
    }

    public void getIncome(){
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    int countmoney = Integer.parseInt(accountDB.getAccountDAO().findIncome());
                    int countmoney2 = Integer.parseInt(accountDB.getAccountDAO().findPayment());

                    return (countmoney - countmoney2);
                }catch (Exception e){
                    int countmoney = Integer.parseInt(accountDB.getAccountDAO().findIncome());
                    int countmoney2 = 0;

                    return (countmoney - countmoney2);
                }
            }

            @Override
            protected void onPostExecute(Integer sum) {
                String sum2 = sum.toString();

                sum_amount = findViewById(R.id.sum_money);
                sum_amount.setText(sum2);
                getIncome2();
            }
        }.execute();
    }

    public void getIncome2(){
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                int countmoney = Integer.parseInt(accountDB.getAccountDAO().findIncome());

                return countmoney;
            }

            @Override
            protected void onPostExecute(Integer sum) {
                sum_amount = findViewById(R.id.sum_money);
                int sum1 = Integer.parseInt(sum_amount.getText().toString());
                changeColorMoney(sum1, sum);
                sum_amount.setText(changeColorMoney(sum1, sum));
            }
        }.execute();
    }

    private String changeColorMoney(int countmoney, int countmoney2) {
        int current_money = countmoney;
        int percent = (current_money*100/countmoney2);
        if(percent>50){
            sum_amount.setTextColor(Color.parseColor("#00B800"));
            return String.valueOf(current_money);
        }else if(percent>25){
            sum_amount.setTextColor(Color.parseColor("#ffdb00"));
            return String.valueOf(current_money);
        }else{
            sum_amount.setTextColor(Color.parseColor("#db1a1a"));
            return String.valueOf(current_money);
        }
    }
}
package com.example.j.datatest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bean.DBAdapter;
import bean.People;


public class InActivity extends AppCompatActivity {
    private DBAdapter dbadapter ;
    private EditText nameText;
    private EditText numText;
    private EditText chinText;
    private EditText mathText;
    private EditText engText;
    private EditText idEntry;


    private TextView labelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        nameText = (EditText)findViewById(R.id.editName);
        numText = (EditText)findViewById(R.id.editNum);
        chinText = (EditText)findViewById(R.id.editChin);
        mathText = (EditText)findViewById(R.id.editMath);
        engText = (EditText)findViewById(R.id.editEng);
        idEntry = (EditText)findViewById(R.id.editText);
        labelView = (TextView)findViewById(R.id.label);

        Button add = (Button)findViewById(R.id.but_add);
        Button res = (Button)findViewById(R.id.but_res);
        add.setOnClickListener(addButtonListener);
        res.setOnClickListener(resButtonListener);
        dbadapter = new DBAdapter(this);
        dbadapter.open();
    }

    OnClickListener addButtonListener = new OnClickListener(){

        @Override
        public void onClick(View v) {
            People people = new People();
            people.ID=Integer.parseInt(numText.getText().toString());
            people.Name = nameText.getText().toString();
            people.Num = Integer.parseInt(numText.getText().toString());
            people.Chin = Float.parseFloat(chinText.getText().toString());
            people.Math = Float.parseFloat(mathText.getText().toString());
            people.Eng = Float.parseFloat(engText.getText().toString());
            long colunm = dbadapter.insert(people);
            if (colunm == -1 ){
                labelView.setText("添加过程错误！");
            } else {
                labelView.setText("成功添加数据，ID："+String.valueOf(colunm));

            }
        }
    };
    OnClickListener resButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            People people = new People();
            people.ID=Integer.parseInt(numText.getText().toString());
            people.Name = nameText.getText().toString();
            people.Num = Integer.parseInt(numText.getText().toString());
            people.Chin = Float.parseFloat(chinText.getText().toString());
            people.Math = Float.parseFloat(mathText.getText().toString());
            people.Eng = Float.parseFloat(engText.getText().toString());
            long count = dbadapter.updateOneData(people.ID,people);
         //    dbadapter.insert(people);
            if (count == -1 ){
                labelView.setText("更新错误！");
            } else {
                labelView.setText("更新成功，更新数据"+String.valueOf(count)+"条");

            }
        }
    };


}

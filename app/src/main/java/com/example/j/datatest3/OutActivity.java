package com.example.j.datatest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import bean.DBAdapter;
import bean.People;

public class OutActivity extends AppCompatActivity {
    private DBAdapter dbadapter ;

    private EditText idEntry;

    private TextView labelView;
    private TextView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        idEntry = (EditText)findViewById(R.id.editText);

        labelView = (TextView)findViewById(R.id.display);
        displayView = (TextView)findViewById(R.id.display);
        Button butFind = (Button)findViewById(R.id.but_find);
        Button butDel = (Button)findViewById(R.id.but_del);
        Button butAll = (Button)findViewById(R.id.but_all);

        butFind.setOnClickListener(butFindButtonListener);
        butDel.setOnClickListener(butDelButtonListener);
        butAll.setOnClickListener(butAllButtonListener);

        dbadapter = new DBAdapter(this);
        dbadapter.open();
    }
    OnClickListener butFindButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = Integer.parseInt(idEntry.getText().toString());
            People[] peoples = dbadapter.queryOneDATA(id);

            if (peoples == null){
                labelView.setText("数据库中没有ID为"+String.valueOf(id)+"的数据");
                return;
            }
            labelView.setText("数据库：");
            displayView.setText(peoples[0].toString());
        }
    };

    OnClickListener butDelButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            long id = Integer.parseInt(idEntry.getText().toString());
            long result = dbadapter.deleteOneData(id);
            Log.e("xixi",result+"");
            String msg = "删除ID为"+idEntry.getText().toString()+"的数据" + (result>0?"成功":"失败");
            labelView.setText(msg);
        }
    };

    OnClickListener butAllButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            People[] peoples = dbadapter.queryAllDATA();
            if (peoples == null){
                labelView.setText("数据库中没有数据");
                return;
            }
            labelView.setText("数据库：");
            String msg = "";
            for (int i = 0 ; i<peoples.length; i++){
                msg += peoples[i].toString()+"\n";
            }
            displayView.setText(msg);
        }
    };
}

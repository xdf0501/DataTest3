package com.example.j.datatest3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import static com.example.j.datatest3.R.id.textView;
import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {
    private static final int InActivity = 1;
    private static final int OutActivity = 2;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         TextView textView = (TextView) findViewById(R.id.textView);
        final Button button = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InActivity.class);
                startActivityForResult(intent, InActivity);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OutActivity.class);
                startActivityForResult(intent, OutActivity);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case InActivity:
                if (resultCode == RESULT_OK) {
                    Uri uriData = data.getData();
                    textView.setText(uriData.toString());
                }
                break;
            case OutActivity:
                break;
        }
    }
}

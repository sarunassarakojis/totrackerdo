package com.sarunassarakojis.totrackerdo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

/**
 * Requirements for the second laboratory:
 *
 * 1) Fully completed GUI
 * 2) Required functionality implemented
 *
 * @author Sarunas Sarakojis
 */
// TODO finish GUI
// TODO implement users
// TODO retrieve data from database
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void viewIssues(View view) {
        startActivity(new Intent(this, ViewIssuesActivity.class));
    }
}

package edu.pdx.cs410J.nkhoi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text =(TextView) findViewById(R.id.textView);


        text.setText(Integer.toString(count) + "\t" + "haha");
        count=10;
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView text =(TextView) findViewById(R.id.textView);
        text.setText(Integer.toString(count));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        this.count++;
        outState.putInt("count",this.count);
        super.onSaveInstanceState(outState);
    }

    public void toastMe (View view){
        Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void countMe (View view){
        TextView showCount = (TextView) findViewById(R.id.textView);
        String countString = showCount.getText().toString();
        count = Integer.parseInt(countString);
        count++;
        showCount.setText(String.valueOf(Integer.toString(count)));
        //String.val

    }

    public void randomMe (View view){
        TextView showCount = (TextView) findViewById(R.id.textView);
        Random rand = new Random();
        int n = rand.nextInt(50);
        showCount.setText(Integer.toString(n));
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        //TextView text = findViewById(R.id.text);
        //text.setText(text.getText() + " in here " + myString);
    }
/*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean("MyBoolean", true);
        savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");

        super.onSaveInstanceState(savedInstanceState);
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        myString = savedInstanceState.getString("MyString");
    }*/
}

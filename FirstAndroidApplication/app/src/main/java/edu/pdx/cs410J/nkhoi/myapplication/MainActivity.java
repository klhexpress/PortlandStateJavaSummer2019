package edu.pdx.cs410J.nkhoi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String myString ="nothing here yet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);
        /*int count;
        if(savedInstanceState!= null){
            count = savedInstanceState.getInt("count" ) +1 ;
        }else{
            count =0;
        }*/
        //onSaveInstanceState(savedInstanceState);
        //text.setText(text.getText() + " " );
        //savedInstanceState.putInt("count",count);

    }

    public void toastMe (View view){
        Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void countMe (View view){
        TextView showCount = (TextView) findViewById(R.id.textView);
        String countString = showCount.getText().toString();
        Integer count = Integer.parseInt(countString);
        count++;
        showCount.setText(Integer.toString(count));
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

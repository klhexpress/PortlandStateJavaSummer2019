package edu.pdx.cs410j.nkhoi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import edu.pdx.cs410j.nkhoi.ui.main.Frag;
import edu.pdx.cs410j.nkhoi.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    static final Map<String, AppointmentBook> listofAppointmentBook = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        /*Frag temp = new Frag();
        Bundle extras = new Bundle();
        Bundle x = new Bundle();
        //x.putSerializable("list",listofAppointmentBook);
        temp.setArguments(extras);
*/





        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void setListofAppointmentBook(String owner, Appointment arg){
        if (listofAppointmentBook.containsKey(owner)) {
            getAppointmentBook(owner).addAppointment(arg);
        } else {
            AppointmentBook book = new AppointmentBook(owner);
            this.listofAppointmentBook.put(owner, book);
            book.addAppointment(arg);
        }
    }

    public AppointmentBook getAppointmentBook(String name) {
        return listofAppointmentBook.get(name);
    }
}

/*<com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@android:drawable/ic_dialog_email" />
        */
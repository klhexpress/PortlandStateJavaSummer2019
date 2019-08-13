package edu.pdx.cs410j.nkhoi;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import edu.pdx.cs410j.nkhoi.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private String[] name = null;
    static final Map<String, AppointmentBook> listofAppointmentBook = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readfile();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public void readfile() {
        try {
            File file = getFilesDir();
            TextParser namefile = new TextParser(file, "namedata.txt");
            name = namefile.parsefromnamefile();
            int total = name.length;
            AppointmentBook temp;
            TextParser temp2;

            for (int i = 0; i < total; i++) {
                //temp3 = new File(getFilesDir(),name[i]+".txt");
                temp2 = new TextParser(file, name[i] + ".txt");
                temp = temp2.parse();
                this.listofAppointmentBook.put(name[i], temp);
            }

        } catch (FileNotFoundException fnf) {

        } catch (ParseException p) {

        }

    }


    public void writetofile() {
        if (listofAppointmentBook.size() > 0) {
            TextDumper namefile = new TextDumper("namedata.txt");
            TextDumper[] appointmentbookfile = new TextDumper[100];
            File temp;

            int count = 0;
            String name[] = new String[listofAppointmentBook.size()];

            try {
                for (Map.Entry<String, AppointmentBook> entry : listofAppointmentBook.entrySet()) {
                    name[count] = entry.getKey();
                    appointmentbookfile[count] = new TextDumper(name[count] + ".txt");
                    if (entry.getValue() == null) {
                        Toast myToast = Toast.makeText(this, " ERROR SAVING DATA2", Toast.LENGTH_LONG);
                        myToast.show();
                        return;
                    }
                    temp = new File(getFilesDir(), name[count] + ".txt");
                    appointmentbookfile[count].dump(temp, getAppointmentBook(name[count]));
                    count++;
                }

                temp = new File(getFilesDir(), "namedata.txt");
                namefile.dumpnamefile(temp, count, name);

            } catch (IOException io) {
                Toast myToast = Toast.makeText(this, " ERROR SAVING DATA", Toast.LENGTH_LONG);
                myToast.show();
                return;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        writetofile();

    }


    public String[] getnamefromfile() {
        return name;
    }

    public void setListofAppointmentBook(String owner, Appointment arg) {
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

  /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

/*<com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@android:drawable/ic_dialog_email" />
        */
package edu.pdx.cs410j.nkhoi.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.ParseException;

import edu.pdx.cs410j.nkhoi.Appointment;
import edu.pdx.cs410j.nkhoi.MainActivity;
import edu.pdx.cs410j.nkhoi.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class README extends Fragment implements View.OnClickListener {

    private static final String README = "KHOI NGUYEN - PROJECT 5 - This project will take owner name, description, begin time, and ending time " +
            "to create an appointment. The program will add that appointment to the Appointment Book. Depends on whether the owner already appeared" +
            " in the app, the app will add the appointment or create a whole new appointment book. User can then display the appointment book based " +
            "on the owner name with option to display only appointments happen from a specific date to another specific date. The app will then save " +
            "all the data before it's closed and automatically load the data when it starts";

    private static final String ARG_SECTION_NUMBER = "section_number";

    //private PageViewModel pageViewModel;

    public static README newInstance(int index) {
        README fragment = new README();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.readme_main, container, false);
        Button exampleBtn = (Button) root.findViewById(R.id.displayreadme);
        exampleBtn.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.displayreadme:
                displayREADME(v);
                break;
        }
    }

    public void displayREADME(View v){
        TextView text = getView().findViewById(R.id.display);
        text.setText(README);
    }
}
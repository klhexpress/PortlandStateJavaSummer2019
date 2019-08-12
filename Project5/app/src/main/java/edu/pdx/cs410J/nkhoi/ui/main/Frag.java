package edu.pdx.cs410j.nkhoi.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.pdx.cs410j.nkhoi.Appointment;
import edu.pdx.cs410j.nkhoi.AppointmentBook;
import edu.pdx.cs410j.nkhoi.MainActivity;
import edu.pdx.cs410j.nkhoi.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Frag extends Fragment implements View.OnClickListener {


    private static final String ARG_SECTION_NUMBER = "section_number";

    //private PageViewModel pageViewModel;

    public static Frag newInstance(int index) {
        Frag fragment = new Frag();
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button exampleBtn = (Button) root.findViewById(R.id.add);
        exampleBtn.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addAppointment(v);
                break;
        }
    }

    private String[] splitString(String input) {
        String[] words = input.split(" ");
        return words;
    }

    public void addAppointment(View view) {

        EditText ownerfrombox = getView().findViewById(R.id.Owner);
        TextView descripfrombox = getView().findViewById(R.id.description);
        EditText beginfrombox = getView().findViewById(R.id.editText1);
        EditText endfrombox = getView().findViewById(R.id.editText2);

        String owner = ownerfrombox.getText().toString();
        String description = descripfrombox.getText().toString();
        String beginTime = beginfrombox.getText().toString();
        String endTime = endfrombox.getText().toString();


        if (TextUtils.isEmpty(beginTime) || TextUtils.isEmpty(endTime) || TextUtils.isEmpty(owner) || TextUtils.isEmpty(description)) {
            Toast myToast = Toast.makeText(getActivity(), "No field can empty", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }

        String[] begin = splitString(beginTime);
        String[] end = splitString(endTime);

        Appointment appt = new Appointment(description, begin[0], begin[1], begin[2], end[0], end[1], end[2]);

        ((MainActivity)getActivity()).setListofAppointmentBook(owner,appt);
        Frag2.getInstance().updatelistofname(owner);
        descripfrombox.setText(appt.toString());

    }
}
package edu.pdx.cs410j.nkhoi.ui.main;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.pdx.cs410j.nkhoi.MainActivity;
import edu.pdx.cs410j.nkhoi.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Frag2 extends Fragment implements View.OnClickListener {

    private static Frag2 instance = null;


    private static final String ARG_SECTION_NUMBER = "section_number";
    private Spinner dropdown;
    private ArrayList<String> listofname = new ArrayList<>();

    //private PageViewModel pageViewModel;

    public static Frag2 newInstance(int index) {
        Frag2 fragment = new Frag2();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        //pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        //pageViewModel.setIndex(index);
    }

    public static Frag2 getInstance() {
        return instance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment2_main, container, false);

        dropdown = root.findViewById(R.id.spinner1);
        String[] items = ((MainActivity) getActivity()).getnamefromfile() == null ? new String[]{"No owner yet"} : ((MainActivity) getActivity()).getnamefromfile();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button exampleBtn = (Button) root.findViewById(R.id.retrieve);
        exampleBtn.setOnClickListener(this);

        return root;
    }

    public void updatelistofname(String arg) {
        listofname.add(arg);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, listofname);
        dropdown.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrieve:
                retrieveAppointment(v);
                break;
        }
    }

    public void retrieveAppointment(View view) {

        Toast myToast;
        Date begindateobject = null;
        Date endingdateobject = null;

        EditText from = getView().findViewById(R.id.from);
        EditText until = getView().findViewById(R.id.until);

        String fromString = from.getText().toString();
        String untilString = until.getText().toString();

        TextView list = getView().findViewById(R.id.listofappointment);

        String ownername = dropdown.getSelectedItem().toString();

        if (TextUtils.isEmpty(ownername)) {
            myToast = Toast.makeText(getActivity(), "OWNER NAME CAN'T EMPTY", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }

        if (((MainActivity) getActivity()).getAppointmentBook(ownername) == null) {
            myToast = Toast.makeText(getActivity(), "NO APPOINTMENT", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }

        if (TextUtils.isEmpty(fromString) && TextUtils.isEmpty(untilString)) {
            list.setText(((MainActivity) getActivity()).getAppointmentBook(ownername).prettydisplay(0));
        } else {
            try {
                begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(fromString);
                endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(untilString);
                if (endingdateobject.compareTo(begindateobject) < 0) {
                    myToast = Toast.makeText(getActivity(), "ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE", Toast.LENGTH_LONG);
                    myToast.show();
                    return;
                }
                list.setText(((MainActivity) getActivity()).getAppointmentBook(ownername).prettydisplay(0, begindateobject, endingdateobject));
            } catch (ParseException pe) {
                myToast = Toast.makeText(getActivity(), "MALFORMATTED DATE/TIME", Toast.LENGTH_LONG);
                myToast.show();
                return;
            } catch (IOException io) {
                myToast = Toast.makeText(getActivity(), "CAN NOT PRINT " + ownername + "'S APPOINTMENT BOOK ", Toast.LENGTH_LONG);
                myToast.show();
                return;
            }

        }
    }
}


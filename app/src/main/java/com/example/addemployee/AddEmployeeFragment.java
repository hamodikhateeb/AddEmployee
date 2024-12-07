package com.example.addemployee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEmployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEmployeeFragment extends Fragment {

    private TextView tvEmployees ;
    private EditText etName , etDate , etEmail , etPhone , etCity , etId ;
    private Button btnAdd , btnRead ;
    private CheckBox chMale , chFemale ;
    private FirebaseServices fbs ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEmployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEmployeeFragment newInstance(String param1, String param2) {
        AddEmployeeFragment fragment = new AddEmployeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_employee, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents() ;
    }
//Hello
    private void connectComponents() {
        etId = getActivity().findViewById(R.id.etIdAddEmployee) ;
        etName = getActivity().findViewById(R.id.etNameAddEmployee) ;
        etCity = getActivity().findViewById(R.id.etCityAddEmployee) ;
        etDate = getActivity().findViewById(R.id.etDateAddEmployee) ;
        etEmail = getActivity().findViewById(R.id.etEmailAddEmployee) ;
        etPhone = getActivity().findViewById(R.id.etPhoneAddEmployee) ;
        chMale = getActivity().findViewById(R.id.chMaleAddEmployee) ;
        chFemale = getActivity().findViewById(R.id.chFemaleAddEmployee) ;
        fbs = FirebaseServices.getInstance() ;
        btnAdd = getActivity().findViewById(R.id.btnAddAddEmployee) ;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name , city , date , email , phone , id , gender = "";
                name = etName.getText().toString() ;
                id = etId.getText().toString() ;
                city = etCity.getText().toString() ;
                date = etDate.getText().toString() ;
                email = etEmail.getText().toString() ;
                phone = etPhone.getText().toString() ;
                if (chMale.isChecked()) gender = "Male" ;
                else if (chFemale.isChecked()) gender = "Female" ;
                if (name.trim().isEmpty() || city.trim().isEmpty() || date.trim().isEmpty() ||
                        email.trim().isEmpty() || phone.trim().isEmpty() || gender.trim().isEmpty() || id.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty!", Toast.LENGTH_SHORT).show();
                }

                Employee employee = new Employee(date, name, id, email, phone, gender) ;

                fbs.getFire().collection("Employees").add(employee).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "success!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }) ;
            }
        });
        tvEmployees = getActivity().findViewById(R.id.tvShowAddEmployee);
        tvEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotogotoAllEmployeesFragment();
            }
        });
    }

    private void gotogotoAllEmployeesFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new AllEmployeesFragment());
        ft.commit();
    }
}

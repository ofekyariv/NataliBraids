package com.example.natalibraids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class AppointmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private Spinner spinner;
    private Spinner spinner1;
    private Button btnBook;
    private String day = "";
    private String hour = "";
    private String name = "";
    private String email = "";

    ArrayAdapter<String> dataAdapter;
    private List<String> listOfItems;
    private FirebaseAuth firebaseAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        btnBook = findViewById(R.id.btnBook);

        loadSpinner();
        loadSpinner1();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                firebaseAuth= FirebaseAuth.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference("Users/"+ Objects.requireNonNull(firebaseAuth.getUid())+"/UserData");
                ref.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                            if(datas.getKey().equals("name"))
                                name = datas.getValue().toString();
                            if(datas.getKey().equals("email"))
                                email = datas.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AppointmentActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                openWhatsApp(name,email);
                finish();
            }
        });
    }

    private void openWhatsApp(String name,String email) {
        PackageManager packageManager = this.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        String phone = "+972549796297";
        String message = "שלום, אני אשמח לקבוע פגישה אצלכם!"+"\n"+
                "בתאריך: "+day+"\n"+
                "בשעה: "+hour+"\n"+
                "שמי: "+name+"\n"+
                "האימייל שלי הוא: "+email+"\n"+
                "תודה רבה!";
        try {
            String url = "https://api.whatsapp.com/send?phone=" + phone + "&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                this.startActivity(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSpinner() { //the hours in the week
        listOfItems = (Arrays.asList(getResources().getStringArray(R.array.hours_of_visits)));

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfItems);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void loadSpinner1() { //the days in the week
        listOfItems = (Arrays.asList(getResources().getStringArray(R.array.time_of_visits)));

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfItems);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(this);

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected= parent.getItemAtPosition(position).toString();
        //String checkView= view.toString();
        if (view == spinner)
            day = selected;
        if (view==spinner1)
            hour = selected;
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
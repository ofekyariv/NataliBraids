package com.example.natalibraids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btHairStyles;
    private Button btAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btHairStyles=findViewById(R.id.bt_hairStyles);
        btHairStyles.setOnClickListener(this);

        btAppointment=findViewById(R.id.bt_appointment);
        btAppointment.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==btHairStyles){
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (view==btAppointment){
            Intent intent= new Intent(this, AppointmentActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.share_item){
            Toast.makeText(this, "SHARE", Toast.LENGTH_SHORT).show();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I Love NataliBraids App!");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return true;
    }

}
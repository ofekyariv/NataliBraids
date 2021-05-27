package com.example.natalibraids;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ScatteredHairActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivS;
    private TextView tvS;
    private Button btMorePS;
    public Details[] scatteredHair = new Details[8];
    int photoNumS = 0;
    private ImageButton btnLike;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scattered_hair);
        initViews();
        initObjects();

    }

    private void initObjects() {
        Log.d("Natali", "initObjects");
        scatteredHair[0] = new Details("30₪", "20 minutes", "Two braids", 3);
        scatteredHair[1] = new Details("30₪", "15 minutes", "Headband Braid", 4);
        scatteredHair[2] = new Details("30₪", "20 minutes", "Top braids", 5);
        scatteredHair[3] = new Details("30₪", "15 minutes", "A side braid", 6);
        scatteredHair[4]= new Details("30₪", "15 minutes", "A side part headband braid", 7);
        scatteredHair[5]= new Details("30₪", "10 minutes", "A small side braid", 8);
        scatteredHair[6]= new Details("30₪", "25 minutes", "Two braids with buns",9);
        scatteredHair[7]= new Details("30₪", "15 minutes","A half part headband braid", 10 );
        }

    private void initViews() {
        ivS = findViewById(R.id.ivScattered);
        tvS = findViewById(R.id.etScattered);

        btMorePS = findViewById(R.id.btMoreS);
        btMorePS.setOnClickListener(this);

        btnLike = findViewById(R.id.btnLike);
        btnLike.setOnClickListener(this);
        SharedPreferences prefs = ScatteredHairActivity.this.getSharedPreferences("likes", 0);
        boolean gatheredLike = prefs.getBoolean("Scattered"+0, false);
        if(gatheredLike){
            btnLike.setImageResource(R.drawable.ic_heart_full);
        }
        btnLike.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences prefs = ScatteredHairActivity.this.getSharedPreferences("likes", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Scattered"+photoNumS, false);
                editor.apply();
                btnLike.setImageResource(R.drawable.ic_heart);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btMorePS) {
            if (photoNumS <= 1) {
                ivS.setImageResource(R.drawable.noabraids);
                tvS.setText("Two braids" + "\n" + scatteredHair[0].toString());
                photoNumS=2;
            }
            else if (photoNumS == 2) {
                ivS.setImageResource(R.drawable.headbandbraid);
                tvS.setText("Headband braid" + "\n" + scatteredHair[1].toString());
                photoNumS++;
            }
            else if (photoNumS == 3) {
                ivS.setImageResource(R.drawable.topbraids);
                tvS.setText("Top braids" + "\n" + scatteredHair[2].toString());
                photoNumS++;
            }
            else if (photoNumS == 4) {
                ivS.setImageResource(R.drawable.sidebraid);
                tvS.setText("A side braid" + "\n" + scatteredHair[3].toString());
                photoNumS++;
            }
            else if (photoNumS==5) {
                ivS.setImageResource(R.drawable.sidepartheadband);
                tvS.setText("A side part headband braid" + "\n" + scatteredHair[4].toString());
                photoNumS++;
            }
            else if (photoNumS==6) {
                ivS.setImageResource(R.drawable.smallsidebraid);
                tvS.setText("A small side braid" + "\n" + scatteredHair[5].toString());
                photoNumS++;
            }
            else if (photoNumS==7) {
                ivS.setImageResource(R.drawable.braidswithbuns);
                tvS.setText("Two braids with buns" + "\n" + scatteredHair[6].toString());
                photoNumS++;
            }
            else if (photoNumS==8) {
                ivS.setImageResource(R.drawable.halfpartheadbandbraid);
                tvS.setText("A half part headband braid" + "\n" + scatteredHair[7].toString());
                photoNumS=1;
            }
            SharedPreferences prefs = ScatteredHairActivity.this.getSharedPreferences("likes", 0);
            boolean gatheredLike = prefs.getBoolean("Scattered"+photoNumS, false);
            if(gatheredLike){
                btnLike.setImageResource(R.drawable.ic_heart_full);
            }
            else{
                btnLike.setImageResource(R.drawable.ic_heart);
            }
        }
        if (view == btnLike){
            //save like to database
            SharedPreferences prefs = ScatteredHairActivity.this.getSharedPreferences("likes", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Scattered"+photoNumS, true);
            editor.apply();
            btnLike.setImageResource(R.drawable.ic_heart_full);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Like this Hair Style?");
            alert.setMessage("Would you like to book an appointment?");
            alert.setCancelable(false);

            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                    Intent intent = new Intent(ScatteredHairActivity.this,AppointmentActivity.class);
                    startActivity(intent);
                }
            });

            alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.create();
            alert.show();
        }
    }
}










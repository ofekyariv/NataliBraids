package com.example.natalibraids;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HalfGSActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivH;
    private TextView tvH;
    private Button btMorePH;
    private Details[] halfGS = new Details[3];
    int photoNumH = 0;
    private ImageButton btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half_g_s);
        initViews();
        initObjects();
    }

    private void initObjects() {
        halfGS[0] = new Details("30₪", "20 minutes", "Half swedish braids", 11);
        halfGS[1] = new Details("30₪", "20 minutes", "Two connected braids", 12);
    }

    private void initViews() {
        ivH = findViewById(R.id.ivHalf);
        tvH = findViewById(R.id.etHalf);

        btMorePH = findViewById(R.id.btMoreH);
        btMorePH.setOnClickListener(this);

        btnLike = findViewById(R.id.btnLike);
        btnLike.setOnClickListener(this);
        SharedPreferences prefs = HalfGSActivity.this.getSharedPreferences("likes", 0);
        boolean gatheredLike = prefs.getBoolean("Half"+0, false);
        if(gatheredLike){
            btnLike.setImageResource(R.drawable.ic_heart_full);
        }
        btnLike.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences prefs = HalfGSActivity.this.getSharedPreferences("likes", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Half"+photoNumH, false);
                editor.apply();
                btnLike.setImageResource(R.drawable.ic_heart);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btMorePH) {
            if (photoNumH <=1) {
                ivH.setImageResource(R.drawable.halfswedenbraids);
                tvH.setText("Half swedish braids" + "\n" + halfGS[0].toString());
                photoNumH=2;

            } else if (photoNumH == 2) {
                ivH.setImageResource(R.drawable.twoconnectedbraids);
                tvH.setText("Two connected braids" + "\n" + halfGS[1].toString());
                photoNumH = 1;
            }
            SharedPreferences prefs = HalfGSActivity.this.getSharedPreferences("likes", 0);
            boolean gatheredLike = prefs.getBoolean("Half"+photoNumH, false);
            if(gatheredLike){
                btnLike.setImageResource(R.drawable.ic_heart_full);
            }
            else{
                btnLike.setImageResource(R.drawable.ic_heart);
            }
        }
        if (view == btnLike){
            //save like to database
            SharedPreferences prefs = HalfGSActivity.this.getSharedPreferences("likes", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Half"+photoNumH, true);
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
                    Intent intent = new Intent(HalfGSActivity.this,AppointmentActivity.class);
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
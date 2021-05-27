package com.example.natalibraids;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GatheredHairActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivG;
    private TextView tvG;
    private Button btMoreP;
    private Details[] gatheredHair = new Details[3];
    int photoNumG = 0;
    private ImageButton btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gathered_hair);
        initViews();
        initObjects();
    }

    private void initObjects() {
        gatheredHair[0] = new Details("30₪", "30 minutes", "Swedish Braids", 0);
        gatheredHair[1] = new Details("50₪", "one hour", "Six braids", 1);
        gatheredHair[2] = new Details("50₪", "one hour", "Four braids", 2);
    }

    private void initViews() {
        ivG = findViewById(R.id.ivGathered);
        tvG = findViewById(R.id.etGathered);

        btMoreP = findViewById(R.id.btMoreG);
        btMoreP.setOnClickListener(this);

        btnLike = findViewById(R.id.btnLike);
        btnLike.setOnClickListener(this);

        SharedPreferences prefs = GatheredHairActivity.this.getSharedPreferences("likes", 0);
        boolean gatheredLike = prefs.getBoolean("Gathered"+0, false);
        if(gatheredLike){
            btnLike.setImageResource(R.drawable.ic_heart_full);
        }

        btnLike.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences prefs = GatheredHairActivity.this.getSharedPreferences("likes", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Gathered"+photoNumG, false);
                editor.apply();
                btnLike.setImageResource(R.drawable.ic_heart);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btMoreP) {
            if (photoNumG <= 1) {
                ivG.setImageResource(R.drawable.swedenbraids);
                tvG.setText("Swedish Braids" + "\n" + gatheredHair[0].toString());
                photoNumG=2;

            } else if (photoNumG == 2) {
                ivG.setImageResource(R.drawable.sixbraids);
                tvG.setText("Six braids" + "\n" + gatheredHair[1].toString());
                photoNumG++;
            } else if (photoNumG == 3) {
                ivG.setImageResource(R.drawable.fourbraidso);
                tvG.setText("Four braids" + "\n" + gatheredHair[2].toString());
                photoNumG = 1;
            }
            SharedPreferences prefs = GatheredHairActivity.this.getSharedPreferences("likes", 0);
            boolean gatheredLike = prefs.getBoolean("Gathered"+photoNumG, false);
            if(gatheredLike){
                btnLike.setImageResource(R.drawable.ic_heart_full);
            }
            else{
                btnLike.setImageResource(R.drawable.ic_heart);
            }
        }
        if (view == btnLike){
            //save like to database
            SharedPreferences prefs = GatheredHairActivity.this.getSharedPreferences("likes", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Gathered"+photoNumG, true);
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
                    Intent intent = new Intent(GatheredHairActivity.this,AppointmentActivity.class);
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

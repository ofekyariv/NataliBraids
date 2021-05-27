package com.example.natalibraids;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<BraidsKinds> braidsKindsList;
    BraidsKindsAdapter braidsKindsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setlist();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setlist();
        stopAlert();
    }

    public void setlist(){
        Bitmap gathered = BitmapFactory.decodeResource(getResources(), R.drawable.logo1);
        Bitmap scattered = BitmapFactory.decodeResource(getResources(), R.drawable.logo3);
        Bitmap halfGS = BitmapFactory.decodeResource(getResources(), R.drawable.logo10);

        SharedPreferences prefs = MainActivity.this.getSharedPreferences("likes", 0);

        boolean gatheredLike = prefs.getBoolean("Gathered"+0, false);
        boolean scatteredLike = prefs.getBoolean("Scattered"+0, false);
        boolean halfLike = prefs.getBoolean("Half"+0, false);


        BraidsKinds t1 = new BraidsKinds(gatheredLike, "Gathered", "Braids with gathered hair", gathered);
        BraidsKinds t2 = new BraidsKinds(scatteredLike, "Scattered", "Braids with scattered hair", scattered);
        BraidsKinds t3 = new BraidsKinds(halfLike, "Half gathered half scattered", "Braids with half gathered hair amd half scattered hair", halfGS);

        //phase 2- add to array list
        braidsKindsList = new ArrayList<BraidsKinds>();
        braidsKindsList.add(t1);
        braidsKindsList.add(t2);
        braidsKindsList.add(t3);

        //phase 3- create adapter
        braidsKindsAdapter = new BraidsKindsAdapter(this, 0, 0, braidsKindsList);

        //phase 4- reference to listview
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(braidsKindsAdapter);
        lv.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: {
                startAlert();
                Intent intent = new Intent(this, GatheredHairActivity.class);
                startActivity(intent);
                break;
            }
            case 1: {
                startAlert();
                Intent intent = new Intent(this, ScatteredHairActivity.class);
                startActivity(intent);
                break;
            }
            case 2: {
                startAlert();
                Intent intent = new Intent(this, HalfGSActivity.class);
                startActivity(intent);
                break;
            }

        }
    }

    public void startAlert() {
        int sec = 10;
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (sec * 1000), pendingIntent);
    }
    public void stopAlert() {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);
    }
}
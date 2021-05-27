package com.example.natalibraids;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnReset;
    private String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail=findViewById(R.id.etEmail);
        btnReset=findViewById(R.id.btnReset);
        firebaseAuth= FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etEmail.getText().toString().trim();
                if (validate())
                {
                    try {
                        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Password reset email was sent", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password reset failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid/Empty Email", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public boolean validate()
    {
        if (email.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Email cant be empty", Toast.LENGTH_LONG).show();
            return false;
        }
       return true;
    }
}

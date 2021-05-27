package com.example.natalibraids;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView txtRegister,txtForgotPassword;
    private Button btnlogin;
    private EditText etEmail,etPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        txtRegister=(TextView)findViewById(R.id.txtRegister);
        txtForgotPassword=(TextView)findViewById(R.id.txtForgotPassword);
        firebaseAuth= FirebaseAuth.getInstance();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final String email=etEmail.getText().toString().trim();
                final String password=etPassword.getText().toString().trim();
                try {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                catch (IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(), "Illegal Arguments", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



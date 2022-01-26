package com.example.gchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;

public class LoguinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguin);

        EditText correoET = findViewById(R.id.editTextPhone);
        EditText passwordET = findViewById(R.id.editTextPassword);
        Button btnLoguin = findViewById(R.id.buttonInicioSesion);
        TextView register = findViewById(R.id.textViewRegistrate);

        Intent intent = new Intent(LoguinActivity.this, RegisterPage.class );

        btnLoguin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = correoET.getText().toString();
                String password = passwordET.getText().toString();
                textIsEmpty(correo, password);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    private void loguinProcess(String correo, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                  Intent intent = new Intent(LoguinActivity.this, HomeActivity.class);
                  intent.putExtra("correo", correo);
                  intent.putExtra("password", password);
                  startActivity(intent);
                  finish();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.error_inicio_sesion, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void textIsEmpty(String correo, String password) {
        if (!correo.isEmpty() && !password.isEmpty()){
            loguinProcess(correo,password);
        }else{
            Toast.makeText(getApplicationContext(), R.string.isEmptyEditText, Toast.LENGTH_LONG).show();
        }
    }
}
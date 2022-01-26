package com.example.gchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button register = findViewById(R.id.buttonRegistro);
        EditText correoET = findViewById(R.id.editTextPhoneRegister);
        EditText passwordET = findViewById(R.id.editTextPasswordRegsiter);
        EditText repeatPassET = findViewById(R.id.editTextPasswordRepeat);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = correoET.getText().toString();
                String password = passwordET.getText().toString();
                String repeat = repeatPassET.getText().toString();
                textIsEmpty(correo, password, repeat);
            }
        });

    }

    private void textIsEmpty(String correo, String password, String repeat) {
        if (!correo.isEmpty() && !password.isEmpty() && !repeat.isEmpty()){
            goodPassword(password, repeat, correo);
        }else{
            Toast.makeText(getApplicationContext(), R.string.isEmptyEditText, Toast.LENGTH_LONG).show();
        }
    }

    private void goodPassword(String password, String repeat, String correo) {
        if (!password.equals(repeat) || password.length() < 6){
            Toast.makeText(getApplicationContext(), R.string.password_requeriment, Toast.LENGTH_LONG).show();
        }else{
            registerUser(password,correo);
        }
    }

    private void registerUser(String password, String correo) {
        mAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Intent intent = new Intent(RegisterPage.this, HomeActivity.class);
                    intent.putExtra("correo", correo);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    finish();

//                    Map<String, Object> map = new HashMap<>();
//                    map.put("correo", correo);
//                    map.put("password", password);
//
//                    pushData(map, correo,password);
                }else{
                    Toast.makeText(getApplicationContext(), R.string.error_registro, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private void pushData(Map<String, Object> map, String correo, String password) {
//        String id = mAuth.getCurrentUser().getUid();
//        mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> taskTwo) {
//                if (taskTwo.isSuccessful()){
//                    Intent intent = new Intent(RegisterPage.this, HomeActivity.class);
//                    intent.putExtra("correo", correo);
//                    intent.putExtra("password", password);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(getApplicationContext(), R.string.error_push_data, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
}
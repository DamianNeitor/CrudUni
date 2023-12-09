package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.common.subtyping.qual.Bottom;

public class RegistrarEnElLogin extends AppCompatActivity {

    private EditText registroParaElUsuario;
    private EditText contraseniaParaElUsuario;
    private FirebaseAuth mAuth;
    //private Button btnRegistrar;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_en_el_login);

        mAuth = FirebaseAuth.getInstance();

        registroParaElUsuario = findViewById(R.id.txtRegistrarUser);
        contraseniaParaElUsuario = findViewById(R.id.txtPassword);
       // btnRegistrar = findViewById(R.id.btnRegistrar);

        //crear el evento de button
       /* btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar_usuario();
            }
        });*/
    }

    public void registrar_usuario(View view){
        String correo = registroParaElUsuario.getText().toString().trim();
        String crearContra = contraseniaParaElUsuario.getText().toString().trim();

        if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(crearContra)) {
            // Mostrar mensaje de error
            Toast.makeText(RegistrarEnElLogin.this, "Los campos están vacíos", Toast.LENGTH_SHORT).show();
            return; // Salir del método para evitar continuar con el registros
        }
        //pide contra y correo
        mAuth.createUserWithEmailAndPassword(correo,crearContra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrarEnElLogin.this, "agregado el correo y contraseña", Toast.LENGTH_SHORT).show();
                            //registro al usuario correctamente bien
                            Intent intent = new Intent(RegistrarEnElLogin.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(RegistrarEnElLogin.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
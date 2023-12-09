package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView login;
    TextView contrasenia;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.txtUser);
        contrasenia = findViewById(R.id.txtContrasenia);

    }

    public void logear(View view){
        String login_ = login.getText().toString();
        String contra = contrasenia.getText().toString();

       if(!login_.isEmpty() && !contra.isEmpty()){
           mAuth.signInWithEmailAndPassword(login_,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       registro();
                       String uid = task.getResult().getUser().getUid();
                       Toast.makeText(MainActivity.this, "UID: "+uid, Toast.LENGTH_SHORT).show();

                   }else{
                       Toast.makeText(MainActivity.this, "ESTE USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }else{
           Toast.makeText(this, "esta vacio", Toast.LENGTH_SHORT).show();
       }
    }

    public void registro(){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void registrase_email(View view){
         Intent intent = new Intent(this, RegistrarEnElLogin.class);
         startActivity(intent);
         //da error posiblemente se deba a que no es asincrono o se genero mal lo que seria el registro
        //de la clase arreglarlo yo del futuro
    }

}
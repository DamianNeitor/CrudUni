package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.ModeAction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditarUsuario extends AppCompatActivity {
/*Recordatorio modularizar

    private EditText id,rut;
    private TextView nombre,edad,oficio,salario;

    private FirebaseFirestore db;

    //private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAuth mAuth;
    private String userID ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        //iniacilizar firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //referencias de userID
        nombre = findViewById(R.id.txtNombreEditar);
        edad = findViewById(R.id.txtEdadEditar);
        oficio = findViewById(R.id.txtEdadEditar);
        salario = findViewById(R.id.txtCuantoGanaEdit);
        //obtenemos del usuario actual
        FirebaseUser currenUser = mAuth.getCurrentUser();

        if(currenUser != null){
            userID = currenUser.getUid();
        }
        //cargar datos de usuario
        cargarDatosUser();
        //configurarBotton

        Button btnGuardarCambios = findViewById(R.id.btnGuardarCambios);

        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCambiosDeUsuario();
            }
        });
    }

    public void cargarDatosUser() {
        nombre.setText(nombre.toString());
        edad.setText(edad.toString());
        oficio.setText(oficio.toString());
        salario.setText(salario.toString());
    }

    public void guardarCambiosDeUsuario(){
        //obtener los datos de editext
        String nuevoNombre = nombre.getText().toString();
        int nuevaEdad = Integer.parseInt(edad.getText().toString());
        String nuevoOficio = oficio.getText().toString();
        double nuevoSalario = Double.parseDouble(salario.getText().toString());

        //actualizar los datos en fireStore

        DocumentReference userRef = db.collection("usuarios").document(userID);

        Map<String,Object> nuevosDatos = new HashMap<>();

        nuevosDatos.put("nombre",nuevoNombre);
        nuevosDatos.put("edad",nuevaEdad);
        nuevosDatos.put("trabajo",nuevoOficio);
        nuevosDatos.put("salario",nuevoSalario);

        userRef.update(nuevosDatos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditarUsuario.this, "Cambios guardados con exito", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditarUsuario.this, "fallo al guardar los datos", Toast.LENGTH_SHORT).show();
                    }
                });


    }*/


}
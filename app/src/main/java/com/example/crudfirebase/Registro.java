package com.example.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.proto.TargetOuterClass;
import com.google.firebase.firestore.remote.FirestoreChannel;

public class Registro extends AppCompatActivity {

    private TextView nombre;
    private TextView edad;
    private TextView trabajo;
    private TextView salario;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.txtNombre);
        edad = findViewById(R.id.txtEdad);
        trabajo = findViewById(R.id.txtTrabajo);
        salario = findViewById(R.id.txtCuantoGana);

    }

    public void Registrar(View view) {
        String iNombre = nombre.getText().toString();
        String iEdad = edad.getText().toString();
        String iTrabajo = trabajo.getText().toString();
        String iSalario = salario.getText().toString();

        if(!iNombre.isEmpty() && !iEdad.isEmpty() && !iTrabajo.isEmpty() && !iSalario.isEmpty()){

        //mapeo de datos holi:V hgjhvhjv
        Map<String, Object> datos = new HashMap<>();
        //ingresar el dato
        datos.put("nombre", iNombre);
        datos.put("edad", iEdad);
        datos.put("trabajo", iTrabajo);
        datos.put("salario", iSalario);

        FirebaseFirestore.getInstance().collection("usuarios").add(datos)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                });
    }else{
        Toast.makeText(this, "debes completar todos los campos", Toast.LENGTH_SHORT).show();
    }
}

}



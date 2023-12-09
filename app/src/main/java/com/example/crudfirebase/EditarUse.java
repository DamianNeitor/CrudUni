package com.example.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditarUse extends AppCompatActivity {

    private TextView id,rut;
    private EditText nombre,edad,oficio,salario;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_use);
        rut = findViewById(R.id.lblRutEdit);
        id = findViewById(R.id.lblIdEdit);

        nombre = findViewById(R.id.txtNombreEditar);
        edad = findViewById(R.id.txtEdadEditar);
        oficio = findViewById(R.id.txtOficioEdit);
        salario = findViewById(R.id.txtCuantoGanaEdit);

        Intent intent = getIntent();
        String datoRecibido = intent.getStringExtra("identificador");
        id.setText(datoRecibido);
        nombre.setText(intent.getStringExtra("nombre"));
        rut.setText(intent.getStringExtra("rut"));
        edad.setText(intent.getStringExtra("edad"));
        oficio.setText(intent.getStringExtra("trabajo"));
        salario.setText(intent.getStringExtra("salario"));

    }
//imaginese que se an cambiado los datos :D
    public void editar(View view){
        Map<String, Object> creado = new HashMap<>();
        creado.put("edad",edad.getText().toString());
        creado.put("nombre",nombre.getText().toString());//edad nombre ,rut,salario,trabajo
        creado.put("salario",salario.getText().toString());
        creado.put("trabajo",oficio.getText().toString());

        db.collection("usuarios").document(id.getText().toString()).update(creado)
                .addOnSuccessListener(documentReference -> {

                    Toast.makeText(EditarUse.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditarUse.this , MostrarUsuarios.class);
                    startActivity(intent);

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(EditarUse.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                });
    }
}
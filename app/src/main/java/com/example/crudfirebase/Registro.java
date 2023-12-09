package com.example.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Registro extends AppCompatActivity {

    private TextView nombre;
    private TextView rut;
    private TextView edad;
    private TextView trabajo;
    private TextView salario;
   // private Boolean verdad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rut = findViewById(R.id.txtRut);
        nombre = findViewById(R.id.txtNombreEdit);
        edad = findViewById(R.id.txtEdad);
        trabajo = findViewById(R.id.txtTrabajo);
        salario = findViewById(R.id.txtCuantoGana);

    }

    public void registrar(View view) {
        String iRut = rut.getText().toString();
        String iNombre = nombre.getText().toString();
        String iEdad = edad.getText().toString();
        String iTrabajo = trabajo.getText().toString();
        String iSalario = salario.getText().toString();

        validarRut(iRut, existe -> {
            if (existe) {
                Toast.makeText(Registro.this, "Ya existe ese rut", Toast.LENGTH_LONG).show();
            } else {
                if (!iNombre.isEmpty() && !iEdad.isEmpty() && !iTrabajo.isEmpty() && !iSalario.isEmpty() && !iRut.isEmpty()) {
                    // Mapeo de datos
                    Map<String, Object> datos = new HashMap<>();
                    datos.put("rut", iRut);
                    datos.put("nombre", iNombre);
                    datos.put("edad", iEdad);
                    datos.put("trabajo", iTrabajo);
                    datos.put("salario", iSalario);

                    // Agregar usuarios a Firebase
                    FirebaseFirestore.getInstance().collection("usuarios").add(datos)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                                rut.setText("");
                                nombre.setText("");
                                edad.setText("");
                                trabajo.setText("");
                                salario.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            });
                } else {
                    Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validarRut(String dni, OnRutValidationListener listener) {
        FirebaseFirestore.getInstance().collection("usuarios").get().addOnCompleteListener(task -> {
            boolean encontrado = false;
            for (QueryDocumentSnapshot document : task.getResult()) {
                String rescatarElRut = document.getString("rut");
                if (rescatarElRut.equals(dni)) {
                    encontrado = true;
                    break;
                }
            }
            listener.onRutValidation(encontrado);
        });
    }

    public interface OnRutValidationListener {
        void onRutValidation(boolean existe);
    }

    public void mostrar(View view) {
        Intent intent = new Intent(this, MostrarUsuarios.class);
        startActivity(intent);
    }
}



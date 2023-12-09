package com.example.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostrarUsuarios extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> listaUs = new ArrayList<>();
    //private Map<String, Object> mapeado = new HashMap<>();
    private ListView listaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);
        listaUsuario = findViewById(R.id.listaUsuario);
        cargaUsuarios();
        registerForContextMenu(listaUsuario);
    }

    public void cargaUsuarios() {
        db.collection("usuarios").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String uid = document.getId();
                    String nombre = document.getString("nombre");
                    String rut_ = document.getString("rut");
                    String edad_ = document.getString("edad");
                    String trabajo_ = document.getString("trabajo");
                    String salario_ = document.getString("salario");
                    String datoRecuperado = "id: " + uid + ",\n\nnombre: " + nombre + ",\n\nrut: " + rut_ + ",\n\nedad: " + edad_ + ",\n\ntrabajo: " + trabajo_ + ",\n\nsalario " + salario_;
                    listaUs.add(datoRecuperado);
                }

            } else {
                Toast.makeText(this, "No hay datos registrados", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUs);
            listaUsuario.setAdapter(adapter);
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        registerForContextMenu(listaUsuario);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //posicion del objeto del listview
        int posicion = info.position;
        //Toast.makeText(this, ""+posicion, Toast.LENGTH_SHORT).show();
        // obtenemos el ID del documento de Firestore correspondiente a la posición en la lista
        String documentId = (listaUs.get(posicion).toString());

        //obtenido los datos de la posicion
        String[] arreglo = documentId.split(",");
        String[] datoID = arreglo[0].split(":");
        String datoNombre = arreglo[1].split(":")[1].toString().trim();
        String datoRut = arreglo[2].split(":")[1].toString().trim();
        String datoEdad = arreglo[3].split(":")[1].toString().trim();
        String datoTrabajo = arreglo[4].split(":")[1].toString().trim();
        String datoSalario = arreglo[5].split(" ")[1].toString().trim();

        //optiendo el rut de la posicion
        String rutIdentificador = datoID[1].toString().trim();

        switch (item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(this , EditarUse.class);

                intent.putExtra("identificador",rutIdentificador);
                intent.putExtra("nombre", datoNombre);
                intent.putExtra("rut", datoRut);
                intent.putExtra("edad", datoEdad);
                intent.putExtra("trabajo", datoTrabajo);
                intent.putExtra("salario", datoSalario);

                startActivity(intent);
                return true;

            case R.id.eliminar:
                //Eliminar usuario de Firestore
                db.collection("usuarios").document(rutIdentificador).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MostrarUsuarios.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                                listaUs.remove(posicion);
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(MostrarUsuarios.this, android.R.layout.simple_list_item_1, listaUs);
                                listaUsuario.setAdapter(adapter);
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(MostrarUsuarios.this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                        });
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}
/*package com.example.crudfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends ArrayAdapter <User>{

    public userAdapter(Context context, List<User>users){
        super(Context,0,users);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_mostrar_usuarios);
        }
        //obtener usuario
        User usuario = getItem(position);
        //config la vista con los datos del usuario
        TextView nombre = convertView.findViewById(R.id.txtNombreEditar);
        TextView edad = convertView.findViewById(R.id.txtEdadEditar);
        TextView oficio = convertView.findViewById(R.id.txtOficioEdit);
        TextView salario = convertView.findViewById(R.id.txtCuantoGanaEdit);

        nombre.setText(usuario.getNombre());
        edad.setText(usuario.getEdad());
        oficio.setText(usuario.getOficio());
        salario.setText(usuario.getSalario());



        return super.getView(position, convertView, parent);
    }


}
*/
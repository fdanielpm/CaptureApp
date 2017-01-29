package com.fdanielpm.captureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFechaNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvFechaNacimiento = (TextView) findViewById( R.id.tvFechaNacimiento );
        tvNombre = (TextView) findViewById( R.id.tvNombre );
        tvTelefono = (TextView) findViewById( R.id.tvTelefono );
        tvEmail = (TextView) findViewById( R.id.tvEmail );
        tvDescripcion = (TextView) findViewById( R.id.tvDescripcion );

        buttonEdit = (Button) findViewById(R.id.btnEditar);


        if( getIntent() != null ) {

            Bundle parametros = getIntent().getExtras();
            String nombre =  getResources().getString(R.string.capture_nombre) +
                             parametros.getString( getResources().getString(R.string.pnombre) );
            String fechaNacimiento =  getResources().getString(R.string.capture_fecha_nacimiento) +
                                      parametros.getString( getResources().getString(R.string.pfechaNacimiento) );
            String telefono =  getResources().getString(R.string.capture_telefono) +
                               parametros.getString( getResources().getString(R.string.ptelefono) );
            String email =  getResources().getString(R.string.capture_email) +
                            parametros.getString( getResources().getString(R.string.pemail) );
            String descripcion =  getResources().getString(R.string.capture_descripcion) +
                                  parametros.getString( getResources().getString(R.string.pdescripcion) );


            tvNombre.setText( nombre );
            tvFechaNacimiento.setText( fechaNacimiento );
            tvTelefono.setText(  telefono );
            tvEmail.setText(  email );
            tvDescripcion.setText(  descripcion );
        }

        buttonEdit.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){

                String nombre = tvNombre.getText().toString();
                String fechaNacimiento = tvFechaNacimiento.getText().toString();
                String telefono= tvTelefono.getText().toString();
                String email = tvEmail.getText().toString();
                String descripcion = tvDescripcion.getText().toString();

                nombre = nombre.substring( getResources().getString(R.string.capture_nombre).length() );
                fechaNacimiento = fechaNacimiento.substring( getResources().getString(R.string.capture_fecha_nacimiento).length() );
                telefono = telefono.substring( getResources().getString(R.string.capture_telefono).length()  );
                email = email.substring( getResources().getString(R.string.capture_email).length() );
                descripcion = descripcion .substring( getResources().getString(R.string.capture_descripcion).length() );

                Intent intent = new Intent(DetalleActivity.this,MainActivity.class);
                intent.putExtra( getResources().getString(R.string.pnombre), nombre );
                intent.putExtra( getResources().getString(R.string.pfechaNacimiento), fechaNacimiento );
                intent.putExtra( getResources().getString(R.string.ptelefono), telefono );
                intent.putExtra( getResources().getString(R.string.pemail), email );
                intent.putExtra( getResources().getString(R.string.pdescripcion), descripcion );
                startActivity( intent );

            }

        });

    }

}

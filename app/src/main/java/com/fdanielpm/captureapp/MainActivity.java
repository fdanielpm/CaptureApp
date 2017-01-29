package com.fdanielpm.captureapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txtieNombre;
    private TextInputEditText txtieTelefono;
    private TextInputEditText txtieEmail;
    private TextInputEditText txtieDescripcion;
    private DatePicker dpFechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();
        chkParams();
    }

    private void config(){

        txtieNombre = (TextInputEditText) findViewById(R.id.txtNombreCompleto);
        dpFechaNacimiento = (DatePicker) findViewById(R.id.dpFechaNacimiento);
        txtieTelefono = (TextInputEditText) findViewById(R.id.txtTelefono);
        txtieEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        txtieDescripcion = (TextInputEditText) findViewById(R.id.txtDescripcion);

        Button buttonNext = (Button) findViewById(R.id.btnNext);
        buttonNext.setOnClickListener( new View.OnClickListener(){
                public void onClick(View v){

                    //Toast.makeText(getBaseContext(), txtieNombre.getText().toString(), Toast.LENGTH_LONG ).show();
                    String nombre = txtieNombre.getText().toString();

                    int mes = dpFechaNacimiento.getMonth() +1;

                    String fechaNacimiento = new StringBuilder()
                                            .append( dpFechaNacimiento.getDayOfMonth() ).append("/")
                                            .append( mes < 10 ? "0" + mes : mes ).append("/")
                                            .append( dpFechaNacimiento.getYear() )
                                            .toString();

                    String telefono= txtieTelefono.getText().toString();
                    String email = txtieEmail.getText().toString();
                    String descripcion = txtieDescripcion.getText().toString();

                    Intent intent = new Intent(MainActivity.this,DetalleActivity.class);
                    intent.putExtra( getResources().getString(R.string.pnombre), nombre );
                    intent.putExtra( getResources().getString(R.string.pfechaNacimiento), fechaNacimiento );
                    intent.putExtra( getResources().getString(R.string.ptelefono), telefono );
                    intent.putExtra( getResources().getString(R.string.pemail), email );
                    intent.putExtra( getResources().getString(R.string.pdescripcion), descripcion );
                    startActivity( intent );

                }

        });
    }

    private void chkParams(){
        if( getIntent() != null &&  getIntent().getExtras() !=null ) {
            Bundle parametros = getIntent().getExtras();
            String nombre = parametros.getString( getResources().getString(R.string.pnombre) );
            txtieNombre.setText( nombre );

            String fechaNacimiento = parametros.getString( getResources().getString(R.string.pfechaNacimiento) );

            String telefono = parametros.getString( getResources().getString(R.string.ptelefono) );
            String email = parametros.getString( getResources().getString(R.string.pemail) );
            String descripcion = parametros.getString( getResources().getString(R.string.pdescripcion) );

            txtieTelefono.setText( telefono );
            txtieEmail.setText( email );
            txtieDescripcion.setText( descripcion );
            String [] fecha = fechaNacimiento.split("/");
            dpFechaNacimiento.updateDate(  Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[0]) ); // year, month, day);
        }
    }
}

package com.fiap.fiapnow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//TextView passageiros = null;
	double contador = 0;
	double msgContador = 0;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  passageiros = (TextView) findViewById(R.id.qtdPassageiros);
        
        Button btnSomar = (Button) findViewById(R.id.adicao);
        Button btnDiminuir = (Button) findViewById(R.id.subtrair);
        Button btnConfirmar = (Button) findViewById(R.id.confirmarPass);
        
        final TextView passageiros = ((TextView) findViewById(R.id.qtdPassageiros));
//        EditText qtdPassageiros = (EditText) findViewById(R.id.qtdPassageiros);
        
        btnSomar.setOnClickListener(new View.OnClickListener(){

        	@Override
        	public void onClick(View v) {
        		if(contador < 10){
        			contador++;
        			passageiros.setText(String.valueOf(contador));
        		}
        	}

        });	
        
        btnDiminuir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(contador>0){
        			contador--;
        			passageiros.setText(String.valueOf(contador));
        		}
				msgContador = contador;
        	}			
		});
        
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
			b.setTitle("Confirmar Passageiros");
			b.setMessage("Você deseja confirmar a seguinte quantidade" +
					"de passageiros" + msgContador);
			b.setPositiveButton("Sim", new DialogInterface.OnClickListener() { 
				
				@Override
				public void onClick(DialogInterface dialog, int id) { 
					Intent i = new Intent(MainActivity.this, FilaActivity.class);
					MainActivity.this.startActivity(i);
					}
				});	
			
			b.setNegativeButton("Não", new	DialogInterface.OnClickListener() {
					 
					@Override
					public void onClick(DialogInterface dialog, int id) {
	        			passageiros.setText(String.valueOf(0));
						}
					});
			b.show();
			}
		});
        
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

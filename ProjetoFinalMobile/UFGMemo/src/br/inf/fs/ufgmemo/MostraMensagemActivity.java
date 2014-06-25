package br.inf.fs.ufgmemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MostraMensagemActivity extends Activity {

	public static final String TAG = "MostraMensagemIntent";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostra_mensagem);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Definimos uma TextView para mostrar a mensagem na tela
		TextView texto = (TextView) findViewById(R.id.message);
		// Define como texto da TextView a mensagem recebida do GCM
		Intent it = getIntent();
		String mensagemRecebida = it.getStringExtra("msg");
		Log.i(TAG, "Extras: " + mensagemRecebida);
		texto.setText(mensagemRecebida);
		// Ajusta tamanho e cor da fonte
		texto.setTextSize(20.0F);
		texto.setTextColor(Color.BLACK);

		/*
		 * Para tornar as coisas mais simples, mostraremos apenas uma TextView
		 * na tela com o conte�do da mensagem recebida da Nuvem atrav�s do GCM.
		 */

	}

}
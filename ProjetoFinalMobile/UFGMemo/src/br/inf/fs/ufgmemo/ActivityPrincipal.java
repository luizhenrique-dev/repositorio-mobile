package br.inf.fs.ufgmemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {

	private Button botaoCadastrar;
	private Button botaoVisitante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial);

		botaoCadastrar = (Button) findViewById(R.id.btnCadastro);
		botaoVisitante = (Button) findViewById(R.id.btnVisitanteInicial);

		botaoCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),
						ActivityLogin.class));
			}
		});

		botaoVisitante.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), ActivityListar.class));
			}
		});
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		//Argumentos: grupo, id, ordem, texto a ser mostrado
//		MenuItem item = menu.add(0, MENU_CONFIG, 0, "Configuração");
//		item.setIcon(R.drawable.config);
//		
//		item = menu.add(0, MENU_CHECK_CONNECTION, 0, "Verificar Conexão");
//		return super.onCreateOptionsMenu(menu);
//	}
//	
//	@Override
//	public boolean onMenuItemSelected(int featureID, MenuItem menu){
//		if (menu.getItemId() == MENU_CONFIG){
//			startActivity(new Intent(getBaseContext(), ActivityConfig.class));
//		}
//		return true;
//	}
//	
////	private void checkConnection(){
////		ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
////		//Verifica conexão 3G
////		if (conn.getNetworkInfo(0).getState() == State.CONNECTED){
//////			imgConexao.setImageDrawable(R.drawable.tresg);
//////			imgConexao.setVisibility(1);
////			Toast.makeText(getBaseContext(), "Existe conexão 3G!", Toast.LENGTH_SHORT);
////		} //Verifica conexão WIFI
////		else if (conn.getNetworkInfo(1).getState() == State.CONNECTED){
////			Toast.makeText(getBaseContext(), "Existe conexão WIFI!", Toast.LENGTH_SHORT);
////		} else {
////			Toast.makeText(getBaseContext(), "Não existe conexão com a Internet!", Toast.LENGTH_SHORT);
////		}
////	}
	
}

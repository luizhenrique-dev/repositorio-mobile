package br.ufg.inf.es.memo;

import br.ufg.inf.es.ufgnotifyer.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final EditText login = (EditText) findViewById(R.id.entradaLogin);
		final EditText senha = (EditText) findViewById(R.id.entradaSenha);
		Button entrar = (Button) findViewById(R.id.btnEntrar);
		Button limpar = (Button) findViewById(R.id.btnLimpar);
		Button cadastrar = (Button) findViewById(R.id.btnCadastrar);

		entrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String strLogin = login.getText().toString();
				String strSenha = senha.getText().toString();

				if (validaLogin(strLogin, strSenha)) {
					Toast.makeText(LoginActivity.this,
							"Login realizado com sucesso!", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(LoginActivity.this,
							"Erro! Login e/ou Senha est�o incorretos",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		limpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login.setText("");
				senha.setText("");
				login.requestFocus();
			}
		});
	}

	// M�todo que ir� buscar as informa��es no banco de dados para validar o login
	private boolean validaLogin(String login, String senha) {
		Log.d("Teste", login);
		Log.d("Teste", senha);
		if (login.equals("LuizHenrique") && senha.equals("12345"))
			return true;
		else
			return false;

	}

}

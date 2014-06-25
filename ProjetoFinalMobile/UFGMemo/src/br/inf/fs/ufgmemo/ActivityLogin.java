package br.inf.fs.ufgmemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.inf.fs.ufgmemo.dao.ClienteDAO;
import br.inf.fs.ufgmemo.vo.ClienteVO;

public class ActivityLogin extends Activity {

	private Button btnLogar;
	private EditText campoLogin;
	private EditText campoSenha;
	private Button btnCadastrar;
	private ClienteDAO dao;
	ClienteVO vo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		dao = new ClienteDAO(getBaseContext());
		btnLogar = (Button) findViewById(R.id.btnLogar);
		btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

		campoLogin = (EditText) findViewById(R.id.campoLogin);
		campoSenha = (EditText) findViewById(R.id.campoSenhaLogin);

		btnLogar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					if (validaLogin(campoLogin.getText().toString(), campoSenha.getText().toString())) {
						Intent it = new Intent(getBaseContext(), ActivityListar.class);
						it.putExtra("idCliente", vo.getId());
						it.putExtra("remetentes", vo.getPossiveisRemetentes());
						startActivity(it);
					}
					else {
						Toast.makeText(
								getBaseContext(),
								"Matrícula ou senha incorretos!",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Toast.makeText(
							getBaseContext(),
							"A matrícula informada, não existe!",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		});

		btnCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),
						ActivityCadastro.class));
			}

		});
	}

	private boolean validaLogin(String login, String senha) throws Exception{
		vo = dao.getByMatricula(login);
		
		if (vo != null) {
			if (senha.equals(vo.getSenha())) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return false;
		}
	}

}

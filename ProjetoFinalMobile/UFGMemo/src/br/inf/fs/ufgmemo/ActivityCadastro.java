package br.inf.fs.ufgmemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.inf.fs.ufgmemo.dao.ClienteDAO;
import br.inf.fs.ufgmemo.vo.ClienteVO;

@SuppressLint("NewApi")
public class ActivityCadastro extends Activity implements Runnable {

	private EditText campoNome;
	private EditText campoEmail;
	private EditText campoMatricula;
	private EditText campoSenha;
	private EditText campoConfirmarSenha;
	private Button botaoCadastrar;
	private ArrayList<String> possiveisRemetentes;
	private ProgressDialog pg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);

		botaoCadastrar = (Button) findViewById(R.id.btnEfetivarCadastro);

		possiveisRemetentes = new ArrayList<String>();
		campoNome = (EditText) findViewById(R.id.campoNome);
		campoEmail = (EditText) findViewById(R.id.campoEmail);
		campoMatricula = (EditText) findViewById(R.id.campoMatricula);
		campoSenha = (EditText) findViewById(R.id.campoSenha);
		campoConfirmarSenha = (EditText) findViewById(R.id.campoConfSenha);

		criaAcaoBotaoCadastrar();

	}

	private void criaAcaoBotaoCadastrar() {
		botaoCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// verifica se todos os campos foram preenchidos
				if (validaCampos()) {

					if (campoSenha.getText().toString()
							.equals(campoConfirmarSenha.getText().toString())) {
						// verifica conexão com a internet
						if (checkConnection()) {
							pg = ProgressDialog.show(ActivityCadastro.this,
									"Aguarde",
									"Sincronizando informações do usuário.",
									true, false, null);
							Thread th = new Thread(ActivityCadastro.this);
							th.start();
						}
						// Não conseguiu conexão com o servidor então cria
						// remetentes para fins de teste
						possiveisRemetentes.add("UFG");
						possiveisRemetentes.add("Eng. de Software");
						possiveisRemetentes.add("Integração");

						ClienteVO vo = popularCliente();
						ClienteDAO dao = new ClienteDAO(getBaseContext());

						if (dao.insert(vo)) {
							Toast.makeText(getBaseContext(),
									"Cadastrado com sucesso!",
									Toast.LENGTH_SHORT).show();
							finish();
						}
					} else {
						Toast.makeText(
								getBaseContext(),
								"As senhas devem ser as mesmas em ambos os campos.",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(
							getBaseContext(),
							"Você deve preencher todos os campos para se cadastrar!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private ClienteVO popularCliente() {
		ClienteVO cliente = new ClienteVO();
		cliente.setNome(campoNome.getText().toString());
		cliente.setEmail(campoEmail.getText().toString());
		cliente.setMatricula(campoMatricula.getText().toString());
		cliente.setSenha(campoSenha.getText().toString());
		cliente.setPossiveisRemetentes(possiveisRemetentes);
		return cliente;
	}

	private boolean validaCampos() {
		if ((campoNome.getText().toString().equals("") || (campoNome.getText()
				.toString().length() < 1))) {
			return false;
		} else if ((campoEmail.getText().toString().equals("") || (campoEmail
				.getText().toString().length() < 1))) {
			return false;
		} else if ((campoMatricula.getText().toString().equals("") || (campoMatricula
				.getText().toString().length() < 1))) {
			return false;
		} else if ((campoSenha.getText().toString().equals("") || (campoSenha
				.getText().toString().length() < 1))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkConnection() {
		ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		if ((con.getNetworkInfo(0).getState() == State.CONNECTED)
				|| (con.getNetworkInfo(1).getState() == State.CONNECTED)) {
			return true;
		} else {
			return false;
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				pg.dismiss();
				Toast.makeText(
						getBaseContext(),
						"Sucesso ao sincronizar as informações com o servidor.",
						Toast.LENGTH_SHORT).show();
			} else if (msg.what == 1) {
				pg.dismiss();
				Toast.makeText(
						getBaseContext(),
						"Erro ao sincronizar as informações com o servidor. Tente novamente.",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	public void run() {
		// Valida dados com o servidor da UFG para buscar as
		// disciplinas e instituto vinculado a matrícula informada no cadastro
		StringBuilder strURL = new StringBuilder();
		strURL.append("http://urlDoServidorUFG?matricula=");
		strURL.append(campoMatricula.getText().toString());

		// Transforma String em URL
		try {
			URL url = new URL(strURL.toString());
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			InputStreamReader ipr = new InputStreamReader(http.getInputStream());
			BufferedReader bf = new BufferedReader(ipr);

			// verifica se obteve resposta de sucesso na requisição HTTP
			if (bf.readLine().equals("Y")) {
				possiveisRemetentes.clear();
				while (!bf.readLine().isEmpty()) {
					possiveisRemetentes.add(bf.readLine());
				}
				handler.sendEmptyMessage(0);
			}

		} catch (Exception e) {
			handler.sendEmptyMessage(1);
		}
	}
}

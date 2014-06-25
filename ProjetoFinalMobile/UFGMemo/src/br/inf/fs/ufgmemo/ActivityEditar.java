package br.inf.fs.ufgmemo;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.inf.fs.ufgmemo.dao.ClienteDAO;
import br.inf.fs.ufgmemo.vo.ClienteVO;

public class ActivityEditar extends Activity {

	private EditText campoNome;
	private EditText campoEmail;
	private EditText campoMatricula;
	private EditText campoSenha;
	private EditText campoConfirmarSenha;
	private Button botaoAtualizar;
	private Button botaoApagar;
	private Integer ID;
	private ClienteDAO dao;
	private ClienteVO vo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar);

		// Retorna o intent da listar que contém o parâmetro ID.
		Intent it = getIntent();
		ID = it.getIntExtra("id", 1);
		dao = new ClienteDAO(getBaseContext());
		vo = dao.getById(ID);
		Log.i("EDITAR", ID.toString());

		botaoAtualizar = (Button) findViewById(R.id.btnAtualizar);

		campoNome = (EditText) findViewById(R.id.campoNomeEdit);
		campoEmail = (EditText) findViewById(R.id.campoEmailEdit);
		campoMatricula = (EditText) findViewById(R.id.campoMatriculaEdit);
		campoSenha = (EditText) findViewById(R.id.campoSenhaEdit);
		campoConfirmarSenha = (EditText) findViewById(R.id.campoConfSenhaEdit);

		campoNome.setText(vo.getNome());
		campoEmail.setText(vo.getEmail());
		campoMatricula.setText(vo.getMatricula());
		campoSenha.setText(vo.getSenha());
		campoConfirmarSenha.setText(vo.getSenha());

		botaoAtualizar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ClienteVO vo = new ClienteVO();
				vo.setId(ID);
				vo.setNome(campoNome.getText().toString());
				vo.setEmail(campoEmail.getText().toString());
				vo.setMatricula(campoMatricula.getText().toString());
				vo.setSenha(campoSenha.getText().toString());

				if (dao.update(vo)) {
					Toast.makeText(getBaseContext(), "Atualizado com sucesso!",
							Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		});
	}

}

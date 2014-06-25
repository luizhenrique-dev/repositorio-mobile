package br.inf.fs.ufgmemo;

import java.util.List;

import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import br.inf.fs.ufgmemo.adapters.MensagemAdapter;
import br.inf.fs.ufgmemo.dao.MensagemDAO;
import br.inf.fs.ufgmemo.vo.MensagemVO;

public class ActivityListar extends ListActivity {

	private List<MensagemVO> lista = null;
	private static int MENU_APAGAR = 3;
	private static int MENU_PERFIL = 1;
	private static int MENU_CONFIG = 7;
	private static int MENU_FILTROS = 2;
	private static int RETORNO_MSG_NAO_LIDA = 4;
	private static int RETORNO_MAIOR_DATA = 5;
	private static int RETORNO_MENOR_DATA = 6;
	private static int RETORNO_REMETENTES = 8;
	
	// private static int MENU_EDITAR = 2;
	private int idItem = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerForContextMenu(this.getListView());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Informações para retornar o item do adapter
		MensagemVO vo = (MensagemVO) l.getAdapter().getItem(position);
		startActivity(new Intent(getBaseContext(), MostraMensagemActivity.class).putExtra("id", vo.getId()));
	}

	@Override
	// Importante para sempre manter a lista atualizada
	protected void onResume() {
		super.onResume();
		MensagemDAO dao = new MensagemDAO(getBaseContext());
		lista = dao.getAll();
		setListAdapter(new MensagemAdapter(getBaseContext(), lista));
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle(lista.get(info.position).getRemetente());
		menu.add(Menu.NONE, MENU_APAGAR, 0, "Apagar");
		// menu.add(Menu.NONE, MENU_EDITAR, 0, "Editar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		idItem = lista.get(info.position).getId();

		/*
		 * if (item.getItemId() == MENU_EDITAR) { Intent it = new
		 * Intent(getBaseContext(), ActivityEditar.class); it.putExtra("id",
		 * idItem); startActivity(it);
		 * 
		 * } else
		 */if (item.getItemId() == MENU_APAGAR) {
			Builder confirmacao = new Builder(ActivityListar.this);
			confirmacao.setMessage("Deseja realmente excluir a mensagem?");

			confirmacao.setPositiveButton("Sim",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							MensagemDAO dao = new MensagemDAO(getBaseContext());
							MensagemVO msg = dao.getById(idItem);

							if (dao.delete(msg)) {
								Toast.makeText(getBaseContext(),
										"Mensagem excluída com sucesso!",
										Toast.LENGTH_SHORT).show();
								setListAdapter(new MensagemAdapter(
										getBaseContext(), dao.getAll()));
							}
						}
					});

			confirmacao.setNegativeButton("Não", null);

			confirmacao.show();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Argumentos: grupo, id, ordem, texto a ser mostrado
		MenuItem item = menu.add(0, MENU_PERFIL, 0, "Editar perfil");
		item = menu.add(0, MENU_FILTROS, 0, "Filtros");
		item = menu.add(0, MENU_CONFIG, 0, "Configurações");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureID, MenuItem menu) {
		if (menu.getItemId() == MENU_PERFIL) {
			Intent it = getIntent();
			int ID = it.getIntExtra("idCliente", 1);
			
			Intent it2 = new Intent(getBaseContext(), ActivityEditar.class);
			it2.putExtra("id", ID);
			startActivity(it2);
		}
		
		else if (menu.getItemId() == MENU_FILTROS) {
			startActivityForResult(new Intent(getBaseContext(),
					Filtro.class), RETORNO_MSG_NAO_LIDA);
		}
		
		else if (menu.getItemId() == MENU_CONFIG) {
			startActivityForResult(new Intent(getBaseContext(),
					Configuracoes.class), RETORNO_REMETENTES);
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RETORNO_MAIOR_DATA == requestCode) {
			if (resultCode == RETORNO_MSG_NAO_LIDA) {
				mostrarMensagensNaoLidas();
			}
		}
		else if (RETORNO_MAIOR_DATA == requestCode) {
			if (resultCode == RESULT_OK) {
				ordenarPorDataMenor(lista);
			}
		}
		else {
			if (resultCode == RETORNO_MENOR_DATA) {
				ordenarPorDataMenor(lista);
			}
		}
	}

	private void ordenarPorDataMenor(List<MensagemVO> lista2) {
		// TODO Auto-generated method stub
		
	}

	private void mostrarMensagensNaoLidas() {
		// TODO Auto-generated method stub
		
	}

}

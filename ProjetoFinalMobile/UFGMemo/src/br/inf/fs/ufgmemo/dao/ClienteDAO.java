package br.inf.fs.ufgmemo.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.inf.fs.ufgmemo.DB;
import br.inf.fs.ufgmemo.vo.ClienteVO;

public class ClienteDAO {

	private static String table_name = "clientes";
	private static Context ctx;
	private String[] columns = { "id", "nome", "email", "matricula", "senha", "remetentes" };

	public ClienteDAO(Context ctx) {
		this.ctx = ctx;
	}

	public boolean insert(ClienteVO cliente) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		ContentValues ctv = new ContentValues();
		ctv.put("nome", cliente.getNome());
		ctv.put("email", cliente.getEmail());
		ctv.put("matricula", cliente.getMatricula());
		ctv.put("senha", cliente.getSenha());
		ctv.put("remetentes", cliente.getPossiveisRemetentes().toString());

		return (db.insert(table_name, null, ctv) > 0);
	}

	public boolean delete(ClienteVO cliente) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		return (db.delete(table_name, "id=?", new String[] { cliente.getId()
				.toString() }) > 0);
	}

	public boolean update(ClienteVO cliente) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		ContentValues ctv = new ContentValues();
		ctv.put("nome", cliente.getNome());
		ctv.put("email", cliente.getEmail());
		ctv.put("matricula", cliente.getMatricula());
		ctv.put("senha", cliente.getSenha());

		return (db.update(table_name, ctv, "id=?", new String[] { cliente
				.getId().toString() }) > 0);
	}

	public ClienteVO getById(Integer id) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();

		Cursor rs = db.query(table_name, columns, "id=?",
				new String[] { id.toString() }, null, null, null);
		ClienteVO vo = null;

		if (rs.moveToFirst()) {
			vo = new ClienteVO();
			vo.setId(rs.getInt(rs.getColumnIndex("id")));
			vo.setNome(rs.getString(rs.getColumnIndex("nome")));
			vo.setEmail(rs.getString(rs.getColumnIndex("email")));
			vo.setMatricula(rs.getString(rs.getColumnIndex("matricula")));
			vo.setSenha(rs.getString(rs.getColumnIndex("senha")));
		}
		return vo;
	}

	// public ClienteVO getById(Integer id) {
	// SQLiteDatabase db = new DB(ctx).getWritableDatabase();
	//
	// Cursor rs = db.query(table_name, columns, "id=?",
	// new String[] { id.toString() }, null, null, null);
	// ClienteVO vo = null;
	//
	// if (rs.moveToFirst()) {
	// vo = new ClienteVO();
	// vo.setId(rs.getInt(rs.getColumnIndex("id")));
	// vo.setNome(rs.getString(rs.getColumnIndex("nome")));
	// vo.setEmail(rs.getString(rs.getColumnIndex("email")));
	// vo.setMatricula(rs.getString(rs.getColumnIndex("matricula")));
	// vo.setSenha(rs.getString(rs.getColumnIndex("senha")));
	//
	// String[] remetentes = rs.getString(rs.getColumnIndex("remetentes"))
	// .split(",");
	// int qtdRemetentes = remetentes.length;
	// ArrayList<String> possiveisRemetentes = new ArrayList<String>();
	// for (int i = 0; i < qtdRemetentes; i++) {
	// possiveisRemetentes.add(remetentes[i]);
	// }
	//
	// vo.setPossiveisRemetentes(possiveisRemetentes);
	// }
	// return vo;
	// }

	public ClienteVO getByMatricula(String matricula) throws Exception {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		Cursor rs = db.query(table_name, columns, "matricula=?",
				new String[] { matricula }, null, null, null);
		ClienteVO vo = null;

		if (rs.moveToFirst()) {
			vo = new ClienteVO();
			vo.setId(rs.getInt(rs.getColumnIndex("id")));
			vo.setNome(rs.getString(rs.getColumnIndex("nome")));
			vo.setEmail(rs.getString(rs.getColumnIndex("email")));
			vo.setMatricula(rs.getString(rs.getColumnIndex("matricula")));
			vo.setSenha(rs.getString(rs.getColumnIndex("senha")));

			 String[] remetentes =
			 rs.getString(rs.getColumnIndex("remetentes"))
			 .split(",");
			 int qtdRemetentes = remetentes.length;
			 ArrayList<String> possiveisRemetentes = new ArrayList<String>();
			 for (int i = 0; i < qtdRemetentes; i++) {
			 possiveisRemetentes.add(remetentes[i]);
			 }
			
			 vo.setPossiveisRemetentes(possiveisRemetentes);
		}

		return vo;

	}

	// public List<ClienteVO> getAll() {
	// SQLiteDatabase db = new DB(ctx).getReadableDatabase();
	//
	// Cursor rs = db.rawQuery("SELECT * FROM clientes", null);
	//
	// List<ClienteVO> lista = new ArrayList<ClienteVO>();
	//
	// while (rs.moveToNext()) {
	// ClienteVO vo = new ClienteVO(rs.getInt(0), rs.getString(1),
	// rs.getString(2), rs.getString(3), rs.getString(4). rs.get);
	// lista.add(vo);
	// }
	//
	// return lista;
	// }
}

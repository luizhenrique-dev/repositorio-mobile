package br.inf.fs.ufgmemo.dao;

import java.util.ArrayList;
import java.util.List;

import br.inf.fs.ufgmemo.DB;
import br.inf.fs.ufgmemo.vo.MensagemVO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO {

	private static String table_name = "mensagens";
	private static Context ctx;
	private String[] columns = { "id", "remetente", "conteudo", "data"};

	public MensagemDAO(Context ctx) {
		this.ctx = ctx;
	}

	public boolean insert(MensagemVO mensagem) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		ContentValues ctv = new ContentValues();
		ctv.put("remetente", mensagem.getRemetente());
		ctv.put("conteudo", mensagem.getConteudo());
		ctv.put("data", mensagem.getData());

		return (db.insert(table_name, null, ctv) > 0);
	}

	public boolean delete(MensagemVO mensagem) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		return (db.delete(table_name, "id=?", new String[] { mensagem.getId()
				.toString() }) > 0);
	}

	public boolean update(MensagemVO mensagem) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();
		ContentValues ctv = new ContentValues();
		ctv.put("remetente", mensagem.getRemetente());
		ctv.put("conteudo", mensagem.getConteudo());
		ctv.put("data", mensagem.getData());
		
		return (db.update(table_name, ctv, "id=?", new String[] { mensagem
				.getId().toString() }) > 0);
	}

	public MensagemVO getById(Integer id) {
		SQLiteDatabase db = new DB(ctx).getWritableDatabase();

		Cursor rs = db.query(table_name, columns, "id=?",
				new String[] { id.toString() }, null, null, null);
		MensagemVO vo = null;

		if (rs.moveToFirst()) {
			vo = new MensagemVO();
			vo.setId(rs.getInt(rs.getColumnIndex("id")));
			vo.setRemetente((rs.getString(rs.getColumnIndex("remetente"))));
			vo.setData((rs.getLong(rs.getColumnIndex("data"))));
			vo.setConteudo((rs.getString(rs.getColumnIndex("conteudo"))));
		}
		return vo;
	}

	public List<MensagemVO> getAll() {
		SQLiteDatabase db = new DB(ctx).getReadableDatabase();

		Cursor rs = db.rawQuery("SELECT * FROM mensagens", null);

		List<MensagemVO> lista = new ArrayList<MensagemVO>();

		while (rs.moveToNext()) {
			MensagemVO vo = new MensagemVO(rs.getInt(0), rs.getString(1),
					rs.getString(2), rs.getLong(3));
			lista.add(vo);
		}
		return lista;
	}
}

package br.inf.fs.ufgmemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper{

	private static String dbName = "ufgmemo.db";
	private static String sqlCliente = "Create table [clientes] ( [id] INTEGER PRIMARY KEY AUTOINCREMENT, [nome] VARCHAR(255), [email] VARCHAR(40), [matricula] VARCHAR(10), [senha] VARCHAR(20), [remetentes] TEXT)";
	private static String sqlMsg = "Create table [mensagens] ( [id] INTEGER PRIMARY KEY AUTOINCREMENT, [remetente] VARCHAR(255), [conteudo] TEXT, [data] LONG)";
	private static int version = 2;
	public static final String NOME_TABELA = "clientes";
	public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	public DB(Context context) {
		super(context, dbName, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCliente);
		db.execSQL(sqlMsg);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SCRIPT_DELECAO_TABELA);
		db.execSQL(sqlCliente);
		db.execSQL(sqlMsg);
		
	}
}

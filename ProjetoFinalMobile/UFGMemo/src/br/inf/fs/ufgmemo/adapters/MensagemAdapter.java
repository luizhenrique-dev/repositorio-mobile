package br.inf.fs.ufgmemo.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.inf.fs.ufgmemo.vo.MensagemVO;

//Quando trabalha com listView, é útil para recuperação de dados
public class MensagemAdapter extends BaseAdapter{

	private Context ctx;
	private List<MensagemVO> lista;
	
	
	public MensagemAdapter(Context ctx, List<MensagemVO> lista) {
		this.ctx = ctx;
		this.lista = lista;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		MensagemVO vo = (MensagemVO) getItem(position);
		
		LayoutInflater layout = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = layout.inflate(br.inf.fs.ufgmemo.R.layout.activity_lista, null);
		
		TextView txvTitulo = (TextView) v.findViewById(br.inf.fs.ufgmemo.R.id.textoTitulo);
		txvTitulo.setText(vo.getRemetente());
		
		TextView txvMsg = (TextView) v.findViewById(br.inf.fs.ufgmemo.R.id.textoMensagem);
		txvMsg.setText(vo.getConteudo());
		
		TextView txvData = (TextView) v.findViewById(br.inf.fs.ufgmemo.R.id.textoData);
		txvMsg.setText(vo.getData(vo.getData()));
		
		return v;
	}


	
	
	
}

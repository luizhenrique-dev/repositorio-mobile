package br.inf.fs.ufgmemo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MensagemVO {

	private Integer id;
	private String remetente;
	private String conteudo;
	private Long data;
	private String dataFormatada;

	public MensagemVO() {

	}

	public MensagemVO(Integer id, String remetente, String conteudo, Long data) {
		super();
		this.id = id;
		this.remetente = remetente;
		this.conteudo = conteudo;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public static String getData(Long dataLong) {
		Date data = new Date(dataLong);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
				new Locale("pt", "BR"));
		return dateFormat.format(data);

	}
}

package br.inf.fs.ufgmemo;
//Final

//Classe que cont�m algumas constantes relacionadas ao projeto
public interface Config {
  
  // Servidor GCM usando Java
  // Pegar o ip do adaptaor de rede sem fio endere�o IPv4:
	static final String APP_SERVER_URL =
   "http://192.168.1.3:8080/ServidorDeAplicacaoGCM/NotificacaoGCM?shareRegId=1";
 
  // N�mero do Projeto Google, email: lhfses@gmail.com
  static final String GOOGLE_PROJECT_ID = "825461449027";
  static final String MESSAGE_KEY = "message";
 
}
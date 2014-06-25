package br.inf.fs.ufgmemo;

import java.util.Date;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import br.inf.fs.ufgmemo.dao.MensagemDAO;
import br.inf.fs.ufgmemo.vo.MensagemVO;

import com.google.android.gms.gcm.GoogleCloudMessaging;
//Final
public class GCMServicoNotificacaoIntent extends IntentService {

	//Em um caso real o ID modificaria de acordo com o remetente
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public GCMServicoNotificacaoIntent() {
		super("GcmServicoIntent");
	}

	public static final String TAG = "GCMServicoNotificacaoIntent";

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Erro ao enviar: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Mensagens deletadas no servidor: "
						+ extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {

//				for (int i = 0; i < 3; i++) {
//					Log.i(TAG,
//							"Funcionando... " + (i + 1) + "/5 @ "
//									+ SystemClock.elapsedRealtime());
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//					}
//
//				}
//				Log.i(TAG, "Trabalho completo @ " + SystemClock.elapsedRealtime());

				sendNotification("Nova notificação recebida : "
						+ extras.get(Config.MESSAGE_KEY));
				Log.i(TAG, "Received: " + extras.toString());
			}
		}
		WakefulBroadcastReceiver.completeWakefulIntent(intent);
	}

	//Método responsável por receber a mensagem e enviar para a aplicação
	private void sendNotification(String msg) {
		Log.d(TAG, "Preparando para enviar notificação...: " + msg);
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		msg = msg + new Date(System.currentTimeMillis());
//		Intent openNewActivityWindow = new Intent(this, MostraMensagemActivity.class);
	//	openNewActivityWindow.putExtra("mensagem_recebida", msg);
		Intent a = new Intent("MostraMensagem").putExtra("msg", msg);
		Log.i(TAG, "Mensagem:" + a.getExtras().toString());
		
		PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,
                a, PendingIntent.FLAG_UPDATE_CURRENT);
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.notificacao)
				.setContentTitle("Nova Notificação")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg)
				.setAutoCancel(true);
		
		MensagemVO mens = new MensagemVO();
		mens.setRemetente("UFG");
		mens.setConteudo(msg);
		mens.setData(System.currentTimeMillis());
		
		MensagemDAO mensDAO = new MensagemDAO(getBaseContext());
		mensDAO.insert(mens);
		
		mBuilder.setContentIntent(pendingIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		Log.d(TAG, "Notificação enviada com sucesso.");
	}
}
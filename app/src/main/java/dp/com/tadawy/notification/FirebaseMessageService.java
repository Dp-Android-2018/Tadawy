package dp.com.tadawy.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import dp.com.tadawy.R;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.SharedPrefrenceUtils;
import dp.com.tadawy.view.activity.MainActivity;


public class FirebaseMessageService extends FirebaseMessagingService {
    private SharedPrefrenceUtils pref;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        System.out.println("Notification Log Title:"+remoteMessage.getNotification().getTitle());
        System.out.println("Notification Log Body:"+remoteMessage.getNotification().getBody());
        System.out.println("Notification Log Data :"+remoteMessage.getData());
        if(remoteMessage.getNotification().getTitle().equals("Activated successfully")) {
            LoginResponseContent response=CustomUtils.getInstance().getSaveUserObject(this);
            response.setStatus(true);
            saveDataToPrefs(response);
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            Notify(i, remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    public void Notify(Intent intent, String messageTitle, String nb) {
        System.out.println("onNotify method : "+messageTitle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500, 500, 500, 500, 500};

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this,"androidChannel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(nb)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setLights(Color.BLUE, 1, 1)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(this);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME,data);
    }


}



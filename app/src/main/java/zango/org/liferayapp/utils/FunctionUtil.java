package zango.org.liferayapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import zango.org.liferayapp.R;

/**
 * Created by reda on 22/12/16.
 */

public class FunctionUtil {


    public static final int NOTIFICATION_ID = 2;


    public static void createGlobalNotification(Context context, String title, String description) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.zango_logo);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(uri)
                .setVibrate(new long[] { 2000, 1000, 2000, 1000 })
                .setSmallIcon(R.drawable.liferay_glyph);


        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}

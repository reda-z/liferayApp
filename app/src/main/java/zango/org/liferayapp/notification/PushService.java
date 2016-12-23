package zango.org.liferayapp.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.dlfileentry.DLFileEntryService;

import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.push.AbstractPushService;
import com.liferay.mobile.screens.util.LiferayLogger;

import org.json.JSONException;
import org.json.JSONObject;

import zango.org.liferayapp.BooksActivity;
import zango.org.liferayapp.utils.FunctionUtil;

/**
 * @author Javier Gamarra
 */
public class PushService extends AbstractPushService {

	public static final int NOTIFICATION_ID = 2;

	@Override
	protected void processJSONNotification(final JSONObject json) throws JSONException {
		FunctionUtil.createGlobalNotification(getApplicationContext(),"New message :",json.getString("body"));
	}
}

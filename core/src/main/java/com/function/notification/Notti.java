package com.function.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.function.notification.actions.NotificationAction;
import com.function.notification.config.LightSettings;
import com.function.notification.config.NottiConfig;
import com.function.notification.config.VibrationSettings;
import com.function.notification.notifications.CustomNotification;
import com.utils.util.AppUtils;

import java.util.HashMap;
import java.util.Map;

public class Notti {

    private final Context context;
    private final NottiConfig nottiConfig;
    private final NotificationManager notificationManager;
    private final Map<String, Integer> ids = new HashMap<>();
    private int currentID = 0;

    public Notti(Context context, NottiConfig nottiConfig) {
        this.context = context;
        this.nottiConfig = nottiConfig;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void show(CustomNotification customNotification) {
        int notificationId = 1;
        String channelName = AppUtils.getAppName();
        String channelId = "channel-loitp" + channelName;
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        setBuilderWithConfig(builder);
        customNotification.setBuilder(builder);
        setActionsForNotification(builder, customNotification);
        setContentIntent(customNotification, builder);
        setDiode(customNotification, builder);
        setVibrations(customNotification, builder);

        notificationManager.notify(getNotificationID(), builder.build());
    }

    private void setVibrations(CustomNotification customNotification, NotificationCompat.Builder builder) {
        VibrationSettings vibrationSettings = nottiConfig.getVibrationSettings();
        VibrationSettings customVibrationSettings = customNotification.getVibrationSettings();
        if (vibrationSettings != null && vibrationSettings.isVibrate()) {
            builder.setVibrate(vibrationSettings.getPattern());
        } else if (customVibrationSettings != null && customVibrationSettings.isVibrate()) {
            builder.setVibrate(customVibrationSettings.getPattern());
        }
    }

    private void setDiode(CustomNotification customNotification, NotificationCompat.Builder builder) {
        LightSettings lightSettings = nottiConfig.getLightSettings();
        LightSettings customLightSettings = customNotification.getLightSettings();
        if (lightSettings != null) {
            builder.setLights(lightSettings.getArgb(), lightSettings.getOnMs(),
                    lightSettings.getOffMs());
        } else if (customLightSettings != null) {
            builder.setLights(customLightSettings.getArgb(), customLightSettings.getOnMs(),
                    customLightSettings.getOffMs());
        }
    }

    private void setContentIntent(CustomNotification customNotification, NotificationCompat.Builder builder) {
        if (customNotification.getContentAction() != null) {
            builder.setContentIntent(customNotification.getContentAction().getPendingIntent());
        }
    }

    private int provideIcon(Integer icon) {
        if (null == icon) {
            return nottiConfig.getDefaultActionImage();
        }
        return icon;
    }

    private void setActionsForNotification(NotificationCompat.Builder builder, CustomNotification customNotification) {
        if (customNotification.getActions() != null) {
            for (NotificationAction notificationAction : customNotification.getActions()) {
                builder.addAction(provideIcon(notificationAction.getImage()),
                        notificationAction.getText(), notificationAction.getPendingIntent());
            }
        }
    }

    public int getNotificationID() {
        if (!nottiConfig.isSameID()) {
            return currentID++;
        }
        return 0;
    }

    public int getIDbyString(String string) {
        return ids.get(string);
    }

    public void putID(String string) {
        // TODO: 28.06.16 maybe take random and check if already exists?
        ids.put(string, getNotificationID());
    }

    public void setBuilderWithConfig(NotificationCompat.Builder builder) {
        builder.setSmallIcon(nottiConfig.getDefaultActionImage());
    }
}

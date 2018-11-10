package widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.raemacias.foodandfittracker.R;

import activities.WeighInActivity;

/**
 * Implementation of App Widget functionality.
 */
public class FitWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fit_widget);
//        views.setTextViewText(R.id.widget_button, widgetText);

        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);

        SharedPreferences myPrefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        String mWeight = myPrefs.getString(context.getString(R.string.appwidget_text), "Fit Widget \nTrack Weight Now");

        Intent intent = new Intent(context, WeighInActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fit_widget);
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

        views.setTextViewText(R.id.appwidget_text, mWeight);

        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);


        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // called when the user selects a recipe in the app, to update the widget accordingly
        ComponentName fitWidget = new ComponentName(context.getPackageName(), FitWidget.class.getName());
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(fitWidget);
        onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
        super.onReceive(context, intent);
    }
}


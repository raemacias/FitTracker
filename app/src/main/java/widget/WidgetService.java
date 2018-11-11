package widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class WidgetService extends IntentService {

    public static final String WEIGHT = "Weight";

    public WidgetService() {
        super("WidgetService");
    }

    public static void startWidgetService(Context context, ArrayList<String> currentWeight) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(WEIGHT, currentWeight);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> currentIngredients = intent.getExtras().getStringArrayList(WEIGHT);
            handleWidgetService(currentIngredients);

        }
    }

    private void handleWidgetService(ArrayList<String> currentWeight) {
    }
}


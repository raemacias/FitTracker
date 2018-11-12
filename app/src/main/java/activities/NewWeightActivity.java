package activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.raemacias.foodandfittracker.R;

import widget.FitWidget;

public class NewWeightActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.raemacias.foodandfittracker.REPLY";

    private EditText mEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_weight);
        mEditText = findViewById(R.id.edit_weight);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SharedPreferences myPrefs;

                //This came from the tutorial at https://appsandbiscuits.com/saving-data-with-sharedpreferences-android-9-9fecae19896a
                myPrefs = getSharedPreferences (getString(R.string.appwidget_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditText.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditText.getText().toString();
                    editor.putString(getString(R.string.widget_weight), word);
                    editor.apply();

                    //This code came from https://stackoverflow.com/questions/3455123/programmatically-update-widget-from-activity-service-receiver
                    Intent intent = new Intent(view.getContext(), FitWidget.class);
                    intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                    int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), FitWidget.class));
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                    sendBroadcast(intent);
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}


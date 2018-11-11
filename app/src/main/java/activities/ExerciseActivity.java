package activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.raemacias.foodandfittracker.R;

import java.util.ArrayList;
import java.util.List;

import models.getCaloriesBurnedForExercises.Exercise;
import models.getCaloriesBurnedForExercises.ExerciseRequest;
//import network.ApiUtils;
import network.TrackerInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//This was created from the tutorial at:
//https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener, Window.Callback {

    private TextView mResponseTv;
    private String TAG = "ExerciseActivity";

    private String query;
    private String gender;
    private Double weight_kg;
    private Double height_cm;
    private Double age;

    List<Exercise> mExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button submitBtn = findViewById(R.id.btn_submit);
        mResponseTv = findViewById(R.id.tv_response);

        final EditText workoutText = findViewById(R.id.et_query);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                query = workoutText.getText().toString();

                if(!TextUtils.isEmpty(query)) {
                    loadJSON();
                }
                Toast.makeText(ExerciseActivity.this, "Submitted.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadJSON() {
        EditText queryET = findViewById(R.id.et_query);
        String mQuery = queryET.getText().toString();
        mExercises = new ArrayList<>();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrackerInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackerInterface trackerInterface = retrofit.create(TrackerInterface.class);

        Call<Exercise> call = trackerInterface.getStringScalar(new ExerciseRequest(queryET.getText().toString()));

        call.enqueue(new Callback<Exercise>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<Exercise> call, @NonNull Response<Exercise> response) {
                if (response.message().contentEquals("OK")) {
                        showResponse(response.body().toString());


                response.body();
                int id = response.body().getTagId();
                String userInput = response.body().getUserInput();
//                float durationMin = response.body().getDurationMin();
//                float met = response.body().getMet();
//                float nf_calories = response.body().getNfCalories();
//                Photo photo = response.body().getPhoto();
                int compendium_code = response.body().getCompendiumCode();
                String name = response.body().getName();
                String description = response.body().getDescription();
                String benefits = response.body().getBenefits();

                    Log.i(TAG, "post submitted to API" + (response.body()).toString());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Exercise> call, @NonNull Throwable t) {
                Log.e(TAG, "Unable to submit post to api.");
            }
        });
    }
    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

    @Override
    public void onClick(View v) {
        String query = mResponseTv.getText().toString();
        Toast.makeText(ExerciseActivity.this, R.string.button_clicked, Toast.LENGTH_SHORT).show();

    }
}



package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.raemacias.foodandfittracker.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Implement butterknife here

        TextView exercise = findViewById(R.id.exercise);
        exercise.setOnClickListener(this);
        TextView weighin = findViewById(R.id.weighin);
        weighin.setOnClickListener(this);

//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        //To show banner ads
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        //This is the test ads id
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.exercise) {
            Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.weighin) {
            Intent in = new Intent(getApplicationContext(), WeighInActivity.class);
            startActivity(in);


        }
    }
}

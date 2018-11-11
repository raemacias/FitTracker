package activities;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.raemacias.foodandfittracker.R;

import java.util.List;

import adapters.WeightListAdapter;
import database.Weight;
import database.WeightViewModel;
import widget.FitWidget;

public class WeighInActivity extends AppCompatActivity {

    public static final int NEW_WEIGHT_ACTIVITY_REQUEST_CODE = 1;

    private WeightViewModel mWeightViewModel;
    String mWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weighin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WeightListAdapter adapter = new WeightListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWeightViewModel = ViewModelProviders.of(this).get(WeightViewModel.class);

        mWeightViewModel.getAllWeights().observe(this, new Observer<List<Weight>>() {
            @Override
            public void onChanged(@Nullable final List<Weight> weights) {
                //Update the chached copy of the weights in the adapter
                adapter.setWeights(weights);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeighInActivity.this, NewWeightActivity.class);
                startActivityForResult(intent, NEW_WEIGHT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WEIGHT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Weight weight = new Weight(data.getStringExtra(NewWeightActivity.EXTRA_REPLY));
            mWeightViewModel.insert(weight);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}

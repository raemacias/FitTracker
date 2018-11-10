package database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WeightViewModel extends AndroidViewModel {

    private WeightRepository mRepository;
    private LiveData<List<Weight>> mAllWeights;

    public WeightViewModel(Application application) {
        super(application);
        mRepository = new WeightRepository(application);
        mAllWeights = mRepository.getAllWeights();
    }

    //this getter method completely hides the implementation from the ui
    public LiveData<List<Weight>> getAllWeights() {
        return mAllWeights;
    }

    public void insert(Weight weight) {
        mRepository.insert(weight);
    }
}

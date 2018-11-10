package database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WeightRepository {

    private WeightDao mWeightDao;
    private LiveData<List<Weight>> mAllWeights;

    WeightRepository(Application application) {
        WeightRoomDatabase db = WeightRoomDatabase.getDatabase(application);
        mWeightDao = db.weightDao();
        mAllWeights = mWeightDao.getAllWeights();
    }
    LiveData<List<Weight>> getAllWeights() {
        return mAllWeights;
    }

    public void insert (Weight weight) {
        new insertAsyncTask(mWeightDao).execute(weight);
    }

    private static class insertAsyncTask extends AsyncTask<Weight, Void, Void> {

        private WeightDao mAsyncTaskDao;

        insertAsyncTask(WeightDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Weight... params) {

            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

package database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface WeightDao {

    @Insert
    void insert(Weight weight);

    @Query("DELETE FROM weight_table")
    void deleteAll();

    @Query("SELECT * from weight_table ORDER BY weight ASC")
    LiveData<List<Weight>> getAllWeights();


}






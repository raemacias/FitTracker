package database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "weight_table")
public class Weight {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "weight")
    private String mWeight;

    public Weight(@NonNull String weight) {
        this.mWeight = weight;
    }
    public String getWeight() {
        return this.mWeight;
    }
}

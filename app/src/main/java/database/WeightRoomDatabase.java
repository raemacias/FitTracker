package database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Weight.class}, version = 1)
public abstract class WeightRoomDatabase extends RoomDatabase {
    public abstract WeightDao weightDao();

    private static volatile WeightRoomDatabase INSTANCE;

    static WeightRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeightRoomDatabase.class) {
                //Create database here
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeightRoomDatabase.class, "weight_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }

        return INSTANCE;
    }

    //To delete all content and repopulate the database whenever the app is started
    //create a Callback
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WeightDao mDao;

        PopulateDbAsync(WeightRoomDatabase db) {
            mDao = db.weightDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Weight weight = new Weight("Good Luck!");
            mDao.insert(weight);
            weight = new Weight("Weigh-in Time!");
            mDao.insert(weight);
            return null;
        }
    }
}

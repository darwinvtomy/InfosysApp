package darwinvtomy.open.com.infosysassignment.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import darwinvtomy.open.com.infosysassignment.model.Canada;

@Database(entities = {Canada.RowsBean.class}, version = 2)
public abstract class CanadaRoomDatabase extends RoomDatabase {

    public abstract CanadaDao canadaDao();

    private static CanadaRoomDatabase INSTANCE;

    static CanadaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CanadaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CanadaRoomDatabase.class, "canada_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new CanadaRoomDatabase.PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more news, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CanadaDao mDao;

        PopulateDbAsync(CanadaRoomDatabase db) {
            mDao = db.canadaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

           /* Canada.RowsBean word = new Canada.RowsBean("Hello","The discription will be here","Image Link Here");
            mDao.insert(word);
            word = new Canada.RowsBean("Holla","The Second discription will be here","And the second Image Link Here");
            mDao.insert(word);*/
            return null;
        }
    }
}

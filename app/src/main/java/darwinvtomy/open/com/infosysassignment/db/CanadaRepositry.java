package darwinvtomy.open.com.infosysassignment.db;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import darwinvtomy.open.com.infosysassignment.model.Canada;

public class CanadaRepositry {

    private CanadaDao mCanadaDao;
    private LiveData<List<Canada.RowsBean>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    CanadaRepositry(Application application) {
        CanadaRoomDatabase db = CanadaRoomDatabase.getDatabase(application);
        mCanadaDao = db.canadaDao();
        mAllWords = mCanadaDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Canada.RowsBean>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (Canada.RowsBean news) {
        new CanadaRepositry.insertAsyncTask(mCanadaDao).execute(news);
    }

    private static class insertAsyncTask extends AsyncTask<Canada.RowsBean, Void, Void> {

        private CanadaDao mAsyncTaskDao;

        insertAsyncTask(CanadaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Canada.RowsBean... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

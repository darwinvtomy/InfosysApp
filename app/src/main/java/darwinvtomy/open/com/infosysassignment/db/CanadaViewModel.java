package darwinvtomy.open.com.infosysassignment.db;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import darwinvtomy.open.com.infosysassignment.model.Canada;

public class CanadaViewModel  extends AndroidViewModel {
    private CanadaRepositry mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Canada.RowsBean>> mAllnews;

    public CanadaViewModel (Application application) {
        super(application);
        mRepository = new CanadaRepositry(application);
        mAllnews = mRepository.getAllWords();
    }

    public LiveData<List<Canada.RowsBean>> getAllNews() { return mAllnews; }

    public void insert(Canada.RowsBean news) {
        mRepository.insert(news); }

}

package darwinvtomy.open.com.infosysassignment;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import darwinvtomy.open.com.infosysassignment.model.Canada;
import darwinvtomy.open.com.infosysassignment.restservice.Utils;
import darwinvtomy.open.com.infosysassignment.ui.main.CanadaFragment;

public class MainActivity extends AppCompatActivity implements CanadaFragment.OnListFragmentInteractionListener {

    private static final String COMMON_TAG = MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        if (Utils.isNetworkAvailable(getApplicationContext())) {
            if (savedInstanceState == null)
                launchTheFragment();
        }


    }

    private void launchTheFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CanadaFragment.newInstance(1))
                .commitNow();
    }

    @Override
    public void onListFragmentInteraction(Canada.RowsBean item) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(COMMON_TAG, "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(COMMON_TAG, "portrait");
        }

    }

}

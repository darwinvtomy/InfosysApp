package darwinvtomy.open.com.infosysassignment;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import darwinvtomy.open.com.infosysassignment.model.Canada;
import darwinvtomy.open.com.infosysassignment.restservice.Utils;
import darwinvtomy.open.com.infosysassignment.ui.main.CanadaFragment;

public class MainActivity extends AppCompatActivity implements CanadaFragment.OnListFragmentInteractionListener {

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


}

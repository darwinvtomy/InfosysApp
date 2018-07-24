package darwinvtomy.open.com.infosysassignment.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import darwinvtomy.open.com.infosysassignment.R;
import darwinvtomy.open.com.infosysassignment.db.CanadaViewModel;
import darwinvtomy.open.com.infosysassignment.model.Canada;
import darwinvtomy.open.com.infosysassignment.restservice.RestManager;
import androidx.lifecycle.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CanadaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MainViewModel mViewModel;
    SwipeRefreshLayout mSwipeRefreshLayout;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RestManager restManager;
    Canada canadaList;
    RecyclerView recyclerView;
    private static final String TAG = CanadaFragment.class.getSimpleName();
    // private WordViewModel mWordViewModel;
    private CanadaViewModel mCanadaViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CanadaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CanadaFragment newInstance(int columnCount) {
        CanadaFragment fragment = new CanadaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutcanada_list, container, false);

        mCanadaViewModel = ViewModelProviders.of(this).get(CanadaViewModel.class);

        mCanadaViewModel.getAllNews().observe(this, new Observer<List<Canada.RowsBean>>() {
            @Override
            public void onChanged(@Nullable final List<Canada.RowsBean> words) {
                // Update the cached copy of the words in the adapter.
                // adapter.setWords(words);

                for (Canada.RowsBean word :
                        words) {

                    Log.e(TAG, "onChanged: " + word.toString());

                }
            }
        });

        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                loadRecyclerViewData();
            }
        });


        return view;
    }

    private void loadRecyclerViewData() {

        mSwipeRefreshLayout.setRefreshing(true);
        Call<Canada> videolistfromApi = restManager.getApiService().gettheCanadalist();
        videolistfromApi.enqueue(new Callback<Canada>() {
            @Override
            public void onResponse(Call<Canada> call, Response<Canada> response) {
                if (response.isSuccessful()) {

                    canadaList = response.body();
                    getActivity().setTitle(canadaList.getTitle());
                    recyclerView.setAdapter(new MyAboutCanadaRecyclerViewAdapter(canadaList, mListener));
                    for (Canada.RowsBean word :
                            canadaList.getRows()) {

                        mCanadaViewModel.insert(word);
                        Log.e(TAG, "onChanged: " + word.toString());

                    }

                } else {

                    int sc = response.code();
                    switch (sc) {
                        case 400:
                            Log.e("Error 400", "Bad Request");
                            break;
                        case 404:
                            Log.e("Error 404", "Not Found");
                            break;
                        default:
                            Log.e("Error", "Generic Error");
                    }
                }

                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<Canada> call, Throwable t) {

                mSwipeRefreshLayout.setRefreshing(false);

            }
        });


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {

        loadRecyclerViewData();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Canada.RowsBean item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {

        super.onResume();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

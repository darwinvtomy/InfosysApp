package darwinvtomy.open.com.infosysassignment.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import darwinvtomy.open.com.infosysassignment.R;
import darwinvtomy.open.com.infosysassignment.model.Canada;
import darwinvtomy.open.com.infosysassignment.ui.main.CanadaFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Canada.RowsBean} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAboutCanadaRecyclerViewAdapter extends RecyclerView.Adapter<MyAboutCanadaRecyclerViewAdapter.ViewHolder> {

    private final Canada mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyAboutCanadaRecyclerViewAdapter(Canada items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_aboutcanada, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.getRows().get(position);
        holder.mContentTitle.setText(mValues.getRows().get(position).getTitle());
        holder.mContentDescription.setText(mValues.getRows().get(position).getDescription());
        Picasso.get()
                .load(mValues.getRows().get(position).getImageHref())
                .resize(100, 60)
                .centerCrop()
                .into(holder.mContentThumb);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.getRows().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentTitle;
        public final TextView mContentDescription;
        public final ImageView mContentThumb;
        public Canada.RowsBean mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentTitle = (TextView) view.findViewById(R.id.content_title);
            mContentDescription = (TextView) view.findViewById(R.id.content_description);
            mContentThumb = (ImageView) view.findViewById(R.id.content_thumbnail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentDescription.getText() + "'";
        }
    }
}

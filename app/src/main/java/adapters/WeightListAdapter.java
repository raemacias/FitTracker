package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raemacias.foodandfittracker.R;

import java.util.List;

import database.Weight;

public class WeightListAdapter extends RecyclerView.Adapter<WeightListAdapter.WeightViewHolder> {

    class WeightViewHolder extends RecyclerView.ViewHolder {
        private final TextView weightItemView;

        private WeightViewHolder (View itemView) {
            super(itemView);
            weightItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Weight> mWeights; // Cached copy of words

    public WeightListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WeightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WeightViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeightViewHolder holder, int position) {
        if (mWeights != null) {
            Weight current = mWeights.get(position);
            holder.weightItemView.setText(current.getWeight());
        } else {
            // Covers the case of data not being ready yet.
            holder.weightItemView.setText("No weight added.");
        }
    }

    public void setWeights(List<Weight> weights){
        mWeights = weights;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWeights != null)
            return mWeights.size();
        else return 0;
    }
}

package sayed.com.bakeryapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.details.DetailsContract;
import sayed.com.bakeryapp.listener.RecyclerViewItemClickListener;
import sayed.com.bakeryapp.listener.StepItemClickListener;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> implements StepItemClickListener {
    List<Step> steps;
    DetailsContract.Presenter presenter;

    public StepAdapter(List<Step> list, DetailsContract.Presenter presenter) {
        this.presenter = presenter;
        steps = list;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item_view, parent, false);
        return new StepViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.textView.setText(steps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps != null ? steps.size() : 0 ;
    }

    @Override
    public void onClick(int itemPosition) {
        presenter.onStepClicked(steps.get(itemPosition));
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public StepViewHolder(View itemView, StepItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.step_item_view);
            itemView.setOnClickListener(view -> listener.onClick(getAdapterPosition()));
        }


    }
}

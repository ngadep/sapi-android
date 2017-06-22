package com.ngadep.fatteningcattle.progresses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.Locale;

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    private final TextView mProgressDate;
    private final TextView mProgressWeight;
    private final TextView mProgressPrice;

    public ProgressViewHolder(View itemView) {
        super(itemView);
        mProgressDate = (TextView) itemView.findViewById(R.id.progress_date);
        mProgressWeight =(TextView) itemView.findViewById(R.id.progress_weight);
        mProgressPrice =(TextView) itemView.findViewById(R.id.progress_price);
    }

    public void bindToProgress(Progress model) {
        mProgressDate.setText(model.getFormatDate());
        mProgressWeight.setText(String.format(Locale.ENGLISH,"%d Kg", model.getWeight()));
        mProgressPrice.setText(String.format(Locale.ENGLISH, "Rp. %1$,.2f", model.getPrice()));
    }
}

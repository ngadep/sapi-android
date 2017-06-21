package com.ngadep.fatteningcattle.progresses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Progress;

import java.util.Locale;

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    private final TextView mCowDate;
    private final TextView mCowWeight;
    private final TextView mCowPrice;

    public ProgressViewHolder(View itemView) {
        super(itemView);
        mCowDate = (TextView) itemView.findViewById(R.id.progress_date);
        mCowWeight =(TextView) itemView.findViewById(R.id.progress_weight);
        mCowPrice =(TextView) itemView.findViewById(R.id.progress_price);
    }

    public void bindToProgress(Progress model) {
        mCowDate.setText(model.getFormatDate());
        mCowWeight.setText(String.format(Locale.ENGLISH,"%d Kg", model.getWeight()));
        mCowPrice.setText(String.format(Locale.ENGLISH, "Rp. %1$,.2f", model.getPrice()));
    }
}

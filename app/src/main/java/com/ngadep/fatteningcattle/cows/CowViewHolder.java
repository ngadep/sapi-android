package com.ngadep.fatteningcattle.cows;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Cow;

import java.util.Locale;

public class CowViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mCowImage;
    private final TextView mCowEarTag;
    private final ImageView mCowSex;
    private final TextView mCowDate;
    private final TextView mCowWeight;
    private final TextView mCowPrice;

    public CowViewHolder(View itemView) {
        super(itemView);

        mCowImage = (ImageView) itemView.findViewById(R.id.cow_image);
        mCowEarTag = (TextView) itemView.findViewById(R.id.cow_ear_tag);
        mCowSex = (ImageView) itemView.findViewById(R.id.cow_sex);
        mCowDate = (TextView) itemView.findViewById(R.id.cow_date);
        mCowWeight =(TextView) itemView.findViewById(R.id.cow_weight);
        mCowPrice =(TextView) itemView.findViewById(R.id.cow_price);
    }
    public void bindToCow(Cow model) {
        mCowImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
        mCowEarTag.setText(model.getEar_tag());
        if (model.getSex().equals("male")) {
            mCowSex.setImageResource(R.drawable.ic_sex_male_24dp);
        } else {
            mCowSex.setImageResource(R.drawable.ic_sex_female_24dp);
        }
        mCowDate.setText(model.getFormatDate());
        mCowWeight.setText(String.format(Locale.ENGLISH,"%d Kg", model.getWeight()));
        mCowPrice.setText(String.format(Locale.ENGLISH, "Rp. %1$,.2f", model.getPrice()));
    }
}

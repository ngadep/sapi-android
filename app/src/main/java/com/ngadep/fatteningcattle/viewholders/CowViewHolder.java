package com.ngadep.fatteningcattle.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Cow;

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
        mCowWeight =(TextView) itemView.findViewById(R.id.cow_wight);
        mCowPrice =(TextView) itemView.findViewById(R.id.cow_price);
    }
    public void bindToCow(Cow model) {
        mCowImage.setImageResource(R.drawable.ic_action_account_circle_40);
        mCowEarTag.setText(model.getEarTag());
        mCowSex.setImageResource(R.drawable.ic_toggle_star_24);
        mCowDate.setText(model.getDate());
        mCowWeight.setText(String.valueOf(model.getWeight()));
        mCowPrice.setText(String.valueOf(model.getPrice()));
    }
}

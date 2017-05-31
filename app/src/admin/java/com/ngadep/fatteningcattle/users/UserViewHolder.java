package com.ngadep.fatteningcattle.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;

class UserViewHolder extends RecyclerView.ViewHolder{

    private final TextView mUserName;
    private final TextView mEmail;

    public UserViewHolder(View itemView) {
        super(itemView);
        mUserName = (TextView) itemView.findViewById(R.id.user_name);
        mEmail = (TextView) itemView.findViewById(R.id.user_email);
    }

    public void bindToPackage(User model) {
        mUserName.setText(model.getUserName());
        mEmail.setText(model.getEmail());
    }
}

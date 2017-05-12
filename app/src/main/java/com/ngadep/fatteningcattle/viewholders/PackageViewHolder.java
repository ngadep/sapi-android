package com.ngadep.fatteningcattle.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.models.Package;

public class PackageViewHolder extends RecyclerView.ViewHolder {

    private final TextView mPackageName;
    private final TextView mPackageType;
    private final TextView mPackageLocation;
    private final ImageView mPackageActive;

    public PackageViewHolder(View itemView) {
        super(itemView);

        mPackageName = (TextView) itemView.findViewById(R.id.package_name);
        mPackageType = (TextView) itemView.findViewById(R.id.package_type);
        mPackageLocation = (TextView) itemView.findViewById(R.id.package_location);
        mPackageActive =(ImageView) itemView.findViewById(R.id.package_active);
    }

    public void bindToPackage(Package model) {
        mPackageName.setText(model.getName());
        mPackageType.setText(String.valueOf(model.getType()));
        mPackageLocation.setText(model.getLocation());
        // Determine if the current package is active
        if (model.isActive()) {
            mPackageActive.setImageResource(R.drawable.ic_toggle_star_24);
        } else {
            mPackageActive.setImageResource(R.drawable.ic_toggle_star_outline_24);
        }
    }
}

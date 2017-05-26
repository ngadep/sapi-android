package com.ngadep.fatteningcattle.packages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ngadep.fatteningcattle.R;
import com.ngadep.fatteningcattle.data.models.User;
import com.ngadep.fatteningcattle.utils.ActivityUtils;

public class PackageActivity extends AppCompatActivity {

    public static final String EXTRA_USER_MODEL = "extra user id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        // Get the requested user model
        User user = getIntent().getParcelableExtra(EXTRA_USER_MODEL);

        setTitle(user.getDisplayName());
        PackageFragment packageFragment = (PackageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (packageFragment == null) {
            // Create the fragment
            packageFragment = new PackageFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), packageFragment, R.id.content_frame);
        }

    }
}

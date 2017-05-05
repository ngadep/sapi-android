package com.ngadep.fatteningcattle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ngadep.fatteningcattle.models.Package;
import com.ngadep.fatteningcattle.models.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    TextView mText;
    Button mBtnSignOut, mBtnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.tx_hello);

        mBtnSignOut = (Button) findViewById(R.id.btn_sign_out);
        mBtnSignOut.setOnClickListener(this);

        mBtnRun = (Button) findViewById(R.id.btn_run);
        mBtnRun.setOnClickListener(this);

        mText.setText("Hallo " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign_out) {
            signOut();
        } else if (i== R.id.btn_run) {
            createPackage();
        }
    }

    private void createPackage() {
        // [START single_value_read]
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(MainActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPackage(userId);
                        }

                        // Finish this Activity, back to the stream
                        // finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        // setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void writeNewPackage(String userId) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("packages").push().getKey();
        Package aPackage = new Package("paket S001", "sumur", 5, true);
        Map<String, Object> postValues = aPackage.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/packages/" + key, postValues);
        childUpdates.put("/user-packages/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);

    }
}

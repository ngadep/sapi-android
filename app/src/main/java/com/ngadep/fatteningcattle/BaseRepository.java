package com.ngadep.fatteningcattle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class BaseRepository {

    protected final FirebaseDatabase mDatabase;
    private final FirebaseStorage mStorage;
    protected final FirebaseAuth mAuth;

    private DatabaseReference modelRef;
    private ValueEventListener modelValueEventListener;
    private final Map<DatabaseReference, ValueEventListener> mSettingQuery;

    protected BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mSettingQuery = new HashMap<>();
    }

    protected DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    protected StorageReference getStrorageRef() {
        return mStorage.getReference();
    }

    public void getSetting(String id, final SettingListener callback) {
        DatabaseReference settingRef = getRef().child("settings").child(id);
        ValueEventListener settingValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //noinspection unchecked
                callback.onValueChange(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        
        settingRef.addValueEventListener(settingValueEventListener);
        mSettingQuery.put(settingRef, settingValueEventListener);
    }

    protected void getModelFromId(DatabaseReference baseRef, final Class cl, final ModelListener callback) {
        modelRef = baseRef;
        modelValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //noinspection unchecked
                callback.onModelChange(dataSnapshot.getValue(cl));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        modelRef.addValueEventListener(modelValueEventListener);
    }

    public void cleanup() {
        if (modelValueEventListener != null) {
            modelRef.removeEventListener(modelValueEventListener);
        }

        for (Map.Entry<DatabaseReference, ValueEventListener> entry:mSettingQuery.entrySet() ) {
            if (entry.getValue() != null) {
                entry.getKey().removeEventListener(entry.getValue());
            }
        }
    }

    public String getUid() {
        String result = "";
        if (mAuth.getCurrentUser() != null) {
            result = mAuth.getCurrentUser().getUid();
        }
        return result;
    }

    public interface SettingListener<T> {
        void onValueChange(T value);
    }
    
    public interface ModelListener<T> {
        void onModelChange(T model);
    }
}

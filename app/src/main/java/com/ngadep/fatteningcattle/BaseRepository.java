package com.ngadep.fatteningcattle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class BaseRepository {

    protected final FirebaseDatabase mDatabase;
    protected final FirebaseAuth mAuth;

    private DatabaseReference modelRef;
    private ValueEventListener modelValueEventListener;
    
    private Map<DatabaseReference, ValueEventListener> mSettingQuery;

    protected BaseRepository() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mSettingQuery = new HashMap<>();
    }

    protected DatabaseReference getRef() {
        return mDatabase.getReference();
    }

    public void getSetting(String id, final SettingListener callback) {
        DatabaseReference settingRef = getRef().child("settings").child(id);
        ValueEventListener settingValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onValueChange(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        
        if (! mSettingQuery.containsKey(settingRef)) {            
            settingRef.addValueEventListener(settingValueEventListener);
            mSettingQuery.put(settingRef, settingValueEventListener);
        }
    }

    protected void getModelFromId(DatabaseReference baseRef, final ModelListener callback) {
        modelRef = baseRef;
        modelValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onModelChange(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        modelRef.addValueEventListener(modelValueEventListener);
    }

    public void cleanup() {
        modelRef.removeEventListener(modelValueEventListener);

        for (Map.Entry<DatabaseReference, ValueEventListener> entry:mSettingQuery.entrySet() ) {
            entry.getKey().removeEventListener(entry.getValue());
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

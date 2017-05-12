package com.ngadep.fatteningcattle.contracts;

import com.google.firebase.database.Query;

public interface CowContract {
    interface View {

        void getAllPackageCow(Query cows);
    }

    interface Repository {

        Query getPackageCowFromId(String packageId);
    }
}

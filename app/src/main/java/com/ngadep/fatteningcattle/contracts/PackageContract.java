package com.ngadep.fatteningcattle.contracts;

import com.google.firebase.database.Query;

public interface PackageContract {
    interface View{

        void getPackages(Query packages);

        void startCowActivity(String packageKey);
    }

    interface Repository {

        Query getPackagesFromCurrentUser();
    }
}

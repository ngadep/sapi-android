package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.Query;

public interface PackageContract {
    interface View{

        void getPackages(Query packages);

        void startCowActivity(String packageKey, String packageName);
    }

    interface Repository {

        Query getPackagesFromCurrentUser();
    }
}

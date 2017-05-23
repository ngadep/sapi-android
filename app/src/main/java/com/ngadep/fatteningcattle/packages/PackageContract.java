package com.ngadep.fatteningcattle.packages;

import com.google.firebase.database.Query;
import com.ngadep.fatteningcattle.data.models.Package;

interface PackageContract {
    interface View{

        void getPackages(Query packages);

        void startCowActivity(String packageKey, String packageName);
    }

    interface Repository {

        Query getPackagesFromCurrentUser();

        Query getPackagesFromUserId(String uid);

        void addNewPackage(Package pkg);

        void updatePackage(String packageId, Package pkg);
    }
}

package com.ngadep.fatteningcattle.packages.edit;

import com.ngadep.fatteningcattle.BasePresenter;
import com.ngadep.fatteningcattle.BaseView;

public interface EditPackageContract {
    interface Presenter extends BasePresenter {

        boolean isNewPackage();

        void populatePackage();

        void savePackage(String name, String location, int type, boolean active);

        void cleanup();
    }

    interface View extends BaseView<Presenter>{

        boolean isActive();

        void setName(String name);

        void setLocation(String location);

        void setType(int type);

        void setActive(boolean active);

        void showPackageList();
    }
}

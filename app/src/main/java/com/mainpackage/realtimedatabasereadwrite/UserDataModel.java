package com.mainpackage.realtimedatabasereadwrite;

public class UserDataModel {String name,email;

    // Default Constructor require for call to DataSnapShot.getValue(DataModel.class)
    public UserDataModel(){

    }
    public UserDataModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

}

package com.ar.yonseiunivview.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MenuViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public MenuViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Menu Fragment");
    }
    public LiveData<String> getText(){return mText;}
}

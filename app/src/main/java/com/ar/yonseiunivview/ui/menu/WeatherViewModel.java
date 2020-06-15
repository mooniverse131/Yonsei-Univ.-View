package com.ar.yonseiunivview.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class WeatherViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<String> CurPos;
    public WeatherViewModel(){

        mText = new MutableLiveData<>();
        mText.setValue("Weather Fragment");

    }
    public LiveData<String> getText(){return mText;}
}

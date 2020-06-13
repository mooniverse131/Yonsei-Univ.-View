package com.ar.yonseiunivview.ui.setting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public NotificationViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Noti fragment");
    }
    public LiveData<String> getText(){return mText;}
}

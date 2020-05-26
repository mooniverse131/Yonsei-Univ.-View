package com.ar.yonseiunivview.ui.arcamera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArcameraViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public ArcameraViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Arcamera fragment");
    }
    public LiveData<String> getText(){return mText;}
}

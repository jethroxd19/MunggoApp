package com.example.mungoapp.dashboard.ui.rfid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RfidViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RfidViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rfid fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
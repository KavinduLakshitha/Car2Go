package com.example.car2go.ui.rentCar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RentCarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RentCarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rent car fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
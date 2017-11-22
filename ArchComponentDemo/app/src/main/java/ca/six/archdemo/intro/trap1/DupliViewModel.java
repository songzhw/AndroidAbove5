package ca.six.archdemo.intro.trap1;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


public class DupliViewModel extends ViewModel {
    private MutableLiveData<String> message = new MutableLiveData<>();

    public void fetchMessage(){
        message.setValue("A New Value");
    }

    public LiveData<String> getMessage() {
        return message;
    }
}



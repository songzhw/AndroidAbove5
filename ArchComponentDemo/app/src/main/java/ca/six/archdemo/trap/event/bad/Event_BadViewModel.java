package ca.six.archdemo.trap.event.bad;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class Event_BadViewModel extends ViewModel {
    private MutableLiveData<String> message = new MutableLiveData<>();

    public void fetchMessage(){
        message.setValue("A New Value");
    }

    public LiveData<String> getMessage() {
        return message;
    }
}

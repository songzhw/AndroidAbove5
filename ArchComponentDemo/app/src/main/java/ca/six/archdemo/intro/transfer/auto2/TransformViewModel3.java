package ca.six.archdemo.intro.transfer.auto2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;


public class TransformViewModel3 extends ViewModel {

    private MutableLiveData<String> name = new MutableLiveData<>();

    private LiveData<String> penName;

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getPenName() {
        penName = Transformations.switchMap(name, str -> {
            MutableLiveData<String> ret = new MutableLiveData<>();
            String penNameString = "from " + str;
            ret.setValue(penNameString);
            System.out.println("szw getPenName(" + penNameString + ")");
            return ret;
        });
        return penName;
    }

    public void updateName(String newName) {
        name.setValue(newName);
    }


}

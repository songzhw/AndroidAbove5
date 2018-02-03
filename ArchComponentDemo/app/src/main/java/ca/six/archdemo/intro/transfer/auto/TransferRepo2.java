package ca.six.archdemo.intro.transfer.auto;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TransferRepo2 {

    MutableLiveData<List<String>> data = new MutableLiveData<>();

    public MutableLiveData<List<String>> getData() {
        return data;
    }

    public MutableLiveData<List<String>> getAllNames(){
        List<String> names = new ArrayList<>();
        names.add("songzhw");
        names.add("ddxx");
        names.add("test");

        data = new MutableLiveData<>();
        data.setValue(names);
        return data;
    }
}

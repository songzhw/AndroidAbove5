package ca.six.archdemo.intro.transfer;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;


public class TransformationDemoViewMode extends ViewModel {
    public void foo(){
        System.out.println("szw foo 1");
        Transformations.switchMap(getNames(), list -> bing(list) );
    }

    private LiveData<String> bing(List<String> list) {
        System.out.println("szw foo 2");
        MutableLiveData<String> mutable =  new MutableLiveData<>();
        mutable.setValue(list.get(0));
        return mutable;
    }

    public LiveData<List<String>> getNames(){
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        MutableLiveData<List<String>> mutable =  new MutableLiveData<>();
        mutable.setValue(names);
        return mutable;
    }

}

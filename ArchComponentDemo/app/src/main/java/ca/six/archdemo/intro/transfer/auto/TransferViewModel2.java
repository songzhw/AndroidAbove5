

package ca.six.archdemo.intro.transfer.auto;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.List;


public class TransferViewModel2 extends ViewModel {

    private final TransferRepo2 repo;
    private MutableLiveData<String> currentName = new MutableLiveData<>();
    private LiveData<User> user;
    private MutableLiveData<String> first = new MutableLiveData<>();

    public TransferViewModel2() {
        repo = new TransferRepo2();
    }

    public void onCreate() {
        repo.getAllNames();
    }

    public void onNamesFetched() {
        List<String> names = repo.getData().getValue();
        currentName.setValue(names.get(0));
    }


    public MutableLiveData<List<String>> getData() {
        return repo.getData();
    }

    public LiveData<User> getUser() {
        if (user == null) {
            user = Transformations.switchMap(currentName, name -> {
                first.setValue("fetched "+name);
                User user = new User(name);
                MutableLiveData<User> ret = new MutableLiveData<>();
                ret.setValue(user);
                return ret;
            });
        }

        return user;
    }

    public MutableLiveData<String> getFirst() {
        return first;
    }
}



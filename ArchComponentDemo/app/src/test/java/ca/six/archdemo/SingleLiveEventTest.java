package ca.six.archdemo;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.os.Looper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// LiveData在stop时不会通知observers, 所以和lifeCycle相关

public class SingleLiveEventTest {

    // Execute tasks synchronously (同步地)
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock LifecycleOwner owner;
    @Mock Observer<Integer> observer;
    private LifecycleRegistry lifecycleRegistry;
    private SingleLiveEvent<Integer> event;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        lifecycleRegistry = new LifecycleRegistry(owner);
        when(owner.getLifecycle()).thenReturn(lifecycleRegistry);

        event = new SingleLiveEvent<>();

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        event.observe(owner, observer);

    }

    @Test
    public void noValueSet_OnFirstOnResume(){
        verify(observer, never()).onChanged(anyInt());
    }

    @Test
    public void singleUpdate_onResumeAndDataSend(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        event.call();
        verify(observer).onChanged(null);
    }

    @Test
    public void noUpdate_onStopAndDataSet(){

    }

    @Test
    public void twoUpdates_noUpdateUntilActive(){

    }

    @Test
    public void noUpdates_whenConfigChanges(){

    }
}
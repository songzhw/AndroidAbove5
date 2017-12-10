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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// LiveData在stop时不会通知observers, 所以和lifeCycle相关

public class SingleLiveEventTest {

    // Execute tasks synchronously (同步地)
    @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock LifecycleOwner owner;
    @Mock Observer<Void> observer;
    private LifecycleRegistry lifecycleRegistry;
    private SingleLiveEvent<Void> event;

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
        verify(observer, never()).onChanged(null);
    }

    @Test
    public void singleUpdate_onResumeAndDataSend(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        event.call();
        verify(observer).onChanged(null);
    }

    @Test
    public void noUpdate_onStopAndDataSet(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        event.call();
        verify(observer, never()).onChanged(null);

    }

    @Test
    public void twoUpdates_onTwoCalls(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        event.call();
        event.call();
        verify(observer, times(2)).onChanged(null);
    }

    @Test
    public void twoUpdates_noUpdateUntilActive(){
        event.call();
        event.call();
        event.call();
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        verify(observer, times(1)).onChanged(null);
    }

    @Test
    public void noUpdates_whenConfigChanges(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        event.call();
        verify(observer).onChanged(null);
        reset(observer);

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        verify(observer, never()).onChanged(null);
    }
}
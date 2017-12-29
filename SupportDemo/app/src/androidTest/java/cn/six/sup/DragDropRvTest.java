package cn.six.sup;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cn.six.sup.rv.dragdrop.groups.DragRecyclerViewDemo2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DragDropRvTest {

    @Rule
    public ActivityTestRule<DragRecyclerViewDemo2> activityRule = new ActivityTestRule(DragRecyclerViewDemo2.class);

    @Test
    public void dragAndDrop() {
        onView(withText("Alibaba ; China"))
                .check(matches(isDisplayed()));
    }
}

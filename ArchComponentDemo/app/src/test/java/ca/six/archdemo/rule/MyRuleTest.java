package ca.six.archdemo.rule;

import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MyRuleTest {
    @Rule MyRule rule = new MyRule();

    @Test
    public void test1Plus1() {
        int ret = 1 + 1;
        assertEquals(2, ret);
    }

    @Test
    public void failedTest() {
        int ret = 1 + 1;
        assertEquals(3, ret);
    }
}

package ca.six.archdemo.rule;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class MyRule extends TestWatcher {
    @Override
    protected void starting(Description description) {
        super.starting(description);
        System.out.println("szw starting()");
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        System.out.println("szw finished()");
    }

    @Override
    protected void succeeded(Description description) {
        super.succeeded(description);
        System.out.println("szw succeeded()");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
        System.out.println("szw failed()");
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        super.skipped(e, description);
        System.out.println("szw skipped()");
    }


}

package ca.six.biz;

import ca.six.common.VersionHelper;

public class CustomVersion {
    public String getFormatVersion() {
        VersionHelper v = new VersionHelper();
        return "v" + v.getVersion();
    }
}

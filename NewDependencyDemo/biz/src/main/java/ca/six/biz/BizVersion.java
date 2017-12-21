package ca.six.biz;

import ca.six.common.CommonVersion;

public class BizVersion {
    public String getFormatVersion() {
        CommonVersion v = new CommonVersion();
        return "v" + v.getVersion();
    }
}

package cn.six.sup.rv.grid;

class WidthNumber implements IWidthType {
    private String num;

    public WidthNumber(String n) {
        num = n;
    }

    @Override
    public String value() {
        return num;
    }

    @Override
    public int teyp() {
        return TYPE_NUM;
    }
}

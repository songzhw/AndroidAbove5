package cn.six.sup.rv.sort;

public class TaskStatus {
    private static int idCounter = 0;
    final public int id;
    String text;
    boolean isDone = false;

    public TaskStatus(String text) {
        id = idCounter++;
        this.text = text;
    }
}
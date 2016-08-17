package cn.six.sup.rv.sort;

public class TaskStatus {
    String text;
    boolean isDone = false;

    final public int id;
    private static int idCounter = 0;

    public TaskStatus(String text) {
        id = idCounter++;
        this.text = text;
    }
}
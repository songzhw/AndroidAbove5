package cn.six.sup.g_component.livedata;

public class Worker {
    public int id;
    public String name;

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

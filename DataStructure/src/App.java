import org.ignite.iterables.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {

        LinkedList<Integer> list = new LinkedList<Integer>();

        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        for (int v : list) {
            System.out.println(v);
        }
    }
}

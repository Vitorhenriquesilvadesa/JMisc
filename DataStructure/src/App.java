import org.ignite.iterables.LinkedList;
import org.ignite.iterables.List;

public class App {
    public static void main(String[] args) throws Exception {

        List<Integer> list = new LinkedList<Integer>();

        list.add(20);
        list.add(30);
        list.add(40);
        list.add(0, 50);
        list.add(60);

        list.sort();

        for (int v : list) {
            System.out.println(v);
        }
    }
}

import org.vtko.datum.generics.TraverseType;
import org.vtko.datum.trees.BinaryTree;

import java.util.Random;

public class AppClass09 {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(100);
            tree.insert(value);
        }

        tree.balance();

        System.out.println(AppClass08.listToJSON(tree.traverse(TraverseType.IN_LEVEL)));

        for (int i = 0; i < 20; i++) {
            int value = random.nextInt(100);
            tree.insert(value);
        }

        tree.balance();
    }
}

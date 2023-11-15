import org.vtko.datum.generics.TraverseType;
import org.vtko.datum.trees.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> listToRemove = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int value = random.nextInt(100);
            tree.insert(value);

            if(i < 5){
                listToRemove.add(value);
            }
        }


        System.out.println("\n\nBEFORE REMOVE: ");

        System.out.print("IN-LEVEL:  ");
        System.out.println(listToJSON(tree.traverse(TraverseType.IN_LEVEL)));

        System.out.print("PRE-ORDER: ");
        System.out.println(listToJSON(tree.traverse(TraverseType.PRE_ORDER)));

        System.out.print("POS-ORDER: ");
        System.out.println(listToJSON(tree.traverse(TraverseType.POS_ORDER)));

        System.out.print("IN-ORDER:  ");
        System.out.println(listToJSON(tree.traverse(TraverseType.IN_ORDER)));


        for (Integer i : listToRemove) {
            tree.delete(i);
        }


        System.out.println("\nAFTER REMOVE: ");

        System.out.print("IN-LEVEL:  ");
        System.out.println(listToJSON(tree.traverse(TraverseType.IN_LEVEL)));

        System.out.print("PRE-ORDER: ");
        System.out.println(listToJSON(tree.traverse(TraverseType.PRE_ORDER)));

        System.out.print("POS-ORDER: ");
        System.out.println(listToJSON(tree.traverse(TraverseType.POS_ORDER)));

        System.out.print("IN-ORDER:  ");
        System.out.println(listToJSON(tree.traverse(TraverseType.IN_ORDER)));
    }

    public static String listToJSON(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (Integer i : list) {
            sb.append(i);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" }");
        return sb.toString();
    }
}
package com.swlo.tree;

import java.util.Random;

public class Test {

    private static Random random = new Random();

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();

        for (int i = 0; i < 20; i++) {
            tree.add(random.nextInt(0, 100));
        }

        System.out.println(tree.getOrdered(PrintEnum.POSTFIX));

    }

}
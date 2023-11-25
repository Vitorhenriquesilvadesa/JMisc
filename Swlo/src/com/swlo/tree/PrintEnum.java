package com.swlo.tree;

import java.util.ArrayList;
import java.util.List;

public enum PrintEnum {
    PREFIX("PREFIX", (node) -> {

        List<String> list = new ArrayList<>();
        printPrefix(node, list);

        return list;
    }),

    INFIX("INFIX", (node) -> {

        List<String> list = new ArrayList<>();
        printInfix(node, list);

        return list;

    }),
    POSTFIX("POSTFIX", (node) -> {

        List<String> list = new ArrayList<>();
        printPostfix(node, list);

        return list;


    }),

    LEVEL("LEVEL", (node) -> {

        var root = node;

        List<String> list = new ArrayList<>();

        var current = node;





        while (true) {


        }

        switch (current.hasChild()){

            case -1:
                break;
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;

        }




        return null;

    });


    private final String name;
    private final TreeFunction function;

    <T extends Comparable<T>> PrintEnum(String name, TreeFunction<T> function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public TreeFunction getFunction() {
        return function;
    }

    private static <T extends Comparable<T>> void printPrefix(TreeNode<T> node, List<String> list) {
        if (node == null) {
            return;
        }

        list.add(node.getElement().toString());
//        System.out.println(node.getElement());
        printPrefix(node.getLeftChild(), list);
        printPrefix(node.getRightChild(), list);
    }

    private static <T extends Comparable<T>> void printInfix(TreeNode<T> node, List<String> list) {
        if (node == null) {
            return;
        }
        printInfix(node.getLeftChild(), list);
        list.add(node.getElement().toString());
//        System.out.println(node.getElement());
        printInfix(node.getRightChild(), list);
    }

    private static <T extends Comparable<T>> void printPostfix(TreeNode<T> node, List<String> list) {
        if (node == null) {
            return;
        }
        printPostfix(node.getLeftChild(), list);
        printPostfix(node.getRightChild(), list);
//        System.out.println(node.getElement());
        list.add(node.getElement().toString());
    }

    private static  <T extends Comparable<T>> void printLeveled(TreeNode<T> node, List<String> list) {
        if (node == null) {
            return;
        }


    }

    interface TreeFunction<T extends Comparable<T>> {
        List<String> apply(TreeNode<T> tree);
    }
}
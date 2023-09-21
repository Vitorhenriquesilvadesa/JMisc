package org.vtko.parser;

import java.util.List;

public abstract class Component {
    private List<Component> children;
    private String value;

    public Component(String value, List<Component> children){
        this.value = value;
        this.children = children;
    }
}

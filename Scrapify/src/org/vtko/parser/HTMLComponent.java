package org.vtko.parser;

import java.util.List;

public abstract class HTMLComponent extends Component {

    public HTMLComponent(String value, List<Component> children){
        super(value, children);
    }
}

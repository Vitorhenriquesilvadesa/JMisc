package org.vtko.parser;

import java.util.List;

public class HTMLPage {

    private List<HTMLComponent> components;
    private Parser parser;

    public HTMLPage(String html){
        this.parser = new HTMLParser(html);
        this.components = parser.parseHtml();
    }

}

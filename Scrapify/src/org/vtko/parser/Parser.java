package org.vtko.parser;

import java.util.List;

public abstract class Parser {

    protected String content;
    protected String[] tokens;

    public Parser(String html){
        this.content = html;
    }

    public abstract List<Token> identifyTokens();

    public abstract boolean checkTokenValidate(String type, String expected);
    public abstract List<HTMLComponent> parseHtml();
}

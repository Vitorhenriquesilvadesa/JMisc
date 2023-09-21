package org.vtko.parser;

public class HTMLToken extends Token {

    public HTMLToken(String type, String value){
        super(type, value);
    }

    @Override
    public String toString(){
        return "{ " + this.getType() + ": " + this.getValue() + " }";
    }
}

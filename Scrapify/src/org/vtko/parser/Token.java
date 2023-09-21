package org.vtko.parser;

public abstract class Token {

    private String type;
    private String value;

    public Token(String type, String value) { this.type = type; this.value = value; }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public abstract String toString();
}

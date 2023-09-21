package org.vtko.parser;

import java.util.ArrayList;
import java.util.List;

public class HTMLParser extends Parser {

    private final String[] tags = new String[] { "aside" };
    public HTMLParser(String html){
        super(html);
    }

    @Override
    public List<Token> identifyTokens() {

        StringBuilder current = new StringBuilder();
        List<Token> tokens = new ArrayList<>();
        boolean startedSingleToken = false;

        int i = 0;

        while(i < this.content.length()) {

            String s = "" + this.content.charAt(i);

            if (s.equals("<")) {
                startedSingleToken = true;
            }


            if (startedSingleToken) {
                current.append(s);

                if (s.equals(">")) {

                    if(("" + current.toString().charAt(1)).equals("/")){
                        addToken(tokens, "CLOSING_TAG", current.toString());
                    } else {
                        addToken(tokens, "OPENING_TAG", current.toString());
                    }

                    startedSingleToken = false;

                    if(("" + this.content.charAt(i)).equals(">")) {
                        StringBuilder literalCheck = new StringBuilder();

                        String currentLiteralChar = "" + this.content.charAt(i);

                        while((i < this.content.length()) && !currentLiteralChar.equals("<") && !currentLiteralChar.equals("\0")){
                            currentLiteralChar = "" + this.content.charAt(i);
                            literalCheck.append(currentLiteralChar);

                            if(currentLiteralChar.equals("<")){
                                i--;
                                break;
                            }
                            i++;
                        }

                        if(!literalCheck.toString().equals("\n")) {
                            String literalResult = stripAngularParentheses(literalCheck);

                            if(!literalResult.isEmpty() && !literalResult.replaceAll("\t", "").replaceAll(" ", "").isEmpty()){
                                addToken(tokens, "LITERAL", "\"" + literalResult + "\"");
                            }
                        }

                        i-=2;
                    }

                    current.setLength(0);
                }
            }

            i++;
        }


        return tokens;
    }

    private static String stripAngularParentheses(StringBuilder literalCheck) {

        String literalResult = literalCheck.toString();
        String left = "" + literalResult.charAt(0);
        String right = "" + literalResult.charAt(literalResult.length() - 1);

        if(left.equals(">")) {
            literalResult = literalResult.substring(1);
        }

        if(right.equals("<")) {
            literalResult = literalResult.substring(0, literalResult.length() - 1);
        }

        return literalResult;
    }

    public void addToken(List<Token> source, String type, String value){
        HTMLToken token = new HTMLToken(type, value);
        source.add(token);
        System.out.println(token);
    }

    public boolean matchTokenWithTokenList(String[] tags, String token){

        for(String s : tags){
            if(token.equals(s)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkTokenValidate(String type, String expected){
        return type.equals(expected);
    }

    @Override
    public List<HTMLComponent> parseHtml() {

        List<HTMLComponent> components = new ArrayList<>();
        List<Token> tokens = identifyTokens();

       return new ArrayList<>();
    }
}

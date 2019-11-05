package com.juck.stack.infixexpresstion;

public class InfixExpression {
    public static void main(String[] args) {
        /**
         *  1+2*3/4-5
         *  1 2 3 * 4 / + 5 -
         */
        System.out.println(ExpressionUtil.infix2Suffix("1+2*3/4-5*5"));
    }
}

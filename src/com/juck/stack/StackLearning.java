package com.juck.stack;

import java.util.Arrays;
import java.util.Stack;

public class StackLearning {
    private static final String OPT_PLUS_AND_SUB = "+-";
    private static final String OPT_MULTI_AND_DIVISION = "*/";
    private static final String OPT_LEFT_PARENTHESIS = "(";
    private static final String OPT_RIGHT_PARENTHESIS = ")";

    public static void main(String[] args) {
        String expression = "1+(2-3)*5/6";

        expression = nifix2Suffix(expression);
        System.out.println(expression);
    }

    private static String nifix2Suffix(String expression) {
        StringBuilder sb = new StringBuilder();

        Stack<String> opt = new Stack<>();
        if (expression == null || expression.trim().length() < 1) {
            return sb.toString();
        }

        Arrays.asList(expression.split("")).forEach(e -> {
            if (isLeftParenthesis(e)) {
              opt.push(e);
            } else if (isPlusOrSub(e)) {
                if (opt.empty()) {
                    opt.push(e);
                } else {
                    String topOpt = opt.peek();
                    while(!opt.empty() && isPlusOrSub(topOpt)) {
                        topOpt = opt.pop();
                        sb.append(topOpt).append(" ");
                    }

                    opt.push(e);
                }
            } else if (isMultiOrDivision(e)) {
                if (opt.empty()) {
                    opt.push(e);
                } else {
                    String topOpt = opt.peek();
                    while(!opt.empty() && isMultiOrDivision(topOpt)) {
                        topOpt = opt.pop();
                        sb.append(topOpt).append(" ");
                    }

                    opt.push(e);
                }
            } else if (isRightParenthesis(e)) {
                String topOpt = opt.peek();
                while(!opt.empty()) {
                    topOpt = opt.pop();
                    if (!isLeftParenthesis(topOpt))
                        sb.append(topOpt).append(" ");
                }
            } else {
                sb.append(e).append(" ");
            }
        });

        while(!opt.empty()) {
            sb.append(opt.pop()).append(" ");
        }

        return sb.toString();
    }

    private static boolean isPlusOrSub(String opt) {
        return OPT_PLUS_AND_SUB.contains(opt);
    }

    private static boolean isMultiOrDivision(String opt) {
        return OPT_MULTI_AND_DIVISION.contains(opt);
    }

    private static boolean isLeftParenthesis(String opt) {
        return OPT_LEFT_PARENTHESIS.equals(opt);
    }

    private static boolean isRightParenthesis(String opt) {
        return OPT_RIGHT_PARENTHESIS.equals(opt);
    }

    enum Operation {
        CALC {
            @Override
            public void calc() {

            }
        },
        ;

        public abstract void calc();
    }
}

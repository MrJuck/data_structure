package com.juck.stack.infixexpresstion;

import java.util.Deque;

public enum Operation {
    OPT_NUMERIC {
        @Override
        public void calc(Deque<String> optStack, StringBuilder sb, String e) {
            Operation.append(sb, e);
        }
    },
    OPT_OPERATOR {
        @Override
        public void calc(Deque<String> optStack, StringBuilder sb, String e) {
            if (optStack.isEmpty()) {
                optStack.push(e);
                return ;
            }

            String topOpt = optStack.peek();
            if (Operation.isMultiOrDivision(e)) {
                while (!optStack.isEmpty() && Operation.isMultiOrDivision(topOpt)) {
                    Operation.append(sb, optStack.pop());
                    topOpt = optStack.peek();
                }
            } else if (Operation.isPlusOrSub(e)) {
                while (!optStack.isEmpty()) {
                    Operation.append(sb, optStack.pop());
                }
            }

            optStack.push(e);
        }
    },
    OPT_RIGHT_PARENTHESIS {
        @Override
        public void calc(Deque<String> optStack, StringBuilder sb, String e) {
            while (!optStack.isEmpty()) {
                String topOpt = optStack.pop();
                if (!Operation.isLeftParenthesis(topOpt)) {
                    Operation.append(sb, topOpt);
                }
            }
        }
    },
    OPT_LEFT_PARENTHESIS {
        @Override
        public void calc(Deque<String> optStack, StringBuilder sb, String e) {
            optStack.push(e);
        }
    },
    ;

    private static final String OPERATOR_PLUS_SUB = "+-";
    private static final String OPERATOR_MULTI_DIVISION = "*/";

    public abstract void calc(Deque<String> optStack, StringBuilder sb, String e);

    private static void append(StringBuilder sb, String e) {
        sb.append(e).append(' ');
    }

    private static boolean isPlusOrSub(String operator) {
        return OPERATOR_PLUS_SUB.contains(operator);
    }

    private static boolean isMultiOrDivision(String operator) {
        return OPERATOR_MULTI_DIVISION.contains(operator);
    }

    private static boolean isLeftParenthesis(String operator) {
        return "(".equals(operator);
    }
}

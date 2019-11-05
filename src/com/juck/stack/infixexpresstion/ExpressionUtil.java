package com.juck.stack.infixexpresstion;

import java.util.*;

public class ExpressionUtil {
    private ExpressionUtil(){}
    private static final String REGEX_NUMERIC = "\\d+";
    private static final String OPERATOR = "+-*/";

    private static final String OPERATION_CODE_NUMERIC = "NUMERIC";
    private static final String OPERATION_CODE_OPERATOR = "OPERATOR";
    private static final String OPERATION_CODE_LEFT_PARENTHESIS = "LEFT_PARENTHESIS";
    private static final String OPERATION_CODE_RIGHT_PARENTHESIS = "RIGHT_PARENTHESIS";

    private static final Map<String, Operation> OPERATION_MAP = new HashMap<>();
    static {
        OPERATION_MAP.put(OPERATION_CODE_NUMERIC, Operation.OPT_NUMERIC);
        OPERATION_MAP.put(OPERATION_CODE_OPERATOR, Operation.OPT_OPERATOR);
        OPERATION_MAP.put(OPERATION_CODE_LEFT_PARENTHESIS, Operation.OPT_LEFT_PARENTHESIS);
        OPERATION_MAP.put(OPERATION_CODE_RIGHT_PARENTHESIS, Operation.OPT_RIGHT_PARENTHESIS);
    }

    public static String infix2Suffix(String infix) {
        StringBuilder sb = new StringBuilder();
        Deque<String> optStack = new ArrayDeque<>();

        if (infix == null || infix.trim().length() < 1) {
            return sb.toString();
        }

        Arrays.stream(infix.split("")).forEach(e->
            OPERATION_MAP.get(getOperationCode(e)).calc(optStack, sb, e)
        );

        while (!optStack.isEmpty()) {
            sb.append(optStack.pop()).append(' ');
        }

        return sb.toString();
    }

    private static String getOperationCode(String e) {
        if (e.matches(REGEX_NUMERIC)) {
            return OPERATION_CODE_NUMERIC;
        }

        if (OPERATOR.contains(e)) {
            return OPERATION_CODE_OPERATOR;
        }

        if (e.equals("(")) {
            return OPERATION_CODE_LEFT_PARENTHESIS;
        }

        if (e.equals(")")) {
            return OPERATION_CODE_RIGHT_PARENTHESIS;
        }

        return null;
    }
}

import java.util.*;

import static jdk.nashorn.internal.objects.Global.print;


/**
 * 1. 先解开括号，到最里层的时候就没有括号了
 * 如果上一个是前括号，设置一个flag是true，继续入栈，flag是true的同时，遇到后括号，则退栈到前括号
 * 退栈的数据使用2的方法计算
 * 2. 实现方法：计算没有括号的算式
 * 3. 实现2时，乘除法入栈时后面是加减法，加减法停止入栈，乘除法出栈计算出结果后入栈
 * 4. 实现方法：计算纯乘除法，如入参是stack
 */
public class Test0130 {

    public static void main(String[] args) {
//        new Test0130().parse("2+8-1*21-8/65", new ArrayList<>(), new ArrayList<>());
//        new Test0130().parse("2+(8-1)", new ArrayList<>(), new ArrayList<>());
//        new Test0130().cal1("2+8-1*21-8/65");
        Test0130 t = new Test0130();
        t.l("cal:" + t.cal("6+(2+(8-2)*21)-9"));
        t.l("cal:" + t.cal("2*(14/2 - 1)-9+((2-3)*8)"));





//        t.l("cal1:" + t.cal1("56+2+8-2*21/3-5/65"));
//        t.testCalChun();
    }

    private void testCalChun() {
        List<Integer> numList = new ArrayList<>();
        List<Character> symbolList = new ArrayList<>();
        parse("1*21", numList, symbolList);

        if (numList.size() > 1 && symbolList.size() > 0 && (numList.size() - symbolList.size()) == 1) {

            Stack<Object> stack = new Stack();
            stack.add(numList.get(0));
            for (int i = 0; i < symbolList.size(); i++) {
                stack.add(symbolList.get(i));
                stack.add(numList.get(i + 1));
            }
            l("calChun:" + calChun(stack));
        }
    }

    private int cal(String s) {
        s = s.replaceAll(" ", "");
        int left = -1, right = -1;
        while (s.contains("(") || s.contains(")")) {
            for (int i = 0; i < s.length(); i++) {
                l("## for loop:" + s);
                char c = s.charAt(i);
                if (c == '(') {
                    if (right == -1) {
                        left = i;
                    }
                } else if (c == ')') {
                    right = i;
                    if (left > -1) {
                        l("left:" + left + ",right:" + right);
                        String start = s.substring(0, left);
                        int ret = cal1(s.substring(left + 1, right));
                        String end = s.substring(right + 1, s.length());
                        l("start:" + start + ",ret:" + ret + ", end:" + end);
                        s = start + ret + end;
                        left = -1;
                        right = -1;
                        l("for loop:" + s);
                        break;
                    }
                }
            }
        }
        l("at last:" + s);
        return cal1(s);
    }


    private void parse(String s, List<Integer> numList, List<Character> symbolList) {
        l("parse:" + s);
        String[] numberStrings = s.split("[+\\-*/]");
        String[] symbolStrings = s.split("\\d+");
        l("num strings len:" + numberStrings.length + ", sumbol strings len:" + symbolStrings.length);
        for (String str : numberStrings) {
            l("str:>>>" + str + "<<<");
            if (str.length() > 0) {
                int n = Integer.parseInt(str);
//            l("add:" + n);
                if ((numberStrings.length - symbolStrings.length) == 1 && symbolStrings[0].equals("-")) {
                    numList.add(-1 * n);
                } else {
                    numList.add(n);
                }
            }
        }
//        l("----------------------");

        for (String s2 : symbolStrings) {
            if (s2.length() > 0) {
                char c = s2.charAt(0);
//                l("add:" + c);
                symbolList.add(c);
            }
        }
    }

    // 处理括号版本
    private void parseWithParentheses(String s, List<Integer> numList, List<Character> symbolList) {
       String[] numberStrings = s.split("[+\\-*/()]");
        for (String str : numberStrings) {
            if (str.length() > 0) {
                Integer n = Integer.valueOf(str);
                l("add:" + n);
                numList.add(n);
            }
        }
        l("----------------------");
        String[] symbolStrings = s.split("\\d+");
        for (String s2 : symbolStrings) {
            if (s2.length() > 0) {
                char c = s2.charAt(0);
                l("add:" + c);
                symbolList.add(c);
                if (s2.length() > 1) {
                    c = s2.charAt(1);
                    l("add:" + c);
                    symbolList.add(c);
                }
            }
        }
    }

    // 计算没有括号的算式，加减乘除都有
    // 实现2时，乘除法入栈时后面是加减法，加减法停止入栈，乘除法出栈计算出结果后入栈
    // -1 * 8
    private int cal1(String s) {
//        l("没有括号的算式:" + s);
        if (s.contains("(") || s.contains(")")) {
//            l("ERROR, contain()");
            return 0;
        }
        List<Integer> numList = new ArrayList<>();
        List<Character> symbolList = new ArrayList<>();
        parse(s, numList, symbolList);

        if (numList.size() > 1 && symbolList.size() > 0 && (numList.size() - symbolList.size()) == 1) {

            Stack<Object> stack = new Stack();
            Stack<Object> chengchuStack = new Stack();
            stack.add(numList.get(0));
            boolean calChengchu = false;

            for (int i = 0; i < symbolList.size(); i++) {
                if (isChengchu(symbolList.get(i))) {
                    if (!calChengchu) {
                        calChengchu = true;
                        chengchuStack.clear();
                        chengchuStack.add(stack.pop());
                    }
                    chengchuStack.add(symbolList.get(i));
                    chengchuStack.add(numList.get(i + 1));
                } else {
                    if (calChengchu && !chengchuStack.isEmpty()) {
//                        l("计算乘除法：");
                        int chengchuResult = calChun(chengchuStack);
                        stack.add(chengchuResult);
                        calChengchu = false;
                    }
                    stack.add(symbolList.get(i));
                    stack.add(numList.get(i + 1));
                }
                if (i == symbolList.size() - 1) {
                    if (calChengchu && !chengchuStack.isEmpty()) {
//                        l("计算乘除法：");
                        int chengchuResult = calChun(chengchuStack);
                        stack.add(chengchuResult);
                        calChengchu = false;
                    }
                }
            }
            return calChun(stack);
        } else {
//            l("ERROR!!!!");
            return 0;
        }
    }

    // 计算纯乘除法或纯加减法
    // 栈顶元素是符号后面的数字
    private int calChun(Stack stack) {
        stack = reverse(stack);
//        l("计算纯乘除法或纯加减法:");
//        print(stack);
        Object top = stack.pop();
        if (top instanceof Integer) {
            int num = (Integer) top;
            while(!stack.isEmpty()) {
                Object symbol = stack.pop();
                if (symbol instanceof Character) {
                    Character c = (Character) symbol;
                    switch (c) {
                        case '+':
                            num += ((Integer)stack.pop());
                            break;
                        case '-':
                            num -= ((Integer)stack.pop());
                            break;
                        case '*':
                            num *= ((Integer)stack.pop());
                            break;
                        case '/':
                            num = num / ((Integer)stack.pop());
                            break;
                        default:
//                            l("error, symbol default");
                            return 0;
                    }
                } else {
                    l("error, symbol instanceof Character false");
                    return 0;
                }

            }
            return num;
        } else {
//            l("error, top is not integer");
            return 0;
        }
    }

    private Stack<Object> reverse(Stack<Object> stack) {
        Stack<Object> ret = new Stack<>();
        while(!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }


    // 计算没有括号的
    /*private int cal2(String s) throws Exception {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        Set<Character> symbolSet = symbolSet();

        // 循环结束后栈中都是加减法
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (i == 0 && symbolSet.contains(c)) {
                throw new Exception("第一个不是数字");
            }

            boolean highLevel = false;
            stack.push(c);
            if (c == '*' || c == '/') {
                highLevel = true;
            }

            if (highLevel && (c == '+' || c == '-')) {      // 出栈计算
                ArrayList<Character> list = new ArrayList<>();
                while (stack.peek() != '+' && stack.peek() != '-') {
                    list.add(stack.pop());
                }

            }
        }

        return 0;
    }
*/
    private void l(String s) {
        System.out.println(s);
    }

    private void print(Stack<Object> stack) {
        Stack<Object> s2 = (Stack<Object>) stack.clone();
        while (!s2.isEmpty()) {
            l("stack item:" + s2.pop());
        }
    }

    private boolean isChengchu(Character c) {
        return c.equals('*') || c.equals('/');
    }
}

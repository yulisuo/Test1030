import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Test0130 {
    public static void main(String[] args) {

        System.out.println(4 / 3);  // 1
        System.out.println(5 / 3);  // 1

        System.out.println(-4 / 3);  // -1
        System.out.println(-5 / 3);  // -1
    }

    private Set<Integer> numberSet() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        return set;
    }

    private Set<Character> symbolSet() {
        HashSet<Character> set = new HashSet<>();
        set.add('+');
        set.add('-');
        set.add('*');
        set.add('/');
        return set;
    }

    // 16:05
    public int calculate(String s) {

        return 0;
    }

    // 计算没有括号的
    private int cal2(String s) throws Exception {
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

    // 计算纯加减法或纯乘除法的字符串
    private int cal3(String s) {
        String[] ss = s.split("+");

        return 0;
    }


}

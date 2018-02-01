import java.util.Stack;

/**
 * Created by candy on 2/1/18.
 */
public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate(" 2-1 + 2 "));
    }

    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> signStack = new Stack<>();
        int num = 0;
        int i = 0;
        while (i < s.length()) {
            char curr = s.charAt(i);
            if (curr == ' ') {
                i++;
                continue;
            };
            if (curr == '+' || curr == '-' || curr == '*' || curr == '/' || curr == '(' || curr == ')') {
                if (curr == '(') {
                    signStack.push(curr);
                } else if (curr == ')') {
                    while (!signStack.isEmpty() && signStack.peek() != '(') {
                        int second = numStack.pop();
                        int first = numStack.pop();
                        numStack.push(calc(first, second, signStack.pop()));
                    }
                    signStack.pop();
                } else {
                    while (!signStack.isEmpty() && checkPriority(curr, signStack.peek())) {
                        int second = numStack.pop();
                        int first = numStack.pop();
                        numStack.push(calc(first, second, signStack.pop()));
                    }
                    signStack.push(curr);
                }

            } else {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                numStack.push(num);
                num = 0;
                i--;
            }
            i++;
        }
        while (!signStack.isEmpty()) {
            int second = numStack.pop();
            int first = numStack.pop();
            numStack.push(calc(first, second, signStack.pop()));
        }
        return numStack.isEmpty() ? 0 : numStack.pop();
    }

    private static int calc(int num1, int num2, char operator) {
        if (operator == '+') return num1 + num2;
        if (operator == '-') return num1 - num2;
        if (operator == '*') return num1 * num2;
        return num1 / num2;
    }

    private static boolean checkPriority(char op1, char op2) {
        if (op2 == '*' || op2 == '/') return true;
        if (op2 == '+' || op2 == '-') {
            if (op1 == '*' || op1 == '/') {

                return false;
            }
           return true;
        }
        return false;
    }
}

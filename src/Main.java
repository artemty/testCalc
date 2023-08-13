import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //подрубаем сканер
        String input = scanner.nextLine(); //читаем строку
        String result = calc(input); //кидаем строку в calc, все по сути там
        System.out.println(result); //выход результата
    }
    public static String calc(String input) {
        String [] str = input.split(" ");
        try {if (!trowsEx(str)) throw new Exception();} catch (Exception e) {throw new RuntimeException("throws Exception");}
        int num1 = romanToInt(str[0]); int num2 = romanToInt(str[2]); // переводим в арабские
        int result = switch (str[1]) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            default -> num1 / num2;
        };

        if (str[0].matches("[1-9]|10")) return Integer.toString(result); //если арабские нет ограничений, возвращаем и - и 0
        return intToRoman(result); //римские
    }
    private static boolean trowsEx(String [] str) {
        if (str.length != 3) return false; //не три операнда
        if (!str[0].matches("[1-9]|10|I|II|III|IV|V|VI|VII|VIII|IX|X|10]") || //принадлежит числам 0..10 и целое
            !str[2].matches("[1-9]|10|I|II|III|IV|V|VI|VII|VIII|IX|X|10]")) return false;
        if (!str[1].matches("[*/+-]")) return false; //не арифметическое действо
        if (str[0].matches("[1-9]|10") && str[2].matches("I|II|III|IV|V|VI|VII|VIII|IX|X")) return false;
        if (str[2].matches("[1-9]|10") && str[0].matches("I|II|III|IV|V|VI|VII|VIII|IX|X")) return false;
        return true;
    }
    private static int romanToInt(String s) {
        if (s.matches("[1-9]|10")) return Integer.parseInt(s);
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("I", 1); put("II", 2); put("III", 3); put("IV", 4); put("V", 5);
            put("VI", 6); put("VII", 7); put("VIII", 8); put("IX", 9); put("X", 10);
        }}; return map.get(s);
    }
    private static String intToRoman(int num) {
        try {if (num <= 0) throw new Exception();} catch (Exception e) {throw new RuntimeException("throws Exception");}
        String[] c = {"", "C"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return c[num/100] + x[(num%100)/10] + i[num%10];
    }
}
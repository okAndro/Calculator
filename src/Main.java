import java.util.Scanner;
public class Main
{
    private static final String[] ROMANUNITS = new String[] {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; 
    private static final String[] ROMANTENS = new String[] {"","X", "XX", "XXX", "LX", "L", "LX", "LXX", "LXXX", "XC", "C"}; 
    public static void main(String[] args) {
        while (true){
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("↓ Введите выражение строкой ниже (например, 2 + 3 или VI / III). Можно вводить с пробелом между операндами или без ↓:");
            System.out.println(calc(in.nextLine())); 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }}
    public static String calc(String input) throws Exception {
        String[] parts = input.replaceAll("\\s", "").split("(?<=[-+*/])|(?=[-+*/])");
        if((parts.length != 3)){
            throw new Exception("Привет\nЧтобы работало, нужно ввести два операнда и один оператор (+, -, /, *).");
        }
        int a = tryParse(parts[0]); 
        String operator = parts[1]; 
        int b = tryParse(parts[2]); 
        int answ = 0; //ответ
        boolean isRoman = false; 
        if((a == 0) && (b == 0)){ 
            a = romanToArabic(parts[0]); 
            b = romanToArabic(parts[2]); 
            isRoman = true;
        }
        if(((a == 0) || (b == 0)) || ((a > 10) || (b > 10))){ 
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления\nОба операндов должны быть одной системы счисления, а также принадлежать диапозону [1..10].");
        }
        switch(operator){ 
            case "+":
                answ = a + b;
                break;
            case "-":
                answ = a - b;
                break;
            case "*":
                answ = a * b;
                break;
            case "/":
                answ = a / b;
                break;
            default: 
                throw new Exception("throws Exception //т.к. оператор должен быть (+, -, /, *).");
        }
        if(isRoman && answ < 1){ 
            throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел и нуля");
        }
        return "Ответ: " + (isRoman ? arabicToRoman(answ) : Integer.toString(answ)); 


    }
    private static int tryParse(String value){ 
        try {
            return Integer.parseInt(value); 
        }
        catch (NumberFormatException e) {
            return 0; 
        }
    }
    private static int romanToArabic(String value){ 
        for(int i = 1; i < ROMANUNITS.length; i++){ 
            if(ROMANUNITS[i].equals(value)){
                return  i; 
            }
        }
        return 0; 
    }
    private static String arabicToRoman(int value){ 
        return ROMANTENS[value / 10] + ROMANUNITS[value % 10]; 
    }
}

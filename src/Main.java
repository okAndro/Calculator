import java.util.Scanner;
public class Main
{
    private static final String[] ROMANUNITS = new String[] {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // римские числа от 1 до 10 (единицы)
    private static final String[] ROMANTENS = new String[] {"","X", "XX", "XXX", "LX", "L", "LX", "LXX", "LXXX", "XC", "C"}; // римские числа от 10 до 100 (десятки)
    public static void main(String[] args) {
        while (true){
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("↓ Введите выражение строкой ниже (например, 2 + 3 или VI / III). Можно вводить с пробелом между операндами или без ↓:");
            System.out.println(calc(in.nextLine())); // чтение строки из консоли, запуск метода calc
        }
        catch(Exception e){
            System.out.println(e.getMessage()); // вывод ошибок
        }
    }}
    public static String calc(String input) throws Exception {
        String[] parts = input.replaceAll("\\s", "").split("(?<=[-+*/])|(?=[-+*/])");// разбивает строку input на массив строк, используя регулярное выражение
        if((parts.length != 3)){
            throw new Exception("Привет\nЧтобы работало, нужно ввести два операнда и один оператор (+, -, /, *).");
        }
        int a = tryParse(parts[0]); // пытаемся перевести первое число в стандартный вид
        String operator = parts[1]; // оператор
        int b = tryParse(parts[2]); // пытаемся перевести второе число в стандартный вид
        int answ = 0; //ответ
        boolean isRoman = false; // true - если римские числа, в противном случае - false
        if((a == 0) && (b == 0)){ // проверка на не арабские цифры
            a = romanToArabic(parts[0]); // пытаемся первести первое число в римское
            b = romanToArabic(parts[2]); // пытаемся первести второе число в римское
            isRoman = true;
        }
        if(((a == 0) || (b == 0)) || ((a > 10) || (b > 10))){ //  проверка на одинаковую систему счисления и принадлежность диапозону [1..10]
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления\nОба операндов должны быть одной системы счисления, а также принадлежать диапозону [1..10].");
        }
        switch(operator){ // выбор действия относительно оператора
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
            default: //если такого оператора нет
                throw new Exception("throws Exception //т.к. оператор должен быть (+, -, /, *).");
        }
        if(isRoman && answ < 1){ // проверка на ответ меньше 1 (в случае с римскими цифрами)
            throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел и нуля");
        }
        return "Ответ: " + (isRoman ? arabicToRoman(answ) : Integer.toString(answ)); // возвращаем ответ относительно системе счисления


    }
    private static int tryParse(String value){ // перевод строки в число
        try {
            return Integer.parseInt(value); // перевод удался
        }
        catch (NumberFormatException e) {
            return 0; // перевод не удался
        }
    }
    private static int romanToArabic(String value){ // перевод из римских в арабские цифры
        for(int i = 1; i < ROMANUNITS.length; i++){ // поиск значения value по массиму ROMANUNITS
            if(ROMANUNITS[i].equals(value)){
                return  i; // перевод удался
            }
        }
        return 0; // перевод не удался
    }
    private static String arabicToRoman(int value){ //перевод из арабских в римские цифры
        return ROMANTENS[value / 10] + ROMANUNITS[value % 10]; // получаем десятки и единицы, объединяем
    }
}
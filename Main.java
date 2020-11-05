package bullscows;


import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static char[] values = {'0','1','2','3',
            '4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t',
            'u','v','w','x','y','z'};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input the length of the secret code: ");
        String num = scan.nextLine();
        if (!num.matches("[0-9]*")) {
            System.out.println("Error: " + "\"" + num + "\" " + "isn't a valid number.");
            System.exit(0);
        }else if (Integer.parseInt(num) > 36 || Integer.parseInt(num) < 1) {
            System.out.println("Error: can't generate a secret number with a length of " + num + " because there aren't enough unique digits.");
        } else{
            System.out.println("Input the number of possible symbols in the code:");
            String n = scan.nextLine();
            if (!n.matches("[0-9]*")) {
                System.out.println("Error: " + "\"" + n + "\" " + "isn't a valid number.");
                System.exit(0);
            }

            if(Integer.parseInt(n) < Integer.parseInt(num)) {
                System.out.println("Error: it's not possible to generate a code with a length of " + num + " with " + n + " unique symbols.");
                System.exit(0);
            }else if(Integer.parseInt(n) > 36){
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }



            String expected = generateRandomString(Integer.parseInt(num), Arrays.copyOfRange(values, 0, Integer.parseInt(n)));
            System.out.println("The secret is prepared " + stars(Integer.parseInt(num)) + interval(values, Integer.parseInt(n)));
            System.out.println("Okay, let's start a game! ");
            String[] arrayExpected = expected.split("");
            int turnCount = 1;
            Scanner scan2 = new Scanner(System.in);
            while (true) {
                System.out.println("turn " + turnCount);
                String guess = scan2.nextLine();
                String[] arrayGuess = guess.split("");

                int cowCount = 0;
                int bullCount = 0;
                for (int i = 0; i < arrayGuess.length; i++) {
                    boolean inPositon = arrayExpected[i].equals(arrayGuess[i]);
                    if (arrayExpected[i].equals(arrayGuess[i])) {
                        bullCount++;
                    }

                    if (expected.contains(arrayGuess[i]) && !inPositon) {
                        cowCount++;
                    }
                }


                if (cowCount == 0 && bullCount == 0) {
                    System.out.println("Grade: None. The secret code is " + expected);
                } else {
                    System.out.println("Grade: " + bullCount + " bull(s) and " + cowCount + " cow(s).");
                }

                if (bullCount == Integer.parseInt(num)) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
                turnCount++;
            }

        }
    }




    public static String generateRandomString(int length, char[] values) {
        Random random = new Random((new Date()).getTime());
        String out = "";

        while (out.length() != length){
            int idx=random.nextInt(values.length);
            if(out.indexOf(values[idx]) == -1){
                out += values[idx];
            }
        }
        return out;
    }

    public static  String stars(int num) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < num; i++) {
            string.append("*");
        }

        return  string.toString();
    }

    public static String interval(char[] values, int n){
        String res = "";
        if(n < 10) {
            res = " (" + values[0] + "-" + values[n - 1] + ")";
        }else if(n == 10) {
            res = " (" + values[0] + "-" + values[9] + ", a)";
        }else {
            res = " (" + values[0] + "-" + values[9] + ", a-" + values[n - 1] + ")";
        }
        return  res;
    }

}

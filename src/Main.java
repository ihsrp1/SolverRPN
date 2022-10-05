// Your First Program

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.File;  // Import the File class
import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;
class SolverRPN {
    private static TokenType getTokenType (String data) {
        if (data.equals("+")) {
            return TokenType.PLUS;
        } else if (data.equals("-")) {
            return TokenType.MINUS;
        } else if (data.equals("*")) {
            return TokenType.STAR;
        } else if (data.equals("/")) {
            return TokenType.SLASH;
        } else {
            try {
                int number = Integer.parseInt(data);
                return TokenType.NUM;
            } catch (Exception e) {
                throw new Error("Unexpected character: "+data);
            }
        }
    }
    private static int doOp (Integer[] numbers, String op) {
        Integer result = numbers[numbers.length-1];
        for (int i = numbers.length-2; i >= 0; i--){
            if (op.equals("+")) {
                result += numbers[i];
            } else if (op.equals("-")) {
                result -= numbers[i];
            } else if (op.equals("*")) {
                result *= numbers[i];
            } else if (op.equals("/")) {
                result /= numbers[i];
            }
        }
        return result;
    }
    private static int calculate(List<Token> tokens) {
        Stack <Integer> stk = new Stack();
        for (Token t: tokens) {
            if (t.type != TokenType.NUM) {
                Integer[] numbers;
                numbers = new Integer[stk.size()];
                int i = 0;
                while(!stk.empty()){
                    numbers[i] = stk.pop();
                    i++;
                }
                int result = doOp(numbers, t.lexeme);
                stk.push(result);
            } else {
                stk.push(Integer.parseInt(t.lexeme));
            }
        }
        return stk.pop();
    }
    private static List<Token> scan(Scanner myReader) {
        List<Token> tokens = new ArrayList<Token>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            TokenType tp = getTokenType(data);
            Token t = new Token(tp, data);
            tokens.add(t);

        }
        return tokens;
    }
    public static void main(String[] args) {
        try {
            File myObj = new File("src/Calc1.stk");
            Scanner myReader = new Scanner(myObj);
            List<Token> tokens = scan(myReader);

            System.out.println(tokens);

            int result = calculate(tokens);

            System.out.println("Saída: " + result);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
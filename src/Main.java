// Your First Program

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.File;  // Import the File class
import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;
import stacker.rpn.lexer.Regex;
class SolverRPN {
    private static TokenType getTokenType (String data) {
        if(Regex.isNum(data)) {
            return TokenType.NUM;
        } else if (Regex.isOp(data)) {
            if (Regex.isPlus(data)) {
                return TokenType.PLUS;
            } else if (Regex.isMinus(data)) {
                return TokenType.MINUS;
            } else if (Regex.isStar(data)) {
                return TokenType.STAR;
            } else if (Regex.isSlash(data)) {
                return TokenType.SLASH;
            }
        } else {
            throw new Error("Unexpected character: "+data);
        }

//        if (data.equals("+")) {
//            return TokenType.PLUS;
//        } else if (data.equals("-")) {
//            return TokenType.MINUS;
//        } else if (data.equals("*")) {
//            return TokenType.STAR;
//        } else if (data.equals("/")) {
//            return TokenType.SLASH;
//        } else {
//            try {
//                int number = Integer.parseInt(data);
//                return TokenType.NUM;
//            } catch (Exception e) {
//                throw new Error("Unexpected character: "+data);
//            }
//        }
        return TokenType.EOF;
    }
    private static int doOp (Integer[] numbers, Token op) {
        Integer result = numbers[numbers.length-1];
        for (int i = numbers.length-2; i >= 0; i--){
            if (op.type == TokenType.PLUS) {
                result += numbers[i];
            } else if (op.type == TokenType.MINUS) {
                result -= numbers[i];
            } else if (op.type == TokenType.STAR) {
                result *= numbers[i];
            } else if (op.type == TokenType.SLASH) {
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
                int result = doOp(numbers, t);
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
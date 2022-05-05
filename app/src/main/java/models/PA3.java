package models;

import java.util.Scanner;
import java.util.Stack;

public class PA3 {
    private static Scanner scan = new Scanner(System.in);
    private static Stack<Double> stack = new Stack<>();

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("Enter an RPN expression or <CR> to exit");
            String userInput = scan.nextLine();
            if(userInput.equals("")){
                break;
            }
            System.out.println(evaluate(userInput));
        }
        System.out.println("Thanks for using!");
    }

    public static String evaluate(String expression)
    {
        String[] values = expression.trim().split("\\s+");
        for(String s : values)
        {
            if(isNumber(s))
            {
                stack.push(Double.parseDouble(s));
            }else
            {
                try {
                    //check if stack empty
                    Double two = stack.peek();
                    stack.pop();
                    Double one = stack.peek();
                    stack.pop();
                    if(s.equals("+"))
                    {
                        stack.push(one + two);
                    }else if(s.equals("-")){
                        stack.push(one - two);
                    }else if(s.equals("*"))
                    {
                        stack.push(one * two);
                    }else if(s.equals("/"))
                    {
                        stack.push(one / two);
                    }
                }catch(Exception e){
                    return "Something went wrong. Try again!";
                }
            }
        }

        loop:
        if(!stack.isEmpty()){
            Double top = stack.peek();
            stack.pop();
            if(stack.isEmpty()){
                while(!stack.isEmpty())
                {
                    stack.pop();
                }
                return String.valueOf(top);
            }else{
                break loop;
            }
        }else{
            break loop;
        }
        while(!stack.isEmpty())
        {
            stack.pop();
        }
        return "Something went wrong. Try again!";
    }

    public static Boolean isNumber(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
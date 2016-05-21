package book.chap1_3;

import java.util.Stack;

/**
 * Created by kenvi on 16/5/21.
 */
public class P_1_3_3 {
     public static void main(String[] args){
          char[] in = new char[] {'0','1','2','3','4','5','6','7','8','9'};

          System.out.println(match(in, string2CharArray("4321098765")));
          System.out.println(match(in, string2CharArray("4687532901")));
          System.out.println(match(in, string2CharArray("2567489310")));
          System.out.println(match(in, string2CharArray("4321056789")));
          System.out.println(match(in, string2CharArray("1234569870")));
          System.out.println(match(in, string2CharArray("0465381729")));
          System.out.println(match(in, string2CharArray("1479865302")));
          System.out.println(match(in, string2CharArray("2143658790")));
     }

     private static char[] string2CharArray(String str) {
          char[] charArray = new char[str.length()];

          for (int i = 0; i < str.length(); i++) {
               charArray[i] = str.charAt(i);
          }
          return charArray;
     }

     private static boolean match(char[] in, char[] out) {
          Stack<Character> stack = new Stack<Character>();

          int i = 0;
          int index = 0;
          stack.push(in[i]);
          while(i < in.length && !stack.isEmpty()) {
               //consume stack and out array
               while (!stack.isEmpty() && stack.peek() == out[index]){
                    stack.pop();
                    index++;
               }
               if(i < in.length - 1) {
                    stack.push(in[++i]);
               }

               //no input, cannot consume stack
               if(i == in.length-1 && !stack.isEmpty() && stack.peek() != out[index]) {
                    return false;
               }
          }

          if(!stack.isEmpty() || index < out.length) {
               return false;
          }
          return true;

     }
}

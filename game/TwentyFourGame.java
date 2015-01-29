//Samier Trabilsy 109839226
package game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TwentyFourGame
{
  public static ArrayList<ArrayList<Double>> permutation(ArrayList<Double> a) { 
    ArrayList<ArrayList<Double>> perms = new ArrayList<ArrayList<Double>>();
    if(a.isEmpty()) {
      ArrayList<Double> onePerm = new ArrayList<Double>();
      perms.add(onePerm);
      return perms;
    }
    for (Double oneElem:a) {
      ArrayList<Double> b = (ArrayList<Double>)(a.clone());
      b.remove(oneElem);
      ArrayList<ArrayList<Double>> perms2 = permutation(b);
      for (ArrayList<Double> onePerm: perms2) {
        onePerm.add(oneElem);
        perms.add(onePerm);
      }
    }
    return perms;
  }
  
  public static void main(String[] args)
  {
    System.out.print("Please enter four numbers(1-9) (e.g. 1 1 1 1): ");
    Scanner input = new Scanner(System.in);
    double n1 = input.nextDouble();
    double n2 = input.nextDouble();
    double n3 = input.nextDouble();
    double n4 = input.nextDouble();
    ArrayList<Double> nums = new ArrayList<Double>(Arrays.asList(n1, n2, n3, n4));
    ArrayList<ArrayList<Double>> perms = permutation(nums);
    char[] ops = {'+', '-', 'x', '/'};
    int op1, op2, op3;
    double answer = 0;
    for (ArrayList<Double> onePerm: perms) {
      n1 = onePerm.get(0);
      n2 = onePerm.get(1);
      n3 = onePerm.get(2);
      n4 = onePerm.get(3);
      for (op1 = 0; op1 < ops.length; op1++) {
        for (op2 = 0; op2 < ops.length; op2++) {
          for (op3 = 0; op3 < ops.length; op3++) {
            //parentheses grouping
            
            //groups of 2 in group of 3
            
            //((a op b) op c) op d
            if (compute(compute(compute(n1, ops[op1], n2), ops[op2], n3), ops[op3], n4) >= 23.9 &&
                compute(compute(compute(n1, ops[op1], n2), ops[op2], n3), ops[op3], n4) <= 24.1) {
              System.out.printf("((%.1f %c %.1f) %c %.1f) %c %.1f = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }
            //(a op (b op c)) op d
            if (compute(compute(n1, ops[op1], compute(n2, ops[op2], n3)), ops[op3], n4) >= 23.9 &&
                compute(compute(n1, ops[op1], compute(n2, ops[op2], n3)), ops[op3], n4) <= 24.1) {
              System.out.printf("(%.1f %c (%.1f %c %.1f)) %c %.1f = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }
            //a op ((b op c) op d)
            if (compute(n1, ops[op1], compute(compute(n2, ops[op2], n3), ops[op3], n4)) >= 23.9 &&
                compute(n1, ops[op1], compute(compute(n2, ops[op2], n3), ops[op3], n4)) <= 24.1) {
              System.out.printf("%.1f %c ((%.1f %c %.1f) %c %.1f) = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }
            //a op (b op (c op d))
            if (compute(n1, ops[op1], compute(n2, ops[op2], compute(n3, ops[op3], n4))) >= 23.9 &&
                compute(n1, ops[op1], compute(n2, ops[op2], compute(n3, ops[op3], n4))) <= 24.1) {
              System.out.printf("%.1f %c (%.1f %c (%.1f %c %.1f)) = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }
            
            //2 groups of 2
            //(a op b) op (c op d)
            if (compute(compute(n1, ops[op1], n2), ops[op2], compute(n3, ops[op3], n4)) >= 23.9 &&
                compute(compute(n1, ops[op1], n2), ops[op2], compute(n3, ops[op3], n4)) <= 24.1) {
              System.out.printf("(%.1f %c %.1f) %c (%.1f %c %.1f) = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }
            
            /* for debugging, use answer variable
             * answer = compute(n1, ops[op1], compute(n2, ops[op2], compute(n3, ops[op3], n4)));
            if (answer==24) {
              System.out.printf("%.1f %c %.1f %c %.1f %c %.1f = 24\n", n1, ops[op1], n2, ops[op2], n3, ops[op3], n4);
              System.exit(0);
            }*/
          }
            
        }
      }
    }
    System.out.println("There is no possible equation equal with 24.");
  }
  
  public static double compute(double x, char op, double y) {
    switch(op) {
      case '+': return x+y;
      case '-': return x-y;
      case 'x': return x*y;
      case '/': return x/y;
      default: return 99999;
    }
  }
  
}

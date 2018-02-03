package hw02cats;

import java.util.Random;
import java.util.Scanner;

/**
 * Simulates a group of cats who want to sleep on the various levels of a
 * cat tree. The user can specify the number of levels in the tree and the
 * total number of times that the cats move. These cats are polite and will not
 * force another cat to move.
 *
 * Eventually, two cats will end up in positions where they cannot move because
 * they want to swap locations, causing deadlock.
 *
 */
public class Main {

   private static final String[] CATS = {"Fidget", "Hash Brown", "Brulee", "Mow", "Leo"};
   private static int levels;
   private static int turns;
   private static Tree tree;

   public static void main(String[] args) {
      levels = chooseLevels();
      turns = chooseTurns();
      tree = new Tree(levels);

      for (int i = 0; i < CATS.length; i++) {
         Thread cat = new Thread(new Move(CATS[i], turns, tree));
         cat.start();
      }

   }

   /**
    * Prompts for the number of levels in the cat tree
    *
    * @return the number of levels
    */
   private static int chooseLevels() {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter the number of levels in the cat tree: ");
      return scanner.nextInt();
   }

   /**
    * Prompts for the number of turns
    *
    * @return the number of turns
    */
   private static int chooseTurns() {
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nEnter the number of turns: ");
      return scanner.nextInt();
   }

}

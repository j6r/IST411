package hw02cats;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Move simulates a cat moving between levels of or existing a cat tree
 *
 */
public class Move implements Runnable {

   private final String name;
   private final int moves;
   private final Tree tree;

   private int level;

   /**
    * Constructs a new Move
    *
    * @param name the cat's name
    * @param moves the number of moves the cat can make
    * @param tree the cat tree
    */
   public Move(String name, int moves, Tree tree) {
      this.name = name;
      this.moves = moves;
      this.tree = tree;
      level = -1;
   }

   @Override
   public void run() {
      try {

         for (int i = 0; i < moves; i++) {
            level = selectLevel();
            tree.moveCat(name, level);
            System.out.printf("%30s|%s%n", " ", tree.toString());
            Thread.sleep(1000);
         }

      } catch (InterruptedException ex) {
         Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Chooses a random level for the cat to move to
    *
    * @return the level
    */
   private int selectLevel() {
      Random r = new Random();
      return r.nextInt(tree.getHeight() + 1) - 1;
   }
  

}

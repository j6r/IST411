package hw02cats;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Tree represents a multilevel cat tree. Only one cat is allowed per level.
 * Cats can also choose to leave the tree.
 *
 */
public class Tree {

   private Hashtable<Integer, String> tree;
   private Lock treeLock;

   /**
    * Constructs a new tree with the specified number of levels
    *
    * @param levels the number of levels
    */
   public Tree(int levels) {

      tree = new Hashtable<>(levels);
      for (int i = 0; i < levels; i++) {
         tree.put(i, "");
      }
      treeLock = new ReentrantLock();
   }

   public int getHeight() {
      return tree.size();
   }

   /**
    * Moves a cat to the specified level or removes them from the tree.
    *
    * Cats can exit the tree at any time but must wait for a level to be empty
    * before moving to it.
    *
    * @param cat the cat's name
    * @param level the level number or -1 to exit the tree
    */
   public synchronized void moveCat(String cat, int level) {
      if (level >= tree.size()) {
         throw new IllegalArgumentException();
      }

      if (level < 0) {
         treeLock.lock();
         removeCat(cat);
         treeLock.unlock();
         System.out.printf("%s is eating%n", cat);

      } else if (!tree.get(level).equals(cat)) {

         while (!tree.get(level).equals("")) {
            try {
               System.out.printf("%s waiting for level %d%n", cat, level);
               wait();
            } catch (InterruptedException e) {
               System.err.println("interrupted");
            }
         }
         treeLock.lock();
         removeCat(cat);
         tree.put(level, cat);
         System.out.printf("%s moved to level %d%n", cat, level);
         treeLock.unlock();
         notifyAll();
         
      }

   }

   /**
    * Removes a cat from the tree if present
    *
    * @param cat the cat to remove
    */
   public void removeCat(String cat) {
      if (tree.containsValue(cat)) {
         for (Map.Entry<Integer, String> entry : tree.entrySet()) {
            if (entry.getValue().equals(cat)) {
               treeLock.lock();
               entry.setValue("");
               treeLock.unlock();
               notifyAll();
               return;
            }
         }
      }
   }

   public void printTree() {
      System.out.println("--------------------------------------------------");
      for (Map.Entry<Integer, String> entry : tree.entrySet()) {
         System.out.printf("Level %2d: %s%n", entry.getKey(), entry.getValue());
      }
   }

   @Override
   public String toString() {
     treeLock.lock();
      StringBuilder sb = new StringBuilder("| ");
      
      for (Map.Entry<Integer, String> entry : tree.entrySet()) {
         sb.append(String.format("%2d %-10s |", entry.getKey(), entry.getValue()));
      }
      treeLock.unlock();
      return sb.toString();
   }

}

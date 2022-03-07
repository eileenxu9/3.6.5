/**********************************************************************
* Sample solution for Activity 3.6.2
**********************************************************************/
 
/**
* Generate random permutations of integers
*/
public class RandomPermutation
{
 /**
  * Generates one permutation of random integers. The number of
  * integers to generate is designated by length and the
  * randomized list of integers is returned.
  *
  * Initial conditions: length cannot be negative
  */
   public static int[] next(int length)
   {
     int[] p = new int[length];
     int[] r = new int[length];
 
     for (int i = 0; i < length; i++)
       p[i] = i + 1;
      
     for (int n = length; n > 0; n--)
     {
       int pos = (int)(Math.random() * n);
       r[n - 1] = p[pos];
       p[pos] = p[n - 1];
     }
    
     // debug: System.out.println(java.util.Arrays.toString(r));
     return r;
   }
 /**
 * Genereate one random permutation of a provided array of strings, seq.
 * A ranomly ordered version of the array is returned.
 *
 * Initial conditions: seq cannot be null
   */
 public static String[] next(String[] seq)
 { 
   int length = seq.length;
  
   String[] p = new String[length];
   String[] r = new String[length];
 
   int i = 0;
   for (String s : seq)
   {
     p[i] = s;
     i++;
   }
 
   for (int n = length-1; n >=0; n--)
     {
       int pos = (int)(Math.random() * n);
       r[n] = p[pos];
       p[pos] = p[n];
     }
     // debug: System.out.println(java.util.Arrays.toString(r));
     return r;
 }
 
}

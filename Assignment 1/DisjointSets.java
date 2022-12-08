import java.io.*;
import java.util.*;


/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2
*
*****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {

        /* Fill this method (The statement return 0 is here only to compile) */
        if (this.par[i] == i) {
            return i; // if we find the root node, just return its value.
        } else {
            this.par[i] = find(this.par[i]); // path compression
            return find(this.par[i]);
        }
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
    
        /* Fill this method (The statement return 0 is here only to compile) */
        int i_ind = this.find(i); // assign the index of integer i.
        int j_ind = this.find(j); // assign the index of integer j.

        if (i_ind != j_ind) {
            if (rank[i_ind] == rank[j_ind]){ // if the ranks of i and j are the same,
                par[i_ind] = j_ind; // the root of tree containing j become the child of the root of the tree containing i.
                rank[j_ind]+=1; // increase the rank of j.
            }
            else if (rank[i_ind] > rank[j_ind]){
                par[j_ind] = i_ind; // if rank of i is larger than rank of j,
                // the root of tree containing j become the child of the root of the tree containing i.
            }
            else if (rank[i_ind] < rank[j_ind]){
                par[i_ind] = j_ind; // if rank of i is smaller than rank of j,
                // the root of tree containing i become the child of the root of the tree containing j.
            }
        }

        return 0;
        
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }

}

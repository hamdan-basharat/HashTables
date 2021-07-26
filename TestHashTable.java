/*******************************************************************************
* Hamdan Basharat / basham1 / 400124515
* TestHashTable Class 
*******************************************************************************/
package HashTable;
import java.util.*;

public class TestHashTable {
    public static void main(String[] args) {
        
        System.out.println("**********Regular Insertion**********");
        System.out.println("Shows use of constructors (the size of the table is prime),");
        System.out.println("inserting while removing duplicates, and using the print methods.");
        int max1 = 3;
        double load1 = 0.5;
        System.out.println("Linear Hashing:");
        HashTableLin test1 = new HashTableLin(max1,load1);
        System.out.println("Size is: "+test1.getSize());
        test1.insert(1);
        test1.insert(2);
        test1.insert(3);
        test1.insert(4);
        test1.printKeys();
        test1.printKeysAndIndexes();
        System.out.println("Number of keys: "+test1.getKeys());
        System.out.println("Quadratic Hashing:");
        HashTableQuad test2 = new HashTableQuad(max1,load1);
        System.out.println("Size is: "+test2.getSize());
        test2.insert(14);
        test2.insert(14);
        test2.insert(15);
        test2.insert(16);
        test2.printKeys();
        test2.printKeysAndIndexes();
        System.out.println("Number of keys: "+test2.getKeys());
        
        System.out.println("\n**********Inserting with Collision**********");
        System.out.println("Shows collision handling with linear and quadratic probing.");
        int max2 = 5;
        double load2 = 0.4;
        System.out.println("Linear Hashing:");
        HashTableLin test3 = new HashTableLin(max2,load2);
        System.out.println("Size is: "+test3.getSize());
        test3.insert(15);
        test3.insert(28);
        test3.printKeys();
        test3.printKeysAndIndexes();
        System.out.println("Number of keys: "+test3.getKeys());
        System.out.println("Quadratic Hashing:");
        HashTableQuad test4 = new HashTableQuad(max2,load2);
        System.out.println("Size is: "+test4.getSize());
        test4.insert(15);
        test4.insert(28);
        test4.printKeys();
        test4.printKeysAndIndexes();
        System.out.println("Number of keys: "+test4.getKeys());
        
        System.out.println("\n**********Inserting with Rehashing**********");
        System.out.println("Shows rehashing to a larger table when load is exceeded.");
        int max3 = 5;
        double load3 = 0.4;
        System.out.println("Linear Hashing:");
        HashTableLin test5 = new HashTableLin(max2,load2);
        System.out.println("Size is: "+test5.getSize());
        test5.insert(15);
        test5.insert(28);
        test5.insert(5);
        test5.insert(7);
        test5.insert(45);
        test5.insert(3);
        test5.insert(39);
        test5.insert(13);
        test5.printKeys();
        test5.printKeysAndIndexes();
        System.out.println("Number of keys: "+test5.getKeys());
        System.out.println("Quadratic Hashing:");
        HashTableQuad test6 = new HashTableQuad(max2,load2);
        System.out.println("Size is: "+test6.getSize());
        test6.insert(15);
        test6.insert(28);
        test6.insert(5);
        test6.insert(7);
        test6.insert(45);
        test6.insert(3);
        test6.insert(39);
        test6.insert(13);
        test6.printKeys();
        test6.printKeysAndIndexes();
        System.out.println("Number of keys: "+test6.getKeys()+"\n\n");  
        
        Simulation();
    }
    public static void Simulation(){
        int max = 100000;
        double probesL = 0.0;
        double probesQ = 0.0;
        Random rand = new Random();
        for(double i=0.1;i<=0.9;i+=0.1){
            HashTableLin sim1 = new HashTableLin(max,i);
            HashTableQuad sim2 = new HashTableQuad(max,i);
            for(int j=0;j<100000;j++){
                int q = rand.nextInt(100000000);
                probesL += sim1.insertSim(q);
                probesQ += sim2.insertSim(q);
            }        
            System.out.println("Theoretical: "+0.5*(1+(1/(1-i))));
            probesL = probesL/100000.0;
            probesQ = probesQ/100000.0;
            System.out.println("Actual Linear Probes: "+probesL);
            System.out.println("Actual Quadratic Probes: "+probesQ);
            probesL=0.0; probesQ=0.0;
        }
    }
}

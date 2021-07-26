/*******************************************************************************
* Hamdan Basharat / basham1 / 400124515
* HashTableQuad Class 
*******************************************************************************/
package HashTable;

public class HashTableQuad {
private int[] table; //reference to the array which stores the keys
    private int size; //size of the hash table
    private int keys; //number of keys in the hash table
    private double maxLoad; //maximum load factor allowed
    /***************************************************************************
    * Constructor: Creates an object representing an empty hash table with max
    * allowed load factor equal to load.
    * @param: type Integer, type double
    ***************************************************************************/
    public HashTableQuad(int maxNum, double load){
        this.keys = 0; //intializes variable fields
        this.maxLoad = load;
        double prime = Math.ceil(maxNum/load); //determines minimum size of array
        while(isPrime(prime) == false){prime++;} //increments number if its not prime
        this.size = (int)prime; //sets size to value of prime
        this.table = new int[size]; //makes 'table' an array of length 'size'
    }   
    public boolean isPrime(double prime){
        if(prime<=1){return false;} //if the number is smaller than or equal to one, it isn't prime 
        for(double i=2;i<prime;i++)
            if(prime%i == 0){return false;} //returns that number isnt prime 
        return true; //returns that the number is prime
    }
    
    /***************************************************************************
    * Accessors & Mutators: Allow access to the fields relevant to the table
    ***************************************************************************/
    public int[] getTable(){return this.table;}
    public int getSize(){return this.size;}
    public int getKeys(){return this.keys;}
    public double getMaxLoad(){return this.maxLoad;}
    public void setTable(int[] n){this.table = n;}
    public void setSize(int n){this.size = n;}
    public void setKeys(int n){this.keys = n;}
    
    /***************************************************************************
    * Method: Inserts the key n in this hash table if the key is not already 
    * there.
    * @param: type Integer @return: none
    ***************************************************************************/
    public void insert(int n){
        if(this.isIn(n) == true || n<=0){return;} //if key already exists in table, or isn't positive, it isnt added
        if(((double)(this.getKeys()+1) / this.getSize()) > this.maxLoad)
            this.rehash(); //if inserting would exceed the max load, the table is rehashed
        else{
            int x = n;
            int y = 0;
            boolean probe = true; //flag for quadratic probing
            while(probe == true){ //runs while the flag is true
                if(this.getTable()[x%this.getSize()] == 0){
                    this.getTable()[x%this.getSize()] = n; //if index is empty, key is placed there
                    probe = false; //flag is made false
                }
                else{y++; x += y*y;} //quadratically increases value to avoid collision on next attempt
            }
            this.setKeys(this.getKeys()+1); //number of keys is updated
        }
    }
     public int insertSim(int n){
        int probes = 0;
        if(this.isIn(n) == true || n<=0){return 0;} //if key already exists in table, or isn't positive, it isnt added
        if(((double)(this.getKeys()+1) / this.getSize()) > this.maxLoad)
            this.rehash(); //if inserting would exceed the max load, the table is rehashed
        else{
            int x = n;
            int y = 0;
            boolean probe = true; //flag for quadratic probing
            while(probe == true){ //runs while the flag is true
                if(this.getTable()[x%this.getSize()] == 0){
                    this.getTable()[x%this.getSize()] = n; //if index is empty, key is placed there
                    probe = false; //flag is made false
                }
                else{y++; x += y*y;probes++;} //quadratically increases value to avoid collision on next attempt
            }
            this.setKeys(this.getKeys()+1); //number of keys is updated
        }
        return probes;
    }
    /***************************************************************************
    * Method: Allocates a bigger table and hashes all the old keys to the new
    * table.
    * @param: none @return: none
    ***************************************************************************/
    public void rehash(){
        System.out.println("Table was rehashed.");
        int[] temp = this.getTable(); //temporarily stores hash table
        int[] larger = new int[2*this.getSize()]; //creates new hash table with twice the size
        this.setTable(larger); //sets larger table as the hash table
        this.setSize(2*this.getSize()); //updates size of table
        int tempKeys = this.getKeys(); //temporarily stores number of keys
        
        for(int i=0;i<temp.length;i++){
            if(temp[i]!=0) 
                this.insert(temp[i]); //hashes old keys to new table
        }
        this.setKeys(tempKeys); //updates the number of keys
    }
    /***************************************************************************
    * Method: Returns true if key n is in this hash table, and returns false 
    * otherwise.
    * @param: type Integer @return: type boolean
    ***************************************************************************/
    public boolean isIn(int n){
        if(this.getSize() == 0)
            return false;
        for(int i = n%this.getSize(); i<this.getSize(); i++){ //scans through the hash table
            if(this.getTable()[i%this.getSize()]== 0)//uses the hash function to check for key
                return false;
            else if(this.getTable()[i%this.getSize()] == n) 
                return true; //key is found 
        }
        return false;
    }
    /***************************************************************************
    * Method: Prints all keys stored in this table, in no particular order.
    * @param: none @return: none
    ***************************************************************************/
    public void printKeys(){
        System.out.println("Keys in the hash table: ");
        for(int i=0;i<this.getSize();i++){ //scans through the hash table
            if(this.getTable()[i] != 0)
                System.out.print(this.getTable()[i] + " "); //if there is a key, prints it
        }
        System.out.print("\n");
    }
    /***************************************************************************
    * Method: Print all keys stored in this table, and the array index in which
    * each key is stored, in increasing order of array indexes.
    * @param: none @return: none
    ***************************************************************************/
    public void printKeysAndIndexes(){
        System.out.println("Keys and indexes in the hash table: ");
        for(int i=0;i<this.getSize();i++){ //scans through the hash table
            if(this.getTable()[i] != 0)
                System.out.println("["+i+"] -> "+this.getTable()[i]); //if there is a key, prints it and the index
        }
    }
}

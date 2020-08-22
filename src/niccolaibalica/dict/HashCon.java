package src.niccolaibalica.dict;

import java.lang.reflect.Array;

public class HashCon<V> implements Dictionary<V> {

  class ConHashEntry<V>{
      ConHashEntry next;
      String key;
      V data;

      /* Constructor */
      ConHashEntry(String key, V data)
      {
          this.key = key;
          this.data = data;
          this.next = null;
      }

      public V getData(){
          return this.data;
      }

  }

  private int TABLE_SIZE;
  private int size;
  private ConHashEntry[] table;
  final Class<V> param;

  /* Constructor */
  public HashCon(int ts,Class<V> p) {
        size = 0;
        TABLE_SIZE = ts;
        table = new ConHashEntry[TABLE_SIZE];
        this.param = p;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
  }

  //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ U T I L S $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    /* Function to get number of key-value pairs */
  public int getSize(){
        return size;
  }
    /* Function to clear hash table */
  public void makeEmpty(){
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
  }

  private int myHash(String x){
       int hashVal = x.hashCode( );
       hashVal %= TABLE_SIZE;
       if (hashVal < 0)
           hashVal += TABLE_SIZE;
       return hashVal;
   }

   /* Function to print hash table */
   public void printHashTable(){
       for (int i = 0; i < TABLE_SIZE; i++)
       {
           System.out.print("\nBucket "+ (i + 1) +" : ");
           ConHashEntry entry = table[i];
           while (entry != null)
           {
               System.out.print(entry.key +" ");
               entry = entry.next;
           }
       }
   }

   public int count(){
     return getSize();
   }

   // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ R I C E R C A $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

   public V search(String k) throws ExceptionKeyNotFound{
     int hash = (myHash(k) % TABLE_SIZE);
        if (table[hash] == null)
            throw new ExceptionKeyNotFound();
        else
        {
            ConHashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(k))
                entry = entry.next;
            if (entry == null)
                throw new ExceptionKeyNotFound();
            else
                return ((ConHashEntry<V>)entry).data;
        }
   }

   // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ D E L E T E $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

   public void delete(String k){
     int hash = (myHash(k) % TABLE_SIZE);
        if (table[hash] != null)
        {
            ConHashEntry prevEntry = null;
            ConHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(k))
            {
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(k))
            {
                if (prevEntry == null)
                    table[hash] = entry.next;
                else
                    prevEntry.next = entry.next;
                size--;
            }
        }
   }

   // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ I N S E R I M E N T O $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

   public void insert(String k, V val){
     int hash = (myHash(k) % TABLE_SIZE);
        if (table[hash] == null)
            table[hash] = new ConHashEntry(k, val);
        else
        {
             ConHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(k))
                entry = entry.next;
            if (entry.key.equals(k))
                entry.data = val;
            else
                entry.next = new ConHashEntry(k, val);
        }
        size++;
   }

   //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ A R R A Y $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

   public V[] toArray(){ //(!!) da testare
    int n = getSize();

     V[] arr = null;
     arr = (V[]) Array.newInstance(param, n);

     int k = 0;

     for (int i = 0; i < TABLE_SIZE; i++)
     {
         ConHashEntry entry = table[i];
         while (entry != null)
         {
             arr[k] = (V)entry.getData();
             entry = entry.next;
             k++;
         }
     }

     return arr;
   }


}
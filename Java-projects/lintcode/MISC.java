import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MISC {
	/*
	 * There are N gas stations along a circular route, where the amount of gas
	 * at station i is gas[i].
	 * 
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
	 * travel from station i to its next station (i+1). You begin the journey
	 * with an empty tank at one of the gas stations.
	 * 
	 * Return the starting gas station's index if you can travel around the
	 * circuit once, otherwise return -1.
	 * 
	 * Note: The solution is guaranteed to be unique.
	 */

	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas == null || cost == null || gas.length != cost.length)
			return -1;
		int i = 0, j = 0;
		int sum = 0;
		int total = 0;
		while (j < gas.length) {
			int diff = gas[j] - cost[j];
			if (sum + diff < 0) {
				i = j + 1;
				sum = 0;
			} else {
				sum += diff;
			}
			j++;
			total += diff;
		}
		return total >= 0 ? i : -1;
	}

	/*
	 * Least Recently Used (LRU) cache implementation
	 */

	class LRUCache<K, V> {
		// ~ Instance fields
		// ----------------------------------------------------------------------------

		private LinkedHashMap<K, LRUCacheEntry<K, V>> internalHashMap;

		// entries are linked by access order
		private java.util.LinkedList<LRUCacheEntry<K, V>> internalList;
		private int capacity;

		// ~ Constructors
		// -------------------------------------------------------------------------------

		public LRUCache(final int capacity) {
			this.internalHashMap = new LinkedHashMap<K, LRUCacheEntry<K, V>>();
			this.internalList = new java.util.LinkedList<LRUCacheEntry<K, V>>();
			this.capacity = capacity;
		}

		// ~ Methods
		// ------------------------------------------------------------------------------------

		/*
		 * 1. retrieve hashmap's cache entry 2. adjust entries's order in the
		 * internal doubly linked list
		 */
		public V get(K key) {
			LRUCacheEntry<K, V> cacheEntry = internalHashMap.get(key);

			// remove the cache entry in O(1)
			// why we can do this? because inside the cache entry, we have
			// previous and next pointers
			LRUCacheEntry<K, V> previousEntry = cacheEntry.getPrevious();
			LRUCacheEntry<K, V> nextEntry = cacheEntry.getNext();
			nextEntry.setPrevious(previousEntry);
			previousEntry.setNext(nextEntry);
			// set found cache entry's next and previous null values
			cacheEntry.setNext(null);
			cacheEntry.setPrevious(null);

			// move the cahce entry to the end of the doubly linked list
			internalList.addLast(cacheEntry);

			return cacheEntry.getValue();
		}

		/*
		 * insert a new cache entry into the LRU cache 1. update hashmap 2.
		 * update linkedlist
		 */
		public void put(LRUCacheEntry<K, V> cacheEntry) {
			internalHashMap.put(cacheEntry.getKey(), cacheEntry);
			internalList.addLast(cacheEntry);
		}

		/*
		 * delete least recently used entry
		 */
		public void deleteLRUEntry() {
			// remove the LRU entry's reference from internal list
			LRUCacheEntry<K, V> cacheEntry = internalList.removeFirst();
			// remove the LRU entry's reference from hash map
			internalHashMap.remove(cacheEntry.getKey());

			// now the cache entry can be garbage collected
		}
	}

	class LRUCacheEntry<K, V> {
		// ~ Instance fields
		// ----------------------------------------------------------------------------

		private K key;
		private V value;
		private LRUCacheEntry<K, V> next;
		private LRUCacheEntry<K, V> previous;

		// ~ Methods
		// ------------------------------------------------------------------------------------

		public void setKey(K key) {
			this.key = key;
		}

		public K getKey() {
			return key;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public V getValue() {
			return value;
		}

		public void setNext(LRUCacheEntry<K, V> next) {
			this.next = next;
		}

		public LRUCacheEntry<K, V> getNext() {
			return this.next;
		}

		public void setPrevious(LRUCacheEntry<K, V> previous) {
			this.previous = previous;
		}

		public LRUCacheEntry<K, V> getPrevious() {
			return previous;
		}
	}

	/*
	 * Candy
	 * 
	 * There are N children standing in a line. Each child is assigned a rating
	 * value.
	 * 
	 * You are giving candies to these children subjected to the following
	 * requirements:
	 * 
	 * Each child must have at least one candy. Children with a higher rating
	 * get more candies than their neighbors. What is the minimum candies you
	 * must give?
	 */

	public int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0) {
			return 0;
		}

		int[] candies = new int[ratings.length];
		// every child should has at least one candy
		for (int i = 0; i < candies.length; i++) {
			candies[i] = 1;
		}
		// if child i has rating higher than i-1, which should 1 bigger than its
		// left neighbour
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}
		// if child i has rating higher than its right neighbour, but the
		// candies array did not
		// represented this situation correctly, then correct it.

		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
				candies[i] = candies[i + 1] + 1;
			}
		}
		int total = 0;
		// calculate the total candies needed
		for (int i = 0; i < candies.length; i++) {
			total += candies[i];
		}
		return total;
	}
	
	
	/*
	 * Restore IP Addresses
	 * 
	 */
	
    public ArrayList<String> restoreIpAddresses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<String> res = restoreIPAddresses(s,4);
        if(res==null) res=new ArrayList<String>();
        return res;
    }
    
    public ArrayList<String> restoreIPAddresses(String s, int k){
        assert(k<=4 && k>=1);
        if(s==null||s.length()<k||s.length()>3*k) return null;
        ArrayList<String> res = new ArrayList<String>();
        
        
        for(int i=0; i<Math.min(s.length(),3);i++){
            String num = s.substring(0,i+1);
            if( (i==0 || num.charAt(0)>'0') && Integer.parseInt(num)<=255){
                if(k==1){
                    if(i==s.length()-1) 
                        res.add(num);
                }else{
                    ArrayList<String> remain = restoreIPAddresses(s.substring(i+1),k-1);
                    if(remain!=null){
                        for(String r:remain){
                            String temp = num+'.'+r;
                            res.add(temp);
                        }
                    }
                }
            }else
                break;
        }
        return res;
    }
}

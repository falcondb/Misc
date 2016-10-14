package Cases;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;


public class P {

    public int findMissing(int[] nums) {
        if(nums == null || nums.length ==0 )
            return 0;
        
        for(int i = 0; i< nums.length ; ++i)
            // assume it will not cause integer overflow
            nums[i] ++;
        
        for(int i = 0; i < nums.length; ++i){
            if(Math.abs(nums[i]) -  1 < nums.length) 
            nums[Math.abs(nums[i])-1] *= -1;
        }
            
        for (int i = 0; i < nums.length; ++i)
            if(nums[i] > 0)
                return i;
        return nums.length;
    }
    
    int firstMissingPositive(int A[], int n, int[] c) {
        int i=0;
        int cnt = 0;
        while(i<n) {
            if(A[i]!=i+1 && A[i]>0 && A[i]<=n && A[i]!=A[A[i]-1])
                {
                A[i] = A[i] ^ A[A[i]-1];
                A[A[i]-1] = A[i] ^ A[A[i]-1];
                A[i] = A[i] ^ A[A[i]-1];
                cnt++;
                }
            else
                i++;
        }
        c[0] = cnt;
        for(int j=0; j<n; j++) {
            if(A[j]!=j+1) return j+1;
        }

        return n+1;
    }
    
    public int aplusb(int a, int b) {
        // add negtive cases
        int d = a ^ b;
        int c = a & b;
        int t;
        c <<=1;
        while(c != 0){
            t = d ^ c;
            c = d & c;
            c<<=1;
            d = t;
        }
        return d;
    }
    
    public int digitCounts(int k, int n) {
        int result = 0;
        int base = 1;
        while (n/base > 0) {
            int cur = (n/base)%10;
            int low = n - (n/base) * base;
            int high = n/(base * 10);

            if (cur == k) {
                result += high * base + low + 1;
            } else if (cur < k) {
                result += high * base;
            } else {
                result += (high + 1) * base;
            }
            base *= 10;
        }
        return result;
    }
    
    public long kthPrimeNumber(int k) {
        if(k<0)
            return -1;
        long[] res = new long[k+1];
        res[0] = 1;
        int k3 = 0, k5 = 0, k7 = 0;
        for (int i=1; i<=k; i++) {
            res[i] = Math.min(Math.min(res[k3]*3, res[k5]*5), res[k7]*7);
            if (res[i]/res[k3] == 3) k3++;
            if (res[i]/res[k5] == 5) k5++;
            if (res[i]/res[k7] == 7) k7++;
        }
        return res[k];        
//        long next_ugly_no=1, next_multiple_of_3=3, next_multiple_of_5=5, next_multiple_of_7=7;
//        int i3=0, i5=0, i7=0;
//        long[] ugly = new long[k];
//        ugly[0] = 1;
//        for(int i = 1; i< k ; ++i){
//            next_ugly_no =  Math.min(Math.min(next_multiple_of_3, next_multiple_of_5), next_multiple_of_7);
//            ugly[i] =  next_ugly_no;
//            if(next_ugly_no == next_multiple_of_3){
//                i3++;
//                next_multiple_of_3 =  ugly[i3] * 3;
//            }
//            else  if(next_ugly_no == next_multiple_of_5){
//                i5++;
//                next_multiple_of_5 =  ugly[i5] * 5;
//            }else  if(next_ugly_no == next_multiple_of_7){
//                i7++;
//                next_multiple_of_7 =  ugly[i7] * 7;
//                }
//            }
//            return ugly[k-1];
        }
         
    public void invertBinaryTree(TreeNode root) {
        if (root == null)
            return;
        TreeNode tmp;
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
    
    public int numIslands(boolean[][] grid) {
    if (grid==null || grid.length==0 || grid[0].length==0) return 0;
    int count = 0;
 
    for (int i=0; i<grid.length; i++) {
        for (int j=0; j<grid[0].length; j++) {
            if(grid[i][j] == true){
                count++;
                merge(grid, i, j);
            }
        }
    }
    return count;
}
 
public void merge(boolean[][] grid, int i, int j){
    //validity checking
    if(i<0 || j<0 || i>grid.length-1 || j>grid[0].length-1)
        return;
 
    //if current cell is water or visited
    if(grid[i][j] != true) return;
 
    //set visited cell to '0'
    grid[i][j] = false;
 
    //merge all adjacent land
    merge(grid, i-1, j);
    merge(grid, i+1, j);
    merge(grid, i, j-1);
    merge(grid, i, j+1);
}
    public int numIslands2(boolean[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length ==0)
            return 0;
        int h = grid.length, w = grid[0].length, cnt = 0;
        for(int j = 0; j< h; ++j){
            for(int i = 0; i < w; ++i)
                if(grid[j][i] == true){
                    cnt++;
                    eat(grid, i, j);
                }
        }
        return cnt;
    }
    
    public void eat(boolean[][] grid, int r, int c){
        if(r <0 || c < 0 || r>= grid[0].length || c >= grid.length)
            return;
        if(grid[c][r] == false)
            return;
            
        grid[c][r] = false;

        
        eat(grid, r-1, c);
        eat(grid, r+1, c);
        eat(grid, r , c-1);
        eat(grid, r , c+1);
        
    }
    
    private void printGrid(boolean[][] grid) {
        System.out.println("----------------------");
        for(int j = 0 ; j < grid.length; ++j){
            for(int i = 0; i < grid[0].length; ++i){
                System.out.print(((grid[j][i])?1:0) + " ");
            }
            System.out.println();
        }
        
        System.out.println("----------------------");
    }

    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if(node == null)
            return root;
        
        if (root == null){
            root = node;
        }else{
            if(root.val >= node.val){
                if(root.left == null)
                    root.left = node;
                else
                    insertNode(root.left, node);
            }
            else{
                if(root.right == null)
                    root.right = node;
                else
                insertNode(root.right, node);
            }
        }
        return root;
    }
    
    public boolean isPalindrome(String s) {
        char[] trimed = new char[s.length()];
        int l = 0;
        for(int i = 0; i<s.length(); ++i)
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= '0' && s.charAt(i) <= '9' ){
                trimed[l++] = s.charAt(i);
            }else if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                trimed[l++] = (char)(((int)s.charAt(i)) - 'A' + 'a');       
            }
        System.out.println(new String(trimed));
        l--;
        for(int i = 0; i<l ; ++i, --l)
            if(trimed[i] != trimed[l])
                return false;
        return true;  
    }
    
    public boolean isSubtree2(TreeNode T1, TreeNode T2) {
        if ( T2 == null)
            return true;
        if ( T1 == null)
            return false;

        if(T1.val == T2.val && isSameTree(T1, T2))
            return true;
        if(T1.left != null && isSubtree(T1.left, T2))
            return true;
        if(T1.right != null && isSubtree(T1.right, T2))
            return true;
        return false;
    }
    
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        if ( T2 == null)
            return true;
        if ( T1 == null)
            return false;
        Stack<TreeNode> st = new Stack<TreeNode>();
        
        st.push(T1);
        TreeNode cur;
        while(!st.isEmpty()){
            cur = st.pop();
            if(cur.val == T2.val && isSameTree(cur, T2))
                return true;
            if(cur.left != null)
                st.push(cur.left);
            if(cur.right != null)
                st.push(cur.right);
        }
        return false;
//        if(T1.val == T2.val && isSameTree(T1, T2))
//            return true;
//        if(T1.left != null && isSubtree(T1.left, T2))
//            return true;
//        if(T1.right != null && isSubtree(T1.right, T2))
//            return true;
//        return false;
    }
    
    private boolean isSameTree(TreeNode T1, TreeNode T2){
        if (T1 == null || T2 == null)
            return false;
        Stack<TreeNode> st1 = new Stack<TreeNode>(), st2 = new Stack<TreeNode>();
        
        st1.add(T1); st2.add(T2);
        TreeNode sn = null, tn = null;
        while(!st1.isEmpty() && !st2.isEmpty()){
            sn = st1.pop(); tn = st2.pop();
            if(sn != null && tn != null && sn.val == tn.val){
                st1.push(sn.left);
                st1.push(sn.right);
                st2.push(tn.left);
                st2.push(tn.right);
            }else if( sn != null && tn != null && sn.val != tn.val)
                return false;
            else if(!(sn == null && tn == null))
                return false;
        }
        return true;
    }
    
    public int reverseInteger(int n) {
        if(n<0)
            return - reverseInteger(-n);
        if(n == 0)
            return 0;
        int res = 0;
        for(; n > 0; ){
            if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && n%10 > Integer.MAX_VALUE%10))
                return 0;
            res = res*10 + n%10;
            n = n/10;
        }
        return res;
    }
    
    public int[] plusOne(int[] digits) {
        int ca = 1;
        for(int i = digits.length - 1; i>=0  && ca != 0; --i){
            digits[i] = (digits[i] + ca) % 10;
            ca = digits[i] == 0?1:0;
        }
        if(ca == 0)
            return digits;
        
        int[] res = new int[digits.length+1];
        for(int i = 0; i< digits.length; ++i)
            res[i+1] = digits[i];
        res[0] = 1;
        return res;
    }
    
    public TreeNode sortedArrayToBST(int[] A) {  
        if(A == null || A.length == 0)
            return null;
        
        TreeNode cur = new TreeNode(A[A.length/2]);
        cur.left = sortedArrayToBSTHelper(A, 0, A.length/2-1);
        cur.right = sortedArrayToBSTHelper(A, A.length/2+1, A.length-1);
        
        return cur;
    } 
    
    public TreeNode sortedArrayToBSTHelper(int[] A, int s, int e){
        if(s>e)
            return null;
        TreeNode cur = new TreeNode(A[s + (e-s)/2]);
        cur.left = sortedArrayToBSTHelper(A, s, s + (e-s)/2-1);
        cur.right = sortedArrayToBSTHelper(A, s + (e-s)/2 +1, e);
        
        return cur;
    }
    
    public ListNode addLists(ListNode l1, ListNode l2) {
        int ca = 0, s = 0;
        ListNode res = new ListNode(0), cur=res;
        while(l1 != null || l2 != null){
            s = ca;
            if(l1 != null){
                s += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                s += l2.val;
                l2 = l2.next;
            }
            ListNode n = new ListNode(s%10);
            ca = s > 9? 1:0;
            cur.next = n;
            cur = cur.next;
        }
        if(ca != 0){
            cur.next = new ListNode(ca);
        }
        return res.next;
    }
    public ArrayList<Integer> subarraySum(int[] nums) {
        int agg = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Integer,Integer> sums = new HashMap<Integer,Integer>((int)(nums.length *1.2));
        sums.put(0, -1);
        for(int i = 0; i<nums.length; ++i){
            agg += nums[i];
            if(sums.containsKey(agg)){
                res.add(sums.get(agg)+1);
                res.add(i);
                return res;
            }
            else
                sums.put(agg, i);
        }
        return res;
    }
    public ArrayList<Integer> subarraySum2(int[] nums) {
        if(nums == null || nums.length == 0)
            return null;
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        int agg = 0;
        for(int i = 0; i<nums.length; ++i){
            agg = 0;
            for(int j = i; j < nums.length; ++j){
                agg += nums[j];
                if(agg == 0){
                    res.add(i);
                    res.add(j);
                    return res;
                }
            }
        }
        return res;
    }
    
    public int maxProduct(int[] nums) {
        
        if(nums.length<=0) return 0;
        int ret, curMax, curMin;
        ret = curMax = curMin = nums[0];
        for(int i=1; i<nums.length; i++) {
            int temp = curMax;
            curMax = Math.max(Math.max(curMax*nums[i], curMin*nums[i]), nums[i]);
            curMin = Math.min(Math.min(temp*nums[i], curMin*nums[i]),nums[i]);
            ret = Math.max(ret, curMax);
        }
        return ret;
    }
    
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if(A == null || A.length ==0)
            return -1;
        int is = 0, ds = 0, max = 1;
        for(int i = 1 ; i< A.length; ++i){
            if(A[i] < A[i-1]){
                max = max > (i-is)? max: i-is;
                is = i;
            }else if(A[i] > A[i-1]){
                max = max > (i-ds)? max: i-ds;
                ds = i;
            }
        }
        if(ds != A.length - 1)
            max = max > (A.length-ds)? max: A.length-ds;
        if(is != A.length - 1)
           max = max > (A.length-is)? max: A.length-is;
        
        return max;
    }
    
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        if(A == null)
            return null;
        
        long[] fLeft = new long[A.size()];
        long[] fRight = new long[A.size()];
        ArrayList<Long> res = new ArrayList<Long>();
        if(A.size() == 1){
            res.add((long) A.get(0));
            return res;
        }
            
        fLeft[0] = A.get(0);
        fRight[A.size()-1] = A.get(A.size()-1);
        for(int i = 1; i<A.size(); ++i){
            fLeft[i] = fLeft[i-1] * A.get(i);
        }
        for(int i = A.size() - 2; i>=0 ; --i)
            fRight[i] = fRight[i+1] * A.get(i);
        
        res.add(fRight[1]);
        for(int i = 1; i< A.size() - 1; ++i)
            res.add(fLeft[i-1] * fRight[i+1]);
        if(A.size() > 1)
            res.add(fLeft[A.size()-2]);
        return res;
    }
    
    public String serialize(TreeNode root) {
        if(root == null)
            return new String("#");
        
        StringBuffer sb = new StringBuffer();
        serializeHelper(root, sb);
        return sb.toString();
    }
    public void serializeHelper(TreeNode root, StringBuffer sb) {
        if(root == null)
            sb.append("#");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }
    public TreeNode deserialize(String data) {
        return null;
    }
       
    
    public static void main(String[] args) {
        P p = new P();
        int[] s = {7};
        TreeNode r = p.createBT(s);
        p.printBT(r);
    }
    
    
    
    
    
    private TreeNode createBT(int[] size){
        Random r = new Random();
        
        if(size[0] <= 0)
            return null;
        
        TreeNode root = new TreeNode(r.nextInt(10));
        size[0] --;
        if(r.nextInt(size[0] *2) % size[0] == 0 )
            root.left = null;
        else
            root.left =  createBT(size);
        
        if(r.nextInt(size[0] *2) % size[0] == 0 )
            root.right = null;
        else
            root.right =  createBT(size);
        
        return root;
    }
    
    private void printBT(TreeNode r){
        if(r == null)
            return;
        ArrayList<TreeNode> cur = new ArrayList<TreeNode>();
        ArrayList<TreeNode> nxt = null;
        cur.add(r);
        
        while(!cur.isEmpty()){
            nxt =  new ArrayList<TreeNode>();
            for(TreeNode tn : cur){
                if(tn != null){
                System.out.print(tn.val + " ");
                nxt.add(tn.left);
                nxt.add(tn.right);
                }else{
                    System.out.print("# ");
                }
            }
            System.out.println();
            cur = nxt;
        }
    }
}
//TreeNode root = new TreeNode(0), cur = root, s = new TreeNode(0);
//for(int i = 1; i<11; ++i){
//  cur.right = new TreeNode(i);
//  cur = cur.right;
//}
//cur = s;
//for(int i = 4; i<11; ++i){
//  cur.right = new TreeNode(i);
//  cur = cur.right;
//}
//
//boolean r = new P().isSubtree(root.right, s.right);
//if(r == true)
//  System.out.println("TRUE");
//else
//  System.out.println("FALSE");
//}

final class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int v) {
        this.val = v;
    }
}

final class ListNode {
  int val;
   ListNode next;
 ListNode(int x) {
       val = x;
      next = null;      
    }
}
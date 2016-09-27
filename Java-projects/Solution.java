package Cases;

import java.util.ArrayList;

public class Solution{
    ArrayList<Integer> st = new ArrayList<Integer>();
    ArrayList<Integer> min = new ArrayList<Integer>();


public void push(int number) {
    st.add(number);
    if(min.size() == 0)
        min.add(number);
    else
        min.add(number<min.get(min.size()-1)? number: min.get(min.size()-1));
}

public int pop() {
    if (st.isEmpty())
        return -1;
    min.remove(min.size()-1);
    int res = st.get(st.size()-1);
    st.remove(st.size()-1);
    return res;
}

public int min() {
    return min.get(min.size()-1);
}

public int findPeak(int[] A) {
    if(A == null || A.length <=2)
        return -1;
    int s = 1, e = A.length - 2, mid = 0;
    
    while(s <= e){
        mid = s+ (e-s)/2;
        if(A[mid] > A[mid-1] && A[mid] > A[mid +1])
            return mid;
        else if(A[mid] < A[mid - 1])
            e = mid - 1;
        else
            s = mid + 1;
    }
    return -1;
}

public static void main(String... args){
    
}
}
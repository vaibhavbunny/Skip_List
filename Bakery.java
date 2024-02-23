import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        // TO be completed by students
        int answer = 0;
        SkipList l = new SkipList() ; 
        for(int i = 0 ; i < cakes.size() ; i++){
            int num = cakes.get(i) ; 
            l.insert(num);
            if(l.upperBound(num) != Integer.MAX_VALUE){
                l.delete(l.upperBound(num)) ; 
            }
        }
        SkipListNode pointer = l.head ; 
        while(pointer.next.get(0).value != Integer.MAX_VALUE){
            answer = answer + 1 ; 
            pointer = pointer.next.get(0) ;
        }
        return answer;
    }
}

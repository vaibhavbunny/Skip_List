import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        public boolean delete(int num){
            // TO be completed by students
            if(!search(num)){
                return false;
            }
            else{
                int h = height - 1 ;
                SkipListNode predecessor = head ; 
                SkipListNode successor = head ; 
                while(predecessor.next.get(0).value != num){
                    successor = predecessor ; 
                    while(num > successor.value){
                        predecessor = successor ; 
                        successor = successor.next.get(h) ; 
                    }
                    h = h - 1 ;
                }
                SkipListNode requiredNode = predecessor.next.get(0) ; 
                int requiredNodeHeight = requiredNode.height ; 
                int h1 = requiredNodeHeight - 1; 
                SkipListNode firstListNode = head ; 
                SkipListNode seconListNode = head ; 
                while(h1 >= 0){
                    seconListNode = firstListNode ; 
                    while(num > seconListNode.value){
                        firstListNode = seconListNode ; 
                        seconListNode = seconListNode.next.get(h1) ;
                    }
                    firstListNode.next.remove(h1) ; 
                    firstListNode.next.add(h1, seconListNode.next.get(h1));
                    h1 = h1 - 1 ; 
                }
                return true ; 
            }
        }

        public boolean search(int num){
            // TO be completed by students
            SkipListNode predecessor = head ;
            SkipListNode successor = head ; 
            int h = height-1 ; 
            while(h >= 0){
                successor = predecessor ; 
                while(num > successor.value){
                    predecessor = successor ; 
                    successor = successor.next.get(h) ; 
                    if(successor == null){
                        successor = predecessor ; 
                        break ; 
                    }
                }
                if(predecessor.value == num){
                    return true ;
                }
                else if(successor.value == num){
                    return true ; 
                }
                else{
                    h = h - 1 ; 
                }
            }
            return false;
        }
        public Integer upperBound(int num){ 
            // TO be completed by students    
            if(!search(num)){
                return Integer.MAX_VALUE;
            }
            else{
                SkipListNode predecessor = head ;
                SkipListNode successor = head ; 
                int h = height-1 ; 
                while(h >= 0){
                    successor = predecessor ; 
                    while(num >= successor.value){
                        predecessor = successor ; 
                        successor = successor.next.get(h) ; 
                    }
                    h = h - 1 ; 
                }
                return predecessor.next.get(0).value ; 
            }
            
        }

        public void insert(int num){
            // TO be completed by students
            int t = 0 ;
            while(t < height){
                if(randomizer.binaryRandomGen()){
                    t = t + 1 ; 
                }
                else{
                    break ; 
                }
            }
            if(t == height){
                height = height + 1 ;
                head.height = head.height + 1 ; 
                tail.height = tail.height + 1 ;
                head.next.add(t,tail);
                tail.next.add(t,null);
            }
            SkipListNode predecessor = head ;
            SkipListNode successor = head ; 
            int h = t ; 
            SkipListNode newNode = new SkipListNode(num,t+1) ; 
            for(int i = 0 ; i < t+1 ; i++){
                newNode.next.add(i,null) ; 
            }
            while(h >= 0){
                successor = predecessor ; 
                while(num > successor.value){
                    predecessor = successor ; 
                    successor = successor.next.get(h) ;  
                }
                predecessor.next.remove(h) ;
                predecessor.next.add(h, newNode);
                newNode.next.remove(h) ; 
                newNode.next.add(h,successor);
                h = h - 1 ;
            }
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");  
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }

}
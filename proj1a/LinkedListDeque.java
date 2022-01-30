public class LinkedListDeque<T> {
    public class TypeNode {
        public T item;
        public TypeNode next;
        public TypeNode prev;

        public TypeNode(T i){
            item = i;
        }
    }
    private TypeNode sentinel;
    private int size;

    // creates an empty LinkedListDeque.
    public LinkedListDeque(){
        sentinel = new TypeNode(null);
        sentinel.next= sentinel;
        sentinel.prev= sentinel;
        size=0;
    }

    public void addFirst(T item){
        TypeNode temp = sentinel.next;
        sentinel.next = new TypeNode(item);
        sentinel.next.next = temp;
        sentinel.next.prev = sentinel;
        temp.prev = sentinel.next;
        if (sentinel.prev==sentinel){
            sentinel.prev=sentinel.next;
        }
        size+=1;
    }

    public void addLast(T item){
        TypeNode temp = sentinel.prev;
        sentinel.prev = new TypeNode(item);
        sentinel.prev.next = sentinel;
        sentinel.prev.prev = temp;
        temp.next = sentinel.prev;
        size+=1;
    }

    public boolean isEmpty(){
        if (sentinel.next==sentinel){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TypeNode p = sentinel.next;
        for (int i=size(); i>0;i--){
            System.out.print(p.item+" ");
            p=p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        TypeNode temp = sentinel.next;
        sentinel.next = temp.next;
        if(temp.next==sentinel){
            sentinel.prev=sentinel;
        }
        size-=1;
        return temp.item;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        TypeNode temp = sentinel.prev;
        sentinel.prev =temp.prev;
        if (temp.prev==sentinel){
            sentinel.next=sentinel;
        }
        size-=1;
        return temp.item;
    }

    public T get(int index){
        if (index>size-1){
            return null;
        }
        while (index>0){
            sentinel.next=sentinel.next.next;
            index--;
        }
        return sentinel.next.item;
    }
    //constructor - creates a deep copy
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new TypeNode(null);
        sentinel.next= sentinel;
        sentinel.prev= sentinel;
        size=0;

        for (int i=0; i<other.size();i++){
            addLast((T) other.get(i));
        }
    }

    public T getRecursive(int index){
        if (index>size-1){
            return null;
        }
        if (index==0){
            return sentinel.item;
        }
        sentinel = sentinel.next;
        return getRecursive(index-1);
    }

}

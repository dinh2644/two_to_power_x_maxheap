import java.util.Arrays;

public class MaxHeap {
 
     // ************************************* FIELDS ************************************

    int[] Heap;
    int size;
    int CHILD_ARITY;
    int parent(int i) { return (i - 1) / CHILD_ARITY; }
    // NOTE: CHILD_ARITY * i + CHILD_POS; 
    // is how you would calculate to get each child up to CHILD_ARITY, used in heapifyDown after extractMax

    // ************************************ CONSTRUCTOR ************************************

    public MaxHeap(int CHILD_ARITY) {
        this.CHILD_ARITY = CHILD_ARITY; // use "this" to avoid ambiguity w/ passed in arguments that may have same name
        size = 0;
        Heap = new int[10];
    }
    
    // ************************************ HEAP METHODS ************************************

    public void insert(int element) {
        Heap[size] = element;
        int current = size;
        heapifyUp(current);
        size++;
    }

    public void heapifyUp(int i){
        int temp = Heap[i];
        while (i > 0 && temp > Heap[parent(i)]){
            Heap[i] = Heap[parent(i)];
            i = parent(i);
        }
        Heap[i] = temp; 
    }

    public int popMax() {
        if (size <= 0) throw new IllegalStateException("Heap is empty!");
        int max = Heap[0];
        Heap[0] = Heap[size - 1];
        Heap[size - 1] = max; 
        heapifyDown(0);
        size--;
        return max;
    }

    public void heapifyDown(int i){
        // Will represent new largest child if any 
        int parent = i;
        
        for (int childPos = 1; childPos <= CHILD_ARITY; childPos++) {
                int currentChild = CHILD_ARITY * i + childPos;
                if (currentChild < size && Heap[currentChild] > Heap[parent]){
                    parent = currentChild;
                }
            }

        if (parent != i){
                int temp = Heap[i];
                Heap[i] = Heap[parent]; // move new largest child up to parent
                Heap[parent] = temp; // move old parent down to largest child's position
            }
    }

    public void Print() {
        if (size <= 0) throw new IllegalStateException("Heap is empty!");

        for (int i = 0; i < size; i++)
        {
            System.out.println(Heap[i]);
        }
        
    }

    // ************************************ MAIN ************************************
    public static void main(String[] args) {
        MaxHeap mh = new MaxHeap(4);
        mh.insert(1);
        mh.insert(4);
        mh.insert(2);
        mh.insert(5);
        mh.insert(13);
        mh.insert(6);
        mh.insert(17);

        System.out.println("Heap: " + Arrays.toString(mh.Heap));
        System.out.println("Heap Size: " + mh.size);
        System.out.println("Popped Max: " + mh.popMax());
        System.out.println("New Heap: " + Arrays.toString(mh.Heap));
    }
}

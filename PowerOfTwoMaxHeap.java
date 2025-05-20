import java.util.ArrayList;

public class PowerOfTwoMaxHeap {
 
     // ************************************* FIELDS ************************************

    ArrayList<Integer> Heap; 
    int CHILD_ARITY;
    int parent(int i) { return (i - 1) / CHILD_ARITY; }
    /*  
        NOTE: 
        CHILD_ARITY * i + CHILD_POS; 
        Is how we will calculate to get each child of a parent up to CHILD_ARITY, used in heapifyDown after extractMax 
     */

    // ************************************ CONSTRUCTOR ************************************

    public PowerOfTwoMaxHeap(int CHILD_ARITY) {
        this.CHILD_ARITY = CHILD_ARITY;
        this.Heap = new ArrayList<Integer>();
    }
    
    // ************************************ HEAP METHODS ************************************

    // Insert new element at end of array, heapifyUp from there
    public void insert(int element) {
        Heap.add(element);
        int current = Heap.size() - 1;
        heapifyUp(current);
    }

    // Compares i to its parent, continue swapping if i > parent
    public void heapifyUp(int i){
        int temp = Heap.get(i);
        while (i > 0 && temp > Heap.get(parent(i))){
            Heap.set(i, Heap.get(parent(i))); // set current child value to parent value
            i = parent(i); // move up child index become parent index
        }
        Heap.set(i, temp); // set the child -- now the new parent, its value, completing the swap
    }

    // Returns first element (max), swaps places with last element, and heapify down from root (index 0) until max heap property is satisifed
    public int popMax() {
        if (Heap.size() <= 0) {
            System.out.println("HEAP IS EMPTY!!!"); return 0; 
        } 
        int max = Heap.get(0);
        Heap.set(0, Heap.get(Heap.size() - 1)); // set root to last element
        heapifyDown(0);
        Heap.remove(Heap.size() - 1); // chop off last element (no need to set it to the old max)
        return max;
    }

    // Will have a variable to keep track of largest chilren, if any, swaps places with largest child, and continues until max heap property is satisifed
    public void heapifyDown(int i){
        // Will represent new largest child if any 
        int largest = i;
        
        for (int childPos = 1; childPos <= CHILD_ARITY; childPos++) {
                int currentChild = CHILD_ARITY * i + childPos;
                if (currentChild < Heap.size() && Heap.get(currentChild) > Heap.get(largest)){
                    largest = currentChild;
                }
            }

        if (largest != i){
                int temp = Heap.get(i); // old parent value
                Heap.set(i, Heap.get(largest)); // move new largest child up to parent
                Heap.set(largest, temp); // move old parent down to largest child's position
                heapifyDown(largest);

            }     
    }

    public void Print() {
        if (Heap.size() <= 0) {
            System.out.println("HEAP IS EMPTY!!!"); 
            return; 
        } 
        for (int i = 0; i < Heap.size(); i++)
        {
            System.out.println(Heap.get(i));
        }
    }

    public boolean isValidMaxHeap() {
    for (int i = 0; i < Heap.size(); i++) {
        for (int childPos = 1; childPos <= CHILD_ARITY; childPos++) {
            int childIndex = CHILD_ARITY * i + childPos;
            if (childIndex < Heap.size() && Heap.get(i) < Heap.get(childIndex)) {
                return false;
            }
        }
    }
    return true;
}

    // ************************************ MAIN ************************************

    public static void main(String[] args) {
        // PowerOfTwoMaxHeap mh = new PowerOfTwoMaxHeap(4); // CHILD_ARITY = x
        // mh.insert(1);
        // mh.insert(4);
        // mh.insert(2);
        // mh.insert(5);
        // mh.insert(13);
        // mh.insert(6);
        // mh.insert(17);

        // System.out.println("Heap: " + mh.Heap.toString());
        // System.out.println("Heap Size: " + mh.Heap.size());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax());
        // System.out.println("New Heap: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax()); // Should print HEAP IS EMPTY!!!
        // System.out.println("New Heap: " + mh.Heap.toString());

        // // Insert new values

        // mh.insert(35);
        // mh.insert(33);
        // mh.insert(42);
        // mh.insert(10);
        // mh.insert(14);
        // mh.insert(19);
        // mh.insert(27);
        // mh.insert(44);
        // mh.insert(26);
        // mh.insert(31);

        // System.out.println("Heap after new values: " + mh.Heap.toString());
        // System.out.println("Popped Max: " + mh.popMax()); 
        // System.out.println("New Heap: " + mh.Heap.toString()); 
        // System.out.println("Popped Max: " + mh.popMax()); 
        // System.out.println("New Heap: " + mh.Heap.toString()); 

        // Edge case
        System.out.println("\n--- EDGE CASE: Insert Descending Order ---");
        PowerOfTwoMaxHeap edgeHeap = new PowerOfTwoMaxHeap(4);
        for (int i = 100; i >= 1; i--) {
            edgeHeap.insert(i);
        }
        System.out.println("Heap after inserting 100 to 1 descending: " + edgeHeap.Heap.toString());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Popped Max: " + edgeHeap.popMax());
        System.out.println("Heap after popping max: " + edgeHeap.Heap.toString());
        System.out.println("Heap valid after inserts: " + edgeHeap.isValidMaxHeap());

    }
}
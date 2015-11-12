package textgen;

public class MeOwnTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyLinkedList<String> myOwnList = new MyLinkedList<String>();
		myOwnList.add("String#0");
		myOwnList.add("String#1");
		myOwnList.add("String#2");
		myOwnList.add("String#3");
		myOwnList.add("String#4");
		
		//String indexOfTail = myOwnList.get(1);
		int indexOfTail = myOwnList.tail.indexNode;
		int indexOfHead = myOwnList.head.indexNode;
		
		System.out.println("size" + "\t" + myOwnList.size());
		System.out.println("index of tail" + "\t" + indexOfTail);
		System.out.println("index of head" + "\t" + indexOfHead);
		
		System.out.print("content of tail" + "\t");
        myOwnList.tail.displayNode();
        
        //System.out.print("content of head" + "\t");
        //myOwnList.head.displayNode();
        
        LLNode<String> searchNode_0 = myOwnList.recFindIndex(0, myOwnList.tail);
        LLNode<String> searchNode_1 = myOwnList.recFindIndex(1, myOwnList.tail);
        LLNode<String> searchNode_2 = myOwnList.recFindIndex(2, myOwnList.tail);
        //LLNode<String> searchNode_5 = myOwnList.recFindIndex(5, myOwnList.head, myOwnList.tail);
        
        System.out.println();
        System.out.println();
        System.out.println("index of searched Node #0" + "\t" + searchNode_0.indexNode);
        //searchNode_0.displayNode();
        
        System.out.println();
        System.out.println();
        System.out.println("index of searched Node #1" + "\t" + searchNode_1.indexNode);
        searchNode_1.displayNode();
        
        System.out.println();
        System.out.println();
        System.out.println("index of searched Node #2" + "\t" + searchNode_2.indexNode);
        searchNode_2.displayNode();
        
       // System.out.println();
       // System.out.println();
        //System.out.println("index of searched Node #5" + "\t" + searchNode_5.indexNode);
        //searchNode_5.displayNode();
        
        System.out.println();
        System.out.println();        
        System.out.println("getting an Element at index 0" + "\t" + myOwnList.get(0));
        
        
		//System.out.println("content of head" + "\t");
		//myOwnList.head.displayNode();
        
        
        
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        MyLinkedList<String> myOwnListSingle = new MyLinkedList<String>();
		myOwnListSingle.add("First String at index 0");
		int indexOfTailSingle = myOwnListSingle.tail.indexNode;
		int indexOfHeadSingle = myOwnListSingle.head.indexNode;
		
		System.out.println("size" + "\t" + myOwnListSingle.size());
		System.out.println("index of tail" + "\t" + indexOfTailSingle);
		System.out.println("index of head" + "\t" + indexOfHeadSingle);
		
		System.out.print("content of tail" + "\t");
        myOwnListSingle.tail.displayNode();
        
		//System.out.print("content of head" + "\t");
        //myOwnListSingle.head.displayNode();
        
        System.out.println();
        System.out.println();        
        System.out.println("getting an Element at index 0" + "\t" + myOwnListSingle.get(0));
        
        myOwnListSingle.add("A");
        myOwnListSingle.add("B");
        myOwnListSingle.add("C");
        myOwnListSingle.add("D");
        myOwnListSingle.add("E");
        myOwnListSingle.add("F");
        
        System.out.println("size" + "\t" + myOwnListSingle.size());
		System.out.println("index of tail" + "\t" + indexOfTailSingle);
		System.out.println("index of head" + "\t" + indexOfHeadSingle);
		System.out.println("getting an Element at index 0" + "\t" + myOwnListSingle.get(0));
		System.out.println("getting an Element at index 1" + "\t" + myOwnListSingle.get(1));
		System.out.println("getting an Element at index 2" + "\t" + myOwnListSingle.get(2));
		System.out.println("getting an Element at index 3" + "\t" + myOwnListSingle.get(3));
		System.out.println("getting an Element at index 4" + "\t" + myOwnListSingle.get(4));
		System.out.println("getting an Element at index 5" + "\t" + myOwnListSingle.get(5));
		System.out.println("getting an Element at index 6" + "\t" + myOwnListSingle.get(6));
		
		
		System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        myOwnListSingle.add(7,"XXX");
        
        System.out.println("size" + "\t" + myOwnListSingle.size());
		System.out.println("index of tail" + "\t" + myOwnListSingle.tail.indexNode);
		System.out.println("index of head" + "\t" + myOwnListSingle.head.indexNode);
		System.out.println("getting an Element at index 0" + "\t" + myOwnListSingle.get(0));
		System.out.println("getting an Element at index 1" + "\t" + myOwnListSingle.get(1));
		System.out.println("getting an Element at index 2" + "\t" + myOwnListSingle.get(2));
		System.out.println("getting an Element at index 3" + "\t" + myOwnListSingle.get(3));
		System.out.println("getting an Element at index 4" + "\t" + myOwnListSingle.get(4));
		System.out.println("getting an Element at index 5" + "\t" + myOwnListSingle.get(5));
		System.out.println("getting an Element at index 6" + "\t" + myOwnListSingle.get(6));
		System.out.println("getting an Element at index 7" + "\t" + myOwnListSingle.get(7));
		System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
		myOwnListSingle.add(4,"YYY");
		System.out.println("size" + "\t" + myOwnListSingle.size());
		System.out.println("index of tail" + "\t" + myOwnListSingle.tail.indexNode);
		System.out.println("index of head" + "\t" + myOwnListSingle.head.indexNode);
		System.out.println("getting an Element at index 0" + "\t" + myOwnListSingle.get(0));
		System.out.println("getting an Element at index 1" + "\t" + myOwnListSingle.get(1));
		System.out.println("getting an Element at index 2" + "\t" + myOwnListSingle.get(2));
		System.out.println("getting an Element at index 3" + "\t" + myOwnListSingle.get(3));
		System.out.println("getting an Element at index 4" + "\t" + myOwnListSingle.get(4));
		System.out.println("getting an Element at index 5" + "\t" + myOwnListSingle.get(5));
		System.out.println("getting an Element at index 6" + "\t" + myOwnListSingle.get(6));
		System.out.println("getting an Element at index 7" + "\t" + myOwnListSingle.get(7));
		System.out.println("getting an Element at index 8" + "\t" + myOwnListSingle.get(8));
		System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
		myOwnListSingle.add(6,"ZZZ");
		System.out.println("size" + "\t" + myOwnListSingle.size());
		System.out.println("index of tail" + "\t" + myOwnListSingle.tail.indexNode);
		System.out.println("index of head" + "\t" + myOwnListSingle.head.indexNode);
		System.out.println("getting an Element at index 0" + "\t" + myOwnListSingle.get(0));
		System.out.println("getting an Element at index 1" + "\t" + myOwnListSingle.get(1));
		System.out.println("getting an Element at index 2" + "\t" + myOwnListSingle.get(2));
		System.out.println("getting an Element at index 3" + "\t" + myOwnListSingle.get(3));
		System.out.println("getting an Element at index 4" + "\t" + myOwnListSingle.get(4));
		System.out.println("getting an Element at index 5" + "\t" + myOwnListSingle.get(5));
		System.out.println("getting an Element at index 6" + "\t" + myOwnListSingle.get(6));
		System.out.println("getting an Element at index 7" + "\t" + myOwnListSingle.get(7));
		System.out.println("getting an Element at index 8" + "\t" + myOwnListSingle.get(8));
		System.out.println("getting an Element at index 9" + "\t" + myOwnListSingle.get(9));
	}

}

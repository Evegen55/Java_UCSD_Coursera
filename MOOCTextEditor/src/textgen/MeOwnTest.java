package textgen;

public class MeOwnTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyLinkedList<String> myOwnList = new MyLinkedList<String>();
		myOwnList.add("String#1");
		myOwnList.add("String#2");
		myOwnList.add("String#3");
		myOwnList.add("String#4");
		myOwnList.add("String#5");
		
		int indexOfTail = myOwnList.tail.indexNode;
		int indexOfhead = myOwnList.head.indexNode;
		
		System.out.println("size" + "\t" + myOwnList.size());
		System.out.println("index of tail" + "\t" + indexOfTail);
		System.out.println("index of head" + "\t" + indexOfhead);
		
		System.out.print("content of tail" + "\t");
        myOwnList.tail.displayNode();
        
		//System.out.println("content of head" + "\t");
		//myOwnList.head.displayNode();

	}

}

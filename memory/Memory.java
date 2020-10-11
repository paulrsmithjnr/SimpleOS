package simpleos.memory;
import java.util.ArrayList;
import simpleos.processor.Instruction;

public abstract class Memory {

    //ATTRIBUTES

    //Changed the initial array of integers to an arraylist of objects so that it can store both instructions and integers
    //It's static so that all objects of this class can reference the same arraylist (making it more realistic)
    protected static ArrayList<Object> memloc; 
    protected int size;

    /**
     * Empty constructor
     */
    public Memory() {}

    /**
     * Constructor that initializes the size and contents of memory
     * @param size The size of memory
     */
    public Memory(int size){
        System.out.println("Initializing memory size...");
        this.size=size;

        //initializing memory blocks to null
        memloc = new ArrayList<Object>();
        for(int i = 0; i < size; i++) {
            memloc.add(null);
        }
    }

    /**
     * Getter
     * @return int The size of memory
     */
    public int getSize(){
        return size;
    }

    /**
     * Method to return the value at a particular location in memory
     * @param index The index/address of the value to be returned
     * @return int The value at the index given
     */
    public int getValue(int index){
        try {
            return (int) Memory.memloc.get(index);
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Memory location does not exist!");
            return -1;
        }
        
    }

    /**
     * Method to return the instruction at a location in memory
     * @param index The index/address of the instruction to be returned
     * @return Instruction The instruction at the given index
     */
    public Instruction retrieveIntstruction(int index){
        try {
            // return memloc[index];
            return (Instruction) Memory.memloc.get(index);
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Memory location does not exist!");
            return null;
        }
        
    }

    /**
     * Method to set a value at the specified location in memory
     * @param index The index/address to which the value is to be stored
     * @param value The value that is to be stored at the given index
     * @return int 1 if no exceptions were raised, -1 otherwise
     */
    public int setValue(int index, int value) {
        try{
            memloc.set(index, value);
            // System.out.println("Value set successfully!");
            return 1;

        }catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }//set Value

}//end abstract class memory

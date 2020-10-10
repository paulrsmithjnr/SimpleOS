package simpleos.memory;
import java.util.ArrayList;
import simpleos.processor.Instruction;

public abstract class Memory {

    // private static int memloc [];
    protected static ArrayList<Object> memloc;
    protected int size;

    public Memory(int size){
        System.out.println("Initializing memory size...");
        this.size=size;
        // memloc = new int[size];
        //initializing memory blocks to null
        memloc = new ArrayList<Object>();
        for(int i = 0; i < size; i++) {
            memloc.add(null);
        }
    }

    public Memory() {}

    public int getSize(){
        return size;
    }

    public int getValue(int index){
        try {
            // return memloc[index];
            return (int) this.memloc.get(index);
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Memory location does not exist!");
            return -1;
        }
        
    }

    public Object retrieveIntstruction(int index){
        try {
            // return memloc[index];
            return (Instruction) this.memloc.get(index);
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Memory location does not exist!");
            return null;
        }
        
    }



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

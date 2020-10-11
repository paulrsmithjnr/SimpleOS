package simpleos.memory;
import simpleos.processor.Instruction;
import simpleos.processor.Converter;

public class MyMemory extends Memory {

    //ATTRIBUTES
    private Instruction instruction; //only the IR register will use this attribute
    public int stdin = 0; //to simulate input from stdin
    private int data; //only the PC and ACC registers will use this attribute

    /**
     * Constructor for MyMemory objects that are not registers
     * @param size The size of memory
     */
    public MyMemory(int size){
        super(size);
        printSize();
        loadMemory(); //loads memory with instructions to be executed during the instruction cycle
    }

    /**
     * Constructor for MyMemory objects that are registers
     */
    public MyMemory(){
        super();
        this.instruction = new Instruction(); //Only the IR register will use this attribute
    }

    public void printSize(){
        System.out.println("The Size of the memory is: " + getSize());
    }

    /**
     * Getter that only the IR register will use
     * @return Instruction The instruction attribute
     */
    public Instruction getInstruction() {
        return this.instruction;
    }

    /**
     * Setter that only the IR register will use
     * @param instruction The instruction to be stored in the instruction attribute of this class
     */
    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    /**
     * Getter that only the ACC and PC registers will use
     * @return int The contents of the data attribute 
     */
    public int getData() {
        return this.data;
    }

    /**
     * Setter that only the ACC register will use
     * @param data The value to be stored in the data attribute of this class
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Mutator that only the PC register will use
     * - increments the contents of the data attribute by one
     */
    public void increment() {
        this.data++;
    }

    /**
     * Method to load memory with all the instructions needed to generate the fibonacci sequence
     * during the instruction cycle
     * 
     * This method splits memory into two "equal" sections, the first of which stores the instructions (in order)
     * needed to generate the fibonacci sequence and the second section stores the integer values resulting from executing
     * said instructions
     */
    public void loadMemory() {
        Converter c = new Converter(); //object to help with binary to decimal conversions and vice-versa
        
        int midpoint = (int) Math.floor(size/2); //divide memory into two haves
        
        //variables that will be needed
        int opcode;
        int address = midpoint;
        int index=0;

        //Initial instructions
        opcode = c.convertDecToBin(3); //0011
        instruction = new Instruction(opcode, c.convertDecToBin(address)); //load AC from stdin
        Memory.memloc.set(index, instruction); //saving instruction to memory

        opcode = c.convertDecToBin(7); //0111;
        instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to stdout
        Memory.memloc.set(index+1, instruction);

        opcode = c.convertDecToBin(2); //0010
        instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to memory
        Memory.memloc.set(index+2, instruction); //saving instruction to memory

        opcode = c.convertDecToBin(3); //0011
        instruction = new Instruction(opcode, c.convertDecToBin(address)); //load AC from stdin
        Memory.memloc.set(index+3, instruction); //saving instruction to memory

        opcode = c.convertDecToBin(2); //0010
        instruction = new Instruction(opcode, c.convertDecToBin(address+1)); //store AC to memory
        Memory.memloc.set(index+4, instruction); //saving instruction to memory


        //The rest of the instructions
        for (int i = 5; i < midpoint; i = i + 6) {
            index = i;

            if ((index + 5) < midpoint-1) { //if index + 5 is not greater than the midpoint-1

                opcode = c.convertDecToBin(1); //0001
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //load AC from memory
                Memory.memloc.set(index, instruction); //saving instruction to memory
                
                opcode = c.convertDecToBin(5); //0101;
                address++;
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //add to AC from memory
                Memory.memloc.set(index + 1, instruction);

                opcode = c.convertDecToBin(7); //0111;
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to stdout
                Memory.memloc.set(index + 2, instruction);

                opcode = c.convertDecToBin(2); //0010;
                address++;
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to memory
                Memory.memloc.set(index + 3, instruction);

                opcode = c.convertDecToBin(4); //0100;
                address--;
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //subtract from AC from memory
                Memory.memloc.set(index + 4, instruction);

                opcode = c.convertDecToBin(2); //0010;
                address = address + 2;
                instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to memory
                Memory.memloc.set(index + 5, instruction);

                address--;
            }                
        }

        //Loading the final intsruction in memory
        opcode = c.convertDecToBin(15); //1111;
        address++;
        instruction = new Instruction(opcode, c.convertDecToBin(address)); //halt
        Memory.memloc.set(index++, instruction);


    }
}//end abstract class memory

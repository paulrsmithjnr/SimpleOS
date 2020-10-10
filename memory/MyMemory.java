package simpleos.memory;
import simpleos.processor.Instruction;
import simpleos.processor.Converter;

public class MyMemory extends Memory {

        private Instruction instruction;
        private Converter c = new Converter();
        private int data;

        public MyMemory(int size){
            super(size);
            printSize();
            loadMemory();
            this.instruction = null;
            this.data = 0;
        }

        public MyMemory(){
            super();
            this.instruction = new Instruction();
        }

        public void printSize(){
            System.out.println("The Size of the memory is: " + getSize());
        }

        public Instruction getInstruction() {
            return this.instruction;
        }

        public void setInstruction(Instruction instruction) {
            this.instruction = instruction;
        }

        public int getData() {
            return this.data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void increment() {
            this.data++;
        }

        public void loadMemory() {
            int midpoint = (int) Math.floor(size/2);
            int opcode;
            int address = midpoint;
            int index = 0;

            this.memloc.set(midpoint, 0);
            this.memloc.set(midpoint+1, 1);

            System.out.println("\nThe first two values of the fibonacci sequence, i.e. 0 and 1,\nwere preloaded in memory at addresses " + c.convertDecToBin(midpoint) + " and " + c.convertDecToBin(midpoint+1) + " respectively.");

            for (int i = 0; i < midpoint; i = i + 6) {
                index = i;

                if ((index + 5) < midpoint-1) { //if index + 5 is not greater than the midpoint

                    opcode = c.convertDecToBin(1); //0001
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //load AC from memory
                    this.memloc.set(index, instruction); //saving instruction to memory
                    // System.out.println(instruction);
                    
                    opcode = c.convertDecToBin(5); //0101;
                    address++;
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //add to AC from memory
                    this.memloc.set(index + 1, instruction);
                    // System.out.println(instruction);

                    opcode = c.convertDecToBin(7); //0111;
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to stdout
                    this.memloc.set(index + 2, instruction);
                    // System.out.println(instruction);

                    opcode = c.convertDecToBin(2); //0010;
                    address++;
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to memory
                    this.memloc.set(index + 3, instruction);
                    // System.out.println(instruction);

                    opcode = c.convertDecToBin(4); //0100;
                    address--;
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //subtract from AC from memory
                    this.memloc.set(index + 4, instruction);
                    // System.out.println(instruction);

                    opcode = c.convertDecToBin(2); //0010;
                    address = address + 2;
                    instruction = new Instruction(opcode, c.convertDecToBin(address)); //store AC to memory
                    this.memloc.set(index + 5, instruction);
                    // System.out.println(instruction);

                    address--;
                }                
            }

            //Loading the final intsruction in memory, i.e. HALT
            opcode = c.convertDecToBin(15); //1111;
            address++;
            instruction = new Instruction(opcode, c.convertDecToBin(address)); //halt
            this.memloc.set(index++, instruction);
            // System.out.println(instruction);

            // System.out.println("Memloc: " + memloc);

        }
}//end abstract class memory

package simpleos.memory;
import simpleos.processor.Instruction;

public class MyMemory extends Memory {

        private Instruction instruction;
        private int data;

        public MyMemory(int size){
            super(size);
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
            Instruction instruction = new Instruction();
            int index;

            this.memloc.set(midpoint, 0);
            this.memloc.set(midpoint+1, 1);

            for (int i = 0; i < midpoint; i = i + 4) {
                index = i;

                if ((index + 3) < midpoint) { //if index + 3 is not greater than the midpoint

                    opcode = instruction.convertDecToBin(1); //0001
                    instruction = new Instruction(opcode, instruction.convertDecToBin(address)); //load AC from memory
                    this.memloc.set(index, instruction); //saving instruction to memory
                    // System.out.println(instruction);
                    
                    opcode = instruction.convertDecToBin(5); //0101;
                    address++;
                    instruction = new Instruction(opcode, instruction.convertDecToBin(address)); //add to AC from memory
                    this.memloc.set(index + 1, instruction);
                    // System.out.println(instruction);

                    opcode = instruction.convertDecToBin(7); //0111;
                    instruction = new Instruction(opcode); //store AC to stdout
                    this.memloc.set(index + 2, instruction);
                    // System.out.println(instruction);

                    opcode = instruction.convertDecToBin(2); //0010;
                    address++;
                    instruction = new Instruction(opcode, instruction.convertDecToBin(address)); //store AC to memory
                    this.memloc.set(index + 3, instruction);
                    // System.out.println(instruction);

                    address--;
                }
            }

            System.out.println("Memloc: " + memloc);

        }
}//end abstract class memory

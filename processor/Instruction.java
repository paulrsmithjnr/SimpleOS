package simpleos.processor;

public class Instruction {

    //attributes
    private int opcode;
    private int address;

    /**
     * Empty constructor
     */
    public Instruction() {}

    /**
     * Constructor initializes the attributes of the class
     * @param opcode
     * @param address
     */
    public Instruction(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }

    /**
     * Getter
     * @return int The opcode of the instruction
     */
    public int getOpcode() {
        return this.opcode;
    }

    /**
     * Getter
     * @return int The address stored in the instruction
     */
    public int getAddress() {
        return this.address;
    }

    /**
     * toString method
     * @return String The instruction properly formatted in binary
     */
    @Override
    public String toString() {

        //Declarations
        int count = 1;
        int digits = this.opcode;
        String zeroes = "";

        //Counts the number of zeroes in which the opcode needs to be padded by
        digits = (int) Math.floor(digits/10);
        while (digits != 0) {
            count++;
            digits = (int) Math.floor(digits/10);
        }

        int numberOfZeroes = 4 - count;
        if (numberOfZeroes == 1) {
            zeroes = "0";
        }else if (numberOfZeroes == 2) {
            zeroes = "00";
        }else if (numberOfZeroes == 3) {
            zeroes = "000";
        }
        //ALL THE CODE IN THIS METHOD ABOVE THIS COMMENT WAS JUST TO CALCULATE
        //THE NUMBER OF ZEROES TO BE ADDED TO THE OPCODE

        return zeroes + this.opcode + " " + this. address;
    }
}
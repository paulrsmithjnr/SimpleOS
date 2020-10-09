package simpleos.processor;

public class Instruction {
    private int opcode;
    private int address;

    public Instruction() {}

    public Instruction(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }

    public Instruction(int opcode) {
        this.opcode = opcode;
        this.address = -1;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getAddress() {
        return this.address;
    }

    // @Override
    // public String toString() {
    //     return convertBinToDec(this.opcode) + " " + convertBinToDec(this. address);
    // }

    @Override
    public String toString() {
        return this.opcode + " " + this. address;
    }
}
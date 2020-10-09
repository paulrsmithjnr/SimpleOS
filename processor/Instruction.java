package simpleos.processor;

public class Instruction {
    private int opcode;
    private int address;

    public Instruction() {}

    public Instruction(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }

    // public Instruction(int opcode) {
    //     this.opcode = opcode;
    //     this.address = -1;
    // }

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

        int count = 1;
        int digits = this.opcode;
        String zeroes = "";

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

        return zeroes + this.opcode + " " + this. address;
    }
}
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

    public int convertBinToDec(int binaryVal) {
        int currentPower = 0;
        int decimalVal = 0;
        int currentBinVal;
        while (binaryVal != 0) {
            currentBinVal = binaryVal%10;
            binaryVal = (int) Math.floor(binaryVal/10);
            decimalVal = decimalVal + (currentBinVal * (int) Math.pow(2, currentPower));
            currentPower = currentPower + 1; 
        }
        return decimalVal;
    }

    public int convertDecToBin(int decimalVal) {
        int currentPower = 0;
        int binaryVal = 0;
        int remainder;
        if (decimalVal <= 1) {
            return decimalVal;
        }
        while (decimalVal > 0){
            remainder = decimalVal%2;
            decimalVal = (int) Math.floor(decimalVal/2);
            binaryVal = binaryVal + (remainder * (int) Math.pow(10, currentPower));
            currentPower++;
        }
        return binaryVal;
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
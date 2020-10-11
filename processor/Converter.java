package simpleos.processor;

public class Converter {
    /**
     * Empty Constructor
     */
    public Converter() {}
    
    /**
     * Method to convert binary values to decimal
     * @param binaryVal The binary value to be converted
     * @return int The decimal equivalent of the binary value inputted
     */
    public int convertBinToDec(int binaryVal) {
        int currentPower = 0;
        int decimalVal = 0;
        int lastBit;
        while (binaryVal != 0) {
            lastBit = binaryVal%10; //stores the last bit of binaryVal
            binaryVal = (int) Math.floor(binaryVal/10); //removes the last bit from binaryVal since it was previously stored
            decimalVal = decimalVal + (lastBit * (int) Math.pow(2, currentPower)); //multiplies the collected bit by it's binary place value and accumulates the results in decimalVal
            currentPower = currentPower + 1; //increases the power that two will be raised by in order to accurately keep track of the binary place values
        }
        return decimalVal;
    }

    /**
     * Method to convert decimal values to binary
     * @param decimalVal The decimal value to be converted
     * @return int The binary equivalent of the decimal value inputted
     */
    public int convertDecToBin(int decimalVal) {
        int currentPower = 0;
        int binaryVal = 0;
        int remainder;
        if (decimalVal <= 1) {
            return decimalVal;
        }
        while (decimalVal > 0){
            remainder = decimalVal%2; //gets the remainder from dividing the current decimalVal by 2
            decimalVal = (int) Math.floor(decimalVal/2); //replaces decimalVal with the quotient after floor dividing it by 2
            binaryVal = binaryVal + (remainder * (int) Math.pow(10, currentPower)); //compiles the bits of the final solution in their correct order
            currentPower++;
        }
        return binaryVal;
    }
}
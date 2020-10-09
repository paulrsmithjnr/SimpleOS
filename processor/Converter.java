package simpleos.processor;

public class Converter {
    public Converter() {}
    
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
}
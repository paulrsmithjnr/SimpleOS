package simpleos.processor;

import java.util.Scanner;
import simpleos.memory.*;


public class MyProcessor extends Processor {
    private MyMemory m; //wil act as the link between this class and the MyMemory class
    private Converter c = new Converter(); //this object will be used to perform binary to decimal conversions and vice versa
    private MyMemory PC;    
    private MyMemory IR;    
    private MyMemory ACC;
    
    public MyProcessor() {
        try {
            System.out.println("Initializing PC...");
            this.PC = new MyMemory();
            Thread.sleep(500);

            System.out.println("Initializing IR...");
            this.IR = new MyMemory();
            Thread.sleep(500);

            System.out.println("Initializing ACC...");
            this.ACC = new MyMemory();
            Thread.sleep(500);

            this.m = new MyMemory();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int fetch(){
        System.out.println("Processor is now fetching..");
        int instructionAddress = this.PC.getData(); //gets the address stored in the PC register
        Object currentInstruction = m.retrieveIntstruction(instructionAddress); //retrieves currrent instuction from memory
        if (currentInstruction instanceof Instruction) {
            this.IR.setInstruction((Instruction) currentInstruction); //stores current instruction to the IR register
            // System.out.println(currentInstruction);
            this.PC.increment(); //increments the contents of the PC register
            return 1;
        } else {
            System.out.println("Could not find anymore instructions to fetch");
            return 0;
        }
        
    } 
    public int execute(){
        System.out.println("Processor is now executing..");
        Instruction currentInstruction = this.IR.getInstruction(); //references the instruction stored in the IR register
        int opcode = c.convertBinToDec(currentInstruction.getOpcode()); //gets the opcode as a decimal value
        int address;//to hold instruction address
        int valueFromMemory, valueFromACC, newValue;
        System.out.println("Current Instruction: " + currentInstruction);
        switch (opcode) {
            case 1: //Load AC from memory
                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address);
                this.ACC.setData(valueFromMemory);
                System.out.println("AC was loaded from memory");
                break;
            
            case 2: //Store AC to memory
                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromACC = this.ACC.getData();
                this.m.setValue(address, valueFromACC);
                System.out.println("AC was stored to memory");
                break;

            case 3: //Load AC from stdin
                Scanner stdin = new Scanner(System.in);
                int input;
                System.out.print("Enter integer value to load to ACC: ");
                input = stdin.nextInt();
                this.ACC.setData(input);
                System.out.println("\nAC was loaded from stdin");
                break;

            case 4: //Subtract from AC from memory
                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address);
                valueFromACC = this.ACC.getData();
                newValue = valueFromACC - valueFromMemory;
                this.ACC.setData(newValue);
                System.out.println("Subtracted value from memory from the value in AC");
                break;

            case 5: //Add to AC from memory
                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address);
                valueFromACC = this.ACC.getData();
                newValue = valueFromACC + valueFromMemory;
                this.ACC.setData(newValue);
                System.out.println("Added value from memory to the value in AC");
                break;

            case 7: //Store AC to stdout
                valueFromACC = this.ACC.getData();
                System.out.println("Value from AC: " + valueFromACC);
                break;
        
            default:
                break;
        }
        return 1;
    } 

} //end abstract class Processor

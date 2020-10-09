package simpleos.processor;

import java.util.Scanner;
import simpleos.memory.*;


public class MyProcessor extends Processor {
    private MyMemory m; //will act as the link between this class and the MyMemory class

    private Converter c = new Converter(); //this object will be used to perform binary to decimal conversions and vice versa
    
    //Registers
    private MyMemory PC;    
    private MyMemory IR;    
    private MyMemory ACC;

    private String fibSequence = "0, 1"; //String that will store the portion of the fibonacci sequence generated
    
    public MyProcessor() {
        try {
            //INITIALIZING ALL "MyMemory" OBJECTS OF THIS CLASS

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

        if (currentInstruction instanceof Instruction) { //if the contents of currentInstruction is an Instruction object...

            this.IR.setInstruction((Instruction) currentInstruction); //stores current instruction to the IR register

            // System.out.println(currentInstruction);

            this.PC.increment(); //increments the contents of the PC register - points to the address of the next instruction
            return 1;

        } else {

            System.out.println("Could not find anymore instructions to fetch");
            this.fibSequence += ",..."; //last addition to the fib sequence attribute
            return 0;

        }
        
    } 
    public int execute(){
        System.out.println("Processor is now executing...");

        Instruction currentInstruction = this.IR.getInstruction(); //references the instruction stored in the IR register

        int opcode = c.convertBinToDec(currentInstruction.getOpcode()); //gets the opcode as a decimal value
        int address; //to hold instruction address
        
        int valueFromMemory, valueFromACC, newValue; //variables to potentially be used in the switch statement below

        System.out.println("Current Instruction: " + currentInstruction);

        switch (opcode) {
            case 1: //Load AC from memory
            
                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address); //references the value at that particular address in memory
                this.ACC.setData(valueFromMemory); //loads the referenced value to AC
                System.out.println("AC was loaded from memory");
                break;
            
            case 2: //Store AC to memory

                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromACC = this.ACC.getData(); //references the value stored in AC
                this.m.setValue(address, valueFromACC); //stores the referenced value to the specified address in memory
                System.out.println("AC was stored to memory");
                break;

            case 3: //Load AC from stdin

                //Declarations
                Scanner stdin = new Scanner(System.in);
                int input;

                //prompts user
                System.out.print("Enter integer value to load to ACC: ");
                input = stdin.nextInt();

                //loads user input to AC
                this.ACC.setData(input);
                System.out.println("\nAC was loaded from stdin");
                break;

            case 4: //Subtract from AC from memory

                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address); //references the value at that particular address in memory
                valueFromACC = this.ACC.getData(); //references the value stored in AC

                newValue = valueFromACC - valueFromMemory;

                this.ACC.setData(newValue); //loads difference value to AC
                System.out.println("Subtracted value from memory from the value in AC");
                break;

            case 5: //Add to AC from memory

                address = c.convertBinToDec(currentInstruction.getAddress()); //gets the address as a decimal value
                valueFromMemory = this.m.getValue(address); //references the value at that particular address in memory
                valueFromACC = this.ACC.getData(); //references the value stored in AC

                newValue = valueFromACC + valueFromMemory;

                this.ACC.setData(newValue); //loads sum value to AC
                System.out.println("Added value from memory to the value in AC");
                break;

            case 7: //Store AC to stdout

                valueFromACC = this.ACC.getData(); //references the value stored in AC

                System.out.println("Value from AC: " + valueFromACC);
                this.fibSequence += ", " + valueFromACC; //adds the content of the AC register to the portion of the fibonacci sequence being generated
                break;
        
            default:
                break;
        }
        return 1;
    }
    
    public String getFibSequenceGenerated() {
        return this.fibSequence;
    }

} //end abstract class Processor

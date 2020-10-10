package simpleos.sys;

import simpleos.memory.*;
import simpleos.processor.*;


public class OS {

    public static void main(String[] args){

        try {

            final int SIZE = 100;
            MyMemory m = new MyMemory(SIZE);
            // m.printSize();
            System.out.println();

            Thread.sleep(1000);

            MyProcessor p = new MyProcessor();

            // m.printSize();

            System.out.println("\nStarting the instruction cycle...");
            Thread.sleep(2000);

            System.out.println();
            p.fetch(); //fetches the first instruction from memory
            boolean flag = p.execute() == 1? true : false; //executes the first instruction that was fetched
            while (flag) { //if the first instruction was executed properly...
                Thread.sleep(10);
                System.out.println();
                p.fetch();
                flag = p.execute() == 1? true : false;
            }
            System.out.println();
            Thread.sleep(2000);
            System.out.println("Portion of the Fibonacci Sequence generated:");
            System.out.println(p.getFibSequenceGenerated());
            
            // p.fetch();            
			// Thread.sleep(2000);
            // p.execute();
            // Thread.sleep(2000);
            
            // System.out.println(i.convertBinToDec(100));
            // System.out.println(i.convertBinToDec(1000));
            // System.out.println(i.convertBinToDec(10000));
            // System.out.println(i.convertBinToDec(0));
            // System.out.println(i.convertBinToDec(1));
            // System.out.println(i.convertBinToDec(10));
            // System.out.println(i.convertBinToDec(11));
            // System.out.println(i.convertBinToDec(100));
            // System.out.println(i.convertBinToDec(101));
            // System.out.println(i.convertDecToBin(4));
            // System.out.println(i.convertDecToBin(8));
            // System.out.println(i.convertDecToBin(16));
            // System.out.println(i.convertDecToBin(0));
            // System.out.println(i.convertDecToBin(1));
            // System.out.println(i.convertDecToBin(2));
            // System.out.println(i.convertDecToBin(3));
            // System.out.println(i.convertDecToBin(4));
            // System.out.println(i.convertDecToBin(5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }// End man method

}// END class OS

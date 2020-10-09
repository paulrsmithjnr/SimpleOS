package simpleos.sys;

import simpleos.memory.*;
import simpleos.processor.*;


public class OS {

    public static void main(String[] args){

        try {
            // Instruction i = new Instruction(); //for testing purposes

            final int SIZE = 100;
            MyMemory m = new MyMemory(SIZE);
            m.printSize();
            System.out.println();

            Thread.sleep(500);

            MyProcessor p = new MyProcessor();
            // m.printSize();

            System.out.println();
            boolean flag = p.fetch() == 1? true : false;
            while (flag) {
                p.execute();
                Thread.sleep(500);
                System.out.println();
                flag = p.fetch() == 1? true : false;
            }
            System.out.println();
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

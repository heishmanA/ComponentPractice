import Sorter.ArraySortingMachine;
import Tester.UnitTesterRandom;
import Tester.UnitTesterStatic;

public class Main {

    private static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        ArraySortingMachine m = new ArraySortingMachine();

        if (args.length < 1) {
            // Random test case just using 10 values
            UnitTesterStatic uts = new UnitTesterStatic();
            print("No arguments given. Running standard test cases..");
            uts.startTests();
        } else {
            // Command lines read in
            UnitTesterRandom ut = new UnitTesterRandom();
            switch (args[0]) {
                case "1" -> {
                    int[] sizes = {3, 4, 7, 10, 15, 20, 40};
                    print("Choice 1 chosen. testing 7 randomized input cases..");
                    for (int i = 0; i < sizes.length; i++) {
                        ut.testRandom(m, sizes[i], i);
                    }
                }
                case "2" -> {
                    int[] sizesTen = {10, 20, 30, 40, 50, 60, 70};
                    print("Choice 2 chosen. testing a randomized array with sizes of 10 to 70");
                    for (int i = 0; i < sizesTen.length; i++) {
                        ut.testRandom(m, sizesTen[i], i);
                    }
                }
                case "3" ->{
                    print("Choice 3 chosen. Testing a randomized array with size 1000");
                    ut.testOneThousand(m);
                }
                default -> {
                    System.out.println("ERROR: Please see the README on how to properly run this program.");
                }
            }
        }
    }
}
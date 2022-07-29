package algorithm;

public class PrintABC {
    private volatile int pointer = 1;

    public void printA(){
        new Thread(() -> {
            while (true){
                if (pointer==1){
                    System.out.println("A");
                    pointer = 2;
                }
            }
        }).start();
    }

    public void printB(){
        new Thread(() -> {
            while (true){
                if (pointer==2){
                    System.out.println("B");
                    pointer = 3;
                }
            }
        }).start();
    }

    public void printC(){
        new Thread(() -> {
            while (true){
                if (pointer==3){
                    System.out.println("C");
                    pointer = 1;
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        printABC.printA();
        printABC.printB();
        printABC.printC();
    }
}

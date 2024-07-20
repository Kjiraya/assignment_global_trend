public class Main {

    @LogExecutionTime("Method A")
    public static void methodA() {
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @LogExecutionTime
    public static void methodB() {
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        LogExecutionTimeProcessor.process(main);

                methodA();
        methodB();
    }
}

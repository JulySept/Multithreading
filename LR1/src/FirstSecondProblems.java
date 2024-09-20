//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class FirstSecondProblems {

    public static void main(String[] args) {
        //первая и вторая задачи
        FirstProblem stream1 = new FirstProblem();
        Thread stream2 = new Thread( new SecondProblem());
        stream1.start();
        stream2.start();


    }
}
class FirstProblem extends Thread{
    public void run() {
        for (int i = 0; i <= 100; i+=10) {
            System.out.println(i);
            try {
                // остановка на 400 миллисекунд
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }

}

class SecondProblem implements Runnable {
    public void run() {
        for (int i = 0; i <= 100; i+=10) {
            System.out.println(i);
            try {
                // остановка на 400 миллисекунд
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}

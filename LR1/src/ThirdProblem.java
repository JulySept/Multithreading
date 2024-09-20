public class ThirdProblem {
    public static final Object lock = new Object();

    public static void main(String[] args) {
        Time tm = new Time();
        FiveSecMsg fsm = new FiveSecMsg();
        SevenSecMsg ssm =  new SevenSecMsg();
        tm.start();
        fsm.start();
        ssm.start();
    }

    static class Time extends Thread {
        public void run() {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.print(e);
                }
                System.out.println(++i);
                synchronized (lock) {
                    lock.notify();
                }
            }
        }
    }

    static class FiveSecMsg extends Thread {
        public void run() {
            int i = 0;
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        i++;
                        if (i == 5) {
                            i = 0;
                            System.out.println("5 sec msg");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    static class SevenSecMsg extends Thread {
        public void run() {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    System.err.print(e);
                }
                System.out.println("7 sec msg");
            }
        }
    }
}

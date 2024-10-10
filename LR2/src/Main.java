import java.util.LinkedList;

class Receiver extends Thread {
    Buffer buffer;

    public Receiver(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            if (!buffer.empty) {
                Integer el = buffer.get();
                System.out.println("Получатель: получен элемент " + el);
            } else {
                System.out.println("Получатель: буфер пуст");
            }
            try {
                // остановка на 400 миллисекунд
                Thread.sleep(3840);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}

class Sender extends Thread {
    Buffer buffer;

    public Sender(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        Integer el = 0;
        while (true) {
            if (!buffer.full) {
                System.out.println("Отправитель: добавлен элемент " + el);
                buffer.add(el++);
            } else {
                System.out.println("Отправитель: буфер заполнен");
            }
            try {
                // остановка на 400 миллисекунд
                Thread.sleep(2100);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}

class Buffer {

    private LinkedList<Integer> buf = new LinkedList<>();
    private int size;
    boolean empty = true;
    boolean full = false;

    Buffer(int size) {
        this.size = size;
    }

    void add(Integer el) {
        if (!this.full) {
            this.buf.add(el);
            if (this.empty)
                empty = false;
        }
        if (this.buf.size() == this.size)
            full = true;
    }

    Integer get() {
        Integer el = -1278;
        if (!this.empty) {
            el = this.buf.pop();
            if (this.full)
                full = false;
        }
        if (this.buf.isEmpty())
            empty = true;
        return el;
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Receiver r = new Receiver(buffer);
        Sender s = new Sender(buffer);
        r.start();
        s.start();
    }
}
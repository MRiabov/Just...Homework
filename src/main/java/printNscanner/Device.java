package printNscanner;

import papers.PaperToPrint;
import papers.PaperToScan;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Device{
    private final BlockingQueue<PaperToPrint> devicePrintQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<PaperToScan> deviceScanQueue = new LinkedBlockingQueue<>();

    public Device() {
        print();
    }

    public void printQueueAdd(PaperToPrint paperToPrint, int amount){
        devicePrintQueue.add(paperToPrint);
    }

    public void scanQueueAdd(PaperToScan paperToScan, int amount){
        deviceScanQueue.add(paperToScan);
    }

    private void print(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    PaperToPrint paperToPrint = null;
                    try {
                        paperToPrint = devicePrintQueue.take();
                    } catch (InterruptedException e) { e.printStackTrace();}

                    for (int i = 0; i < paperToPrint.getAmount(); i++) {
                        System.out.println("Printed N" + i + ". There is " + paperToPrint.getData() + " scribbled on the paper");
                        try {
                            wait(1000);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            }
        }).start();
    }

    private void scan(String data, int number){
        for (int i = 0; i < number; i++) {
            System.out.println("Scanned N"+i+". "+data+" shows up on the screen");
        }
    }
}

package printNscanner;

import papers.PaperToPrint;
import papers.PaperToScan;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Device{
    private final Object SCAN_MONITOR=new Object();
    private final Object PRINT_MONITOR=new Object();
    private final BlockingQueue<PaperToPrint> devicePrintQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<PaperToScan> deviceScanQueue = new LinkedBlockingQueue<>();

    public Device() {
        printInit();
        scanInit();
    }

    public void printQueueAdd(PaperToPrint paperToPrint){
        synchronized (PRINT_MONITOR){
            devicePrintQueue.add(paperToPrint);
        }
    }

    public void scanQueueAdd(PaperToScan paperToScan){
        synchronized (SCAN_MONITOR){
            deviceScanQueue.add(paperToScan);
        }
    }

    private void printInit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                    while (true) {
                        PaperToPrint paperToPrint = null;
                        try {
                            paperToPrint = devicePrintQueue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < paperToPrint.getAmount(); i++) {
                            System.out.println("Printed N" + i + ". There is " + paperToPrint.getData() + " scribbled on the paper");
                            try {
                                PRINT_MONITOR.wait(1000);
                            } catch (InterruptedException ignored) {
                            }
                        }
                    }
                }
        }).start();
    }

    private void scanInit(){
        Thread scanThread= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    PaperToScan paperToScan = null;
                    try {
                        paperToScan = deviceScanQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();}
                    for (int i = 0; i < paperToScan.getAmount(); i++) {
                        System.out.println("Scanned N" + i + ". " + paperToScan.getData() + " shows up on the screen");
                        try {
                            SCAN_MONITOR.wait(1000);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            }
        });
             scanThread.start();
    }
}

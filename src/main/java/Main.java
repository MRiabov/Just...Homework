import papers.PaperToPrint;
import papers.PaperToScan;
import printNscanner.Device;

public class Main {
    public static void main(String[] args) {
        Device device = new Device();
            device.printQueueAdd(new PaperToPrint("BrandInfo 8800555535",4));
            device.scanQueueAdd(new PaperToScan("horny stuff",8));
    }
}

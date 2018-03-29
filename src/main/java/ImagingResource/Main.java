package ImagingResource;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to the folder where you want to save files");
        String saveLocation = scanner.nextLine();
        saveLocation = "/Users/Ira/Temp/AAAaaa";

        System.out.println("Enter Maker name. For example: Canon, Nikon, etc.");
        String maker = scanner.nextLine();
        maker = "Canon";

        System.out.println("Enter camera model");
        String model = scanner.nextLine();
        model = "M6";

        System.out.println("Paste page url where photos are located. Something like =>  https://www.imaging-resource.com/PRODS/nikon-aw1/nikon-aw1THMB.HTM");
        String linkToFiles = scanner.nextLine();
        linkToFiles = "https://www.imaging-resource.com/PRODS/nikon-aw1/nikon-aw1THMB.HTM";

        ImagingResource imagingResource = new ImagingResource(saveLocation, maker, model, linkToFiles);
        imagingResource.openBrowser();
        imagingResource.photoGet();
        imagingResource.closeBrowser();

    }
}

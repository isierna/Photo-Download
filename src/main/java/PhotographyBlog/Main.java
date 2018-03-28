package PhotographyBlog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to the folder where you want to save files");
        String saveLocation = scanner.nextLine();
        //saveLocation = "/Users/Ira/Temp/AAAaaa";

        System.out.println("Enter Maker name. For example: Canon, Nikon, etc.");
        String maker = scanner.nextLine();
        maker = "Canon";

        System.out.println("Enter camera model");
        String model = scanner.nextLine();
        model = "M6";

        System.out.println("Paste page url where photos are located. Something like => http://www.photographyblog.com/reviews/canon_eos_m6_review/sample_images/");
        String linkToFiles = scanner.nextLine();
        linkToFiles = "http://www.photographyblog.com/reviews/canon_eos_m6_review/sample_images/";


        Blog blog = new Blog(saveLocation, maker,model,linkToFiles);

        blog.openBrowser();
        blog.photographyBlog();
        blog.closeBrowser();

    }
}

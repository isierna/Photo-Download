package DownloadingPhotos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractPhoto {
    public static WebDriver driver;
    private static FirefoxOptions options;
    private static String maker;
    private static String model;
    public static String linkToFiles;
    public static String directory;
    private static String mainDirectory;

    public AbstractPhoto(String mainDirectory, String maker, String model, String linkToFiles) {
        this.mainDirectory = mainDirectory;
        this.maker = maker;
        this.model = model;
        this.linkToFiles = linkToFiles;
        this.options = new FirefoxOptions().setHeadless(true);
        openBrowser();
    }

    public void openBrowser() {
        this.mainDirectory = maker + "_" + model;
        directory = mainDirectory + "/JPG&RAW";

        new File(mainDirectory).mkdir();
        new File(directory).mkdir();

        driver = new FirefoxDriver(options);
    }

    public abstract void findFile() throws IOException;
    public abstract void download(URL url, String destination) throws IOException;

    public static boolean checkIfFileExists(String file_name, String directory) {
        File file1 = new File(directory);
        String[] all_files = file1.list();

        for (String file2:all_files) {
            file2 = "/" + file2;

            if(file_name.equals(file2)) {
                System.out.println("File names equals");
                return true;
            }
        }
        return false;
    }

    public void closeBrowser() {
        driver.quit();
    }
}

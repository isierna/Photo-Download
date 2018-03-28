package PhotographyBlog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Blog {
    private static WebDriver driver;
    private FirefoxOptions options;
    private String maker;
    private String model;
    private String linkToFiles;
    private static String directory;
    private String main_directory;

    protected Blog(String main_directory, String maker, String model, String linkToFiles) {
        this.main_directory = main_directory;
        this.maker = maker;
        this.model = model;
        this.linkToFiles = linkToFiles;
        this.options = new FirefoxOptions().setHeadless(true);
    }

    public void openBrowser() {
        this.main_directory = main_directory + maker + "_" + model;
        directory = main_directory + "/JPG&RAW";

        new File(main_directory).mkdir();
        new File(directory).mkdir();

        driver = new FirefoxDriver(options);
    }

    public void photographyBlog() throws IOException {
        driver.get(linkToFiles);
        List<WebElement> allPhotos = driver.findElements(By.xpath("//a[1][@href][text()=\"Download Original\"]"));

        for(int i=0; i<allPhotos.size(); i++) {
            String link = allPhotos.get(i).getAttribute("href");
            URL url = new URL(link);
            String fileName = link.substring(link.lastIndexOf("/"));

            String destination = directory + fileName;

            if (!checkIfFileExists(fileName, directory)) {
                download(url, destination);
            }

        }
    }

    private static void download(URL url, String destination) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Referer","http://www.photographyblog.com/reviews/canon_eos_1d_x_mark_ii_review/sample_images/");

        InputStream inputStream = connection.getInputStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bt = new byte[2048];
        int length;

        while ((length = inputStream.read(bt)) != -1) {
            outputStream.write(bt, 0, length);
        }

        outputStream.close();
        inputStream.close();
    }

    private static boolean checkIfFileExists(String file_name, String directory) {
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


package DownloadingPhotos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Blog extends AbstractPhoto {
    public Blog(String mainDirectory, String maker, String model, String linkToFiles) {
        super(mainDirectory, maker, model, linkToFiles);
    }

    @Override
    public void download(URL url, String destination) throws IOException {
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

    @Override
    public void findFile() throws IOException{
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
}

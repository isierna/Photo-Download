package DownloadingPhotos;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Dpreview extends AbstractPhoto{
    public Dpreview(String mainDirectory, String maker, String model, String linkToFiles) {
        super(mainDirectory, maker, model, linkToFiles);
    }

    @Override
    public void findFile() throws IOException {
        driver.get(linkToFiles);

        List<WebElement> strip_items = driver.findElements(By.xpath("//div[@class='filmstripImage']"));
        ArrayList<String> photo_links_urls = new ArrayList<String>();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement imageElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='image'][1]")));
        imageElement.click();

        for (int t = 1; t <= strip_items.size(); t++) {
            WebElement u = driver.findElement(By.xpath("//div[@class='filmstripImage']" + "[" + t + "]"));
            u.click();
            WebElement link_element = waitUntil(presenceOfElementLocated(By.xpath("//tr[./td[text()=\"Download:\"]]/td[@class=\"content\"]/a[1]")));
            String link = link_element.getAttribute("href");
            photo_links_urls.add(link);
            System.out.println("link 1" + link);

            URL url = new URL(link);
            String file = url.getFile();
            String file_name = file.substring(file.lastIndexOf("/"));

            if (checkIfFileExists(file_name, directory)) {
                String destination = directory + file_name;
                download(url, destination);
            } else {
                System.out.println("File already exists");
            }

            try {
                link_element = driver.findElement(By.xpath("//tr[./td[text()=\"Download:\"]]/td[@class=\"content\"]/a[2]"));
                link = link_element.getAttribute("href");
                photo_links_urls.add(link);

                url = new URL(link);
                String file2 = url.getFile();
                file_name = file2.substring(file2.lastIndexOf("/"), file2.indexOf("?"));

                if (checkIfFileExists(file_name, directory)) {
                    String destination2 = directory + file_name;
                    download(url, destination2);
                } else {
                    System.out.println("RAW file already exists");
                }

            } catch (NoSuchElementException e) {

            }
        }
    }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return (new WebDriverWait(driver, 10)).until(condition);
    }

    @Override
    public void download(URL url, String destination) throws IOException {

    }


}

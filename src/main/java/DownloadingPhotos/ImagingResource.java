package DownloadingPhotos;

import java.io.IOException;
import java.net.URL;

public class ImagingResource extends AbstractPhoto {
    public ImagingResource(String mainDirectory, String maker, String model, String linkToFiles) {
        super(mainDirectory, maker, model, linkToFiles);
    }

    @Override
    public void findFile() throws IOException {

    }

    @Override
    public void download(URL url, String destination) throws IOException {

    }
}

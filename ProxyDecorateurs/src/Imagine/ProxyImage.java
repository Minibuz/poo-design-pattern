package Imagine;

import java.util.Objects;

public class ProxyImage implements Image {

    private String url;
    private Image image;

    public ProxyImage(String url) {
        this.url = url;
        this.image = null;
    }

    @Override
    public String name() {
        if(image == null) {
            image = Image.download(url);
        }
        return image.name();
    }

    @Override
    public int size() {
        if(image == null) {
            image = Image.download(url);
        }
        return image.size();
    }

    @Override
    public double hue() {
        if(image == null) {
            image = Image.download(url);
        }
        return image.hue();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}

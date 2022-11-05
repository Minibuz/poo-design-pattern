package fr.uge.poo.cmdline.ex3;

import java.net.InetSocketAddress;

public class PaintSettings {
    private String name;
    private boolean legacy;
    private boolean bordered;
    private int borderSize;
    private int width;
    private int height;
    private InetSocketAddress address;

    public PaintSettings(String name, boolean legacy, boolean bordered, int borderSize, int width, int height, InetSocketAddress address) {
        this.name = name;
        this.legacy = legacy;
        this.bordered = bordered;
        this.borderSize = borderSize;
        this.width = width;
        this.height = height;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public boolean isBordered() {
        return bordered;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PaintSettings{" +
                "name='" + name + '\'' +
                ", legacy=" + legacy +
                ", bordered=" + bordered +
                ", borderSize=" + borderSize +
                ", width=" + width +
                ", height=" + height +
                ", address=" + address +
                '}';
    }
}

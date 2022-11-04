package fr.uge.poo.cmdline.ex3;

import java.net.InetSocketAddress;
import java.util.Objects;

public class PaintSettingsBuilder {

    private String name;
    private boolean legacy = false;
    private boolean bordered = false;
    private int borderSize = 10;
    private int width = 500;
    private int height = 500;
    private InetSocketAddress address = null;


    public PaintSettingsBuilder(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public PaintSettingsBuilder withLegacy(boolean legacy) {
        this.legacy = legacy;
        return this;
    }

    public PaintSettingsBuilder withBordered(boolean bordered) {
        this.bordered = bordered;
        return this;
    }

    public PaintSettingsBuilder withBorderSize(int borderSize) {
        this.borderSize = borderSize;
        return this;
    }

    public PaintSettingsBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public PaintSettingsBuilder withServer(String serverName, int port) {
        Objects.requireNonNull(serverName);

        this.address = InetSocketAddress.createUnresolved(serverName, port);
        return this;
    }

    public PaintSettings build() {
        return new PaintSettings(name, legacy, bordered, borderSize, width, height, address);
    }
}

package fr.uge.poo.cmdline.ex3;

import java.net.InetSocketAddress;
import java.util.Objects;

public class PaintSettings {
    private final String name;
    private final boolean legacy;
    private final boolean bordered;
    private final int borderSize;
    private final int width;
    private final int height;
    private final InetSocketAddress address;

    private PaintSettings(PaintSettings.PaintSettingsBuilder builder) {
        this.name = builder.name;
        this.legacy = builder.legacy;
        this.bordered = builder.bordered;
        this.borderSize = builder.borderSize;
        this.width = builder.width;
        this.height = builder.height;
        this.address = builder.address;
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


    public static class PaintSettingsBuilder {

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

        public PaintSettings.PaintSettingsBuilder withName(String name) {
            Objects.requireNonNull(name);

            this.name = name;
            return this;
        }

        public PaintSettings.PaintSettingsBuilder withLegacy(boolean legacy) {
            this.legacy = legacy;
            return this;
        }

        public PaintSettings.PaintSettingsBuilder withBordered(boolean bordered) {
            this.bordered = bordered;
            return this;
        }

        public PaintSettings.PaintSettingsBuilder withBorderSize(int borderSize) {
            this.borderSize = borderSize;
            return this;
        }

        public PaintSettings.PaintSettingsBuilder withSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public PaintSettings.PaintSettingsBuilder withServer(String serverName, int port) {
            Objects.requireNonNull(serverName);

            this.address = InetSocketAddress.createUnresolved(serverName, port);
            return this;
        }

        public PaintSettings build() {
            if(!bordered) {
                borderSize = 0;
            }
            return new PaintSettings(this);
        }
    }
}

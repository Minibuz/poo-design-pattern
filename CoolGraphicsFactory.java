package fr.uge.poo.paint.ex8.adapter;

public class CoolGraphicsFactory implements LibraryFactory {
    private String name;
    private int width;
    private int height;


    public CoolGraphicsFactory() {
    }

    @Override
    public LibraryFactory withHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public LibraryFactory withWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public LibraryFactory withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public LibraryAdapter make() {
        return new CoolGraphicAdapter(name, width, height);
    }
}

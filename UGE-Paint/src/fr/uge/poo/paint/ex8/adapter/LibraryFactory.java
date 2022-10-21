package fr.uge.poo.paint.ex8.adapter;

public interface LibraryFactory {
    LibraryFactory withHeight(int height);

    LibraryFactory withWidth(int width);

    LibraryFactory withName(String name);

    LibraryAdapter make();
}

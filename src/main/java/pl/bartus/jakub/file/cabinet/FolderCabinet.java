package pl.bartus.jakub.file.cabinet;

import pl.bartus.jakub.file.cabinet.composite.Folder;
import pl.bartus.jakub.file.cabinet.composite.MultiFolder;

import java.util.ArrayList;
import java.util.List;

public class FolderCabinet implements MultiFolder {
    private final String name;
    private final String size;
    private final List<Folder> folders;

    @Override
    public List<Folder> getFolders() {
        return this.folders;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    public FolderCabinet(String name, String size) {
        this.name = name;
        this.size = size;
        this.folders = new ArrayList<>();
    }

    public FolderCabinet(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }
}

package pl.bartus.jakub.file.cabinet.folder;

import java.util.ArrayList;
import java.util.List;

public class CompositeFolder implements MultiFolder {
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

    public CompositeFolder(String name, String size) {
        this.name = name;
        this.size = size;
        this.folders = new ArrayList<>();
    }

    public CompositeFolder(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }
}

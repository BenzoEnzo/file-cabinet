package pl.bartus.jakub.file.composite.stub;

import pl.bartus.jakub.file.composite.Folder;
import pl.bartus.jakub.file.composite.MultiFolder;

import java.util.List;

public class MultiFolderStub extends FolderStub implements MultiFolder {
    private final List<Folder> folders;

    public MultiFolderStub(String name, String size, List<Folder> folders) {
        super(name, size);
        this.folders = folders;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}

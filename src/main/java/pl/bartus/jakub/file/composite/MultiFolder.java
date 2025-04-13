package pl.bartus.jakub.file.composite;

import java.util.List;

public interface MultiFolder extends Folder {
    List<Folder> getFolders();
}

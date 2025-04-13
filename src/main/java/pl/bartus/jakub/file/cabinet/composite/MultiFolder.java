package pl.bartus.jakub.file.cabinet.composite;

import java.util.List;

public interface MultiFolder extends Folder {
    List<Folder> getFolders();
}

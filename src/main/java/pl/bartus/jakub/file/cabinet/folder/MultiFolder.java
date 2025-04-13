package pl.bartus.jakub.file.cabinet.folder;

import java.util.List;

public interface MultiFolder extends Folder {
    List<Folder> getFolders();
}
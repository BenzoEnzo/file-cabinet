package pl.bartus.jakub.file;


import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return folders.stream().filter(folder -> folder.getName().equals(name)).findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return 
    }

    @Override
    public int count() {
        return 0;
    }
}




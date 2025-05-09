package pl.bartus.jakub.file.cabinet;

import lombok.RequiredArgsConstructor;
import pl.bartus.jakub.file.cabinet.folder.Folder;
import pl.bartus.jakub.file.cabinet.folder.MultiFolder;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileCabinet implements Cabinet {
    private final List<Folder> folders;

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return filter(folder -> folder.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return filter(folder -> folder.getSize().equalsIgnoreCase(size)).toList();
    }

    @Override
    public int count() {
        return (int) filter(folder -> true).count();
    }

    private Stream<Folder> filter(Predicate<? super Folder> predicate) {
        return folders.stream()
                .flatMap(FileCabinet::collectFolders)
                .filter(predicate);
    }

    private static Stream<Folder> collectFolders(Folder folder) {
        return Stream.concat(Stream.of(folder),
                folder instanceof MultiFolder multiFolder ?
                        multiFolder.getFolders()
                                .stream()
                                .flatMap(FileCabinet::collectFolders) : Stream.of(folder));

    }
}
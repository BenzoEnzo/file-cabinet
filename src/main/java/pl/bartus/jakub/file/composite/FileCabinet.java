package pl.bartus.jakub.file.composite;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileCabinet implements Cabinet {
    private final List<Folder> folders;

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return filter(folder -> folder.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return filter(folder -> folder.getSize().equalsIgnoreCase(size))
                .toList();
    }

    @Override
    public int count() {
        return folders.size();
    }

    private Stream<Folder> filter(Predicate<? super Folder> predicate) {
        return folders.stream()
                .flatMap(FileCabinet::collectFolders)
                .filter(predicate);
    }

    private static Stream<Folder> collectFolders(Folder folder) {
        return (folder instanceof MultiFolder multiFolder)
                ? collectMultiFolders(multiFolder)
                : Stream.of(folder);
    }

    private static Stream<Folder> collectMultiFolders(MultiFolder multiFolder) {
        return Stream.concat(
                Stream.of(multiFolder),
                multiFolder.getFolders()
                        .stream()
                        .flatMap(FileCabinet::collectFolders)
        );
    }
}




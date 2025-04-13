package pl.bartus.jakub.file.composite.stub;

import lombok.RequiredArgsConstructor;
import pl.bartus.jakub.file.composite.Folder;

@RequiredArgsConstructor
public class FolderStub implements Folder {
    private final String name;
    private final String size;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }
}

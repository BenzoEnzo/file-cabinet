package pl.bartus.jakub.file.cabinet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartus.jakub.file.cabinet.folder.CompositeFolder;
import pl.bartus.jakub.file.cabinet.folder.Folder;
import pl.bartus.jakub.file.cabinet.folder.MultiFolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileCabinetTest {

    private FileCabinet fileCabinet;

    @BeforeEach
    void setUp() {
        Folder folder1 = new CompositeFolder("Games", "100MB");
        Folder folder2 = new CompositeFolder("Pages", "500MB");
        Folder folder3 = new CompositeFolder("Images", "500MB");
        Folder folder4 = new CompositeFolder("Videos", "500MB");

        MultiFolder multiFolder = new CompositeFolder(
                "User",
                "1GB",
                List.of(folder1, folder2, folder3)
        );

        MultiFolder multiFolder2 = new CompositeFolder(
                "PC",
                "2GB",
                List.of(multiFolder, folder4)
        );

        fileCabinet = new FileCabinet(
                List.of(
                        multiFolder2
                )
        );
    }

    @Test
    void testFindFolderByName_foundAtTopLevel() {
        Optional<Folder> result = fileCabinet.findFolderByName("Pages");
        assertTrue(result.isPresent(), "There should find a folder with name 'Pages'");
        assertEquals("Pages", result.get().getName());
    }

    @Test
    void testFindFolderByName_foundNested() {
        Optional<Folder> result = fileCabinet.findFolderByName("Games");
        assertTrue(result.isPresent(), "Should find a folder with name 'Games'");
        assertEquals("Games", result.get().getName());
    }

    @Test
    void testFindFolderByName_notFound() {
        Optional<Folder> result = fileCabinet.findFolderByName("NonExistent");
        assertFalse(result.isPresent(), "There should be no folders with these name");
    }

    @Test
    void testFindFoldersBySize_multipleMatches() {
        List<Folder> result = fileCabinet.findFoldersBySize("500MB");
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(f -> "Pages".equals(f.getName())));
        assertTrue(result.stream().anyMatch(f -> "Images".equals(f.getName())));
    }

    @Test
    void testFindFoldersBySize_singleMatch() {
        List<Folder> result = fileCabinet.findFoldersBySize("1GB");
        assertEquals(1, result.size());
        assertEquals("User", result.get(0).getName());
    }

    @Test
    void testFindFoldersBySize_noMatches() {
        List<Folder> result = fileCabinet.findFoldersBySize("10GB");
        assertTrue(result.isEmpty(), "There should be no folders with a size of 10GB.");
    }

    @Test
    void testCount() {
        assertEquals(6, fileCabinet.count());
    }
}

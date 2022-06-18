package com.github.as2122.backend.files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void registerToWorkflow() {
        final FileManager fileManager = new FileManager();
        assert fileManager.getAllWorkflowFiles(1) == null;

        final String name = "file.txt";
        fileManager.registerToWorkflow(1, name);

        assert fileManager.getAllWorkflowFiles(1).size() == 1;

        final File file = fileManager.getAllWorkflowFiles(1).get(0);

        assert file.getId() == null;
        assert Objects.equals(file.getName(), name);
    }

    @Test
    void removeWorkflowFromMap() {
        final FileManager fileManager = new FileManager();

        assert fileManager.getAllWorkflowFiles(1) == null;

        final String name = "file.txt";
        fileManager.registerToWorkflow(1, name);

        assert fileManager.getAllWorkflowFiles(1) != null;

        fileManager.removeWorkflowFromMap(1);
        assert fileManager.getAllWorkflowFiles(1) == null;
    }

//    @Test
//    void getAllUserFiles() {
//
//    }

    @Test
    void getAllWorkflowFiles() {
        final FileManager fileManager = new FileManager();
        assert fileManager.getAllWorkflowFiles(1) == null;

        final String name = "file.txt";
        fileManager.registerToWorkflow(1, name);

        assert fileManager.getAllWorkflowFiles(1).size() == 1;

        final File file = fileManager.getAllWorkflowFiles(1).get(0);

        assert file.getId() == null;
        assert Objects.equals(file.getName(), name);

        final String name2 = "file2.txt";
        fileManager.registerToWorkflow(1, name2);

        assert fileManager.getAllWorkflowFiles(1).size() == 2;

        final File file2 = fileManager.getAllWorkflowFiles(1).get(1);

        assert file2.getId() == null;
        assert Objects.equals(file2.getName(), name2);
    }

//    @Test
//    void canAccessFile() {
//    }

//    @Test
//    void delete() {
//    }

//    @Test
//    void getFileNameFromId() {
//    }
}
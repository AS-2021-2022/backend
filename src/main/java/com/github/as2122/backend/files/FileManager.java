package com.github.as2122.backend.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
    private final Path rootLocation = Path.of("/app/files/");

    private final Map<String, String> fileIdNameMap = new HashMap<>();
    private final Map<String, List<String>> userFiles = new HashMap<>();
    private final Map<Integer, List<String>> workflowFiles = new HashMap<>();

    public void registerToWorkflow(int workflow, String file) {
        if(!workflowFiles.containsKey(workflow))
            workflowFiles.put(workflow, new ArrayList<>());
        workflowFiles.get(workflow).add(file);
    }

    public void removeWorkflowFromMap(int workflow) {
        if (workflowFiles.containsKey(workflow)) {
            workflowFiles.remove(workflow);
        }
    }

    public List<File> getAllUserFiles(String userID) {
        return userFiles.get(userID).stream()
                .map(id -> new File(fileIdNameMap.get(id), id))
                .collect(Collectors.toList());
    }

    public List<File> getAllWorkflowFiles(int workflowID) {
        if (workflowFiles.containsKey(workflowID)) {
            return workflowFiles.get(workflowID).stream()
                    .map(x -> new File(getFileNameFromId(x), x))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public boolean canAccessFile(String userID, String fileID) {
        return userFiles.containsKey(userID) && userFiles.get(userID).contains(fileID);
    }

    public String storeFile(MultipartFile file, String userId) throws Exception {
        if (!(Files.exists(rootLocation) && Files.isDirectory(rootLocation))) {
            Files.createDirectory(rootLocation);
            System.out.println("Created folder");
        }

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file.");
            }

            final String fileID = UUID.randomUUID().toString();
            fileIdNameMap.put(fileID, file.getOriginalFilename());

            if (!userFiles.containsKey(userId))
                userFiles.put(userId, new ArrayList<>());
            userFiles.get(userId).add(fileID);

            final Path destinationFile = this.rootLocation.resolve(
                            Paths.get(fileID))
                    .normalize().toAbsolutePath();
            System.out.println("Saving to " + destinationFile);
            try (final InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return fileID;
        } catch (IOException e) {
            throw new Exception("Failed to store file.", e);
        }
    }

    public boolean delete(String userID, String fileID) {
        if (userFiles.containsKey(userID) && userFiles.get(userID).contains(fileID)) {
            userFiles.get(userID).remove(fileID);
            try {
                return Files.deleteIfExists(
                        this.rootLocation.resolve(Paths.get(fileID))
                                .normalize().toAbsolutePath()
                );
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public String getFileNameFromId(String fileID) {
        return fileIdNameMap.getOrDefault(fileID, null);
    } 
}

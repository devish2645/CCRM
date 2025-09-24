package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecursiveFileUtils {
    
    public static long calculateDirectorySize(Path path) throws IOException {
        long size = 0;
        if (Files.isDirectory(path)) {
            // try-with-resources on a DirectoryStream
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    // Recursive call for subdirectories
                    size += calculateDirectorySize(entry);
                }
            }
        } else {
            // Base case: it's a file
            size = Files.size(path);
        }
        return size;
    }
}
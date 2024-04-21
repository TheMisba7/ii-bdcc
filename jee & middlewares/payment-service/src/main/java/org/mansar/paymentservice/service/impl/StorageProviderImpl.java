package org.mansar.paymentservice.service.impl;

import org.mansar.paymentservice.service.StorageProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageProviderImpl implements StorageProvider {
    @Override
    public String save(InputStream content) {
        Path folder = getFolder();
        String name = UUID.randomUUID().toString();
        Path filepath = folder.resolve(name + ".pdf");
        try {
            Files.copy(content, filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return name + ".pdf";
    }

    @Override
    public byte[] download(String filename) {
        Path folder = getFolder();
        try {
            return Files.newInputStream(folder.resolve(filename)).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String filename) {

    }

    private Path getFolder() {
        Path path = Paths.get(System.getProperty("user.home"), "bddc", "payment");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }

}

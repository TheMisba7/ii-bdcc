package org.mansar.paymentservice.service;

import java.io.InputStream;

public interface StorageProvider {
    String save(InputStream content);
    byte[] download(String filename);
    void delete(String filename);
}

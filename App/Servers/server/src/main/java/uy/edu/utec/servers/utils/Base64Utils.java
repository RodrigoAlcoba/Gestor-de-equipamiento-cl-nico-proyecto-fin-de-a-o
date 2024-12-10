package uy.edu.utec.servers.utils;


import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

public class Base64Utils {

    private static Integer i = 0;
    public static String toBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decodificarBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static Blob base64ToBlob(String base64) throws SQLException {
        byte[] data = decodificarBase64(base64);
        return new SerialBlob(data);
    }

    public static String blobToBase64(Blob blob) throws SQLException {
        byte[] data = blob.getBytes(1, (int) blob.length());
        return toBase64(data);
    }

    public static String imagenToBase64(String path) {
        try {

            byte[] imageBytes = Files.readAllBytes(Path.of(path));
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            return base64Image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File base64ToImage(String base64) throws IOException {
        byte[] fileBytes = decodeBase64(base64);
        return saveToFile(fileBytes);
    }

    public static File saveToFile(byte[] data) throws IOException {
        File tempFile = File.createTempFile("output", ".tmp");
        writeByteArrayToFile(tempFile, data);
        return tempFile;
    }

}

package ontologie_lab3.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class ImageLoader {
    private static final String BASE_PATH = Paths.get("src/main/resources/persons").toAbsolutePath().toString();

    public static String loadImage(String imageUrl, String filename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = new File(BASE_PATH + "/" + filename);

        try {
            URL url = new URL(imageUrl);
            inputStream = url.openStream();

            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }

            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException :- " + e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException :- " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IOException :- " + e.getMessage());

        } finally {
            try {

                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                System.out.println("Finally IOException :- " + e.getMessage());
            }

        }
        return file.getPath();
    }

}

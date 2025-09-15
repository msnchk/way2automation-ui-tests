package helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class CookiesHelper {
    private static final Path COOKIE_FILE_PATH = Paths.get(EnvKeys.SQLEX_COOKIE_FILEPATH.get());

    public static void saveCookiesToFile(WebDriver driver) {
        try {
            Files.createDirectories(COOKIE_FILE_PATH.getParent());
            Set<Cookie> cookies= driver.manage().getCookies();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIE_FILE_PATH.toFile()))) {
                oos.writeObject(cookies);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save cookies to file", e);
        }
    }

    public static void loadCookiesFromFile(WebDriver driver) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COOKIE_FILE_PATH.toFile()))) {;
            Object cookiesObject = ois.readObject();
            Set<Cookie> cookies = (Set<Cookie>)cookiesObject;

            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load cookies from file", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Not expected type of cookie data", e);
        }
    }

    public static boolean cookieFileExists() {
        return Files.exists(COOKIE_FILE_PATH);
    }
}

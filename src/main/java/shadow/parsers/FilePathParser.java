package shadow.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * A utility class for parsing, validating, and managing file paths.
 * <p>
 * This class provides methods to transform input file paths into standardized
 * formats, handle user-specific path expansions, and perform optional validations
 * on the paths. It also includes functionality to create parent directories
 * if necessary.
 */
public class FilePathParser {

    /**
     * Resolves a user-provided file path, applying transformations such as expanding
     * tilde characters, removing quotes, normalizing slashes, and validating the path.
     * Additionally, optional creation of parent directories and checking for illegal
     * characters are supported.
     *
     * @param inputPath the input file path to resolve; must not be null or empty
     * @param createParentDirs if true, ensures that all parent directories exist by creating them if necessary
     * @param checkIllegalChars if true, checks the resolved path for illegal characters
     *                          and throws an exception if any are found
     * @return the resolved and normalized file path as a {@link Path} object
     * @throws IllegalArgumentException if the input path is null, empty,
     *                                  or contains illegal characters when validation is enabled
     * @throws IOException if an I/O error occurs while creating parent directories
     */
    public static Path resolveUserPath(
            String inputPath,
            boolean createParentDirs,
            boolean checkIllegalChars
    ) throws IOException {
        if (inputPath == null || inputPath.isEmpty()) {
            throw new IllegalArgumentException("Input path cannot be null or empty.");
        }

        String expanded = expandTilde(inputPath);
        String unquoted = removeQuotes(expanded);
        String slashed = normalizeSlashes(unquoted);
        Path path = normalizePath(slashed);

        if (checkIllegalChars) {
            validateIllegalCharacters(path);
        }

        if (createParentDirs) {
            createParentDirectories(path);
        }

        return path;
    }

    /**
     * Expands a tilde ("~") in the provided file path to the user's home directory.
     * If the path starts with a tilde, it is replaced with the value of the system
     * property "user.home". If the path does not start with a tilde, it is returned
     * unchanged.
     *
     * @param path the file path to process; must not be null
     * @return the path with the tilde expanded to the user's home directory, or
     *         the original path if no tilde is found
     */
    private static String expandTilde(String path) {
        return path.startsWith("~")
                ? path.replaceFirst("^~", System.getProperty("user.home"))
                : path;
    }

    /**
     * Removes surrounding single or double quotes from the given string if both are present.
     * If the string does not start and end with matching quotes, the string is returned unchanged.
     *
     * @param path the input string to process; must not be null
     * @return the string without surrounding quotes or the original string if no surrounding quotes are found
     */
    private static String removeQuotes(String path) {
        if ((path.startsWith("\"") && path.endsWith("\""))
                || (path.startsWith("'") && path.endsWith("'"))) {
            return path.substring(1, path.length() - 1);
        }
        return path;
    }

    /**
     * Normalizes the given file path by replacing all backslashes with forward slashes for Windows file paths.
     *
     * @param path the file path to normalize; must not be null
     * @return the normalized file path with backslashes replaced by forward slashes
     */
    private static String normalizeSlashes(String path) {
        return path.replace("\\", "/");
    }

    /**
     * Converts the input string representing a file path into a normalized {@link Path} object.
     * The normalization process converts the path to an absolute path and resolves any redundant
     * elements such as "." and "..".
     *
     * @param inputPath the input file path as a string; must not be null
     * @return the normalized {@link Path} object representing the input file path
     */
    private static Path normalizePath(String inputPath) {
        return Paths.get(inputPath).toAbsolutePath().normalize();
    }

    /**
     * Validates a file's path to ensure it does not contain any illegal characters.
     * Illegal characters include: `<`, `>`, `:`, `"`, `/`, `\`, `|`, `?`, `*`.
     * If the path contains any of these characters, an {@link IllegalArgumentException}
     * is thrown with a descriptive message.
     *
     * @param path the {@link Path} object representing the file's path to validate
     * @throws IllegalArgumentException if the file's name contains any illegal characters
     */
    private static void validateIllegalCharacters(Path path) {
        String filename = path.getFileName().toString();
        Pattern illegal = Pattern.compile("[<>:\"/\\\\|?*]");
        if (illegal.matcher(filename).find()) {
            throw new IllegalArgumentException("Path contains illegal characters: " + filename);
        }
    }

    /**
     * Ensures that all parent directories for the given path exist by creating them
     * if they do not already exist.
     *
     * @param path the {@link Path} object representing the file or directory
     *             for which parent directories need to be created
     * @throws IOException if an I/O error occurs during directory creation
     */
    private static void createParentDirectories(Path path) throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }
}

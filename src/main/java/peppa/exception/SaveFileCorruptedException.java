package peppa.exception;

import java.io.IOException;

public class SaveFileCorruptedException extends IOException {
  public SaveFileCorruptedException() {
    super("Save file is corrupted");
  }

  public SaveFileCorruptedException(String message) {
    super(message);
  }
}
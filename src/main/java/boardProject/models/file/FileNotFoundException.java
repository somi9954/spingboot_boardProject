package boardProject.models.file;

import boardProject.commons.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {
        super(bundleValidation.getString("File.notExists"), HttpStatus.BAD_REQUEST);
    }
}

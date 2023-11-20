package boardProject.restcontrollers;

import boardProject.commons.rests.JSONData;
import boardProject.entities.file.FileInfo;
import boardProject.models.file.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService uploadService;
    @PostMapping("/file/upload")
    public ResponseEntity<JSONData<List<FileInfo>>> upload(MultipartFile[] files, String gid, String location) {
        List<FileInfo> items = uploadService.upload(files, gid, location);

        JSONData<List<FileInfo>> jsonData = new JSONData<>();
        jsonData.setSuccess(true);
        jsonData.setData(items);

        return ResponseEntity.ok(jsonData);
    }
}

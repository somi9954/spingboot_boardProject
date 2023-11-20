package boardProject.models.file;

import boardProject.entities.file.FileInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    private final FileInfoService infoService;
    private final HttpServletResponse response;

    public void download(Long id) {
        // 지정된 id를 가지고 FileInfoService를 통해 FileInfo 객체를 가져옴
        FileInfo fileInfo = infoService.get(id);

        String filePath = fileInfo.getFilePath();
        File file = new File(filePath);

        if (!file.exists()) {// 파일이 존재하지 않는 경우
            throw new FileNotFoundException();
        }

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            OutputStream out  = response.getOutputStream();
            String fileName = fileInfo.getFileName();

            // HTTP 응답 헤더 설정
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
            response.setHeader("Content-Type", "application/octet-stream");
            response.setIntHeader("Expires", 0);
            response.setHeader("Cache-Control", "must-revalidate");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Length", String.valueOf(file.length()));

            while (bis.available() > 0) {
                out.write(bis.read());
            }

            out.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package boardProject.repositories;

import boardProject.entities.file.FileInfo;
import boardProject.entities.file.QFileInfo;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {

    /**
     *
     * @param gid
     * @param location
     * @param mode : all - 완료, 미완료 파일 모두 조회, done - 완료 파일, undone : 미완료 파일
     * @return
     */
    default List<FileInfo> getFiles(String gid, String location, String mode) {
        QFileInfo fileInfo = QFileInfo.fileInfo;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fileInfo.gid.eq(gid));

        if (location != null && !location.isBlank()) {
            builder.and(fileInfo.location.eq(location));
        }

        if (mode.equals("done")) builder.and(fileInfo.done.eq(true)); // 작업 완료 파일
        else if (mode.equals("undone")) builder.and(fileInfo.done.eq(false)); // 작업 미완료 파일

        List<FileInfo> items = (List<FileInfo>) findAll(builder, Sort.by(asc("createdAt")));

        return items;
    }

    /**
     *
     * @param gid
     * @param location
     * @return
     */
    default List<FileInfo> getFile(String gid, String location) {
        return getFiles(gid,location,"all");
    }

    default List<FileInfo> getFile(String gid) {
        return getFile(gid, null);
    }

    /**
     *
     * @param gid
     * @param location
     * @return
     */
    default List<FileInfo> getFileDone(String gid, String location) {
        return getFiles(gid,location,"done");
    }

    default List<FileInfo> getFileDone(String gid) {
        return getFileDone(gid, null);
    }

    /**
     * 작업 완료 처리
     *
     * @param gid
     */
    default void processDone(String gid) {
        List<FileInfo> items = getFile(gid);
        items.stream().forEach(item -> item.setDone(true));

        flush();
    }
}

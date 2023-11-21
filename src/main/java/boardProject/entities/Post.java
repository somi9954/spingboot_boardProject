package boardProject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;  // createDate 필드 추가
}

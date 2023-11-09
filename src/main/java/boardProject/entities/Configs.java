package boardProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity @Data
public class Configs {

    @Id
    private String code;

    @Lob
    private String value;
}

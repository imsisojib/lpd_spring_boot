package com.imsisojib.lpd.models.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "diaries", uniqueConstraints = {
        @UniqueConstraint(columnNames = "emi")
})
public class Diary {
    @Id
    @Size(min = 15, max = 15)
    private String emi;
    private String deviceName;
    private String modelName;
    private String brand;
    private Timestamp createdDate;
    private Timestamp lostDate;

}

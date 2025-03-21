package com.personnel.filestorage.metadataservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class FileShare {
    @Id
    @GeneratedValue
    private Integer id;
    private String sharedEmail;
    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private FileDetails file;
    private String permission;
}

package com.personnel.filestorage.metadataservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class FileDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String ownerEmail;
    private Long fileSize;
    private String publicId;
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileShare> sharedWith;
}

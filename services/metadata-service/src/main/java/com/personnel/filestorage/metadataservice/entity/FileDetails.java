package com.personnel.filestorage.metadataservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileShare> sharedWith;
}

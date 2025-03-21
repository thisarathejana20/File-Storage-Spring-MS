package com.personnel.filestorage.metadataservice.controller;

import com.personnel.filestorage.metadataservice.dto.FileResponse;
import com.personnel.filestorage.metadataservice.dto.FileShareRequest;
import com.personnel.filestorage.metadataservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class FileDetailsController {
    private final FileService fileService;

    @PostMapping("/share")
    public ResponseEntity<String> shareFile(@RequestBody FileShareRequest fileShareRequest) {
        return ResponseEntity.ok(fileService.shareFile(fileShareRequest));
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        return ResponseEntity.ok(fileService.deleteFile(fileId));
    }

    @PutMapping("/rename/{fileId}")
    public ResponseEntity<String> renameFile(@PathVariable Integer fileId, @RequestParam String newName) {
        return ResponseEntity.ok(fileService.updateFileDetails(fileId, newName));
    }

    @PostMapping("/remove-share/{fileId}")
    public ResponseEntity<String> removeShare(@PathVariable String fileId, @RequestParam String sharedEmail) {
        return ResponseEntity.ok(fileService.removeFileShare(fileId, sharedEmail));
    }

    @PutMapping("/update-permission/{fileId}")
    public ResponseEntity<String> updatePermission(
            @PathVariable String fileId,
            @RequestParam String sharedEmail,
            @RequestParam String permission) {
        return ResponseEntity.ok(fileService.updatePermission(fileId, sharedEmail, permission));
    }

    @GetMapping("/your-files")
    public ResponseEntity<List<FileResponse>> getYourFiles(@RequestParam String email) {
        return ResponseEntity.ok(fileService.getFiles(email));
    }

    @GetMapping("/shared-with-you")
    public ResponseEntity<List<FileResponse>> getSharedFiles(@RequestParam String email) {
        return ResponseEntity.ok(fileService.getSharedFiles(email));
    }
}

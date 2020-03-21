package pl.mjelen.wad.prepare.exercise2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.mjelen.wad.prepare.exercise2.service.XlsxService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class XlsxController {
    private final XlsxService xlsxService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("baseDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate,
                                           @RequestParam("path") String path) {

        return ResponseEntity.ok(xlsxService.upload(file, localDate, path));
    }
}

package pl.mjelen.wad.prepare.exercise2.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mjelen.wad.prepare.exercise2.service.exception.ApplicationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class XlsxService {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final int DATE_CELL = 1;

    private static final List<Long> ALLOWED_DATE_PERIODS = List.of(1L, 2L);

    public Void upload(MultipartFile file, LocalDate localDate, String path) {
        try (var fileIS = file.getInputStream();
             var workbook = new XSSFWorkbook(fileIS)) {

            var sheet = workbook.getSheetAt(0);

            List<Row> rowsToDelete = new ArrayList<>();
            for (var row : sheet) {
                if (sheet.getRow(0).equals(row)) continue;

                var dateCell = row.getCell(DATE_CELL);
                var date = parseDate(dateCell);

                var period = ChronoUnit.DAYS.between(localDate, date) % 7;
                if (period >= 1) {
                    rowsToDelete.add(row);
                }
            }
            rowsToDelete.forEach(sheet::removeRow);

            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new ApplicationException("Something went wrong, try debugging or check " +
//                    "file format (accepted is xls and xlsx).");
        }
        return null;
    }

    private LocalDate parseDate(Cell dateCell) {
        try {
            return DATE_FORMAT.parse(dateCell.getRichStringCellValue().getString())
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (ParseException e) {
            throw new ApplicationException(
                    String.format(
                            "Expected date format: %s, given: %s",
                            DATE_FORMAT,
                            dateCell.getStringCellValue()
                    )
            );
        }
    }
}

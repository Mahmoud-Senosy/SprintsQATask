package ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ExcelUtils {
    private final Workbook workbook;

    public ExcelUtils(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
    }

    public List<String[]> getData(String sheetName, String label) {
        List<String[]> data = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            Cell labelCell = row.getCell(0);
            if (labelCell != null && labelCell.toString().equals(label)) {
                int lastCellNum = row.getLastCellNum();
                String[] rowData = new String[lastCellNum - 1];  // Exclude label cell

                for (int i = 1; i < lastCellNum; i++) {  // Start from 1 to skip label
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            rowData[i-1] = String.valueOf((long) cell.getNumericCellValue());
                        } else {
                            rowData[i-1] = cell.toString();
                        }
                    } else {
                        rowData[i-1] = "";
                    }
                }
                data.add(rowData);
            }
        }

        return data;
    }

    public void close() throws IOException {
        workbook.close();
    }
}

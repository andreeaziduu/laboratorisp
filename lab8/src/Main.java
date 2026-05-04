import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {

            FileInputStream file = new FileInputStream(new File("lab8/laborator8_input.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();


                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();


                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        default:
                            System.out.print(" ");
                    }
                }
                System.out.println();
            }


            workbook.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        CopiereExcel("lab8/laborator8_input.xlsx", "lab8/laborator8_output2.xlsx");
        ExcelcuFormula("lab8/laborator8_input.xlsx", "lab8/laborator8_output3.xlsx");
    }

    public static void CopiereExcel (String caleIntrare, String caleIesire) {
        try (FileInputStream fileIn = new FileInputStream(new File(caleIntrare));
             XSSFWorkbook workbookIn = new XSSFWorkbook(fileIn);
             XSSFWorkbook workbookOut = new XSSFWorkbook()) {

            XSSFSheet sheetIn = workbookIn.getSheetAt(0);
            XSSFSheet sheetOut = workbookOut.createSheet("Rezultat_Medie");

            int rowNum = 0;
            for (Row rowIn : sheetIn) {

                Row rowOut = sheetOut.createRow(rowNum++);

                int lastCellNum = rowIn.getLastCellNum();
                double suma = 0;
                int countValori = 0;


                for (int i = 0; i < lastCellNum; i++) {
                    Cell cellIn = rowIn.getCell(i);
                    Cell cellOut = rowOut.createCell(i); //

                    if (cellIn != null) {

                        switch (cellIn.getCellType()) {
                            case STRING:
                                cellOut.setCellValue(cellIn.getStringCellValue());
                                break;
                            case NUMERIC:
                                double valoare = cellIn.getNumericCellValue();
                                cellOut.setCellValue(valoare);


                                if (i >= lastCellNum - 3) {
                                    suma += valoare;
                                    countValori++;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }


                Cell cellMedie = rowOut.createCell(lastCellNum);
                if (rowIn.getRowNum() == 0) {
                    cellMedie.setCellValue("Media ultimelor 3");
                } else if (countValori > 0) {
                    cellMedie.setCellValue(suma / countValori);
                }
            }

            try (FileOutputStream out = new FileOutputStream(caleIesire)) {
                workbookOut.write(out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ExcelcuFormula (String caleIntrare, String caleIesire) {
        try (FileInputStream fileIn = new FileInputStream(new File(caleIntrare));
             XSSFWorkbook workbookIn = new XSSFWorkbook(fileIn);
             XSSFWorkbook workbookOut = new XSSFWorkbook()) {

            XSSFSheet sheetIn = workbookIn.getSheetAt(0);
            XSSFSheet sheetOut = workbookOut.createSheet("Rezultat_Formula");

            int rowNum = 0;
            for (Row rowIn : sheetIn) {
                Row rowOut = sheetOut.createRow(rowNum++);
                int lastCellNum = rowIn.getLastCellNum();


                for (int i = 0; i < lastCellNum; i++) {
                    Cell cellIn = rowIn.getCell(i);
                    Cell cellOut = rowOut.createCell(i);

                    if (cellIn != null) {
                        switch (cellIn.getCellType()) {
                            case STRING:
                                cellOut.setCellValue(cellIn.getStringCellValue());
                                break;
                            case NUMERIC:
                                cellOut.setCellValue(cellIn.getNumericCellValue());
                                break;
                            default: break;
                        }
                    }
                }


                Cell cellFormula = rowOut.createCell(lastCellNum);
                if (rowIn.getRowNum() == 0) {
                    cellFormula.setCellValue("Media (Formula)");
                } else {
                    int currentExcelRow = rowIn.getRowNum() + 1;
                    String formula = "AVERAGE(D" + currentExcelRow + ":F" + currentExcelRow + ")";

                    cellFormula.setCellFormula(formula);
                }
            }


            try (FileOutputStream out = new FileOutputStream(caleIesire)) {
                workbookOut.write(out);
                System.out.println("Exercițiul 8.5.3 finalizat: " + caleIesire);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




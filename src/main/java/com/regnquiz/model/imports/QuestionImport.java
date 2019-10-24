package com.regnquiz.model.imports;

import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.MultipleChoice;
import com.regnquiz.model.Question;
import com.regnquiz.model.repositories.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QuestionImport {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private BookingQuestionRepository bookingQuestionRepository;

    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    @Transactional
    public void ImportQuestion(MultipartFile filename, int bookingID) {
        try {
            InputStream is = filename.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                Question question = new Question();

                question.setDescription(currentRow.getCell(1).getStringCellValue());
                question.setTime((int)currentRow.getCell(7).getNumericCellValue());
                List<MultipleChoice> mc = new ArrayList<>();
                //questionRepository.insertQuestionDescriptionTime(currentRow.getCell(1).getStringCellValue(), (int)currentRow.getCell(7).getNumericCellValue());
                if(currentRow.getCell(0).getStringCellValue().equals("TrueFalse")){
                    question.addMultipleChoice(new MultipleChoice(question, "T"));
                    question.addMultipleChoice(new MultipleChoice(question, "F"));
                    question.addBookingQuestion(new BookingQuestion(bookingRepository.findById(bookingID).get(), question));
                }
                questionRepository.save(question);

                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + " ");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + " ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


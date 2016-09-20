package store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import store.Product;
import store.Sale;

public class WriteExcel {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    private List<String> l;
    private List<Sale> ll;
    private String a, title;

    public String getTitle() {
        return title;
    }

    public List<Sale> getLl() {
        return ll;
    }

    public void setLl(List<Sale> ll) {
        this.ll = ll;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WritableCellFormat getTimesBoldUnderline() {
        return timesBoldUnderline;
    }

    public void setTimesBoldUnderline(WritableCellFormat timesBoldUnderline) {
        this.timesBoldUnderline = timesBoldUnderline;
    }

    public WritableCellFormat getTimes() {
        return times;
    }

    public void setTimes(WritableCellFormat times) {
        this.times = times;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> getL() {
        return l;
    }

    public void setL(List<String> l) {
        this.l = l;
    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);
        createContent1(excelSheet, ll, title);
        workbook.write();
        workbook.close();
    }
    public void writeFromAdate(String d) throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);
        createContentFromDate(excelSheet, ll, title,d);
        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TAHOMA, 14);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        //timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);
//sheet.addCell();
        // Write a few headers

        addCaption(sheet, 3, 1, "AMEGERWA");
        addCaption(sheet, 3, 2, "____________________________");
        // addCaption(sheet, 1, 3, "KIGALI-RWANDA");
        //=================================

        addCaption(sheet, 0, 6, "No");
        addCaption(sheet, 1, 6, "Material");
        addCaption(sheet, 2, 6, "Qty");
        addCaption(sheet, 3, 6, "Selling Unitprice");
        addCaption(sheet, 4, 6, "Received Amount");
        addCaption(sheet, 5, 6, "Selling Date");


    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
//        // Write a few number
//        for (int i = 1; i < 10; i++) {
//            // First column
//            addNumber(sheet, 0, i, i + 10);
//            // Second column
//            addNumber(sheet, 1, i, i * i);
//            addNumber(sheet, 2, i, i * i);
//        }
//        // Lets calculate the sum of it
//        StringBuffer buf = new StringBuffer();
//        buf.append("SUM(A2:A10)");
//        Formula f = new Formula(0, 10, buf.toString());
//        sheet.addCell(f);
//        buf = new StringBuffer();
//        buf.append("SUM(B2:B10)");
//        f = new Formula(1, 10, buf.toString());
//        sheet.addCell(f);
//
////    // now a bit of text
////    for (int i = 12; i < 20; i++) {
////      // First column
////       addCaption(sheet, 0, i, "Boring text " + i);
////      // Second column
////       addCaption(sheet, 1, i, "Another text");
////    }
    }
    private void createContent1(WritableSheet sheet, List<Sale> l, String title) throws WriteException,
            RowsExceededException {
        // Write a few number
        //JOptionPane.showMessageDialog(null, list);



        addCaption(sheet, 3, 4, title);

        int no = 1;
        double totqty=0,totam=0;
        for (int i = 0; i < l.size(); i++) {
         totqty=totqty+l.get(i).getQty();
         totam=totam+l.get(i).getReceived_amount();
           
//            vv[i][0] = t[0];
//            vv[i][1] = t[1];
            //addCaption(sheet, 0, i + 8, no+"");
            addNumberInteger(sheet, 0, i + 8, no);
            addCaption(sheet, 1, i + 8, Product.getProduct(l.get(i).getProd_id() + "").getProdname());
            //addCaption(sheet, 2, i + 8, l.get(i).getQty()+"");
            addNumber(sheet, 2, i + 8, l.get(i).getQty());
            // Second column
            // addCaption(sheet, 3, i + 8, l.get(i).getUnitprice()+"");
            addNumber(sheet, 3, i + 8, l.get(i).getUnitprice());
            // addCaption(sheet, 4, i + 8, l.get(i).getReceived_amount()+"");//TEST NUMBER HERE
            addNumber(sheet, 4, i + 8, l.get(i).getReceived_amount());
            addCaption(sheet, 5, i + 8, l.get(i).getSelling_date() + "");
 no++;

        }
        //addNumber(sheet, 2, no + 2, totqty);
        //addNumber(sheet,4, no + 2, totam);

        // Lets calculate the sum of it
//        StringBuffer buf = new StringBuffer();
//        buf.append("SUM(A2:A10)");
//        Formula f = new Formula(0, 10, buf.toString());
//        sheet.addCell(f);
//        buf = new StringBuffer();
//        buf.append("SUM(B2:B10)");
//        f = new Formula(1, 10, buf.toString());
//        sheet.addCell(f);
//    // now a bit of text
//    for (int i = 12; i < 20; i++) {
//      // First column
//       addCaption(sheet, 0, i, "Boring text " + i);
//      // Second column
//       addCaption(sheet, 1, i, "Another text");
//    }
    }
    private void createContentFromDate(WritableSheet sheet, List<Sale> l, String title,String date) throws WriteException,
            RowsExceededException {
        // Write a few number
        //JOptionPane.showMessageDialog(null, list);



        addCaption(sheet, 3, 4, title);

        int no = 0;
        double totqty=0,totam=0;
        for (int i = 0; i < l.size(); i++) {
            if(l.get(i).getSelling_date().toString().equalsIgnoreCase(date)){
         totqty=totqty+l.get(i).getQty();
         totam=totam+l.get(i).getReceived_amount();
            no++;
//            vv[i][0] = t[0];
//            vv[i][1] = t[1];
            //addCaption(sheet, 0, i + 8, no+"");
            addNumberInteger(sheet, 0, i + 8, no);
            addCaption(sheet, 1, i + 8, Product.getProduct(l.get(i).getProd_id() + "").getProdname());
            //addCaption(sheet, 2, i + 8, l.get(i).getQty()+"");
            addNumber(sheet, 2, i + 8, l.get(i).getQty());
            // Second column
            // addCaption(sheet, 3, i + 8, l.get(i).getUnitprice()+"");
            addNumber(sheet, 3, i + 8, l.get(i).getUnitprice());
            // addCaption(sheet, 4, i + 8, l.get(i).getReceived_amount()+"");//TEST NUMBER HERE
            addNumber(sheet, 4, i + 8, l.get(i).getReceived_amount());
            addCaption(sheet, 5, i + 8, l.get(i).getSelling_date() + "");


        }
        //addNumber(sheet, 2, no + 2, totqty);
        //addNumber(sheet,4, no + 2, totam);

        // Lets calculate the sum of it
//        StringBuffer buf = new StringBuffer();
//        buf.append("SUM(A2:A10)");
//        Formula f = new Formula(0, 10, buf.toString());
//        sheet.addCell(f);
//        buf = new StringBuffer();
//        buf.append("SUM(B2:B10)");
//        f = new Formula(1, 10, buf.toString());
//        sheet.addCell(f);
//    // now a bit of text
//    for (int i = 12; i < 20; i++) {
//      // First column
//       addCaption(sheet, 0, i, "Boring text " + i);
//      // Second column
//       addCaption(sheet, 1, i, "Another text");
//    }
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    private void addNumberInteger(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Double integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

//    private void  addCaption(WritableSheet sheet, int column, int row, String s)
//            throws WriteException, RowsExceededException {
//        Label label;
//        label = new Label(column, row, s, times);
//        sheet.addCell(label);
//    }
    public static void main(String[] args) throws WriteException, IOException {
        WriteExcel test = new WriteExcel();
        File f1 = new File("D://RGL");
        f1.mkdir();
        File f2 = new File(f1, "Report1.xls");
        f2.createNewFile();
        test.setOutputFile(f2.getAbsolutePath());
        test.write();
        System.out.println("Please check the result file under " + f2.getAbsolutePath());
    }
}

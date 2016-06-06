package com.custom.customManageSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.custom.customManageSystem.model.Custom;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtils {

	public static List<Custom> readExcel(String path){
		List<Custom> result = new ArrayList<Custom>();
		File file = new File(path);
		Workbook workbook = null;
		try {
			InputStream input = new FileInputStream(file);
			workbook = Workbook.getWorkbook(input);
			Sheet sheet = workbook.getSheet(0);
			int cols = sheet.getColumns();
			int rows = sheet.getRows();
			System.out.println("该sheet有：" + rows + "行   " + cols + "列");
			Custom custom = null;
			for(int i=3;i<rows;i++){
				custom = new Custom();
				String licenseplates = sheet.getCell(0,i).getContents();
				String idcard = sheet.getCell(1,i).getContents();
				String agencycode = sheet.getCell(2,i).getContents();
			    String phonenum = sheet.getCell(3,i).getContents();
			    String carowner = sheet.getCell(4,i).getContents();
			    String insurer = sheet.getCell(5,i).getContents();
			    String insured = sheet.getCell(6,i).getContents();
			    String carmodel = sheet.getCell(7,i).getContents();
			    String carframecode = sheet.getCell(8,i).getContents();
			    String enginecode = sheet.getCell(9,i).getContents();
			    Cell firstCell = sheet.getCell(10,i);
			    Cell startCell = sheet.getCell(11,i);
			    Cell endCell = sheet.getCell(12,i);
//			    String firsttime = null;
//			    String starttime = null;
//			    String endtime = null;
			    Date firsttime = null;
			    Date starttime = null;
			    Date endtime = null;
			    if(firstCell.getType() == CellType.DATE){
//			    	firsttime = formatDate2Str(((DateCell)firstCell).getDate());
			    	firsttime = ((DateCell)firstCell).getDate();
			    }else {
//			    	firsttime = firstCell.getContents();
			    	firsttime = formatStr2Date(firstCell.getContents());
			    }
			    if(startCell.getType() == CellType.DATE){
//			    	starttime = formatDate2Str(((DateCell)startCell).getDate());
			    	starttime = ((DateCell)startCell).getDate();
			    }else {
//			    	starttime = startCell.getContents();
			    	starttime = formatStr2Date(startCell.getContents());
			    }
			    if(endCell.getType() == CellType.DATE){
//			    	endtime = formatDate2Str(((DateCell)endCell).getDate());
			    	endtime = ((DateCell)endCell).getDate();
			    }else {
//			    	endtime = endCell.getContents();
			    	endtime = formatStr2Date(endCell.getContents());
			    }
			    String insurance = sheet.getCell(13,i).getContents();
			    String insurancecode = sheet.getCell(14,i).getContents();
			    String cardamage = sheet.getCell(15,i).getContents();
			    String robbery = sheet.getCell(16,i).getContents();
			    String three20 = sheet.getCell(17,i).getContents();
			    String three30 = sheet.getCell(18,i).getContents();
			    String three50 = sheet.getCell(19,i).getContents();
			    String three100 = sheet.getCell(20,i).getContents();
			    String three150 = sheet.getCell(21,i).getContents();
			    String driver = sheet.getCell(22,i).getContents();
			    String passenger = sheet.getCell(23,i).getContents();
			    String foreignglass = sheet.getCell(24,i).getContents();
			    String domesticglass = sheet.getCell(25,i).getContents();
			    String nick2 = sheet.getCell(26,i).getContents();
			    String nick5 = sheet.getCell(27,i).getContents();
			    String nick10 = sheet.getCell(28,i).getContents();
			    String nick15 = sheet.getCell(29,i).getContents();
			    String autoignition = sheet.getCell(30,i).getContents();
			    String wading = sheet.getCell(31,i).getContents();
			    String remark = sheet.getCell(32,i).getContents();
			    
				custom.setLicenseplates(licenseplates);
				custom.setIdcard(idcard);
				custom.setAgencycode(agencycode);
				custom.setPhonenum(phonenum);
				custom.setCarowner(carowner);
				custom.setInsurer(insurer);
				custom.setInsured(insured);
				custom.setCarmodel(carmodel);
				custom.setCarframecode(carframecode);
				custom.setEnginecode(enginecode);
				custom.setFirsttime(firsttime);
				custom.setStarttime(starttime);
				custom.setEndtime(endtime);
				custom.setInsurance(insurance);
				custom.setInsurancecode(insurancecode);
				custom.setCardamage(cardamage);
				custom.setRobbery(robbery);
				custom.setThree20(three20);
				custom.setThree30(three30);
				custom.setThree50(three50);
				custom.setThree100(three100);
				custom.setThree150(three150);
				custom.setDriver(driver);
				custom.setPassenger(passenger);
				custom.setForeignglass(foreignglass);
				custom.setDomesticglass(domesticglass);
				custom.setNick2(nick2);
				custom.setNick5(nick5);
				custom.setNick10(nick10);
				custom.setNick15(nick15);
				custom.setAutoignition(autoignition);
				custom.setWading(wading);
				custom.setRemark(remark);
				
				result.add(custom);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		return result;
	}
	
	public static String writeExcel(List<Custom> result,String path){
		String reusltPath = "";
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File folder = new File(path);
		File coustomXlsFile = new File(path + "/Custom.xls"); 
		String fileName = StringUtils.getRandomString(5) + time + "Custom" + ".xls";
		File file = new File(folder,fileName);
		WritableWorkbook workbook = null;
		Workbook wb = null;
		try {
			FileUtils.copyFile(coustomXlsFile, file);
			if(!file.exists()){
				file.createNewFile();
			}
			wb = Workbook.getWorkbook(new FileInputStream(file));
			workbook = Workbook.createWorkbook(file, wb);
			WritableSheet sheet = (WritableSheet) workbook.getSheet(0);
			int cols = sheet.getColumns();
			int rows = sheet.getRows();
			System.out.println("该sheet有：" + rows + "行   " + cols + "列");
			WritableCellFormat cellFormat1=new WritableCellFormat();
			DateFormat dFormat = new DateFormat("yyyy/MM/dd");
			WritableCellFormat cellFormat2=new WritableCellFormat(dFormat);//年月日格式
			cellFormat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 
			cellFormat1.setAlignment(Alignment.CENTRE);
			cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
		    cellFormat1.setWrap(true);
		    cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 
			cellFormat2.setAlignment(Alignment.CENTRE);
			cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
		    cellFormat2.setWrap(true);
		    /* 
	         * WritableFont.createFont("微软雅黑")：设置字体为宋体 
	         * 10：设置字体大小 
	         * WritableFont.BOLD:设置字体加粗（BOLD：加粗     NO_BOLD：不加粗） 
	         * false：设置非斜体 
	         * UnderlineStyle.NO_UNDERLINE：没有下划线 
	         */  
		    WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);  
		    cellFormat1.setFont(font);
		    cellFormat2.setFont(font);
		    Label label = null;
		    Custom custom = null;
			for(int i=3;i<result.size() + 3;i++){
				custom = result.get(i - 3);
				
				label = new Label(0,i,custom.getLicenseplates());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(1,i,custom.getIdcard());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(2,i,custom.getAgencycode());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(3,i,custom.getPhonenum());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(4,i,custom.getCarowner());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(5,i,custom.getInsurer());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(6,i,custom.getInsured());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(7,i,custom.getCarmodel());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(8,i,custom.getCarframecode());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(9,i,custom.getEnginecode());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
////				DateTime dateLabel = new DateTime(10,i, formatStr2Date(custom.getFirsttime()));
//				DateTime dateLabel = new DateTime(10,i, custom.getFirsttime());
//				dateLabel.setCellFormat(cellFormat2);
//				sheet.addCell(dateLabel);
////				dateLabel = new DateTime(11,i, formatStr2Date(custom.getStarttime()));
//				dateLabel = new DateTime(11,i, custom.getStarttime());
//				dateLabel.setCellFormat(cellFormat2);
//				sheet.addCell(dateLabel);
////				dateLabel = new DateTime(12,i, formatStr2Date(custom.getEndtime()));
//				dateLabel = new DateTime(12,i, custom.getEndtime());
//				dateLabel.setCellFormat(cellFormat2);
//				sheet.addCell(dateLabel);
				label = new Label(10,i,formatDate2Str(custom.getFirsttime()));
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(11,i,formatDate2Str(custom.getStarttime()));
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(12,i,formatDate2Str(custom.getEndtime()));
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(13,i,custom.getInsurance());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(14,i,custom.getInsurancecode());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(15,i,custom.getCardamage());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(16,i,custom.getRobbery());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(17,i,custom.getThree20());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(18,i,custom.getThree30());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(19,i,custom.getThree50());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(20,i,custom.getThree100());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(21,i,custom.getThree150());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(22,i,custom.getDriver());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(23,i,custom.getPassenger());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(24,i,custom.getForeignglass());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(25,i,custom.getDomesticglass());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(26,i,custom.getNick2());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(27,i,custom.getNick5());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(28,i,custom.getNick10());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(29,i,custom.getNick15());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(30,i,custom.getAutoignition());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(31,i,custom.getWading());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				label = new Label(32,i,custom.getRemark());
				label.setCellFormat(cellFormat1);
				sheet.addCell(label);
				
			}
			workbook.write();
			workbook.close();
			wb.close();
			reusltPath = fileName.substring(0, fileName.length() - 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reusltPath;
	}
	
	public static Date formatStr2Date(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			String[] mmString = dateStr.split("/");
			if(mmString[1].length() < 2){
				mmString[1] = "0" + mmString[1];
				dateStr = mmString[0] + "/" + mmString[1] + "/" + mmString[2];
			}
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String formatDate2Str(Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
//		String path1 = "I:/java����/java��Ŀ/Custom.xls";
//		String path2 = "I:/java����/java��Ŀ";
//		System.out.println(writeExcel(readExcel(path1), path2));;
	}
}

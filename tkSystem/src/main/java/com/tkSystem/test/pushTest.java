package com.tkSystem.test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.tkSystem.tools.WyMap;  
public class pushTest {
	public static void main(String[] args) throws WriterException, IOException {
		/*ShortMessageService sh=new ShortMessageService();
		Byte i=2;
		try {
			int yzm=ToolsUtil.getVerificationCode();
			HashMap<String, String> ce=sh.sendSms("17620588879",i,String.valueOf(yzm));
			System.out.println(ce);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
        String filePath = "D://";  
       String fileName = "zxing.png";  
       WyMap json =new WyMap();
       json.put(  
               "zxing",  
               "https://github.com/zxing/zxing/tree/zxing-3.0.0/javase/src/main/java/com/google/zxing");  
       json.put("author", "shihy");  
      
       System.out.println(MatrixToImageWriter(  filePath,   fileName,  json));  
	}
	public static boolean MatrixToImageWriter( String filePath, String fileName,WyMap wymap) {
		boolean bool=false;
		try {
		    String content = wymap.toString();
		       int width = 200; // 图像宽度  
		       int height = 200; // 图像高度  
		       String format = "png";// 图像类型  
		       Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
		       hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
		       BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
		               BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
		       Path path = FileSystems.getDefault().getPath(filePath, fileName);  
		       MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
		       System.out.println("输出成功."); 
		       bool=!false;
		} catch (Exception e) {
		}
		return bool;
	}
}

package com.jsorg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.date.DateUtil;

public class FileUtil {
	public void saveFile(MultipartFile file,String filename) {
        FileOutputStream fos = null;
        // 文件名设置 yyyyMMdd 1.doc
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        File saveFileDir = new File("D:\\my-project\\static\\uploadimage", filename);
        try {
            fos = new FileOutputStream(saveFileDir);
            fos.write(file.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public Object download(HttpServletResponse response) throws IOException {
		String filePath = "D:\\my-project\\static\\uploadimage\\demo-sheet.xls";
        String fileName = "demo-sheet.xls";
        String fileType = "application/octet-stream";

        //设置文件的类型
        response.setContentType(fileType);
        // 确保弹出下载对话框
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        FileInputStream inputStream = new FileInputStream(filePath);
        //输出流
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[4096];
        int length;
        while ((length = inputStream.read(bytes)) > 0){
            outputStream.write(bytes, 0, length);
        }
        inputStream.close();
        outputStream.flush();
        
        return 0;
	}

}

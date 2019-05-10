package com.jsorg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.date.DateUtil;

public class FileUtil {
	public void saveFile(MultipartFile file,String filename) {
        FileOutputStream fos = null;
        // 文件名设置 yyyyMMdd 1.doc
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        File saveFileDir = new File("D:\\my-project\\static\\image", filename);
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

}

package com.example.demo.util;
import org.apache.commons.io.FilenameUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFileToFileConverter {
    public static File convertToFile(MultipartFile multipartfile)  {
        String type = FilenameUtils.getExtension(multipartfile.getOriginalFilename());

        if(!type.equals("csv"))
            throw new IllegalArgumentException(String.format(
                    "Multipart file: %s has type %s, but need %s",multipartfile.getOriginalFilename(),type,"csv"));

        File convFile = new File(multipartfile.getOriginalFilename());

        try {
            convFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartfile.getBytes());
            fos.close();

        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Multipart file: %s" +
                    "\nerror message: %s",multipartfile.getOriginalFilename(),e.getMessage()));
        }

        return convFile;
    }
}

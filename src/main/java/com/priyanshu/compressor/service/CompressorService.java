package com.priyanshu.compressor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class CompressorService {

    public void compress(String folder) {
        File rootfolder=new File(folder);

        if(!(rootfolder.exists() && rootfolder.isDirectory())) {
            System.out.println("Provided path is not a valid folder.");
        }

        List<String> filesList = new LinkedList<>();
       fetchAllValidFilesList(folder, filesList);
        System.out.println("Total" + filesList.size() + " files found for Conversion. Starting Conversion Now.");

        Iterator<String> it = filesList.iterator();
        while(it.hasNext()) {
            convertToWebp(it.next());
        }
        System.out.println("Conversion Completed");

    }

    private void convertToWebp(String img) {
        System.out.println("Converting "+img+" file to webp");
        try {
            File file = new File(img);
            String newFileName = img.substring(0, img.lastIndexOf(".")) + ".webp";
            BufferedImage image = ImageIO.read(file);
            System.out.println("Writing new File at "+ newFileName);
            boolean fileConversionRes = ImageIO.write(image, "webp",new File(newFileName));
            // Delete Existing File
            if(fileConversionRes) {
                file.delete();
            }
            else {
                System.out.println("Failed to Write File "+newFileName);
            }

        }
        catch(Exception e) {
            System.out.println("Conversion Failed For File: " + img);
        }
    }

    // Returns List of All Files Under a Directory.
    private List<String> fetchAllValidFilesList(String filepath, List<String> filesList)
    {
        ;
        File file=new File(filepath);
        if((file.exists()) && (file.isFile()) && (!file.isHidden())  && (filepath.endsWith(".jpg") || filepath.endsWith(".JPG")) )
        {
            System.out.println("Adding "+filepath+" file into Conversion Queue");
            filesList.add(filepath);
        }
        else
        {
            if((file.exists()) && (file.isDirectory()) && (!file.isHidden()))
            {
                for(File tempFile: file.listFiles())
                {
                    fetchAllValidFilesList(tempFile.getAbsolutePath(), filesList);
                }
            }
        }
        return filesList;
    }
}

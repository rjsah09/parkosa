package com.parkosa.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringTokenizer;

public class ImageSaver {
	public static String saveImage(String path) {
			File file = new File(path);
			System.out.println(path);
			
			String newPath = "C:\\parkingLotImage\\";
			String newFileName = "" + System.nanoTime();
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			
			String newFilePath = newPath + newFileName + "." + extension;
			
			File newFile = new File(newFilePath);
			
			try {
				System.out.println(newFilePath);
				Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				return null;
			}
			
		return newFilePath;
	}
	 
}

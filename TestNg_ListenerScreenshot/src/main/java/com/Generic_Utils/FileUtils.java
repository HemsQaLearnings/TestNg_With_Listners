package com.Generic_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	// type---> /** then press enter
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */

	
	public String readata(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(IframeConstant.PropertyFilePath);
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
		
	}
}

package com.Generic_Utils;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class JavaUtils {

	/**
	 * This Method is Used to Return Random Numbers
	 * @author Hems
	 * @return
	 */
	public int getRandomdata(int digit) {
		Random ran = new Random();
		int random = ran.nextInt(digit);
		return random;
	}

	/**
	 * this method is used to get System Date
	 * 
	 * @return
	 */

	public String getSystemDate() {
		Date dt = new Date();
		String date = dt.toString();
		return date;
	}
	/**
	 * This Method is used to get System Date in Format
	 * @return
	 */
	
	
	public String getSystemDateInFormat() {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH-mm-SS");
		Date dt = new Date();
		String SystemDateFormat = dateformat.format(dt);
		return SystemDateFormat;
	}
}
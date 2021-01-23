package com.simplilearn.virtualkeyrepo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class VirtualKeyRepositories {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to LockedMe.com");
		System.out.println("Developer: Prakash Sagadevan");
		System.out.println("");

		Scanner scanner = new Scanner(System.in);
		displayMenu(scanner);
		scanner.close();
		//getUserInput();
	}

	private static void displayMenu(Scanner scanner) {

		System.out
				.println("Select the following numeric options to do the mentioned operations");
		System.out.println("1:List all files");
		System.out.println("2:Add/Delete/Search a file");
		/*System.out.println("a:Add a file");
		System.out.println("b:Delete a file");
		System.out.println("c:Search a file");
		System.out.println("Press other key to Navigate to main context menu");*/
		//System.out.println("3:Navigate to main context menu");
		System.out.println("Press other key to exit");

		getUserInput(scanner);
	}

	private static void getUserInput(Scanner scanner) {
		int inpNum = 0;
		
		try {
			inpNum = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException nfex) {
			System.exit(0);
		}
		
		String fileName = null;
		switch (inpNum) {
			case 1:
				System.out.println("Provide the file path:");
				fileName = getFileName(inpNum,scanner);
				listFiles(fileName,scanner);
				break;
			case 2:
				doFileOprtn(inpNum,fileName,scanner);
			/*case 3:
				displayMenu(scanner);
				break;*/
			default:
				System.exit(0);
		}
	}

	private static void doFileOprtn(int inpNum, String fileName, Scanner scanner) {
		int fileOpt = 0;

		System.out.println("1:Add a file");
		System.out.println("2:Delete a file");
		System.out.println("3:Search a file");
		System.out.println("Press other key to Navigate to main context menu");
		try {
			fileOpt = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException nfex) {
			displayMenu(scanner);
		}
		
		switch(fileOpt) {
			case 1:
				System.out.println("Provide the file name and path for adding a file:");
				fileName = getFileName(inpNum,scanner);
				addFile(fileName,scanner);
				break;
			case 2:
				System.out.println("Provide the file name and path for deleting the file:");
				fileName = getFileName(inpNum,scanner);
				deleteFile(fileName,scanner);
				break;
			case 3:
				System.out.println("Provide the file name and path to search the file:");
				fileName = getFileName(inpNum,scanner);
				searchFile(fileName,scanner);
				break;
			default:
				displayMenu(scanner);
		}

	}

	private static String getFileName(int inpNum, Scanner scanner) {

		String fileName = scanner.nextLine();
		//scanner.close();
		if(fileName != null && fileName.trim().length() > 0) {
			return fileName;
		} else {
			System.out.println(new StringBuilder("Invalid file name:").append(fileName).toString());
			displayMenu(scanner);
			//getFileName(inpNum,scanner);
		}
		return fileName;
	}

	private static void listFiles(String fileName,Scanner scanner) {
		File file = new File(fileName);
		if(file.exists()) {
			String[] files = file.list();
			if(files != null && files.length > 0) {
				Collections.sort(Arrays.asList(files));
				for(String name : files) {
					System.out.println(name);
				}
			} else {
				System.out.println(new StringBuilder("There are NO files in the given path ").append(fileName).toString());
			}
		} else {
			System.out.println(new StringBuilder("Invalid directory/file path:").append(fileName).toString());
		}
		displayMenu(scanner);
	}

	private static void addFile(String fileName,Scanner scanner) {
		File file = new File(fileName);
		boolean oprFlg = false;
		try {
			oprFlg = file.createNewFile();
		} catch (IOException e) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT added ").toString());
			displayMenu(scanner);
			return;
		}
		if(oprFlg) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" added successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT added ").toString());
		}
			
		displayMenu(scanner);
	}

	private static void deleteFile(String fileName,Scanner scanner) {
		File file = new File(fileName);
		boolean oprFlg = file.delete();
		if(oprFlg) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" deleted successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT deleted ").toString());
		}
		displayMenu(scanner);
		
	}

	private static void searchFile(String fileName,Scanner scanner) {
		File file = new File(fileName);
		boolean oprFlg = file.exists();
		if(oprFlg) {
			System.out.println(new StringBuilder("File ").append(fileName).append(" searched successfully!").toString());
		} else {
			System.out.println(new StringBuilder("File ").append(fileName).append(" NOT available ").toString());
		}
		displayMenu(scanner);
	}

}

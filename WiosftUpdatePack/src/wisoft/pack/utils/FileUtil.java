package wisoft.pack.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	 public static  void delFolder(String folderPath) {
		  try {
		        delAllFile(folderPath); //ɾ����������������
		        String filePath = folderPath;
		        filePath = filePath.toString();
		        java.io.File myFilePath = new java.io.File(filePath);
		        myFilePath.delete(); //ɾ�����ļ���
		     } catch (Exception e) {
		       e.printStackTrace(); 
		     }
	 }
	 
	 public static  boolean  delAllFile(String path)
	 {
		   boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	    	   file.delete();
	         return true;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	             delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
	             flag = true;
	          }
	       }
	       return flag;
	 }
	 
	 public static void copyFile(File f1,File f2) throws Exception{   
		 FileUtils.copyFile(f1, f2);  
	} 
}
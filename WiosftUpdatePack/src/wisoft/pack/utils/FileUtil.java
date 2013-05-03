package wisoft.pack.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

	 public static  void delFolder(String folderPath) {
		  try {
		        delAllFile(folderPath); //删除完里面所有内容
		        String filePath = folderPath;
		        filePath = filePath.toString();
		        java.io.File myFilePath = new java.io.File(filePath);
		        myFilePath.delete(); //删除空文件夹
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
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	 }
	 
	 public static void copyFile(File f1,File f2) throws Exception{   
			int length=2097152;   
			FileInputStream in=new FileInputStream(f1);   
			FileOutputStream out=new FileOutputStream(f2);   
			byte[] buffer=new byte[length];   
			while(true){   
				int ins=in.read(buffer);   
				if(ins==-1){   
					in.close();   
					out.flush();   
					out.close();   
					return;   
				}
				else  
					out.write(buffer,0,ins);   
		   }   
	} 
}

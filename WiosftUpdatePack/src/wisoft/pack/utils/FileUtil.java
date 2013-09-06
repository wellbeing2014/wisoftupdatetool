package wisoft.pack.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	 public static  void delFolder1(String folderPath) {
		  try {
		        delAllFile1(folderPath); //删除完里面所有内容
		        String filePath = folderPath;
		        filePath = filePath.toString();
		        java.io.File myFilePath = new java.io.File(filePath);
		        myFilePath.delete(); //删除空文件夹
		     } catch (Exception e) {
		       e.printStackTrace(); 
		     }
	 }
	 
	 public static  boolean  delAllFile1(String path)
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
	             delAllFile1(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder1(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	 }
	 
	 public static void copyFile(File f1,File f2) throws Exception{   
		 FileUtils.copyFile(f1, f2);  
	} 
	 
	 /**
	  * 根据文件路径删除（包括文件夹及文件夹内文件）
	 * @param filepath
	 * @throws IOException
	 */
	public static void delete(String filepath) throws IOException{  
		File f = new File(filepath);//定义文件路径         
		if(f.exists() && f.isDirectory()){//判断是文件还是目录  
		   if(f.listFiles().length==0){//若目录下没有文件则直接删除  
		      f.delete();  
		   }else{//若有则把文件放进数组，并判断是否有下级目录  
		      File delFile[]=f.listFiles();  
		      int i =f.listFiles().length;  
		      for(int j=0;j<i;j++){  
		         if(delFile[j].isDirectory())
		        	 delete(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径  
		         else
		        	 delFile[j].delete();//删除文件  
		      }  
		   } 
		   f.delete();//再删除空文件夹
		}
		
	 }
}

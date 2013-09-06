package wisoft.pack.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	 public static  void delFolder1(String folderPath) {
		  try {
		        delAllFile1(folderPath); //ɾ����������������
		        String filePath = folderPath;
		        filePath = filePath.toString();
		        java.io.File myFilePath = new java.io.File(filePath);
		        myFilePath.delete(); //ɾ�����ļ���
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
	             delAllFile1(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	             delFolder1(path + "/" + tempList[i]);//��ɾ�����ļ���
	             flag = true;
	          }
	       }
	       return flag;
	 }
	 
	 public static void copyFile(File f1,File f2) throws Exception{   
		 FileUtils.copyFile(f1, f2);  
	} 
	 
	 /**
	  * �����ļ�·��ɾ���������ļ��м��ļ������ļ���
	 * @param filepath
	 * @throws IOException
	 */
	public static void delete(String filepath) throws IOException{  
		File f = new File(filepath);//�����ļ�·��         
		if(f.exists() && f.isDirectory()){//�ж����ļ�����Ŀ¼  
		   if(f.listFiles().length==0){//��Ŀ¼��û���ļ���ֱ��ɾ��  
		      f.delete();  
		   }else{//��������ļ��Ž����飬���ж��Ƿ����¼�Ŀ¼  
		      File delFile[]=f.listFiles();  
		      int i =f.listFiles().length;  
		      for(int j=0;j<i;j++){  
		         if(delFile[j].isDirectory())
		        	 delete(delFile[j].getAbsolutePath());//�ݹ����del������ȡ����Ŀ¼·��  
		         else
		        	 delFile[j].delete();//ɾ���ļ�  
		      }  
		   } 
		   f.delete();//��ɾ�����ļ���
		}
		
	 }
}

package wisoft.pack.utils;
import java.io.File;     
import java.io.FileInputStream;     
import java.io.FileOutputStream;     
import java.io.InputStream;     
import java.util.ArrayList;     
import java.util.Enumeration;     
import java.util.List;     
import org.apache.tools.zip.ZipEntry;     
import org.apache.tools.zip.ZipFile;     
import org.apache.tools.zip.ZipOutputStream;     


public class ZipUtil {     

	private String comment = "";     
	private static int BUFFER = 1024;
	public void setComment(String comment) {     
         this.comment = comment;     
     }     

    public void zip(String src, String dest, List filter) throws Exception {     

    	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest));     
        File srcFile = new File(src);     
        zip(out,srcFile,"",filter);     
        out.close();     

    }     
    public void zip(String src, String dest) throws Exception {     

    	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest));     
        File srcFile = new File(src);     
        zip(out,srcFile,"");     
        out.close();     

    }  

    public void zip(ZipOutputStream out, File srcFile, String base, List filter) throws Exception {     
        if(srcFile.exists()==false) {     
             throw new Exception("压缩目录不存在!");     
        }     
        if(srcFile.isDirectory()) {     
             File[] files = srcFile.listFiles();     
             base = base.length() == 0 ? "" : base + "/";     
             if (isExist(base, filter)) {     
                 out.putNextEntry(new ZipEntry(base));     
             }     
             for(int i=0; i<files.length; i++) {     
                 zip(out,files[i],base + files[i].getName(),filter);     
             }     
         } else {     
             if (isExist(base, filter)) {     
                 base = base.length() == 0 ? srcFile.getName() : base ;     
                 ZipEntry zipEntry = new ZipEntry(base);     
                 zipEntry.setComment(comment);     
                out.putNextEntry(zipEntry);     
                FileInputStream in = new FileInputStream(srcFile);     
                 int length = 0;     
                byte[] b = new byte[BUFFER];     
                 while((length=in.read(b,0,1024))!=-1) {     
                     out.write(b,0,length);     
                 }     
                 in.close();     
             }     
         }     
     }     

     public boolean isExist(String base, List list) {     
         if (list != null && !list.isEmpty()) {     
             for (int i = 0; i < list.size(); i++) {     
                 if (base.indexOf((String) list.get(i)) >= 0) {     
                     return true;     
                 }     
            }     
         }     
         return false;     
     }     

           
     public void unZip(String srcFile,String dest,boolean deleteFile)  throws Exception {     
         File file = new File(srcFile);     
         if(!file.exists()) {     
             throw new Exception("解压文件不存在!");     
        }     
         ZipFile zipFile = new ZipFile(file);     
         Enumeration e = zipFile.getEntries();     
         while(e.hasMoreElements()) {     
            ZipEntry zipEntry = (ZipEntry)e.nextElement();     
             if(zipEntry.isDirectory()) {     
                 String name = zipEntry.getName();     
                 name = name.substring(0,name.length()-1);     
                 File f = new File(dest + name);     
                 f.mkdirs();     
             } else {     

                 File f = new File(dest + zipEntry.getName());     
                 f.getParentFile().mkdirs();     
                 f.createNewFile();     
                 InputStream is = zipFile.getInputStream(zipEntry);     
                 FileOutputStream fos = new FileOutputStream(f);     
                 int length = 0;     
                 byte[] b = new byte[BUFFER];     
                 while((length=is.read(b, 0, BUFFER))!=-1) {     
                     fos.write(b, 0, length);     
                 }     
                 is.close();     
                 fos.close();     
             }     
         }     
         if (zipFile != null) {     
             zipFile.close();     
         }     
         if(deleteFile) {     
             file.deleteOnExit();     
		}     
     }     
    	     
          
     public static String getZipComment(String srcFile) {     
        String comment = "";     
        try {     
            ZipFile zipFile = new ZipFile(srcFile);     
            Enumeration e = zipFile.getEntries();     
       
             while (e.hasMoreElements()) {     
                 ZipEntry ze = (ZipEntry) e.nextElement();     
      
                 comment = ze.getComment();     
                if (comment != null && !comment.equals("")     
                        && !comment.equals("null")) {     
                    break;     
                }     
            }     
      
           zipFile.close();     
        } catch (Exception e) {     
            System.out.println("获取zip文件注释信息失败:" + e.getMessage());     
        }           
         return comment;     
    } 
     

 public static void zip(ZipOutputStream out, File srcFile, String base) throws Exception {   

     if (!srcFile.exists()) {   
         throw new Exception("压缩目录不存在！");   
     }   
     if (srcFile.isDirectory()) {   
         File[] files = srcFile.listFiles();   
        base = base.length() == 0 ? "" : base + "/";   
         if (base.length() > 0) {   
        	 ZipEntry zipEntry = new ZipEntry(base);     
             //zipEntry.setComment(comment);  
             out.putNextEntry(zipEntry);   
         }   
         for (int i = 0; i < files.length; i++) {   
             zip(out, files[i], base + files[i].getName());   
        }   
     } else {   
         base = base.length() == 0 ? srcFile.getName() : base;
         ZipEntry zipEntry = new ZipEntry(base);     
         //zipEntry.setComment(comment); 
         out.putNextEntry(zipEntry);   
         FileInputStream fis = new FileInputStream(srcFile);   
         int length = 0;   
         byte[] b = new byte[BUFFER];   
         while ((length = fis.read(b, 0, BUFFER)) != -1) {   
             out.write(b, 0, length);   
         }   
         fis.close();   
     }   
 } 

    public static void main(String[] args) throws Exception {     
        long begin = System.currentTimeMillis();     
        ZipUtil zu = new ZipUtil();     
        List<String> filter = new ArrayList<String>();     
        filter.add("3RDPARTY");     
        filter.add("BANNER.GIF");     
        zu.setComment("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");     
        zu.zip("D:\\wellbeing_work\\runtime-WiosftUpdatePack.product\\我的模块(maea)1.21.1", "D:\\wellbeing_work\\runtime-WiosftUpdatePack.product\\我的模块(maea)1.21.1.wi");     
        System.out.println(ZipUtil.getZipComment("D:\\wellbeing_work\\runtime-WiosftUpdatePack.product\\我的模块(maea)1.21.1.wi"));     
        long end = System.currentTimeMillis();     
        System.out.println(end-begin);     
    }     

 }  

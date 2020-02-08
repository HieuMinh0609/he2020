package com.hieuminh.utils;

import com.hieuminh.constant.CoreConstant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UploadFileUtils {
    private final int maxMemorySize=1024*1024*3;
    private final int maxRequestSize = 1024*1024*50;
	private static Logger LOGGER = LoggerFactory.getLogger(UploadFileUtils.class);

	@Value("${dir.default}")
	private String dirDefault;

	public void writeOrUpdate(String path, byte[] bytes) {
		path = dirDefault + path;
		File file = new File(StringUtils.substringBeforeLast(path, "/"));
		if (!file.exists()) {
			file.mkdirs();
		}
        if (!Files.exists(Paths.get(path))) {
            file.mkdirs();
        }

		try(FileOutputStream outputStream = new FileOutputStream(path)) {
			LOGGER.info(path);
			outputStream.write(bytes);
			LOGGER.info("upload file success");
			outputStream.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

    public Object[] writeOrUploadFile(HttpServletRequest request,String path) {

        String adress = "/"+ CoreConstant.FOLDER_UPLOAD;
        checkAndCreateFolder(adress,path);
        boolean check=true;
        String fileLocation=null;
        String name=null;


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            System.out.println("not have a enctye multipart/form-data");
            check=false;
        }
        //create a fatory of disk-base file itesms
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        //Create new file Upload handler
        ServletFileUpload upload =new ServletFileUpload(factory);
        upload.setSizeMax(maxRequestSize);

        //parse the request
        try{
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item:items) {
                if (!item.isFormField()) {
                    name = item.getName();
                    if (org.apache.commons.lang.StringUtils.isNotBlank(name)){
                        File uploadFile = new File(adress + File.separator + path + File.separator + name);
                        fileLocation = adress + File.separator + path + File.separator + name;


                        boolean isExit = uploadFile.exists();

                        try{
                            if (isExit) {
                                uploadFile.delete();
                                item.write(uploadFile);

                            } else {
                                item.write(uploadFile);

                            }
                        }catch (Exception e){
                            check=false;
                            LOGGER.error(e.getMessage(),e);
                        }

                    }

                }
            }

        }catch (FileUploadException e){
            check=false;
            LOGGER.error(e.getMessage(),e);
        }

        return new Object[]{check,fileLocation,path + File.separator + name,name};

    }

    private void checkAndCreateFolder(String adress, String path) {
        File folderRoot = new File(adress);
        if (!folderRoot.exists()){
            folderRoot.mkdirs();
        }
        File folderChild = new File(adress + File.separator + path);
        if (!folderChild.exists()){
            folderChild.mkdirs();
        }
    }

}

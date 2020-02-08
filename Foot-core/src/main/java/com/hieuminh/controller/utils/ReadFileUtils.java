package com.hieuminh.controller.utils;

import com.hieuminh.constant.CoreConstant;
import com.hieuminh.constant.SystemConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ReadFileUtils extends HttpServlet {
    private static Logger LOGGER = LoggerFactory.getLogger(ReadFileUtils.class);

    private static final long serialVersionUID = 3633904564880655554L;

    private final String imagesBase = SystemConstant.BASE_DIR;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getRequestURI();
        LOGGER.info(imageUrl);
        int repIndex = imageUrl.indexOf("/repository/");
        String relativeImagePath = null;
        if(repIndex != -1) {
            repIndex += "/repository/".length();
            relativeImagePath = imageUrl.substring(repIndex);
        }
        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        LOGGER.info(imagesBase + File.separator + relativeImagePath);
        FileInputStream fin = new FileInputStream(imagesBase + File.separator + relativeImagePath);
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch =0; ;
        while((ch=bin.read())!=-1)
            bout.write(ch);
        LOGGER.info("Read file success");
        bin.close();
        fin.close();
        bout.close();
        outStream.close();
    }

}

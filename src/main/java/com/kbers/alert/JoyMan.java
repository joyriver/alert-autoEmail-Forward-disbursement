package com.kbers.alert;

import com.amzass.service.common.ApplicationContext;
import com.dropbox.core.*;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.amzass.utils.common.Constants.SLASH;
import static com.amzass.utils.common.Tools.sleep;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/4/18 15:40
 */
public class JoyMan {

    public String accessKey = "TZmKyU0JLtAAAAAAAAAAJmIgYaTTnxNPWJ1HBVqV5ncv8lXBSoAYGHfSdPyQfNBP";
    public final int REPEAT_TIMES = 3;
    public final String appName = "JoyMan";
    public final DbxClient client = new DbxClient(new DbxRequestConfig(appName, Locale.US.toString()), accessKey);

    public static void readFiles() throws DbxException {
        JoyMan joyMan = ApplicationContext.getBean(JoyMan.class);
        DbxEntry.WithChildren listing = joyMan.client.getMetadataWithChildren("/");
        for (DbxEntry child : listing.children) {
            System.out.println("    " + child.name + ": " + child.toString());
        }
    }

    public static void uploadFiles() throws IOException, DbxException {
        JoyMan joyMan = ApplicationContext.getBean(JoyMan.class);
//        File inputFile = new File("working-draft.txt");
//        FileInputStream inputStream = new FileInputStream(inputFile);
//        try {
//            DbxEntry.File uploadedFile = joyMan.client.uploadFile("/magnum-opus.txt",
//                    DbxWriteMode.add(), inputFile.length(), inputStream);
//            System.out.println("Uploaded: " + uploadedFile.toString());
//        } finally {
//            inputStream.close();
//        }

        FileInputStream inputStream = null;

    }

    public DbxEntry.File upload(File file, String dbxFolderPath) throws DbxException, IOException {
        Exception ex = null;
        for (int i = 0; i < REPEAT_TIMES; i++) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                String path = dbxFolderPath.endsWith(SLASH) ? dbxFolderPath : (dbxFolderPath + SLASH);
                return client.uploadFile(path + file.getName(), DbxWriteMode.add(), file.length(), inputStream);
            } catch (DbxException | IOException e) {
                ex = e;
                if (i < REPEAT_TIMES - 1) {
                    sleep(REPEAT_TIMES, TimeUnit.SECONDS);
                }
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }

        if (ex instanceof DbxException) {
            throw (DbxException) ex;
        } else {
            throw (IOException) ex;
        }
    }



    public static void main(String[] args) throws DbxException, IOException {
//        upload(file, rootPath);
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class f {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(f.class);

    public static void a(HttpServletResponse response, String storePath, String fileName) {
        response.setCharacterEncoding("UTF-8");
        File file = new File(storePath);
        if (!file.exists()) {
            throw new NullPointerException("Specified file not found");
        } else if (fileName != null && !fileName.isEmpty()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");

            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            } catch (UnsupportedEncodingException e) {
                a.error(e.getMessage(), e);
            }

            byte[] buffer = new byte[1024];

            try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
            ) {
                OutputStream os = response.getOutputStream();

                for(int i = bis.read(buffer); i != -1; i = bis.read(buffer)) {
                    os.write(buffer, 0, i);
                }
            } catch (Exception e) {
                a.error(e.getMessage(), e);
            }

        } else {
            throw new NullPointerException("The file name can not null");
        }
    }

    public static void a(HttpServletResponse response, List<String> filesPath, String zipFileName) throws IOException {
        String downloadName = zipFileName + ".zip";
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8"));
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        try (
            ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            DataOutputStream os = new DataOutputStream(zipOut);
        ) {
            zipOut.setMethod(8);

            for(String filePath : filesPath) {
                File file = new File(filePath);
                if (file.exists()) {
                    String fileName = file.getName();
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    InputStream is = Files.newInputStream(file.toPath());
                    byte[] b = new byte[1024];

                    int length;
                    while((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }

                    is.close();
                    zipOut.closeEntry();
                }
            }
        } catch (IOException e) {
            a.error(e.getMessage(), e);
        }

    }

    public static String a(String fileUrl, String storePath) {
        try {
            URL url = new URL(fileUrl);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            File file = a(storePath);

            try {
                String var9;
                try (
                    InputStream inStream = conn.getInputStream();
                    FileOutputStream fs = new FileOutputStream(file);
                ) {
                    byte[] buffer = new byte[1204];

                    int byteread;
                    while((byteread = inStream.read(buffer)) != -1) {
                        fs.write(buffer, 0, byteread);
                    }

                    var9 = storePath;
                }

                return var9;
            } catch (IOException e) {
                a.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            a.error(e.getMessage(), e);
        }

        return "";
    }

    public static File a(final File file) {
        if (!file.exists()) {
            return file;
        } else {
            File tmpFile = new File(file.getAbsolutePath());
            File parentDir = tmpFile.getParentFile();
            int count = 1;
            String extension = FilenameUtils.getExtension(tmpFile.getName());
            String baseName = FilenameUtils.getBaseName(tmpFile.getName());

            do {
                tmpFile = new File(parentDir, baseName + "(" + count++ + ")." + extension);
            } while(tmpFile.exists());

            return tmpFile;
        }
    }

    private static File a(String destFilePath) {
        File destFile = new File(destFilePath);
        b(destFile.getParentFile());
        return destFile;
    }

    public static void b(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }

    }
}

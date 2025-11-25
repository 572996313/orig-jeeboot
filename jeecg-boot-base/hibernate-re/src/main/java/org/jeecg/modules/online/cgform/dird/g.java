//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import cn.hutool.core.io.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class g {
    public static File a(List<String> srcFilePaths, String zipFilePath) throws RuntimeException {
        File zipFile = FileUtil.touch(zipFilePath);
        if (zipFile == null) {
            return null;
        } else if (!zipFile.getName().endsWith(".zip")) {
            return null;
        } else {
            ZipOutputStream zos = null;

            try {
                FileOutputStream out = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(out);

                for(String srcFilePath : srcFilePaths) {
                    srcFilePath = URLDecoder.decode(srcFilePath, "UTF-8").replace("生成成功：", "");
                    File srcFile = new File(srcFilePath);
                    if (srcFile != null && srcFile.exists()) {
                        byte[] buf = new byte[8192];
                        String filePathName = null;
                        if (srcFile.getAbsolutePath().indexOf("src\\") != -1) {
                            filePathName = srcFile.getAbsolutePath().substring(srcFile.getAbsolutePath().indexOf("src\\") - 1);
                        } else {
                            filePathName = srcFile.getAbsolutePath().substring(srcFile.getAbsolutePath().indexOf("src/") - 1);
                        }

                        zos.putNextEntry(new ZipEntry(filePathName));
                        FileInputStream in = new FileInputStream(srcFile);

                        int len;
                        while((len = in.read(buf)) != -1) {
                            zos.write(buf, 0, len);
                        }

                        in.close();
                        zos.closeEntry();
                    }
                }

                if (zos != null) {
                    try {
                        zos.close();
                    } catch (IOException e) {
                        System.out.println("ZipUtil toZip close exception" + e);
                    }
                }

                out.close();
                return zipFile;
            } catch (Exception e) {
                throw new RuntimeException("zipFile error from ZipUtils", e);
            }
        }
    }
}

package com.currency.base.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtil操作工具类
 *
 * @author gp
 */
public class ZipUtil {

    /**
     * 压缩文件列表到某ZIP文件
     *
     * @param zipFilename 要压缩到的ZIP文件
     * @param paths       文件列表，多参数
     * @throws Exception
     */
    public static void zip(String zipFilename, String... paths) throws IOException {
        zip(new FileOutputStream(zipFilename), paths);
    }

    /**
     * 压缩文件列表到输出流
     *
     * @param os    要压缩到的流
     * @param paths 文件列表，多参数
     * @throws Exception
     */
    public static void zip(OutputStream os, String... paths) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(os);
        for (String path : paths) {
            if (path.equals(""))
                continue;
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    zipDirectory(zos, file.getPath(), file.getName()
                            + File.separator);
                } else {
                    zipFile(zos, file.getPath(), "");
                }
            }
        }
        zos.close();
    }

    /**
     * 解压缩
     * @param zipPathFile 要解压的文件l路径
     * @param destPath 解压到某文件夹
     * @return
     */
    public static void unzip(String zipPathFile, String destPath) {
        unzipFile(new File(zipPathFile),  destPath);
    }

    /**
     * 解压缩
     * @param zipFile 要解压的文件
     * @param destPath 解压到某文件夹
     * @return
     */
    public static void unzip(File zipFile, String destPath) {
        unzipFile(zipFile,  destPath);
    }

    private static void unzipFile(File file, String destPath){
        ArrayList<String> allFileName = new ArrayList<String>();
        try {
            // 先指定压缩档的位置和档名，建立FileInputStream对象
            FileInputStream fins = new FileInputStream(file);
            // 将fins传入ZipInputStream中
            ZipInputStream zins = new ZipInputStream(fins);
            java.util.zip.ZipEntry ze = null;
            byte[] ch = new byte[256];
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(destPath +File.separator+ ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if (ze.isDirectory()) {
                    if (!zfile.exists())
                        zfile.mkdirs();
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    FileOutputStream fouts = new FileOutputStream(zfile);
                    int i;
                    allFileName.add(zfile.getAbsolutePath());
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
            }
            fins.close();
            zins.close();
        } catch (Exception e) {
            System.err.println("unzip error:" + e.getMessage());
        }
    }

    /**
     * 压缩目录
     * @param zos
     * @param dirName
     * @param basePath
     * @throws Exception
     */
    private static void zipDirectory(ZipOutputStream zos, String dirName,
                                     String basePath) throws IOException{
        File dir = new File(dirName);
        if (dir.exists()) {
            File files[] = dir.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        zipDirectory(zos, file.getPath(), basePath
                                + file.getName().substring(
                                file.getName().lastIndexOf(
                                        File.separator) + 1)
                                + File.separator);
                    } else
                        zipFile(zos, file.getPath(), basePath);
                }
            } else {
                ZipEntry ze = new ZipEntry(basePath);
                zos.putNextEntry(ze);
            }
        }
    }

    /**
     * 压缩文件
     * @param zos
     * @param filename
     * @param basePath
     * @throws Exception
     */
    private static void zipFile(ZipOutputStream zos, String filename,
                                String basePath) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(filename);

            ZipEntry ze = new ZipEntry(basePath + file.getName());
            zos.putNextEntry(ze);
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, count);
            }
            fis.close();
        }
    }
}

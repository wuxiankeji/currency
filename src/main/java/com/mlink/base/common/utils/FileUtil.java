package com.mlink.base.common.utils;

import com.google.common.io.Files;

import java.io.*;

/**
 * @author wyh
 * @version 2017/12/28.
 */
public class FileUtil {

    /**
     * 文件转换为byte[]
     * @param file 文件
     * @return byte[]
     * @throws IOException I/O error
     */
    public static byte[] toByteArray(File file) throws IOException {
        return Files.toByteArray(file);
    }

    /**
     *
     * 获得输入流
     *
     * @param file 文件
     * @return 输入流
     * @throws IOException 文件未找到
     */
    public static BufferedInputStream getInputStream(File file) throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    /**
     * 获得输入流
     *
     * @param absolutePath 文件绝对路径
     * @return 输入流
     * @throws IOException 文件未找到
     */
    public static BufferedInputStream getInputStream(String absolutePath) throws IOException {
        return getInputStream(new File(absolutePath));
    }

    /**
     *
     * 获得输出流
     *
     * @param file 文件
     * @return 输出流
     * @throws IOException 文件未找到
     */
    public static BufferedOutputStream getOutputStream(File file) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(file));
    }

    /**
     * 获得输出流
     * @param absolutePath 文件绝对路径
     * @return 输出流
     * @throws IOException 文件未找到
     */
    public static BufferedOutputStream getOutputStream(String absolutePath) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(absolutePath));
    }

    /**
     * 创建父级目录
     * @param file
     * @return
     * @throws IOException
     */
    public static void createParentDirs(File file) throws IOException {
        Files.createParentDirs(file);
    }

    /**
     * 获取文件后缀(不包含".")
     * @param fileFullName
     * @return
     */
    public static String getFileExtension(String fileFullName){
        return Files.getFileExtension(fileFullName);
    }

    /**
     * 删除目录
     * @param directory
     * @throws IOException
     */
    public static void deleteDirectory(File directory) throws IOException {
        if(directory == null){
            return;
        }

        if (!directory.exists()) {
            return;
        }

        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }

        if (!directory.delete()) {
            String message =
                    "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * 清空目录
     * @param directory
     * @throws IOException
     */
    public static void cleanDirectory(File directory) throws IOException {
        if(directory == null){
            return;
        }

        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    /**
     * 删除文件
     * @param file
     * @throws IOException
     */
    public static void forceDelete(File file) throws IOException {
        if(file == null){
            return;
        }

        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent){
                    throw new FileNotFoundException("FileMeta does not exist: " + file);
                }
                String message =
                        "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }

    /**
     * 校验链接符号
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("FileMeta must not be null");
        }
        //FilenameUtils.isSystemWindows()
        if (File.separatorChar == '\\') {
            return false;
        }
        File fileInCanonicalDir = null;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
            return false;
        } else {
            return true;
        }
    }
}

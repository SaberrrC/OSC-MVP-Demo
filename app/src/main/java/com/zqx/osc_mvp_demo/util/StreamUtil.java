package com.zqx.osc_mvp_demo.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

    /**
     * 从输入流读取字符串至内存
     */
    public static String in2str(InputStream in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8194];
        int len;
        try {
            while ((len = in.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeIO(in);
        }

    }

    /**
     * 将内存中字符串保存至文件
     */
    public static void saveString(String string, File file) {
        ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
        save2file(in, file);
    }

    /**
     * 清空文件中的所有数据
     */
    public static void clear(File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeIO(fos);
        }
    }


    /**
     * 从文件读取字符串,文件不存在则返回null
     */
    public static String readFrom(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return in2str(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeIO(fis);
        }
    }

    public static void save2file(InputStream in, String path) {
        save2file(in, new File(path), null);
    }

    public static void save2file(InputStream in, File dest) {
        save2file(in, dest, null);
    }

    public static void save2file(InputStream in, String path, ProgressListener listener) {
        save2file(in, new File(path), listener);
    }

    /**
     * 从输入流保存数据至文件,可添加进度监听,如在下载文件时使用
     */
    public static void save2file(InputStream in, File dest, ProgressListener listener) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(dest);
            byte[] buffer = new byte[1024 * 10];
            int len;
            int progress = 0;
            while ((len = in.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                if (listener != null) {
                    progress += len;
                    listener.onProgressChanged(progress);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(in, fos);
        }

    }


    private static void closeIO(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String readLineAt(int lineNum, String file) {
        BufferedReader br = null;
        String result = "";
        try {
            br = new BufferedReader(new FileReader(file));
            if (lineNum == 1) {
                result = br.readLine();
            } else {
                for (int i = 1; i < lineNum; i++) {
                    br.readLine();
                }
                result = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            closeIO(br);
        }
        return result;
    }

    public interface ProgressListener {
        /**
         * @param progress 这里回传的progress是当前输出流总共已写的长度,不是百分比
         */
        void onProgressChanged(int progress);
    }
}

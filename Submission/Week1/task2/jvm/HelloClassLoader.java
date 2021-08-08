package jvm;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {

        try {
            Object obj = new HelloClassLoader().findClass("Hello").newInstance();
            Method method = obj.getClass().getMethod("hello");
            method.invoke(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] encodeBytes = readBytesFromFile("Hello.xlass");
        byte[] decodeBytes = new byte[encodeBytes.length];
        for (int i = 0 ; i < decodeBytes.length ; i++) {
            decodeBytes[i] = (byte) (255 - encodeBytes[i]);
        }

        return defineClass(name, decodeBytes, 0, decodeBytes.length);
    }

    public static byte[] readBytesFromFile(String inputFile) {
        byte[] result = new byte[0];

        File file = new File(inputFile);
        if (!file.exists()) {
            return result;
        }

        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(inputFile));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

            byte[] temp = new byte[1024];
            int size;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();

            result = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}


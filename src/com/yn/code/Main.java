package com.yn.code;


import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception{
        String fileDir = "E:/abc";
        String fileName = "abcd.java";

        if(!fileDir.endsWith("\\")){
            fileDir = fileDir+ '\\';
        }
        File file = new File(fileDir + fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
    }
}

package com.mydemo.test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 202509180159
 * 更正小说章节名称错误（发现14章名称错误：原因无后空格...）
 *
 *
 * https://blog.51cto.com/u_16175453/7243673
 * 常看常新
 */
public class MatchAndReplace {

//    public static final String str = "./src/main/resources/public/test/章节名.txt";
//    public static final String strNew = "./src/main/resources/public/test/new.txt";

    public static final String str = "./src/main/resources/public/test/永夜君王.txt";
    public static final String strNew = "./src/main/resources/public/test/永夜君王new.txt";

    public static final String str2 = "./章节名.txt";

    public static final String regex = "章(\\s)?([零一二三四五六七八九十百千]+)(\\s)";

    public static void main(String[] args) throws IOException {
        File file = new File(str);

        File newFile = new File(strNew);
        FileWriter newFileWriter = new FileWriter(newFile);


        FileReader fileReader = new FileReader(file);
        char[] buf = new char[8192]; //1024 更为8192

        Pattern pattern = Pattern.compile(regex);

        while (fileReader.read(buf) != -1) {
            String s = new String(buf);
            String newStr = s;
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                int from = matcher.start();
                int to = matcher.end();
                String ss = s.substring(from, to-1);
//                System.out.println("旧" + ss);
                String name = "第" + ss.substring(1).trim() + "章";
//                System.out.println("新" + name);

                newStr = newStr.replaceFirst(ss, name);
            }
//            System.out.println("新全" + newStr);
            newFileWriter.write(newStr);
        }

        newFileWriter.close();

    }

    public static void createFile() throws IOException {
        File file = new File(str2);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.println(file.getName());
        pw.close();
    }

}

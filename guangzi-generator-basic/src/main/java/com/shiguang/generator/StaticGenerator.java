package com.shiguang.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成器
 * Created By Shiguang On 2024/6/17 15:39
 */
public class StaticGenerator {
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");


        // 输入路径,获取相对路径,不同操作系统可能有所不同
        // D:\Workspace\shiguang-coding\guangzi-generator\guangzi-generator-demo-projects\acm-template
        // File parentFile = new File(projectPath).getParentFile();
        // String inputPath = new File(parentFile, "guangzi-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();

        String inputPath = projectPath + File.separator + "guangzi-generator-demo-projects" + File.separator + "acm-template";

        // 输出路径
        String outputPath = projectPath + File.separator + "guangzi-generator-basic" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "outputdir";

        System.out.println("输入路径: " + inputPath);
        System.out.println("输出路径: " + outputPath);

//        copyFilesByHutool(inputPath, outputPath);
        copyFilesByRecursive(inputPath, outputPath);

    }

    /**
     * 拷贝文件（Hutool 实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }


    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
            System.err.println("文件复制成功!!");
        } catch (Exception e) {
            System.err.println("文件复制失败!!");
            e.printStackTrace();
        }
    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     * <p>
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     *
     * @param inputFile  输入路径
     * @param outputFile 输出路径
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
//            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}

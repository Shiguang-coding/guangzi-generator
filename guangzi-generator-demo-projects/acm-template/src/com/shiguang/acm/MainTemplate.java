package com.shiguang.acm;

import java.util.Scanner;

/**
 * ACM 输入模板（多数之和）
 */
public class MainTemplate {
    public static void main(String[] args) {
        System.out.println("请输入元素个数：");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 读取输入元素个数
            int n = scanner.nextInt();

            // 读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.println("请输入第"+(i+1)+"个元素：");
                arr[i] = scanner.nextInt();
            }

            // 处理问题逻辑，根据需要进行输出
            // 示例：计算数组元素的和
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }

            System.out.println(n+"个元素总和为: " + sum);
        }

        scanner.close();
    }
}

package com.nowcoder.community;

import java.io.IOException;

/**
 * @author VvV
 * @date 2022/10/19
 */
public class WkTests {

    public static void main(String[] args) {
        String cmd = "D:/software/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com e:/work/data/wk-images/3.png";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

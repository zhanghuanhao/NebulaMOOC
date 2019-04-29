package com.nebula.mooc.webserver.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by 15722 on 2019/4/14.
 */
public class CodeUtil {
    // 图片的宽度。
    private static final int width = 100;
    // 图片的高度。
    private static final int height = 36;
    // 验证码字符个数
    private static final int codeCount = 5;
    // 验证码干扰线数
    private static final int lineCount = 50;

    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private static final char[] codeSequence = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'L', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public static String createCode() {
        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 生成随机数
        Random random = new Random();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 将产生的5个随机数组合在一起。
            randomCode.append(strRand);
        }
        return randomCode.toString();
    }

    public static BufferedImage createImgCode(String code) {
        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;

        x = width / (codeCount + 2);//每个字符的宽度(左右各空出一个字符)
        fontHeight = height - 2;//字体的高度
        codeY = height - 4;

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体,可以修改为其它的
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);

        g.setFont(font);

        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            int xs = random.nextInt(width);//x坐标开始
            int ys = random.nextInt(height);//y坐标开始
            int xe = xs + random.nextInt(width / 8);//x坐标结束
            int ye = ys + random.nextInt(height / 8);//y坐标结束

            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(code.substring(i, i + 1), (i + 1) * x, codeY);
        }
        return buffImg;
    }

}

package com.ruoyi.disease.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    private static final DecimalFormat df = new DecimalFormat("0000000000");

    //生成四位的随机数字
    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    //生成六位的随机数字
    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

    //生成十位的随机数字
    public static String getBitRandom() {
        return df.format(random.nextInt(1000000000));
    }
}

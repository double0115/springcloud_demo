package com.zxhl.utils;

import java.util.ArrayList;
import java.util.List;


public class StringUtil {
    public static boolean isNullOrZero(Integer param) {
        return param == null ||param.intValue()==0;
    }
    public static boolean isNullOrZero(Long param) {
        return param == null ||param.intValue()==0;
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() <= 0;
    }
    /**
     * 获取以某分隔符分开后的项的个数
     *
     * @param value     指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<Long> getSplitLongList(String value, String splitChar) {
        List<Long> valueList = new ArrayList<Long>();
        if (!StringUtil.isNullOrEmpty(value)) {
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(getLong(values[i]));
                }
            }
        }
        return valueList;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int stringLength(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return 0;
        }
        int valueLength = 0;
        //textarea 不同浏览器换行不同， 统一转为\n
        value = value.replace("\r\n", "\n");
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
    /**
     * 获取以某分隔符分开后的项的个数
     *
     * @param value     指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<String> getSplitList(String value, String splitChar) {
        List<String> valueList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(value)) {
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(values[i]);
                }
            }
        }
        return valueList;
    }

    public static String padLeft(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    public static String padRight(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    /**
     * 去除字符窜尾字符
     *
     * @param str     字符串
     * @param charStr 尾字符
     * @return string
     */
    public static String trimEnd(String str, String charStr) {
        if (!StringUtil.isNullOrEmpty((str)) && !StringUtil.isNullOrEmpty(charStr)) {
            if (str.substring(str.length() - 1).equals(charStr)) {
                return str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 去除字符窜开始的字符字符串
     *
     * @param str     字符串
     * @param charStr 开始的字符串
     * @return string
     */
    public static String trimStart(String str, String charStr) {
        if (!StringUtil.isNullOrEmpty((str)) && !StringUtil.isNullOrEmpty(charStr)) {
            if(str.startsWith(charStr)){
                return str.substring(str.indexOf(charStr)+1);
            }
        }
        return str;
    }

    public static String Arr2Str(String... strArr) {
        String hostString = "";
        for(int i = 0; i < strArr.length; ++i) {
            hostString = hostString + "," + strArr[i];
        }
        if(hostString.startsWith(",")) {
            ;
        }
        hostString = hostString.substring(1, hostString.length());
        return hostString;
    }
    //region  类型转换


    public static int getInt(Object obj) {
        int def = 0;
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static int getInt(Object obj, int def) {
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static long getLong(Object obj, long def) {
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static long getLong(Object obj) {
        long def = 0;
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static double getDouble(Object obj, double def) {
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static double getDouble(Object obj) {
        double def = 0.0;
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static String getString(Object obj) {
        String def = "";
        if (obj != null) {
            def = obj.toString();

        }
        return def;

    }

    public static String getString(Object obj, String def) {
        if (obj != null) {
            def = obj.toString();

        }
        return def;

    }
    //endregion
    /**
     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     */
    public static String stringTrimSpace(String val) {
        String result = "";
        if (null != val && !"".equals(val)) {
            result = val.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
        }
        return result;
    }

}

package com.zxhl.utils;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.GZIPOutputStream;


public class CommonUtil {

    /**
     * 获取更加详细的异常信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionAllinformation(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    /**
     * 获取当前时间，并格式化输出
     *
     * @param format 格式化例如 "yyyy-MM-dd HH:mm:ss"
     * @return 返回格式化时间的字符串
     */
    public static String getDateNowByFormat(String format) {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 将时间转化为字符串
     *
     * @param date
     * @return
     */
    public static String dataParseString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        //Proxy-Client-IP 字段和 WL-Proxy-Client-IP 字段只在 Apache（Weblogic Plug-In Enable）+WebLogic 搭配下出现
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip!=null) {
            ip = ip.split(",")[0];
        }else{
            ip="";
        }
        return ip;
    }

    /**
     * 返回一个随机的英文字母字符串
     *
     * @param len
     * @return
     */
    public static String makeCodeString(int len) {
        if (len < 1) {
            return "";
        }
        Integer number;
        String checkcode = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            number = random.nextInt(100000);
            if (number % 2 == 0) {
                checkcode += (char) ('a' + (char) (int) (number % 26));
            } else {
                checkcode += (char) ('A' + (char) (int) (number % 26));
            }
        }
        return checkcode;
    }

    /**
     * 判断请求是否来自于移动端，手机和pad。
     *
     * @param userAgent
     * @return
     */
    public static boolean isFromMoblie(String userAgent) {
        boolean bRes = false;
        if (!StringUtil.isNullOrEmpty(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("mobile") != -1 ||
                    userAgent.indexOf("mobi") != -1 ||
                    userAgent.indexOf("android") != -1 ||
                    userAgent.indexOf("iphone") != -1 ||
                    userAgent.indexOf("nokia") != -1 ||
                    userAgent.indexOf("samsung") != -1 ||
                    userAgent.indexOf("sonyericsson") != -1 ||
                    userAgent.indexOf("mot") != -1 ||
                    userAgent.indexOf("blackberry") != -1 ||
                    userAgent.indexOf("lg") != -1 ||
                    userAgent.indexOf("htc") != -1 ||
                    userAgent.indexOf("j2me") != -1 ||
                    userAgent.indexOf("ucweb") != -1 ||
                    userAgent.indexOf("opera mini") != -1) {
                bRes = true;
            }
        }
        return bRes;
    }

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * 删除字符串中的html标记
     *
     * @param htmlStr
     * @return
     */
    public static String clearHtml(String htmlStr) {
        if (!StringUtil.isNullOrEmpty(htmlStr)) {
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            return htmlStr; // 返回文本字符串
        }
        return htmlStr;
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF_8(String str) {
        try {
            return CommonUtil.changeCharset(str, "UTF_8");
        } catch (UnsupportedEncodingException ex) {
        }
        return str;
    }

    /**
     * 将字符编码转换成gb2312码
     */
    public static String toGb2312(String str) {
        try {
            return CommonUtil.changeCharset(str, "gb2312");
        } catch (UnsupportedEncodingException ex) {
        }
        return str;
    }

    /**
     * 去除特殊字符
     *
     * @param htmlStr
     * @return
     */
    public static String clearSpecialChar(String htmlStr, String regEx) {
        if (!StringUtil.isNullOrEmpty(htmlStr) && !StringUtil.isNullOrEmpty(regEx)) {
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(htmlStr);
            return m.replaceAll("");
        }
        return htmlStr;
    }

    /**
     * 过滤sql等非法关键字
     *
     * @param str
     * @return
     */
    public static String getSafeQuery(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            str = str.replace(">", "").replace("<", "");
            str = str.replaceAll("(?i)insert", "");
            str = str.replaceAll("(?i)update", "");
            str = str.replaceAll("(?i)delete", "");
            str = str.replaceAll("(?i)select", "");
            //str=str.replaceAll("(?i)and", "");
            str = str.replaceAll("(?i)where", "");
            //str=str.replaceAll("(?i)or", "");
            //str=str.replaceAll("(?i)limit", "");
        }
        return str;
    }

    //过滤特殊字符[]
    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * hashmap转url拼接地址
     *
     * @param url  接口地址
     * @param data 字符串
     * @return string
     */
    public static String QueryString(String url, Map<String, Object> data) {
        if (data != null && data.size() > 0) {
            for (Map.Entry<String, Object> item : data.entrySet()) {
                url += item.getKey() + "=" + item.getValue() + "&";
            }
            url = StringUtil.trimEnd(url, "&");
        }
        return url;
    }

    /**
     * 将特殊字符转义
     *
     * @return
     */
    public static String htmlEiscode(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            str = str.replace(">", "&gt;");
            str = str.replace("<", "&lt;");
            str = str.replace(" ", "&nbsp;");
            str = str.replace("\\", "&quot;");
            str = str.replace("|", "&brvbar;");
            str = str.replace("'", "\\'");
            str = str.replace("\"", "\\");
        }
        return str;
    }

    /**
     * 将特殊字符转义
     *
     * @return
     */
    public static String htmlencode(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            str = str.replace(">", "&gt;");
            str = str.replace("<", "&lt;");
            str = str.replace(" ", "&nbsp;");
        }
        return str;
    }

    /**
     * 将特殊字符转义 反
     *
     * @returns
     */
    public static String htmldecode(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            str = str.replace("&gt;", ">");
            str = str.replace("&lt;", "<");
            str = str.replace("&nbsp;", " ");
        }
        return str;
    }

    /**
     * 去除字符
     *
     * @param str     字符串
     * @param charStr 尾字符
     * @return string
     */
    public static String replaceChar(String str, String charStr) {
        String result = "";
        if (!StringUtil.isNullOrEmpty((str)) && !StringUtil.isNullOrEmpty(charStr)) {
            for (String item : str.split(charStr)) {
                if (!item.equals("")) {
                    result += item + ",";
                }
            }
            result = StringUtil.trimEnd(result, charStr);
        }
        return result;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getTaskId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String urlEncode(String value)  {
        try {
            return URLEncoder.encode(value, "UTF-8");
        }catch (Exception ex){}
        return value;
    }

    public static String urlDncode(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        }catch (Exception ex){}
        return value;
    }


    public static void main(String[] args) {
//        boolean c= a.startsWith("f");
//        long startTime=System.currentTimeMillis();   //获取开始时间
////        for(int j = 0; j< 100; j++){
////            System.out.println((int)((Math.random()*9+1)*100000));
////        }
//        long endTime=System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }

    /**
     * 将ISO88591编码的字符串转成utf8。
     *
     * @param str
     * @return
     */
    public static String encodeISO88591ToUtf8(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            return "";
        }

        try {
            return new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 字符串去重
     *
     * @param str       原字符串
     * @param exceptStr 排除
     * @return
     */
    public static String noRepeat(String str, String... exceptStr) {
        String newIds = "";
        String[] values = str.split(",");
        for (int i = 0; i < values.length; i++) {
            boolean isExcept = false;
            if (exceptStr != null && exceptStr.length > 0) {
                for (int j = 0; j < exceptStr.length; j++) {
                    if (values[i].equals(exceptStr[j])) {
                        isExcept = true;
                        break;
                    }
                }
            }

            if (isExcept) {
                continue;
            }

            if (!("," + newIds + ",").contains("," + values[i] + ",")) {
                newIds += values[i] + ",";
            }
        }
        return StringUtil.trimEnd(newIds, ",");
    }
    /**
     * 判断字符串中是否包含富文本标签
     *
     * @param htmlStr 原字符串
     * @return
     */
    public static boolean delHTMLTag(String htmlStr) {
        Boolean bl = false;
        if (htmlStr.contains("<")) {
            bl = true;
        }
        if (htmlStr.contains("<")) {
            bl = true;
        }
        return bl;
    }
    /**
     * 比较两个日期时间大小
     * @param s1
     * @param s2
     * @return
     */
    public static boolean compTime(String s1,String s2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(s1);
            Date dt2 = df.parse(s2);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
    /**
     * 去除单引号和双引号。
     *
     * @param text
     * @return
     */
    public static String removeQuotes(String text) {
        if (StringUtil.isNullOrEmpty(text)) {
            return "";
        }
        return text.replace("'", "").replace("\"", "");
    }
    /**
     * 去重一个List,保证原来的顺序。
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> List<T> distinct(List<T> input) {
        boolean isNew;
        List<T> distincted = new ArrayList<>();

        for (T item : input) {

            isNew = true;
            for (T newItem : distincted) {

                if (item.equals(newItem)) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                distincted.add(item);
            }
        }

        return distincted;
    }
    public static String filterKeyword(String str) {
        if (!StringUtil.isNullOrEmpty(str)) {
            String keyword = "svg|onabort|onblur|onchange|onclick|ondblclick|onerror|onfocus|onkeydown|onkeypress|onkeyup|onload|onmousedown|onmousemove|onmouseout|onmouseover|onmouseup|onreset|onresize|onselect|onsubmit|onunload";
            str = CommonUtil.clearSpecialChar(str, keyword);
        }
        return str;
    }
    /**
     * @param str：正常的字符串
     * @return 压缩字符串 类型为：  ³)°K,NIc i£_`Çe#  c¦%ÂXHòjyIÅÖ`
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    public static boolean isSuccess(String respContent,String url){
        if(StringUtil.isNullOrEmpty(respContent)|| StringUtil.isNullOrEmpty(url)){
            return false;
        }
        String respContent2=respContent;
        respContent=respContent.toLowerCase();

        return url.indexOf("platformopen.zhaopin.com/basedata/asp/getdata.ashx")!=-1||
                url.indexOf("rdapi.zpidc.com/resume/getpartab4test")!=-1||
                url.indexOf("ihrapi.zpidc.com")!=-1||
                url.indexOf("MICROSERVICE-IHRAPI")>=0||
                "success".equals(respContent)||
                respContent2.indexOf("\"Result\":True")!=-1||
                respContent2.indexOf("\"Result\":\"True\"")!=-1||
                respContent2.indexOf("\"Code\":1")!=-1||
                respContent2.indexOf("\"Code\":\"1\"")!=-1||
                respContent.indexOf("\"code\":200")!=-1||
                respContent.indexOf("\"code\":\"200\"")!=-1||
                respContent.indexOf("\"status\":\"200\"")!=-1||
                respContent.indexOf("\"status\":200")!=-1||
                respContent.indexOf("\"result\":1")!=-1||
                respContent.indexOf("\"result\":\"1\"")!=-1||
                respContent.indexOf("\"statuscode\":200")!=-1||
                respContent.indexOf("\"statuscode\":\"200\"")!=-1||
                (respContent.indexOf("\"success\":true")!=-1 && respContent.indexOf("\"errorCode\":\"0\"")!=-1) ||
                (respContent.indexOf("\"count\"")!=-1 && respContent.indexOf("\"orders\"")!=-1 && respContent.indexOf("\"start\"")!=-1)||
                (respContent.indexOf("\"session_key\"")!=-1 && respContent.indexOf("\"openid\"")!=-1)||
                (respContent.contains("\"numfound\":")&&respContent.contains("\"results\":")&&respContent.contains("\"facets\":"));
    }
}

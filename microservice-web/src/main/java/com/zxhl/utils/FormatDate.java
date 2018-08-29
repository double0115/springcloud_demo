package com.zxhl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FormatDate {

    private static final Logger LOGGER = com.zxhl.utils.LoggerFactory.getLogger(FormatDate.class);

    /**
     * �õ���ǰϵͳ����,��ʽ:yyyy-mm-dd
     *
     * @return
     */
    public static String getCurrentDate() {
        return getFormatDate("yyyy-MM-dd");
    }

    /**
     * �������
     *
     * @return
     */
    public static String getCurrentYear() {

        return getFormatDate("yyyy");
    }

    /**
     * �����·�
     */
    public static String getCurrentMonth() {
        return getFormatDate("MM");
    }

    /**
     *获取当前小时
     */
    public static String getCurrentHour() {
        return getFormatDate("HH");
    }

    /**
     * �����ض���ʽ������
     * ��ʽ����:
     * yyyy-mm-dd
     *
     * @param formatString
     * @return
     */
    public static String getFormatDate(String formatString) {
        String currentDate = "";
        SimpleDateFormat format1 = new SimpleDateFormat(formatString);
        currentDate = format1.format(new Date());
        return currentDate;
    }

    /**
     * 获取某一date的format格式字符串。
     * @param formatString
     * @param date
     * @return
     */
    public static String getFormatDate(String formatString, Date date) {
        String dateStr = "";
        SimpleDateFormat format1 = new SimpleDateFormat(formatString);
        dateStr = format1.format(date);
        return dateStr;
    }

    /**
     * ����ʱ���ʽ��ʽ
     *
     * @return
     */
    public static String getFormateDateAll() {
        return getFormatDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * ���ؼ���Сʱ���ʱ��
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date getDateAfterHour(Date d, int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
        return now.getTime();
    }

    /**
     * ���ؼ����Ӻ��ĳ��ʱ��
     *
     * @param d
     * @param minutes
     * @return
     */
    public static Date getDateAfterMinute(Date d, int minutes) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
        return now.getTime();
    }

    /**
     * �Ƚ��������ڵĴ�С
     * true date1��date2ǰ
     * false date1��date2��
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateCompare(Date date1, Date date2) {
        boolean dateComPareFlag = true;
        if (date2.compareTo(date1) != 1) {
            dateComPareFlag = false;
        }
        return dateComPareFlag;
    }

    /**
     * ������ʱ��֮��
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long dateMinus(Date startTime, Date endTime) {
        return endTime.getTime() - startTime.getTime();
    }

    public static Date strToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {

            return -1;

        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(oDate);
        long time2 = cal.getTimeInMillis();
        long betweendays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweendays));
    }

    //获取2个日期间隔天数
    public static int getIntervalDaysAccurateToDay(String fDate, String oDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fD=null;
        Date oD=null;
        try {
            fD=sdf.parse(fDate);
            oD=sdf.parse(oDate);
        } catch (Exception e) {
            LOGGER.error("获取2个日期间隔天数" + e.getMessage());
            return  -1;
        }
        return getIntervalDaysAccurateToDay(fD,oD);
    }

    //获取2个日期间隔天数
    public static int getIntervalDaysAccurateToDay(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return -1;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fDate = sdf.parse(sdf.format(fDate));
            oDate = sdf.parse(sdf.format(oDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(fDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(oDate);
            long time2 = cal.getTimeInMillis();
            long betweendays = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(betweendays));
        } catch (Exception e) {
            LOGGER.error("获取2个日期间隔天数" + e.getMessage());
            return -1;
        }
    }
    public static long subMinuters(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ????????
            Date outdate = sdf.parse(endTime);
            Date indate = sdf.parse(startTime);
            long totalhours = (outdate.getTime() - indate.getTime()) / (1000 * 60 * 60); //?
            long totalminutes = (outdate.getTime() - indate.getTime()) / (1000 * 60) - totalhours * 60; //??
            return totalminutes;
        } catch (Exception ex) {
            return 0;
        }
    }
    public static String getCurrentMinu() {
        return getFormatDate("mm");
    }
    /**
     * ��������string���ʱ���
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String subTime(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ��ʽ��ʱ��
            Date outdate = sdf.parse(endTime);
            Date indate = sdf.parse(startTime);
            long totalhours = (outdate.getTime() - indate.getTime()) / (1000 * 60 * 60); //ʱ
            long totalminutes = (outdate.getTime() - indate.getTime()) / (1000 * 60) - totalhours * 60; //��
            long totalseconds = (outdate.getTime() - indate.getTime()) / (1000) - totalminutes * 60; //��
            String totaltime = totalhours + "ʱ" + totalminutes + "��" + totalseconds + "��";
            return totaltime;
        } catch (Exception ex) {
            return "";
        }
    }
    //����ʣ��ʱ��
    public static String getRemainTime(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // ��ʽ��ʱ��
            Date outdate = sdf.parse(endTime);
            Date indate = sdf.parse(startTime);
            long totalminutes = (outdate.getTime() - indate.getTime()) / (1000 * 60); //��
            long totalseconds = (outdate.getTime() - indate.getTime()) / (1000) - totalminutes * 60; //��
            String remainTime = totalminutes + "#" + totalseconds;
            return remainTime;
        } catch (Exception ex) {
            return "";
        }
    }

    public static String parseDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static String parseString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    //格式-时间戳：
    public static long getTimeSpan(String time,String timeFormat) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(timeFormat);
        Date d=sdf.parse(time);
        return d.getTime();
    }
    public static String getTimeString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    //日期：
    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }
    //计算时间段月差
    public static int countMonths(String date1,String date2,String pattern) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);

        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);

        //开始日期若小月结束日期
        if(year<0){
            year=-year;
            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
        }

        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    }
    //计算时间段月差
    public static int countMonths(Date date1 ,Date date2) throws ParseException {
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        //开始日期若小月结束日期
        if(year<0){
            year=-year;
            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
        }
        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    }

    /**
     * long转日期格式
     * @param  mins 时间戳
     * @param formater 日期格式
     */
    public static String getFormatDate(long mins,String formater) {
        if(mins==0){
            return "";
        }
        if(StringUtil.isNullOrEmpty(formater))
            formater="yyyy-MM-dd";
        SimpleDateFormat format=new SimpleDateFormat(formater);
        Date date=new Date(mins);
        return format.format(date);
    }
    /**
     * 日期格式
     * @param  date 日期
     * @param formater 日期格式
     */
    public static Date getFormatDate(Date date,String formater) throws ParseException {
        if(date==null){
            return null;
        }
        SimpleDateFormat format=new SimpleDateFormat(formater);
        String nowStr=format.format(date);
        return format.parse(nowStr);
    }
    /**
     * 日期格式
     * @param  date 日期
     * @param formater 日期格式
     */
    public static Date getFormatDate(String date,String formater) throws ParseException {
        if(StringUtil.isNullOrEmpty(date)){
            return null;
        }
        SimpleDateFormat format=new SimpleDateFormat(formater);
        return format.parse(date);
    }
     /**
     * 日期添加
     */
    public static Date getDateAfterDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
    public static String getTimeTrans(String date,String inFormat,String outFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        Date outdate = sdf.parse(date);
        sdf = new SimpleDateFormat(outFormat);
        return sdf.format(outdate);
    }
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 根据年龄获取出生年份
     */
    public static int getYearByAge(int age) {
        Calendar   calendar=Calendar.getInstance();//获取现在时间
        int currentYear=calendar.get(Calendar.YEAR);//获取年份
        return currentYear-age;
    }
    public static Date getRdDate(String dateStr){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            if(dateStr.indexOf(".")>-1){
                return sdf1.parse(dateStr);
            }
            else {
                return sdf2.parse(dateStr);
            }
        }
        catch (Exception ex){
            return null;
        }

    }
    public static String getTimestamp(String dateString){
        SimpleDateFormat sdfIn;
        Date date = null;
        if (!StringUtil.isNullOrEmpty(dateString)) {
            try {
                if(dateString.length() == 13) {//ʱ�����ʽֱ�ӷ���
                    Long.parseLong(dateString);
                    return dateString;
                }
                if (dateString.length() == 6) {
                    sdfIn = new SimpleDateFormat("yyMMdd");//�������صĸ�ʽ
                } else {
                    //sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�Ƽ��б��صĸ�ʽ
                    sdfIn = new SimpleDateFormat("yyyy-MM-dd");
                }
                date = sdfIn.parse(dateString);
            } catch (Exception ex) {
                //������ת��ʧ��,ʲô������
            }
        }
        //����ʱ����ַ���
        return date != null ? String.valueOf(date.getTime()) : "";
    }
    public static String timestampToDate(String s){
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long lt = new Long(s);
            Date date = new Date(lt);
            String res = format.format(date);
            result = getTimestamp(res);
        } catch (NumberFormatException e) {

        }
        return result;
    }
    public static String getFormatDateTime(String dateString) {
        String time = "";
        if (!StringUtil.isNullOrEmpty(dateString)) {
            String replaceDateString = dateString.replace("/Date(", "").replace(")/", "");
            time = replaceDateString.substring(0, replaceDateString.length() - 5);
        }
        return time;
    }
    public static long getDateMidnight(long timestamp) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(timestamp);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        now.set(Calendar.MILLISECOND, 999);
        return now.getTimeInMillis();
    }
    public static Date getMidnight() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        now.set(Calendar.MILLISECOND, 999);
        return now.getTime();
    }
}

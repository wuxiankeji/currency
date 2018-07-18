package com.mlink.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * @author wyh
 * @version 2017/12/19.
 */
public class ValidateUtil {

    //身份号码正则表达式
    private static final String IDENTITY_CARD_REGEX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    //手机号码正则表达式
    private static final String PHONE_NUMBER_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9])\\d{8}$";

    //Email正则表达式
    private static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    //验证字符串是否只包含数字
    private static final String NUMBER_REGEX = "^[0-9]*$";

    //验证字符串是否只包含字母
    private static final String CHARACTER_REGEX = "^[a-zA-Z]+$";

    //验证字符串是否只包含中文汉字
    private static final String CHINESE_REGEX = "^[\\u2E80-\\uFE4F]{1,}$";

    //验证字符串是否只包含数字、字母
    private static final String NUMBER_CHARACTER_REGEX = "^[a-zA-Z0-9]+$";

    //验证字符串是否只包含数字、字母 、点、下划线、中横线
    private static final String NUMBER_CHARACTER_SPECIAL_REGEX = "^[a-zA-Z0-9.\\-_]+$";
    //验证字符串是否只包含数字、字母、中文汉字
    private static final String NUMBER_CHARACTER_CHINESE_REGEX = "^[a-zA-Z0-9\\u2E80-\\uFE4F]+$";

    //上传图片格式定义
    public static final String[] IMG_SUFFIXES = {".jpg", ".png", ".bmp"};


    /**
     * 判断一个字符串是否不是一个空字符串
     *
     * @param s 要判断的字符串
     * @return 如果不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(String s) {
        return ((s != null) && s.length() > 0);
    }

    /**
     * 判断一个字符串是否是一个空字符串
     *
     * @param s 要判断的字符串
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }

    /**
     * 判断一个Collection类型的集合是否不是一个空集合
     *
     * @param c 要判断集合
     * @return 如果不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(Collection c) {
        return ((c != null) && (c.size() > 0));
    }

    /**
     * 判断一个Collection类型的集合是否是一个空集合
     *
     * @param c 要判断集合
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(Collection c) {
        return ((c == null) || (c.size() == 0));
    }

    /**
     * 判断一个Map类型的集合是否不是一个空集合
     *
     * @param m 要判断的集合
     * @return 如果不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(Map m) {
        return ((m != null) && (m.size() > 0));
    }

    /**
     * 判断一个Map类型的集合是否是一个空集合
     *
     * @param m 要判断的集合
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(Map m) {
        return ((m == null) || (m.size() == 0));
    }

    /**
     * 判断一个int类型的数组是否不是一个空数组
     *
     * @param i 要判断的数组
     * @return 如果不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(int[] i) {
        return ((i != null) && (i.length > 0));
    }

    /**
     * 判断一个int类型的数组是否是一个空数组
     *
     * @param i 要判断的数组
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(int[] i) {
        return ((i == null) || (i.length == 0));
    }

    /**
     * 判断一个String类型的数组是否不是一个空数组
     *
     * @param s 要判断的数组
     * @return 如果不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(String[] s) {
        return ((s != null) && (s.length > 0));
    }

    /**
     * 判断一个String类型的数组是否是一个空数组
     *
     * @param s 要判断的数组
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(String[] s) {
        return ((s == null) || (s.length == 0));
    }

    /**
     * 验证一个字符串是否是Double类型
     *
     * @param s 要验证的字符串
     * @return 如果是Double类型则返回true,否则返回false
     */
    public static boolean isDouble(String s) {
        if (s == null || s.equals(""))
            return false;
        String num = "0123456789.";
        if (s.indexOf('.') >= 0)
            if (s.indexOf('.', s.indexOf('.') + 1) > 0)
                return false;
        for (int i = 0; i < s.length(); i++) {
            if (num.indexOf(s.charAt(i)) < 0) {
                return false;
            } else {
                try {
                    Double.parseDouble(s);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证一个字符串是否是Float类型
     *
     * @param s 要验证的字符串
     * @return 如果是Float类型则返回true,否则返回false
     */
    public static boolean isFloat(String s) {
        if (s == null || s.equals(""))
            return false;
        String num = "0123456789.";
        if (s.indexOf('.') >= 0)
            if (s.indexOf('.', s.indexOf('.') + 1) > 0)
                return false;
        for (int i = 0; i < s.length(); i++) {
            if (num.indexOf(s.charAt(i)) < 0) {
                return false;
            } else {
                try {
                    Float.parseFloat(s);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证一个字符串是否是整形
     *
     * @param s 要验证的字符串
     * @return 如果是整形则返回true,否则返回false
     */
    public static boolean isInteger(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else {
            String str = "0123456789";
            String num = "-0123456789";
            if (num.indexOf(s.charAt(0)) < 0)
                return false;
            for (int i = 1; i < s.length(); i++) {
                if (str.indexOf(s.charAt(i)) < 0) {
                    return false;
                } else {
                    try {
                        Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 验证一个字符串是否是数字组成
     *
     * @param s 要验证的字符串
     * @return 如果字符串是数字组成的则返回true,否则返回false
     */
    public static boolean isNumber(String s) {
        if (s == null || s.equals(""))
            return false;
        String num = "0123456789";
        for (int i = 0; i < s.length(); i++) {
            if (num.indexOf(s.charAt(i)) < 0)
                return false;
        }
        return true;
    }

    /**
     * 验证一个字符串是否一个合法日期,日期格式：yyyy-MM-dd
     *
     * @param date 要验证的字符串日期
     * @return 如果字符串是一个合法的日期返回true,否则返回false
     */
    public static boolean isDate(String date, String... format) {
        if(format == null || format.length == 0){
            format = new String[1];
            format[0] = "yyyy-MM-dd";
        }
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format[0]);
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 验证一个字符串是否一个合法日期时间,日期时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param time 要验证的字符串日期时间
     * @return 如果字符串是一个合法的日期时间返回true,否则返回false
     */
    public static boolean isDateTime(String time) {
        return isDate(time,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param regex
     *            正则表达式字符串
     * @param str
     *            要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    /**
     * 位数检查 **不能超过**位！
     * @param str 检查字符串
     * @param digit 检查位数
     * @return
     */
    public static boolean isDigit(String str, int digit){
        return str != null && str.length() <= digit;
    }

    /**
     * 位数检查 输入的**位数必须在*~*之间！
     * @param str 检查字符串
     * @param begin 检查位数起始数
     * @param end 检查位数结束数
     * @return
     */
    public static boolean isDigit(String str, int begin, int end){
        return str != null && str.length() >= begin && str.length() <= end;
    }

    /**
     * 验证一个字符串是否是数字、字母、点、下划线和中横线
     * @param str 检查字符串
     * @return 如果字符串是是以数字、字母、点、下划线和中横线组合返回true，否则返回false
     */
    public static boolean isAplhaNumberSpecial(String str){
        if(str == null){
            return false;
        }
        return match(NUMBER_CHARACTER_SPECIAL_REGEX, str);
    }

    /**
     *  验证一个字符串是否是数字、字母和中文组成
     * @param str 检查字符串
     * @return 字符串是以数字、字母和中文返回true，否则返回false
     */
    public static boolean isAplhaNumericChinese(String str){
        if(str == null){
            return false;
        }
        return match(NUMBER_CHARACTER_CHINESE_REGEX, str);
    }

    /**
     * 校验手机号格式是否正确
     * @param str 要验证的字符串
     * @return 符合手机号返回true，否则返回false
     */
    public static boolean isPhone(String str){
        if(str == null){
            return false;
        }
        return match(PHONE_NUMBER_REGEX, str);
    }

    /**
     * 校验身份证号码是否正确
     * @param str 要验证的字符串
     * @return 符合身份证号码返回true，否则返回false
     */
    public static boolean isIDCard(String str){
        if(str == null){
            return false;
        }
        return match(IDENTITY_CARD_REGEX, str);
    }

    /**
     * 校验电子邮箱是否正确
     * @param str 要验证的字符串
     * @return 符合电子邮箱格式返回true，否则返回false
     */
    public static boolean isEmail(String str){
        if(str == null){
            return false;
        }
        return match(EMAIL_REGEX, str);
    }

    /**
     * 校验文件后缀是否符合定义规格
     * @param str 要验证的字符串
     * @return 如果字符串与数组中数据匹配上返回true，否则返回false
     */
    public static boolean endsWithAny(String str,String[] suffixes){
        if(str != null){
            str = str.toLowerCase();
        }
        return StringUtils.endsWithAny(str,suffixes);
    }
}

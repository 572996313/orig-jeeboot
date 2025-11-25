//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util;

import com.alibaba.fastjson.JSONArray;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class a {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);

    public static boolean a(Object object) {
        if (object == null) {
            return true;
        } else if ("".equals(object)) {
            return true;
        } else {
            return "null".equals(object);
        }
    }

    public static boolean b(Object object) {
        return object != null && !"".equals(object) && !object.equals("null");
    }

    public static String a(String inStr) {
        if (a((Object)inStr)) {
            return null;
        } else {
            try {
                inStr = URLDecoder.decode(inStr, "UTF-8");
            } catch (Exception var2) {
            }

            return inStr;
        }
    }

    public static String a(String strIn, String sourceCode, String targetCode) {
        String temp = c(strIn, sourceCode, targetCode);
        return temp;
    }

    public static String b(String strIn, String sourceCode, String targetCode) {
        strIn = "";

        try {
            strIn = new String(strIn.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return strIn;
    }

    private static String c(String strIn, String sourceCode, String targetCode) {
        String strOut = null;
        if (strIn != null && !"".equals(strIn.trim())) {
            try {
                byte[] b = strIn.getBytes(sourceCode);

                for(int i = 0; i < b.length; ++i) {
                    System.out.print(b[i] + "  ");
                }

                strOut = new String(b, targetCode);
                return strOut;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return strIn;
        }
    }

    public static int a(String s, int defval) {
        if (s != null && !"".equals(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static int b(String s) {
        if (s != null && !"".equals(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int a(String s, Integer df) {
        if (s != null && !"".equals(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return 0;
            }
        } else {
            return df;
        }
    }

    public static Integer[] a(String[] s) {
        if (s == null) {
            return null;
        } else {
            Integer[] integer = new Integer[s.length];

            for(int i = 0; i < s.length; ++i) {
                integer[i] = Integer.parseInt(s[i]);
            }

            return integer;
        }
    }

    public static double a(String s, double defval) {
        if (s != null && !"".equals(s)) {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException var4) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static double a(Double s, double defval) {
        return s == null ? defval : s;
    }

    public static int a(Object object, int defval) {
        if (a(object)) {
            return defval;
        } else {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException var3) {
                return defval;
            }
        }
    }

    public static Integer a(Object object, Integer defval) {
        if (a(object)) {
            return defval;
        } else {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException var3) {
                return defval;
            }
        }
    }

    public static Integer c(Object object) {
        if (a(object)) {
            return null;
        } else {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException var2) {
                return null;
            }
        }
    }

    public static int a(BigDecimal s, int defval) {
        return s == null ? defval : s.intValue();
    }

    public static Integer[] b(String[] object) {
        int len = object.length;
        Integer[] result = new Integer[len];

        try {
            for(int i = 0; i < len; ++i) {
                result[i] = new Integer(object[i].trim());
            }

            return result;
        } catch (NumberFormatException var4) {
            return null;
        }
    }

    public static String c(String s) {
        return a(s, "");
    }

    public static String d(Object object) {
        return a(object) ? "" : object.toString().trim();
    }

    public static String a(int i) {
        return String.valueOf(i);
    }

    public static String a(float i) {
        return String.valueOf(i);
    }

    public static String d(String input) {
        if (a((Object)input)) {
            return null;
        } else {
            String result = input.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "");
            return result;
        }
    }

    public static String a(String s, String defval) {
        return a((Object)s) ? defval : s.trim();
    }

    public static String a(Object s, String defval) {
        return a(s) ? defval : s.toString().trim();
    }

    public static long e(String str) {
        Long test = new Long(0L);

        try {
            test = Long.valueOf(str);
        } catch (Exception var3) {
        }

        return test;
    }

    public static String getIp() {
        String ip = null;

        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }

    private static boolean a(Class clazz) throws Exception {
        return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive();
    }

    public static String f(String base64Str) {
        byte[] byteContent = Base64.decodeBase64(base64Str);
        if (byteContent == null) {
            return null;
        } else {
            String decodedString = new String(byteContent);
            return decodedString;
        }
    }

    public static String a(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getRealIp() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;

        while(netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration address = ni.getInetAddresses();

            while(address.hasMoreElements()) {
                ip = (InetAddress)address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                }

                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localip = ip.getHostAddress();
                }
            }
        }

        return netip != null && !"".equals(netip) ? netip : localip;
    }

    public static String g(String str) {
        String dest = "";
        if (str != null) {
            String reg = "\\s*|\t|\r|\n";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static boolean a(String child, String[] all) {
        if (all != null && all.length != 0) {
            for(int i = 0; i < all.length; ++i) {
                String aSource = all[i];
                if (aSource.equals(child)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean a(String[] childArray, String[] all) {
        if (all != null && all.length != 0) {
            for(String v : childArray) {
                if (!a(v, all)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean a(JSONArray childArray, String[] all) {
        if (all != null && all.length != 0) {
            for(String v : childArray.toJavaList(String.class)) {
                if (!a(v, all)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static Map<Object, Object> getHashMap() {
        return new HashMap(5);
    }

//    public static Map<Object, Object> a(Set<Object> setobj) {
//        Map<Object, Object> map = getHashMap();
//
//        for(Map.Entry entry : setobj) {
//            map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
//        }
//
//        return map;
//    }

    public static boolean h(String ipAddress) {
        boolean isInnerIp = false;
        long ipNum = n(ipAddress);
        long aBegin = n("10.0.0.0");
        long aEnd = n("10.255.255.255");
        long bBegin = n("172.16.0.0");
        long bEnd = n("172.31.255.255");
        long cBegin = n("192.168.0.0");
        long cEnd = n("192.168.255.255");
        String localIp = "127.0.0.1";
        isInnerIp = a(ipNum, aBegin, aEnd) || a(ipNum, bBegin, bEnd) || a(ipNum, cBegin, cEnd) || localIp.equals(ipAddress);
        return isInnerIp;
    }

    private static long n(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = (long)Integer.parseInt(ip[0]);
        long b = (long)Integer.parseInt(ip[1]);
        long c = (long)Integer.parseInt(ip[2]);
        long d = (long)Integer.parseInt(ip[3]);
        long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
        return ipNum;
    }

    private static boolean a(long userIp, long begin, long end) {
        return userIp >= begin && userIp <= end;
    }

    public static String i(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            if (!name.contains("_")) {
                String var10000 = name.substring(0, 1).toLowerCase();
                return var10000 + name.substring(1).toLowerCase();
            } else {
                String[] camels = name.split("_");

                for(String camel : camels) {
                    if (!camel.isEmpty()) {
                        if (result.length() == 0) {
                            result.append(camel.toLowerCase());
                        } else {
                            result.append(camel.substring(0, 1).toUpperCase());
                            result.append(camel.substring(1).toLowerCase());
                        }
                    }
                }

                return result.toString();
            }
        } else {
            return "";
        }
    }

    public static String j(String names) {
        if (names != null && !"".equals(names)) {
            StringBuffer sf = new StringBuffer();
            String[] fs = names.split(",");

            for(String field : fs) {
                field = i(field);
                sf.append(field + ",");
            }

            String result = sf.toString();
            return result.substring(0, result.length() - 1);
        } else {
            return null;
        }
    }

    public static String k(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            if (!name.contains("_")) {
                String var10000 = name.substring(0, 1).toUpperCase();
                return var10000 + name.substring(1).toLowerCase();
            } else {
                String[] camels = name.split("_");

                for(String camel : camels) {
                    if (!camel.isEmpty()) {
                        result.append(camel.substring(0, 1).toUpperCase());
                        result.append(camel.substring(1).toLowerCase());
                    }
                }

                return result.toString();
            }
        } else {
            return "";
        }
    }

    public static String l(String para) {
        int length = 3;
        if (para.length() < length) {
            return para.toLowerCase();
        } else {
            StringBuilder sb = new StringBuilder(para);
            int temp = 0;

            for(int i = 2; i < para.length(); ++i) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    ++temp;
                }
            }

            return sb.toString().toLowerCase();
        }
    }

    public static String b(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();

        for(int i = 0; i < place; ++i) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }

        return sb.toString();
    }

    public static Field[] e(Object object) {
        Class clazz = object.getClass();

        List fieldList;
        for(fieldList = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldList.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
        }

        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    public static List<Map<String, Object>> a(List<Map<String, Object>> list) {
        List select = new ArrayList();

        for(Map<String, Object> row : list) {
            Map resultMap = new HashMap(5);

            for(String key : row.keySet()) {
                String newKey = key.toLowerCase();
                resultMap.put(newKey, row.get(key));
            }

            select.add(resultMap);
        }

        return select;
    }

    public static <F, T> List<T> a(List<F> fromList, Class<T> tClass) {
        if (fromList != null && !fromList.isEmpty()) {
            List tList = new ArrayList();

            for(Object f : fromList) {
                Object t = a(f, tClass);
                tList.add(t);
            }

            return tList;
        } else {
            return null;
        }
    }

    public static <F, T> T a(F entity, Class<T> modelClass) {
        a.debug("entityToModel : Entity属性的值赋值到Model");
        Object model = null;
        if (entity != null && modelClass != null) {
            try {
                model = modelClass.newInstance();
            } catch (InstantiationException e) {
                a.error("entityToModel : 实例化异常", e);
            } catch (IllegalAccessException e) {
                a.error("entityToModel : 安全权限异常", e);
            }

            BeanUtils.copyProperties(entity, model);
            return (T)model;
        } else {
            return null;
        }
    }

    public static boolean a(Collection list) {
        return list == null || list.size() == 0;
    }

    public static boolean a(Object oldVal, Object newVal) {
        if (oldVal != null && newVal != null) {
            if (f(oldVal)) {
                return a(oldVal, newVal);
            } else if (oldVal instanceof JSONArray) {
                if (newVal instanceof JSONArray) {
                    return a((JSONArray)oldVal, (JSONArray)newVal);
                } else if (!a(newVal) || oldVal != null && ((JSONArray)oldVal).size() != 0) {
                    List arrayStr = Arrays.asList(newVal.toString().split(","));
                    JSONArray newValArray = new JSONArray(arrayStr);
                    return a((JSONArray)oldVal, newValArray);
                } else {
                    return true;
                }
            } else {
                return oldVal.equals(newVal);
            }
        } else {
            return oldVal == null && newVal == null;
        }
    }

    public static boolean f(Object obj) {
        return obj == null ? false : obj.getClass().isArray();
    }

    public static int b(Collection<?> collection) {
        return collection != null ? collection.size() : 0;
    }

    public static boolean a(JSONArray oldVal, JSONArray newVal) {
        if (oldVal != null && newVal != null) {
            Object[] oldValArray = oldVal.toArray();
            Object[] newValArray = newVal.toArray();
            return a(oldValArray, newValArray);
        } else {
            return (oldVal == null || oldVal.size() == 0) && (newVal == null || newVal.size() == 0);
        }
    }

    public static boolean b(String oldVal, String newVal) {
        if (oldVal.equals(newVal)) {
            return true;
        } else {
            if (oldVal.indexOf(",") >= 0 && newVal.indexOf(",") >= 0) {
                String[] arr1 = oldVal.split(",");
                String[] arr2 = newVal.split(",");
                if (arr1.length == arr2.length) {
                    boolean flag = true;
                    Map map = new HashMap();

                    for(String s1 : arr1) {
                        map.put(s1, 1);
                    }

                    for(String s2 : arr2) {
                        if (map.get(s2) == null) {
                            flag = false;
                            break;
                        }
                    }

                    return flag;
                }
            }

            return false;
        }
    }

    public static boolean a(Object[] oldVal, Object[] newVal) {
        if (oldVal != null && newVal != null) {
            Arrays.sort(oldVal);
            Arrays.sort(newVal);
            return Arrays.equals(oldVal, newVal);
        } else {
            return (oldVal == null || oldVal.length == 0) && (newVal == null || newVal.length == 0);
        }
    }

    public static boolean c(Collection list) {
        return !a(list);
    }

    public static String m(String url) {
        String json = "";

        try {
            InputStream stream = a.class.getClassLoader().getResourceAsStream(url.replace("classpath:", ""));
            json = IOUtils.toString(stream, "UTF-8");
        } catch (IOException e) {
            a.error(e.getMessage(), e);
        }

        return json;
    }

    public static JSONArray b(List<String> list) {
        if (list != null && list.size() != 0) {
            JSONArray array = new JSONArray();

            for(String str : list) {
                array.add(str);
            }

            return array;
        } else {
            return null;
        }
    }

    public static boolean a(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        } else {
            for(String str1 : list1) {
                boolean flag = false;

                for(String str2 : list2) {
                    if (str1.equals(str2)) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean b(List<String> list1, List<String> list2) {
        for(String str1 : list1) {
            boolean flag = false;

            for(String str2 : list2) {
                if (str1.equals(str2)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }

    public static Double a(Long uploadCount) {
        double count = (double)0.0F;
        if (uploadCount > 0L) {
            BigDecimal bigDecimal = new BigDecimal(uploadCount);
            BigDecimal divide = bigDecimal.divide(new BigDecimal(1048576));
            count = divide.setScale(2, 4).doubleValue();
            return count;
        } else {
            return count;
        }
    }

    public static String a(Map<String, String[]> map) {
        if (map != null && map.size() != 0) {
            StringBuilder sb = new StringBuilder();

            for(Map.Entry entry : map.entrySet()) {
                String key = (String)entry.getKey();
                String[] values = (String[])entry.getValue();
                sb.append(key).append("=");
                sb.append(values != null ? StringUtils.join(values, ",") : "");
                sb.append("&");
            }

            String result = sb.toString();
            if (result.endsWith("&")) {
                result = result.substring(0, sb.length() - 1);
            }

            return result;
        } else {
            return null;
        }
    }

    public static boolean g(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof CharSequence) {
            return a(obj);
        } else if (obj instanceof Map) {
            return ((Map)obj).isEmpty();
        } else if (obj instanceof Iterable) {
            return g((Object)((Iterable)obj).iterator());
        } else if (obj instanceof Iterator) {
            return !((Iterator)obj).hasNext();
        } else if (f(obj)) {
            return 0 == Array.getLength(obj);
        } else {
            return false;
        }
    }

    public static boolean a(Iterator<?> iterator) {
        return null == iterator || !iterator.hasNext();
    }

    public static boolean h(Object object) {
        return !g(object);
    }

    public static boolean a(Number src, Number des) {
        if (null != src && null != des) {
            return src.doubleValue() > des.doubleValue();
        } else {
            throw new IllegalArgumentException("参数不能为空");
        }
    }

    public static boolean b(Number src, Number des) {
        if (null != src && null != des) {
            return !(src.doubleValue() < des.doubleValue());
        } else {
            throw new IllegalArgumentException("参数不能为空");
        }
    }

    public static <T> boolean a(T obj, T... objs) {
        return a(obj, objs);
    }
}

package org.jeecg.modules.online.cgform.converter.dirb;

import com.alibaba.fastjson.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jetbrains.annotations.NotNull;

public class b implements FieldCommentConverter {
    String a;
    private static final String b = "year";
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy");
    private static final String d = "month";
    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM");
    private static final String f = "week";
    private static final String g = "quarter";
    private static final String h = "default";
    private static final List<bi> i = new ArrayList();

    @NotNull
    private static Date a(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1);
        int i = calendar.get(7);
        if (i > 3) {
            i = 9 - i;
        }

        calendar.set(6, i);
        calendar.add(6, (week - 1) * 7);
        return calendar.getTime();
    }

    public b(OnlCgformField onlCgformField) {
        String fieldExtendJsonStr = onlCgformField.getFieldExtendJson();
        if (oConvertUtils.isNotEmpty(fieldExtendJsonStr)) {
            JSONObject fieldExtendJson = JSONObject.parseObject(fieldExtendJsonStr);
            if (fieldExtendJson.containsKey("picker") && null != fieldExtendJson.get("picker")) {
                this.a = fieldExtendJson.getString("picker");
            }
        }

    }

    public String converterToVal(String txt) {
        if (null != this.a && !this.a.isEmpty()) {
            if (null != txt && !txt.isEmpty()) {
                Date date = this.a(txt);
                return null == date ? "" : ((SimpleDateFormat)DateUtils.date_sdf.get()).format(date);
            } else {
                return txt;
            }
        } else {
            return txt;
        }
    }

    private Date a(String txt) {
        for(bi dateConverter : i) {
            if (dateConverter.b(txt)) {
                try {
                    return dateConverter.a(txt);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    public String converterToTxt(String val) {
        if (null != this.a && !this.a.isEmpty()) {
            if (null != val && !val.isEmpty()) {
                try {
                    Date valDate = ((SimpleDateFormat)DateUtils.date_sdf.get()).parse(val);
                    switch (this.a) {
                        case "year":
                            return c.format(valDate);
                        case "month":
                            return org.jeecg.modules.online.cgform.converter.dirb.b.e.format(valDate);
                        case "week":
                        case "quarter":
                            Calendar calendar = Calendar.getInstance();
                            calendar.setFirstDayOfWeek(1);
                            calendar.setTime(valDate);
                            int year = calendar.get(1);
                            if (this.a.equals("week")) {
                                calendar.set(year, 0, 1);
                                int i = calendar.get(7);
                                if (i > 3) {
                                    i = 8 - i;
                                }

                                calendar.setTime(valDate);
                                int dayOfyear = calendar.get(6);
                                int week = (dayOfyear - i) / 7 + 1;
                                return String.format("%d-%d周", year, week);
                            }

                            int month = calendar.get(2) + 1;
                            int quarter = (int)Math.ceil((double)month / (double)3.0F);
                            return String.format("%d-Q%d", year, quarter);
                        default:
                            return val;
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return val;
            }
        } else {
            return val;
        }
    }

    public Map<String, String> getConfig() {
        return Collections.emptyMap();
    }

    static {
        i.add(new c("^\\d{4}$", "yyyy"));
        i.add(new c("^\\d{4}年$", "yyyy年"));
        i.add(new c("^\\d{4}年\\d{1,2}月$", "yyyy年MM月"));
        i.add(new c("^\\d{4}-\\d{1,2}$", "yyyy-MM"));
        i.add(new c("^\\d{4}\\/\\d{1,2}$", "yyyy/MM"));
        i.add(new a("^\\d{4}-\\d{1,2}周$") {
            public Date a(String txt) {
                int year = Integer.parseInt(txt.substring(0, txt.indexOf("-")));
                int week = Integer.parseInt(txt.substring(txt.indexOf("-") + 1, txt.indexOf("周")));
                return org.jeecg.modules.online.cgform.converter.dirb.b.a(year, week);
            }
        });
        i.add(new a("^\\d{4}年\\d{1,2}周$") {
            public Date a(String txt) {
                int year = Integer.parseInt(txt.substring(0, txt.indexOf("年")));
                int week = Integer.parseInt(txt.substring(txt.indexOf("年") + 1, txt.indexOf("周")));
                return org.jeecg.modules.online.cgform.converter.dirb.b.a(year, week);
            }
        });
        i.add(new a("^\\d{4}-Q\\d{1}$") {
            public Date a(String txt) {
                Calendar calendar = Calendar.getInstance();
                int year = Integer.parseInt(txt.substring(0, txt.indexOf("-")));
                calendar.set(1, year);
                int quarter = Integer.parseInt(txt.substring(txt.indexOf("-Q") + 2));
                int month = quarter * 3;
                calendar.set(2, month - 1);
                return calendar.getTime();
            }
        });
        i.add(new a("^\\d{4}年Q\\d{1}$") {
            public Date a(String txt) {
                Calendar calendar = Calendar.getInstance();
                int year = Integer.parseInt(txt.substring(0, txt.indexOf("年")));
                calendar.set(1, year);
                int quarter = Integer.parseInt(txt.substring(txt.indexOf("年Q") + 2));
                int month = quarter * 3;
                calendar.set(2, month - 1);
                return calendar.getTime();
            }
        });
        i.add(new a("^\\d{4}年\\d{1}季度$") {
            public Date a(String txt) {
                Calendar calendar = Calendar.getInstance();
                int year = Integer.parseInt(txt.substring(0, txt.indexOf("年")));
                calendar.set(1, year);
                int quarter = Integer.parseInt(txt.substring(txt.indexOf("年") + 1, txt.indexOf("季度")));
                int month = quarter * 3;
                calendar.set(2, month - 1);
                return calendar.getTime();
            }
        });
        i.add(new c("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd"));
        i.add(new c("^\\d{4}年\\d{1,2}月\\d{1,2}日$", "yyyy年MM月dd日"));
    }

    abstract static class a implements bi {
        private final String a;

        public a(String reg) {
            this.a = reg;
        }

        public boolean b(String txt) {
            return null != txt && !txt.isEmpty() ? txt.matches(this.a) : false;
        }
    }

    static class c extends a {
        SimpleDateFormat a;

        public c(String reg, String format) {
            super(reg);
            this.a = new SimpleDateFormat(format);
        }

        public Date a(String txt) throws ParseException {
            return this.a.parse(txt);
        }
    }

    interface bi {
        boolean b(String txt);

        Date a(String txt) throws ParseException;
    }
}

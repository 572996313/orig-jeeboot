//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class l {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(l.class);

    public static void a(StringBuilder builder, QueryRuleEnum rule, String nativeValue, String value, String type) {
        if ("date".equals(type) && "ORACLE".equalsIgnoreCase(d.getDatabseType())) {
            value = value.replace("'", "");
            if (value.length() == 10) {
                value = d.b(value);
            } else {
                value = d.a(value);
            }
        }

        switch (rule) {
            case GT:
                builder.append(">").append(value);
                break;
            case GE:
                builder.append(">=").append(value);
                break;
            case LT:
                builder.append("<").append(value);
                break;
            case LE:
                builder.append("<=").append(value);
                break;
            case NE:
                builder.append("!=").append(value);
                break;
            case IN:
                builder.append(" IN (");
                String[] arr = nativeValue.split(",");

                for(int i = 0; i < arr.length; ++i) {
                    String str = arr[i];
                    if (StringUtils.isNotBlank(str)) {
                        String val = a(type, str);
                        builder.append(val);
                        if (i < arr.length - 1) {
                            builder.append(",");
                        }
                    }
                }

                builder.append(")");
                break;
            case LIKE:
                builder.append(" like ").append("N").append("'").append("%").append(nativeValue).append("%").append("'");
                break;
            case LEFT_LIKE:
                builder.append(" like ").append("N").append("'").append("%").append(nativeValue).append("'");
                break;
            case RIGHT_LIKE:
                builder.append(" like ").append("N").append("'").append(nativeValue).append("%").append("'");
                break;
            case EQ:
            default:
                builder.append("=").append(value);
        }

    }

    public static void a(String dataBase, SysPermissionDataRuleModel dataRule, String field, String dbtype, StringBuffer sb) {
        QueryRuleEnum rule = QueryRuleEnum.getByValue(dataRule.getRuleConditions());
        boolean isSq = !j.a(dbtype);
        String value = a(dataRule.getRuleValue(), isSq, rule);
        if (value != null && rule != null) {
            if ("ORACLE".equalsIgnoreCase(dataBase) && "Date".equals(dbtype)) {
                value = value.replace("'", "");
                if (value.length() == 10) {
                    value = d.b(value);
                } else {
                    value = d.a(value);
                }
            }

            switch (rule) {
                case GT -> sb.append(" AND " + field + ">" + value);
                case GE -> sb.append(" AND " + field + ">=" + value);
                case LT -> sb.append(" AND " + field + "<" + value);
                case LE -> sb.append(" AND " + field + "<=" + value);
                case NE -> sb.append(" AND " + field + " <> " + value);
                case IN -> sb.append(" AND " + field + " IN " + value);
                case LIKE -> sb.append(" AND " + field + " LIKE '%" + QueryGenerator.trimSingleQuote(value) + "%'");
                case LEFT_LIKE -> sb.append(" AND " + field + " LIKE '%" + QueryGenerator.trimSingleQuote(value) + "'");
                case RIGHT_LIKE -> sb.append(" AND " + field + " LIKE '" + QueryGenerator.trimSingleQuote(value) + "%'");
                case EQ -> sb.append(" AND " + field + "=" + value);
                default -> a.info("--查询规则未匹配到---");
            }

        }
    }

    public static String a(String type, String value) {
        if (!"int".equals(type) && !"number".equals(type)) {
            if ("date".equals(type)) {
                return "'" + value + "'";
            } else {
                return "SQLSERVER".equals(d.getDatabseType()) ? "N'" + value + "'" : "'" + value + "'";
            }
        } else {
            return value;
        }
    }

    private static String a(String value, boolean flag, QueryRuleEnum rule) {
        if (rule == QueryRuleEnum.IN) {
            return a(value, flag);
        } else {
            return flag ? "'" + QueryGenerator.converRuleValue(value) + "'" : QueryGenerator.converRuleValue(value);
        }
    }

    private static String a(String value, boolean flag) {
        if (value != null && value.length() != 0) {
            value = QueryGenerator.converRuleValue(value);
            String[] arr = value.split(",");
            List ls = new ArrayList();

            for(String a : arr) {
                if (a != null && a.length() != 0) {
                    if (flag) {
                        ls.add("'" + a + "'");
                    } else {
                        ls.add(a);
                    }
                }
            }

            return "(" + StringUtils.join(ls, ",") + ")";
        } else {
            return "()";
        }
    }
}

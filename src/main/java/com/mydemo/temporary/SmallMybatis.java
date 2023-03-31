package com.mydemo.temporary;

import com.alibaba.fastjson2.JSON;
import org.springframework.util.Assert;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author 1
 * @description sql执行->结果映射到实体类
 */
public class SmallMybatis {

    private static final Pattern PATTERN = Pattern.compile("_[a-z]");

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    public static final boolean AUTO_COMMIT_FALSE = false;

    public static void main(String[] args) {
        List<A> aList = querySql(A.class, "select * from test order by a limit 10");

        int delCount = deleteSql("delete from test");
        System.out.println("Delete 数量为:" + delCount);

        int updateCount = updateSql("");
        System.out.println("Update 数量为:" + updateCount);

        List<A> list = insertSql(A.class,"insert into test(b, c) values(1,1)", "a");
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * todo 待用线程池优化
     * @return
     * @throws Exception
     */
    private static Connection getDbConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 执行插入sql
     * @param clazz 映射类名
     * @param sql 执行的插入sql
     * @param generatedKey 主键
     * @param <T> 映射类名
     * @return List
     */
    public static <T> List<T> insertSql(Class<T> clazz, String sql, String generatedKey) {
        List<T> list = new ArrayList<>();
        try {
            Connection connection = getDbConnection();
            connection.setAutoCommit(AUTO_COMMIT_FALSE);
            Statement statement = connection.createStatement();
            int count =  statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            System.out.println(count);
            if (Objects.nonNull(resultSet)) {
                while (resultSet.next()) {
                    T t = clazz.newInstance();
                    // "GENERATED_KEY"

                    int generateKeyValue = resultSet.getInt(1);
                    Method method = clazz.getMethod(transferColumnNameToSetMethodName(generatedKey), Integer.TYPE);
                    method.invoke(t, generateKeyValue);
                    list.add(t);
                }
            }
            connection.commit();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            list = Collections.emptyList();
        }
        return list;
    }

    /**
     * 删除sql执行
     * @param sql
     * @return
     */
    public static int deleteSql(String sql) {
        int count = 0;
        try {
            Connection connection = getDbConnection();
            Statement statement = getDbConnection().createStatement();
            count = statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }



    /**
     * 更新sql执行
     * @param sql
     * @return
     */
    public static int updateSql(String sql) {
        int count = 0;
        try {
            Connection connection = getDbConnection();
            connection.setAutoCommit(AUTO_COMMIT_FALSE);
            Statement statement = getDbConnection().createStatement();
            count = statement.executeUpdate(sql);
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询sql执行，并映射到对应的实体类
     * @param clazz
     * @param sql
     * @param <T>
     * @return
     */
    public static <T> List<T> querySql (Class<T> clazz, String sql) {
        List<T> result = new ArrayList<>(10);
        try {
            Connection connection = getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int columnCount = 0;

            Method[] methods = clazz.getMethods();
            Map<String, Method> methodMap = Arrays.stream(methods)
                    .collect(Collectors.toMap(Method::getName, Function.identity(), (newVal, oldVal) -> newVal));

            while (resultSet.next()) {
                T t = clazz.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                Assert.isTrue(metaData.getColumnCount() != 0, "查询出错");
                if (columnCount == 0 && resultSet.isFirst()) {
                    columnCount = metaData.getColumnCount();
                }
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
//                    Class valClazz = Class.forName(metaData.getColumnClassName(i));
                    Method method = null;
                    String methodName = transferColumnNameToSetMethodName(columnName);
                    method = methodMap.get(methodName);
                    Assert.isTrue(Objects.nonNull(method), methodName + "不存在");
                    Object val = resultSet.getObject(columnName, method.getParameterTypes()[0]);
                    method.invoke(t, val);
                }
                result.add(t);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 转换column名称为setmethod名称
     * @param columnName
     * @return
     */
    public static String transferColumnNameToSetMethodName(String columnName) {
        String methodName = "set_" + columnName;
        Matcher matcher = PATTERN.matcher(methodName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group().replace("_", "").toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    static class A {
        private int a;
        private int b;
        private int c;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }
    }

}

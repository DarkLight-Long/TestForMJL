package com.mydemo.test;

import org.junit.platform.commons.util.ClassLoaderUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: 动态代理
 * invoke: @see InjectTest
 * 2021.11.2
 */
public class AutoProxyTest {

    /**
     * 代理接口
     */
    public interface User {
        String job();
        String job2();
        String jobEmpty();
    }
    /**
     * 代理类
     */
    public static class UserImpl implements User {
        @Override
        public String job() {
            return "job";
        }

        @Override
        public String job2() {
            return "job2";
        }

        @Override
        public String jobEmpty() {
            return "jobEmpty";
        }
    }

    public static class JDKProxy implements InvocationHandler {
        private Object target;

        JDKProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            System.out.println(method.getName());
            System.out.println(proxy.getClass());
            return method.invoke(target, args); //正规的动态代理;类似接口的方法指向实现类的实现方法
        }
    }

    public static void main(String[] args) {
        JDKProxy proxy = new JDKProxy(new UserImpl());
        ClassLoader classLoader = ClassLoaderUtils.getDefaultClassLoader();
        // 实现运行过程中产生的类class输出(可以查看生成的动态代理类的代码)
        // 动态代理生成位置在com.sun.proxy
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        User user = (User) Proxy.newProxyInstance(classLoader, new Class[]{User.class}, proxy);
        System.out.println(user.job());
        System.out.println(user.job2());
        System.out.println(user.jobEmpty());
    }

}

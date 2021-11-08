package com.mydemo.test;

import org.junit.Test;

/**
 * description: 静态代理
 * other: 静态代理与装饰器模式 =>
 *
 * question: 如果动静态的区别只是动态可以重复使用，
 *          那么我将静态代理用范型表示，不也可以达到效果吗？(例子在后面) =>
 *          后续： 经测试，静态代理只能代理单一接口类，而动态代理没有限制
 */
public class StaticProxyTest {

    public interface Wink {
        public String  sellWinkType();
    }

    public static class MaoTaiWink implements Wink {
        @Override
        public String sellWinkType() {
            return "茅台酒";
        }
    }

    public static class Market implements Wink {
        private MaoTaiWink maoTaiWink;
//        Market(MaoTaiWink maoTaiWink) {
//            this.maoTaiWink = maoTaiWink;
//        }
        Market() {
            maoTaiWink = new MaoTaiWink();
        }
        @Override
        public String sellWinkType() {
            marketAd();
            return maoTaiWink.sellWinkType();
        }
        public void marketAd() {
            System.out.println("茅台酒 is good");
        }
    }

    public static void main(String[] args) {
        MaoTaiWink maoTaiWink = new MaoTaiWink();
//        Wink wink = new Market(maoTaiWink);
        Wink wink = new Market();
        System.out.println(wink.sellWinkType());
    }

    /**
     *
     */
    public static class WuLiangYeWink implements Wink {

        @Override
        public String sellWinkType() {
            return "五粮液酒";
        }
    }

    public static class SuperMarket implements Wink {
        private Wink wink;
        SuperMarket(Wink wink) {
            this.wink = wink;
        }
        @Override
        public String sellWinkType() {
            return wink.sellWinkType();
        }
    }

    @Test
    public void main() {
        MaoTaiWink maoTaiWink = new MaoTaiWink();
        Wink wink = new SuperMarket(maoTaiWink);
        System.out.println(wink.sellWinkType());
        WuLiangYeWink wuLiangYeWink = new WuLiangYeWink();
        wink = new SuperMarket(wuLiangYeWink);
        System.out.println(wink.sellWinkType());
    }

}

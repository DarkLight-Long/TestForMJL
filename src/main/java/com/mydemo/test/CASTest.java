package com.mydemo.test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 1
 * @description CAS相关
 * 理念： 内存中值为A;
 *      进程获取A值后保存为B,经过处理后需要更新为C
 *      进行修改时，将A与B比较，若相等，则进行修改，否则修改失败
 * 问题： ①ABA问题：操作过程中，有其他进程将值修改为B后再更改为A（即版本问题） => AtomicStampedReference有计数，可以解决
 */
public class CASTest {

    public static void main(String[] args) {
        AtomicStampedReference<String> str = new AtomicStampedReference<>("1", 1);
        str.set("2", 2);
    }

}

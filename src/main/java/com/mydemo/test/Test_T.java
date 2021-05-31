package com.mydemo.test;

public class Test_T {

    public static void main(String[] args) {
        P<Number> p = new P<>(1,2);
        p.setFirst(1);

        // 此处为代码示例，不可删除
//        P<? extends Number> px = new P<>(1,2);
//        px.setFirst(1);//不能set  null除外
//        Number n = px.getFirst();//get只能用number
//        Integer n1 = px.getFirst();
//        px.setFirst(n);
//
//        Object o1 = (Object) 1;
//        Object o2 = (Object) 2;
//        P<? super Integer> pi = new P<>(o1, o2);
//        pi.setFirst(o1);
//        pi.setFirst(1);//可以set integer
//        Object o3 = pi.getFirst();//可以get object
    }


    static class P<T> {
        T first;
        T last;

        P(T first, T last) {
            this.first = first;
            this.last = last;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public T getLast() {
            return last;
        }

        public void setLast(T last) {
            this.last = last;
        }

        //
        public static <K> P<K> getStaticFirst(K first, K last) {
            P<K> p = new P<>(first, last);
            return p;
        }
    }

}

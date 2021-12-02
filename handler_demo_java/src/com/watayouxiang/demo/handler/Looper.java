package com.watayouxiang.demo.handler;

public class Looper {
    MessageQueue messageQueue;
    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    private Looper() {
        messageQueue = new MessageQueue();
    }

    /**
     * 获取与该线程绑定的Looper
     */
    public static Looper myLooper(){
        return sThreadLocal.get();
    }

    /**
     * 将线程和Looper绑定
     * <p>
     * 为了确保一个线程只有一个消息队列：
     * 因为一个 Looper 对应一个消息队列，所以只要 Looper 唯一，那么消息队列也就是唯一，同时线程也是唯一。
     * 通过查看 Thread 源码可以知道，一个 Thread 对应一个 ThreadLocal.ThreadLocalMap。
     * ThreadLocal.ThreadLocalMap 的 key = ThreadLocal<Looper>，value = Looper。
     * 所以如果要取出 Looper，只要持有 ThreadLocal.ThreadLocalMap 的 key（ThreadLocal）就可以了。
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 开启死循环
     * <p>
     * 这里要调用 handleMessage() 方法，必须要要拿到 handler 对象。
     * 而在一个线程中 handler 是可以无数多个的，是无法管理的。
     * 所以只能妥协让 message 持有 handler 对象。
     */
    public static void looper() {
        Looper looper = sThreadLocal.get();
        MessageQueue messageQueue = looper.messageQueue;
        for (; ; ) {
            Message msg = messageQueue.next();
            msg.target.handleMessage(msg);
        }
    }
}

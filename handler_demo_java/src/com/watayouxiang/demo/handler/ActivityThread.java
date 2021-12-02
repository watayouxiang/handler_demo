package com.watayouxiang.demo.handler;

public class ActivityThread {
    public static void main(String[] args) throws Exception {
        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(Thread.currentThread().getName() + " receive " + msg.obj.toString());
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = new Message("nihao");
                System.out.println(Thread.currentThread().getName() + " send " + msg.obj.toString());
                handler.enqueueMessage(msg);
            }
        }.start();

        Looper.looper();
    }
}

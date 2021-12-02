package com.watayouxiang.demo.handler;

public class Handler {
    Looper mLooper;

    public Handler() {
        this.mLooper = Looper.myLooper();
    }

    public void enqueueMessage(Message msg) {
        msg.target = this;
        mLooper.messageQueue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg) {
    }
}

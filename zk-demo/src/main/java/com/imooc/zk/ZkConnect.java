package com.imooc.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZkConnect implements Watcher {
    private static String path = "172.22.175.124:2181,172.22.175.117:2181,172.22.175.121:2181";

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(path, 5000, new ZkConnect());
        System.out.println(zooKeeper.getState());
        Thread.sleep(2000);
        System.out.println(zooKeeper.getState());
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        ZooKeeper  zk= new ZooKeeper(path, 5000, new ZkConnect(), sessionId, sessionPasswd);
        new Thread().sleep(1000);
        System.out.println(zk.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("--------------------123--------------");
        System.out.println(event.getState());
    }
}

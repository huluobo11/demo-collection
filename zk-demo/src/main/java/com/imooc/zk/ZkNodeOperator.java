package com.imooc.zk;


import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;


public class ZkNodeOperator implements Watcher {
    private static String path = "172.22.175.124:2181,172.22.175.117:2181,172.22.175.121:2181";
    private ZooKeeper zooKeeper;

    public ZkNodeOperator(String path) {
        try {
            zooKeeper = new ZooKeeper(path, 5000, new ZkNodeOperator());
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZkNodeOperator() {

    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("..........123..................");
        System.out.println(event.getState() + "/////////////");
    }

    public void createNode() {
        zooKeeper.create("/test", "mmmmm".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, (rc, path, ctx, name) -> {
            System.out.println("创建节点: " + path);
            System.out.println(ctx);
        }, "{'create':'success'}");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkNodeOperator zkNodeOperator = new ZkNodeOperator(path);
        zkNodeOperator.createNode();
        //Stat stat = zkNodeOperator.zooKeeper.setData("/test1", "xye".getBytes(), 2);
        //  System.out.println(stat.getVersion());
        zkNodeOperator.zooKeeper.delete("/test", 0);
        Stat stat=new Stat();
        byte[] data = zkNodeOperator.zooKeeper.getData("/test1", true, stat);
        Thread.sleep(2000);
        System.out.println(data);
        System.out.println(stat);
    }
}

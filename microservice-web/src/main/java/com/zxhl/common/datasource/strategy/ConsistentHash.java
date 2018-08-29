package com.zxhl.common.datasource.strategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash  http://www.cnblogs.com/hapjin/p/4737207.html
 * 命中率：一致性哈希与取模哈希算法在服务器减少时对命中率的影响
 *          当memcached节点增多时,一致性哈希的命令率为(N-1)/N, 而取模算法则为1/N,
 *          显然,当节点越多,一致性哈希的对缓存的稳定越好（出错率越低）
 */
public class ConsistentHash<T> {
    private final HashFunction hashFunction;
    private final int numberOfReplicas;// 节点的复制因子,实际节点个数 * numberOfReplicas =
    // 虚拟节点个数
    private final SortedMap<Long, T> circle = new TreeMap<Long, T>();// 存储虚拟节点的hash值到真实节点的映射

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
                          Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes)
            add(node);
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            // 对于一个实际机器节点 node, 对应 numberOfReplicas 个虚拟节点
            /*
             * 不同的虚拟节点(i不同)有不同的hash值,但都对应同一个实际机器node
             * 虚拟node一般是均衡分布在环上的,数据存储在顺时针方向的虚拟node上
             */
            circle.put(hashFunction.hash(node.toString() + i), node);
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            circle.remove(hashFunction.hash(node.toString() + i));
    }

    /*
     * 获得一个最近的顺时针节点,根据给定的key 取Hash
     * 然后再取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * 再从实际节点中取得 数据
     */
    public T get(Object key) {
        if (circle.isEmpty())
            return null;
        long hash = hashFunction.hash(String.valueOf(key));// node 用String来表示,获得node在哈希环中的hashCode
        if (!circle.containsKey(hash)) {//数据映射在两台虚拟机器所在环之间,就需要按顺时针方向寻找机器
            SortedMap<Long, T> tailMap = circle.tailMap(hash);//获取一个子集。其所有对象的 key 的值大于等于 fromKey
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public long getSize() {
        return circle.size();
    }

    /*
    MD5算法本身就有一定散列特性，--》保证平均
 * 实现一致性哈希算法中使用的哈希函数,使用MD5算法来保证一致性哈希的平衡性
 */
    public  static class HashFunction {
        private MessageDigest md5 = null;

        public long hash(String key) {
            if (md5 == null) {
                try {
                    md5 = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    throw new IllegalStateException("no md5 algrithm found");
                }
            }

            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            //具体的哈希函数实现细节--每个字节 & 0xFF 再移位
            long result = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF));
            return result & 0xffffffffL;
        }
    }
}

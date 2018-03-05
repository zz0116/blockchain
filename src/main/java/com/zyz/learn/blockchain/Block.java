package com.zyz.learn.blockchain;

import com.zyz.learn.blockchain.utils.ByteUtils;
import com.zyz.learn.blockchain.utils.DigestUtils;
import com.zyz.learn.blockchain.utils.StringUtils;

import java.math.BigInteger;
import java.time.Instant;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/1
 */
public class Block {

    private String hash;// 区块hash值
    private String previousHash;// 前一个区块的hash值
    private String data;// 区块数据
    private long timeStamp;

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Block() {
    }

    public Block(String hash, String previousHash, String data, long timeStamp) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    /**
     * <p> 创建新区块 </p>
     *
     * @param previousHash
     * @param data
     * @return
     */
    public static Block newBlock(String previousHash, String data) {
        Block block = new Block("", previousHash, data, Instant.now().getEpochSecond());
        block.setHash();
        return block;
    }

    /**
     * 计算区块Hash
     * <p>
     *     注意：在准备区块数据时，一定要从原始数据类型转化为byte[]，
     *     不能直接从字符串进行转换
     * </p>
     */
    private void setHash() {
        byte[] preBlockHashBytes = {};
        if (StringUtils.isNoneBlank(this.getPreviousHash())) {
            preBlockHashBytes = new BigInteger(this.getPreviousHash(), 16).toByteArray();
        }

        byte[] headers = ByteUtils.merge(
                preBlockHashBytes,
                this.getData().getBytes(),
                ByteUtils.toBytes(this.getTimeStamp())
        );

        this.setHash(DigestUtils.sha256Hex(headers));
    }

    /**
     * <p> 创建创世区块 </p>
     *
     * @return 创世区块
     */
    public static Block newGenesisBlock() {
        return Block.newBlock("", "Genesis Block");
    }

    public long getNonce() {
        return 0;
    }
}

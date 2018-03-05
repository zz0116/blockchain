package com.zyz.learn.blockchain;

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
    private long nonce;

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

    public long getNonce() {
        return nonce;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public Block(String hash, String previousHash, String data, long timeStamp, long nonce) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }

    /**
     * <p> 创建新区块 </p>
     *
     * @param previousHash
     * @param data
     * @return
     */
    public static Block newBlock(String previousHash, String data) {
        Block block = new Block("", previousHash, data, Instant.now().getEpochSecond(), 0);
        ProofOfWork pow = ProofOfWork.newProofOfWork(block); // 创建Pow算法对象
        PowResult powResult = pow.run(); // 执行Pow算法
        block.setHash(powResult.getHash());
        block.setNonce(powResult.getNonce());
        return block;
    }

    /**
     * <p> 创建创世区块 </p>
     *
     * @return 创世区块
     */
    public static Block newGenesisBlock() {
        return Block.newBlock("", "Genesis Block");
    }
}

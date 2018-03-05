package com.zyz.learn.blockchain;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/4
 */
public class PowResult {
    private long nonce;
    private String hash;

    public PowResult(long nonce, String hash) {
        this.nonce = nonce;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public long getNonce() {
        return nonce;
    }
}

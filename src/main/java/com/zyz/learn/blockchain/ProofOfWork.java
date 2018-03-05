package com.zyz.learn.blockchain;

import com.zyz.learn.blockchain.utils.ByteUtils;
import com.zyz.learn.blockchain.utils.DigestUtils;
import com.zyz.learn.blockchain.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * 工作量证明
 *
 * @author ZhangYuanzhuo
 * @since 2018/3/3
 */
public class ProofOfWork {

    /**
     * 难度目标位
     */
    public static final int TARGET_BITS = 20;

    /**
     * 区块
     */
    private Block block;

    /**
     * 难度目标值
     */
    private BigInteger target;

    public Block getBlock() {
        return block;
    }

    public BigInteger getTarget() {
        return target;
    }

    private ProofOfWork(Block block, BigInteger target) {
        this.block = block;
        this.target = target;
    }

    /**
     * 创建新的工作量证明，设定难度目标值
     * <p>
     *     对1进行移位运算，将1向左移动(256 - TARGET_BITS)位，得到难度目标值
     * </p>
     *
     * @param block
     * @return
     */
    @NotNull
    public static ProofOfWork newProofOfWork(Block block) {
        BigInteger targetValue = BigInteger.valueOf(1).shiftLeft((256 - TARGET_BITS));
        return new ProofOfWork(block, targetValue);
    }

    /**
     * 准备数据
     * <p>
     *     注意：在准备区块链数据时，一定要从原始数据类型转化为byte[]
     * </p>
     *
     * @param nonce 用于工作量证明算法的计数器
     * @return
     */
    private byte[] prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(this.getBlock().getPreviousHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPreviousHash(), 16).toByteArray();
        }

        return ByteUtils.merge(
                prevBlockHashBytes,
                this.getBlock().getData().getBytes(), // 区块中的交易数据
                ByteUtils.toBytes(this.getBlock().getTimeStamp()),
                ByteUtils.toBytes(TARGET_BITS),
                ByteUtils.toBytes(nonce)
        );
    }

    /**
     * 运行工作量证明，开始挖矿，找到小于难度目标值的Hash
     *
     * @return
     */
    public PowResult run() {
        long nonce = 0;
        String shaHex = "";
        System.out.printf("Mining the block containing: %s \n", this.getBlock().getData());

        long startTime = System.currentTimeMillis();
        while (nonce < Long.MAX_VALUE) {
            // 准备数据
            byte[] data = this.prepareData(nonce);
            // 进行sha256计算
            shaHex = DigestUtils.sha256Hex(data);
            // 转化为BigInteger类型与target比较
            if (new BigInteger(shaHex, 16).compareTo(this.target) == -1) {
                System.out.printf("Elapsed Time: %s seconds \n",
                        (float) (System.currentTimeMillis() - startTime) / 1000);
                System.out.printf("correct hash Hex: %s \n\n", shaHex);
                break;
            } else {
                nonce++;
            }
        }
        return new PowResult(nonce, shaHex);
    }

    /**
     * 验证区块是否有效
     *
     * @return
     */
    public boolean validate() {
        byte[] data = this.prepareData(this.getBlock().getNonce());
        return new BigInteger(DigestUtils.sha256Hex(data), 16).compareTo(this.target) == -1;
    }
}

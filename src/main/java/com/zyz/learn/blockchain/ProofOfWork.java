package com.zyz.learn.blockchain;

import com.zyz.learn.blockchain.utils.ByteUtils;
import com.zyz.learn.blockchain.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Arrays;

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
    private String prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(this.getBlock().getPreviousHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPreviousHash(), 16).toByteArray();
        }

        return Arrays.toString(
                ByteUtils.merge(
                        prevBlockHashBytes,
                        this.getBlock().getData().getBytes(), // 区块中的交易数据
                        ByteUtils.toBytes(this.getBlock().getTimeStamp()),
                        ByteUtils.toBytes(TARGET_BITS),
                        ByteUtils.toBytes(nonce)
        ));
    }
}

package com.zyz.learn.blockchain;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> 区块链 </p>
 *
 * @author ZhangYuanzhuo
 * @since 2018/3/2
 */
public class Blockchain {

    private List<Block> blockList;

    public List<Block> getBlockList() {
        return blockList;
    }

    public Blockchain(List<Block> blockList) {
        this.blockList = blockList;
    }

    /**
     * <p>  添加区块 </p>
     *
     * @param data
     */
    public void addBlock(String data) {
        Block previousBlock = blockList.get(blockList.size() - 1);
        this.addBlock(Block.newBlock(previousBlock.getHash(), data));
    }

    /**
     * <p> 添加区块 </p>
     *
     * @param block
     */
    private void addBlock(Block block) {
        this.blockList.add(block);
    }

    /**
     * <p> 创建区块链 </p>
     *
     * @return 仅含创世区块的区块链
     */
    public static Blockchain newBlockchain() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(Block.newGenesisBlock());
        return new Blockchain(blocks);
    }
}

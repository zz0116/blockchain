package com.zyz.learn.blockchain;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/2
 */
public class BlockchainTest {

    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.newBlockchain();
        blockchain.addBlock("Send 1 BTC to Ivan");
        blockchain.addBlock("Send 2 more BTC to Ivan");

        for (Block block : blockchain.getBlockList()) {
            System.out.println("Prev.hash: " + block.getPreviousHash());
            System.out.println("Data: " + block.getData());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Nonce: " + block.getNonce());

            ProofOfWork pow = ProofOfWork.newProofOfWork(block);
            System.out.println("Pow valid: " + pow.validate() + "\n");
        }
    }
}
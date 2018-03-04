package com.zyz.learn.blockchain.utils;

import org.jetbrains.annotations.Contract;

import java.nio.ByteBuffer;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/2
 */
public final class ByteUtils {

    public static byte[] toBytes(ByteBuffer bb) {
        int length = bb.limit();
        byte [] result = new byte[length];
        System.arraycopy(bb.array(), bb.arrayOffset(), result, 0, length);
        return result;
    }

    public static byte[] toBytes(long val) {
        byte [] b = new byte[8];
        for(int i=7;i>0;i--) {
            b[i] = (byte)(val);
            val >>>= 8;
        }
        b[0] = (byte)(val);
        return b;
    }

//    public static byte[] merge(byte[] preBlockHashBytes, byte[] dataBytes, byte[] timeStampBytes) {
//        assert preBlockHashBytes != null;
//        assert timeStampBytes != null;
//
//        byte[] result = new byte[preBlockHashBytes.length + dataBytes.length + timeStampBytes.length];
//        System.arraycopy(preBlockHashBytes, 0, result, 0, preBlockHashBytes.length);
//        System.arraycopy(dataBytes, 0, result, preBlockHashBytes.length, dataBytes.length);
//        System.arraycopy(timeStampBytes, 0, result, dataBytes.length, timeStampBytes.length);
//
//        return result;
//    }

    @Contract("null, _ -> fail")
    public static byte[] merge(byte[] firstArray, byte[]... additionalArrays) {
        assert firstArray != null;
        assert additionalArrays != null;

        int capacity = firstArray.length;
        for (byte[] additionalArray : additionalArrays) {
            capacity += additionalArray.length;
        }

        byte[] result = new byte[capacity];
        System.arraycopy(firstArray, 0, result, 0, firstArray.length);
        int cursor = firstArray.length;
        for (byte[] additionalArray : additionalArrays) {
            System.arraycopy(additionalArray, 0, result, cursor, additionalArray.length);
            cursor += additionalArray.length;
        }

        return result;
    }
}

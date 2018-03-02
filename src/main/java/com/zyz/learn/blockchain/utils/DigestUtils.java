package com.zyz.learn.blockchain.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhangYuanzhuo
 * @since 2018/3/2
 */
public final class DigestUtils {

    public static String sha256Hex(final byte[] data) {
        return Hex.encodeHexString(sha256(data));
    }

    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    public static MessageDigest getSha256Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_256);
    }

    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

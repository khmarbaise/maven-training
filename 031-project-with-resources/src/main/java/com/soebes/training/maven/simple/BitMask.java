package com.soebes.training.maven.simple;

import org.apache.log4j.Logger;



/**
 * XXX.
 *
 * @author Karl Heinz Marbaise
 *
 */
public class BitMask {

    private static Logger LOGGER = Logger.getLogger(BitMask.class);

    private long bitMaskValue;

    public long getBitMaskValue() {
        return bitMaskValue;
    }

    public BitMask() {
        bitMaskValue = 0;
    }

    public BitMask(long currentValue) {
        this.bitMaskValue = currentValue;
    }

    public void setBit(int bit) {
        long bitMask = Long.rotateLeft(1, bit);
        bitMaskValue |= bitMask;
    }

    public void unsetBit(int bit) {
        long bitMask = Long.rotateLeft(1, bit);
        bitMaskValue ^= bitMask;
    }

    public boolean isBitSet(int bit) {
        long bitMask = Long.rotateLeft(1, bit);
        long result = this.bitMaskValue & bitMask;
        if (result != 0) {
            return true;
        }
        return false;
    }
}

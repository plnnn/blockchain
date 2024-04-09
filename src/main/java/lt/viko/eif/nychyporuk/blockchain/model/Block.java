package lt.viko.eif.nychyporuk.blockchain.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Represents a block in the blockchain.
 * Each block contains its own hash, the previous block's hash, and data.
 */
public class Block {
    private int nonce;
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;

    /**
     * Constructor for Block.
     *
     * @param data        The data contained within the block.
     * @param previousHash The hash of the previous block in the chain.
     * @param timeStamp    The timestamp of when the block was created.
     */
    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    /**
     * Calculates the hash of the block using SHA-256.
     *
     * @return A string representing the hash of the block.
     */
    public String calculateBlockHash() {
        String dataToHash = previousHash + timeStamp + nonce + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    /**
     * Performs the proof-of-work algorithm to mine the block.
     *
     * @param prefix The number of leading zeros required in the block hash.
     * @return The computed hash of the block that satisfies the mining condition.
     */
    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    // Standard getters and setters follow

    public int getNonce() {
        return nonce;
    }

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

    /**
     * Sets the data of the block.
     *
     * @param data The data to set.
     */
    public void setData(String data) {
        this.data = data;
    }
}



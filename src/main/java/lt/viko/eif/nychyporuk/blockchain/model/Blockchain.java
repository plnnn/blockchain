package lt.viko.eif.nychyporuk.blockchain.model;

import java.util.ArrayList;

/**
 * Represents a simple blockchain, which is a sequence of blocks that are linked
 * together by hashes. This class implements the functionality to manage the
 * blockchain, including adding blocks and verifying the chain's integrity.
 */
public class Blockchain {
    private int prefix; // The number of leading zeros required in the proof of work.
    private String prefixString; // The string representation of the prefix used in the proof of work.
    private ArrayList<Block> blockchain; // The list of blocks in the chain.

    /**
     * Constructs a new blockchain with a specified difficulty for the proof of work.
     *
     * @param difficulty The difficulty level for the proof of work,
     *                   specified as the number of leading zeros required in the hash.
     */
    public Blockchain(int difficulty) {
        this.blockchain = new ArrayList<>();
        this.prefix = difficulty;
        this.prefixString = new String(new char[prefix]).replace('\0', '0');
        blockchain.add(generateGenesisBlock());
    }

    /**
     * Generates the genesis block (the first block in the blockchain).
     * The genesis block does not have a previous hash.
     *
     * @return The generated genesis block.
     */
    private Block generateGenesisBlock() {
        Block genesisBlock = new Block("Genesis Block", "0", System.currentTimeMillis());
        genesisBlock.mineBlock(prefix);
        return genesisBlock;
    }

    /**
     * Adds a new block to the blockchain after mining it to satisfy the
     * proof of work condition based on the difficulty level.
     *
     * @param newBlock The block to be added to the chain.
     */
    public void addBlock(Block newBlock) {
        newBlock.mineBlock(prefix);
        this.blockchain.add(newBlock);
    }

    /**
     * Checks the integrity of the blockchain, ensuring that all blocks are valid and the chain
     * has not been tampered with.
     *
     * @return true if the blockchain is valid, false otherwise.
     */
    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); ++i) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateBlockHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            if (!currentBlock.getHash().substring(0, prefix).equals(prefixString)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the details of each block in the blockchain to the console.
     */
    public void displayChain() {
        for (Block block : blockchain) {
            System.out.printf("Block: \nData: %s\nHash: %s\nPrevious Hash: %s\nNonce: %d\n",
                    block.getData(), block.getHash(), block.getPreviousHash(), block.getNonce());
            System.out.println();
        }
    }

    /**
     * Gets the list of blocks in the blockchain.
     *
     * @return The list of blocks in the blockchain.
     */
    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    /**
     * Gets the difficulty level for the proof of work.
     *
     * @return The difficulty level as the number of leading zeros required in the hash.
     */
    public int getPrefix() {
        return prefix;
    }
}

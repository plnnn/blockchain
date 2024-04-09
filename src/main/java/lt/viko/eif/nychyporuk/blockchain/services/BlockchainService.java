package lt.viko.eif.nychyporuk.blockchain.services;

import lt.viko.eif.nychyporuk.blockchain.model.Block;
import lt.viko.eif.nychyporuk.blockchain.model.Blockchain;
import org.springframework.stereotype.Service;

/**
 * Service class for managing blockchain operations.
 * This class provides methods to interact with the blockchain,
 * such as adding new blocks and checking the blockchain's validity.
 */
@Service
public class BlockchainService {
    private final Blockchain blockchain;

    /**
     * Constructor that initializes the blockchain with a predefined difficulty level.
     * The difficulty level determines the complexity of the proof of work required
     * to mine a new block.
     */
    public BlockchainService() {
        this.blockchain = new Blockchain(4);
    }

    /**
     * Retrieves the current blockchain.
     *
     * @return The current state of the blockchain.
     */
    public Blockchain getBlockchain() {
        return blockchain;
    }

    /**
     * Adds a new block to the blockchain with the given data.
     *
     * @param data The data for the new block.
     * @return The newly added block after it has been mined and added to the chain.
     */
    public Block addBlock(String data) {
        Block newBlock = new Block(data, blockchain.getBlockchain().getLast().getHash(), System.currentTimeMillis());
        blockchain.addBlock(newBlock);
        return newBlock;
    }

    /**
     * Validates the integrity of the blockchain by ensuring that all blocks are properly
     * linked and the data has not been tampered with.
     *
     * @return True if the blockchain is valid, false otherwise.
     */
    public boolean isBlockchainValid() {
        return blockchain.isChainValid();
    }
}

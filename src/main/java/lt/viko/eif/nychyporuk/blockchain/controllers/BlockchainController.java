package lt.viko.eif.nychyporuk.blockchain.controllers;

import lt.viko.eif.nychyporuk.blockchain.model.Block;
import lt.viko.eif.nychyporuk.blockchain.model.Blockchain;
import lt.viko.eif.nychyporuk.blockchain.services.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The BlockchainController class provides RESTful APIs to interact with the blockchain.
 * It enables clients to get the current state of the blockchain, add new blocks, and
 * verify the blockchain's integrity.
 */
@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    private final BlockchainService blockchainService;

    /**
     * Autowires (injects) the BlockchainService into this controller.
     *
     * @param blockchainService The service responsible for blockchain operations.
     */
    @Autowired
    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    /**
     * Retrieves the current state of the blockchain.
     *
     * @return An instance of Blockchain representing the entire chain.
     */
    @GetMapping
    public Blockchain getBlockchain() {
        return blockchainService.getBlockchain();
    }

    /**
     * Adds a new block to the blockchain with the provided data.
     * The data is contained in the request body.
     *
     * @param data The data to be included in the new block.
     * @return The newly added Block object.
     */
    @PostMapping("/addBlock")
    public Block addBlock(@RequestBody String data) {
        return blockchainService.addBlock(data);
    }

    /**
     * Validates the integrity of the entire blockchain.
     *
     * @return true if the blockchain is valid, false otherwise.
     */
    @GetMapping("/isValid")
    public boolean isBlockchainValid() {
        return blockchainService.isBlockchainValid();
    }
}

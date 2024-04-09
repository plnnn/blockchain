fetchBlockchain();

function setLoadingState(isLoading) {
    const addButton = document.querySelector('button[onclick="addBlock()"]');
    const loadingIndicator = document.getElementById('loadingIndicator');
    addButton.disabled = isLoading;
    if (isLoading) {
        loadingIndicator.style.display = 'inline';
        addButton.textContent = 'Adding...';
    } else {
        loadingIndicator.style.display = 'none';
        addButton.textContent = 'Add Block';
    }
}

function fetchBlockchain() {
    fetch('/api/blockchain')
        .then(response => response.json())
        .then(data => {
            const blockchainElement = document.getElementById('blockchain');
            blockchainElement.innerHTML = '';

            const blocks = data.blockchain || data;

            blocks.forEach((block, index) => {
                const blockCard = document.createElement('div');
                blockCard.classList.add('card', 'mb-4');

                blockCard.innerHTML = `
                    <div class="card-header">
                        Block ${index + 1}
                    </div>
                    <div class="card-body">
                        <p class="card-text"><strong>Nonce:</strong> ${block.nonce}</p>
                        <p class="card-text"><strong>Hash:</strong> ${block.hash}</p>
                        <p class="card-text"><strong>Previous Hash:</strong> ${block.previousHash}</p>
                        <p class="card-text"><strong>Data:</strong> ${block.data}</p>
                        <p class="card-text"><strong>Timestamp:</strong> ${new Date(block.timeStamp).toLocaleString()}</p>
                    </div>
                `;
                blockchainElement.appendChild(blockCard);
            });
        })
        .catch(error => console.error('Failed to fetch blockchain data:', error));
}

function addBlock() {
    const dataInput = document.getElementById('dataInput');
    const data = dataInput.value;
    if (!data.trim()) {
        alert('Please enter some data for the block.');
        return;
    }
    setLoadingState(true);
    fetch('/api/blockchain/addBlock', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: data,
    })
        .then(() => {
            dataInput.value = '';
            fetchBlockchain();
        })
        .catch(error => {
            console.error('Error adding block:', error);
            alert('Failed to add block.');
        })
        .finally(() => setLoadingState(false));
}

function validateBlockchain() {
    fetch('/api/blockchain/isValid')
        .then(response => response.json())
        .then(isValid => alert(`Blockchain is valid: ${isValid}`));
}

fetchBlockchain();

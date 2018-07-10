# Ethereum Smart Contract Voting System

## How to build

### 1. Install web3j command line tool
https://web3j.readthedocs.io/en/latest/command_line.html
<br><br>

### 2. Create web3j wrapper for solidity code
https://web3j.readthedocs.io/en/latest/smart_contracts.html#solidity-smart-contract-wrappers
<br><br>

### 3. Deploy the smart contract written by the solidity code 
<br><br>

### 4. Import the smart contract in the android application
<br>

Example
```
public class MainActivity extends AppCompatActivity {

    private final String msContractAddr = "0x0101010101010101010101010101010101010101";
    private final String msPrikey = "0x0000000000000000000000000000000000000000000000000000000000000000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Credentials credentials = Credentials.create(msPrikey);
        Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/your-api-key"));

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
				// call your contract here

                        } catch (Exception e) {
                            e.toString();
                        }
                    }
                }.start();
            }
        });

    }
}
```

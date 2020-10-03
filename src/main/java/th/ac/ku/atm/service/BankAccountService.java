package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class BankAccountService {
    private List<BankAccount> bankAccounts;
    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://localhost:8091/api/bankaccount/customer/" +
                customerId;
       ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);

    }

    @PostConstruct
    public void BankAccountService(){
        bankAccounts = new ArrayList<>();
    }
    public void CreateAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return new ArrayList<>(this.bankAccounts);
    }
}

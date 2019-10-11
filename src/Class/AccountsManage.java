package Class;

import java.util.HashMap;

public class AccountsManage {
    private HashMap<String, Account> accountHashMap = new HashMap<String, Account>();

    private AccountsManage(){
        createAccount("test", "1234", "erf@h.com", "sdfsdf", "sdfsdf");
    }

    private static AccountsManage accountsManageInstance;
    public static synchronized AccountsManage getInstance(){
        if(accountsManageInstance == null){
            accountsManageInstance = new AccountsManage();
        }
        return accountsManageInstance;
    }

    public boolean createAccount(String username, String password, String email, String firstName, String lastName){
        if(haveAccountAvailable(username)) return false;
        Account account = new Account(username, password, email, firstName, lastName);
        accountHashMap.put(username, account);
        return true;
    }

    public boolean loginAccount(String username, String password){
        if(haveAccountAvailable(username)){
            if(accountHashMap.get(username).checkPasswordCorrect(password)) return true;
        }
        return false;
    }

    public boolean haveAccountAvailable(String username){
        return accountHashMap.keySet().contains(username);
    }
}

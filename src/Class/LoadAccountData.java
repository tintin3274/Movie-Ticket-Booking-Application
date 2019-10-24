package Class;

import java.io.*;

public class LoadAccountData {
    private AccountsManage accountsManage = AccountsManage.getInstance();

    public void readAccountData(){
        String line;
        String[] data;
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/AccountData.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null){
                data = line.split(",");
                if(!accountsManage.haveAccountAvailable(data[0])){
                    accountsManage.createAccount(data[0], data[1], data[2], data[3], data[4]);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

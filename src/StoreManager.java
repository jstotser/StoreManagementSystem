//package COMP3700;

import javax.swing.*;

public class StoreManager {
    public static final String DBMS_SQ_LITE = "SQLite";
    public static final String DB_FILE = "C:/Users/jacob/OneDrive/Documents/Fall 2019 Semester/COMP 3700/store.db";

    IDataAdapter adapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {

            String dbfile = DB_FILE;
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                dbfile = fc.getSelectedFile().getAbsolutePath();

            instance = new StoreManager(DBMS_SQ_LITE, dbfile);
        }
        return instance;
    }

    private StoreManager(String dbms, String dbfile) {
        if (dbms.equals("Oracle"))
            adapter = new OracleDataAdapter();
        else
        if (dbms.equals("SQLite"))
            adapter = new SQLiteDataAdapter();

        adapter.connect(dbfile);
        //ProductModel product = adapter.loadProduct(3);

        //System.out.println("Loaded product: " + product);

        //CustomerModel customer = adapter.loadCustomer(3);

        //System.out.println("Loaded customer: " + customer);

    }

    public IDataAdapter getDataAdapter() {
        return adapter;
    }

    public void setDataAdapter(IDataAdapter a) {
        adapter = a;
    }


    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Hello class!");
        StoreManager.getInstance().run();
    }

}
import domein.*;

import java.sql.*;

import java.sql.DriverManager;

public class Main {

    private static Connection connection;


    public static void main(String[] args) throws SQLException {

        getConnection();

        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
        AdresDAOPsql adrdao = new AdresDAOPsql(connection);
        OVChipkaartDAOPsql ovrdao = new OVChipkaartDAOPsql(connection);
        ProductDAOPsql prdao = new ProductDAOPsql(connection);
        prdao.setPrRdao(prdao);
        ovrdao.setOvRdao(ovrdao);
        rdao.setAdrdao(adrdao);
        adrdao.setRdao(rdao);

    }

    private static void getConnection() {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Shahabi1999!";

        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException sqlException) {

            System.err.println(sqlException.getMessage());
        }
    }
    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
}
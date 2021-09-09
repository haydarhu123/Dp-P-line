import p2.Reiziger;
import p2.ReizigerDAO;
import p2.ReizigerDAOPsql;


import java.sql.*;
import java.util.List;
import java.sql.DriverManager;

public class Main {

    private static Connection connection;


    public static void main(String[] args) throws SQLException {

        getConnection();

        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);


        testReizigerDAO(rdao);
        //testAdresDAO(adrdao);
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
     * <p>
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        // rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


        sietske.setVoorletters("S");
        sietske.setTussenvoegsel("AL");
        sietske.setAchternaam("Boers");
        sietske.setGeboortedatum(java.sql.Date.valueOf(gbdatum));


        rdao.update(sietske);
        System.out.println(sietske);

        rdao.delete(sietske);

        rdao.findById(3);

        rdao.findByGbdatum("1998-08-11");

        rdao.findAll();
    }

}
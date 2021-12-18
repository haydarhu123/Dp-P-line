import domein.*;
import dao.ReizigerDAO;
import dao.AdresDAO;


import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.sql.DriverManager;

public class Main {

    private static Connection connection;


    public static void main(String[] args) throws SQLException {

        getConnection();

        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
        AdresDAOPsql adrdao = new AdresDAOPsql(connection);
        OVChipkaartDAOPsql ovrdao = new OVChipkaartDAOPsql(connection);
        ovrdao.setOvRdao(ovrdao);
        rdao.setAdrdao(adrdao);
        adrdao.setRdao(rdao);
        //testReizigerDAO(rdao);
        //testAdresDAO(adrdao, rdao);
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
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
//    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Haal alle reizigers op uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r : reizigers) {
//            System.out.println(r);
//        }
//        System.out.println();
//        String gbdatum = "1981-03-14";
//        //Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum),6);
//        //Adres adres1 = new Adres(6, "3564TA", "40", "sint maartendreef", "Utrecht", sietske);
//        // Maak een nieuwe reiziger aan en persisteer deze in de database
//       // String gbdatum = "1981-03-14";
//       // Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum),6);
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        // rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
////
////
////        sietske.setVoorletters("S");
////        sietske.setTussenvoegsel("AL");
////        sietske.setAchternaam("Yahya");
////        sietske.setGeboortedatum(java.sql.Date.valueOf(gbdatum));
////
////
////        rdao.update(sietske);
////        System.out.println(sietske);
////
////        rdao.delete(sietske);
//
//        rdao.findById(3);
//
//        rdao.findByGbdatum("1998-08-11");
//
//        rdao.findAll();
//    }
//
//    private static void testAdresDAO(AdresDAO adrdao, ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
////
////        // Haal alle adressen op uit de database
////        List<Adres> adres = adrdao.findAll();
////        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
////        for (Adres adr : adres) {
////            System.out.println(adr);
////        }
////        System.out.println();
//
////        // Maak een nieuwe reiziger aan en persisteer deze in de database
//        String gbdatum = "1981-03-14";
////        String gbdatum1 = "1999-04-19";
//        //Reiziger sietske = new Reiziger(6, "S", "", "Yahya", java.sql.Date.valueOf(gbdatum),6);
////        Reiziger haydar = new Reiziger(7, "H", "AL", "Yahya", java.sql.Date.valueOf(gbdatum1));
////        rdao.findById(6);
//        //rdao.save(sietske);
////        rdao.save(haydar);
////
//
//        // Maak een nieuwe adres aan en persisteer deze in de database
//        //Adres adres1 = new Adres(6, "3564TA", "40", "sint maartendreef", "Utrecht", sietske);
//        //System.out.println(adres.size());
//        ///System.out.println(sietske);
//        //adres1.setWoonplaats("almere");
//        //adres1.setAdres("kanariestraat");
//        //adres1.setHuisnummer("122");
//        //adres1.setPostcode("3597XX");
//        //Adres adres2 = new Adres(7, "3564TA", "40", "sint kanariestraat", "Utrecht", haydar);
//        //adres2.setPostcode("3597XX");
//        //adrdao.save(adres1);
//        //adrdao.save(adres2);
//        //adrdao.findAll();
//        //adrdao.findByReiziger(sietske);
//
//        //adrdao.update(adres2);
//
//
//    }
}
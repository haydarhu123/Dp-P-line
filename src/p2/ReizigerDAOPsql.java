package p2;

import p3.AdresDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {

    private Connection conn;
    private AdresDAO adrdao;

    public ReizigerDAOPsql(Connection connection) {
        this.conn = connection;

    }

    public void setAdrdao(AdresDAO adrdao) {
        this.adrdao = adrdao;
    }


    @Override
    public boolean save(Reiziger reiziger) {
        try {
            String query = "INSERT INTO reiziger (reiziger_id , voorletters , tussenvoegsel , achternaam, geboortedatum) VALUES (?,?,?,?,?)";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            int id = reiziger.getId();
            String voorletters = reiziger.getVoorletters();
            String tussenvoegsel = reiziger.getTussenvoegsel();
            String achternaam = reiziger.getAchternaam();
            java.sql.Date geboortedatum = reiziger.getGeboortedatum();

            prepstmt.setInt(1, reiziger.getId());
            prepstmt.setString(2, reiziger.getVoorletters());
            prepstmt.setString(3, reiziger.getTussenvoegsel());
            prepstmt.setString(4, reiziger.getAchternaam());
            prepstmt.setDate(5, reiziger.getGeboortedatum());

            prepstmt.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            String query = "DELETE FROM reiziger WHERE reiziger_id = ?";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            prepstmt.setInt(1, reiziger.getId());

            prepstmt.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel =?, achternaam=?, geboortedatum=? WHERE reiziger_id=?";

            PreparedStatement prepstmt = conn.prepareStatement(query);

//            int id = reiziger.getId();
//            String voorletters = reiziger.getVoorletters();
//            String tussenvoegsel = reiziger.getTussenvoegsel();
//            String achternaam = reiziger.getAchternaam();
//            java.sql.Date geboortedatum = reiziger.getGeboortedatum();


            prepstmt.setString(1, reiziger.getVoorletters());
            prepstmt.setString(2, reiziger.getTussenvoegsel());
            prepstmt.setString(3, reiziger.getAchternaam());
            prepstmt.setDate(4, reiziger.getGeboortedatum());
            prepstmt.setInt(5, reiziger.getId());

            prepstmt.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }


    @Override
    public Reiziger findById(int id) {

        try {
            PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id=?");
            prepstmt.setInt(1, id);

            ResultSet rsltst = prepstmt.executeQuery();
            while (rsltst.next()) {
                int reiziger_id = rsltst.getInt("reiziger_id");
                String voorletters = rsltst.getString("voorletters");
                String tussenvoegsel = rsltst.getString("tussenvoegsel");
                String achternaam = rsltst.getString("achternaam");
                Date geboortedatum = Date.valueOf(String.valueOf(rsltst.getDate("geboortedatum")));

                System.out.println(reiziger_id);
                System.out.println(voorletters);
                System.out.println(tussenvoegsel);
                System.out.println(achternaam);
                System.out.println(geboortedatum);

                return new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);
            }
            rsltst.close();
            prepstmt.close();

        } catch (SQLException sqlException) {
            System.err.println("De gegevens van deze ID kunnen niet opgehaald worden, probeer opnieuw!" + sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum=?");
            prepstmt.setDate(1, Date.valueOf(datum));

            ResultSet rsltst = prepstmt.executeQuery();
            ArrayList<Reiziger> aantalreizigers = new ArrayList<Reiziger>();
            while (rsltst.next()) {
                int reiziger_id = rsltst.getInt("reiziger_id");
                String voorletters = rsltst.getString("voorletters");
                String tussenvoegsel = rsltst.getString("tussenvoegsel");
                String achternaam = rsltst.getString("achternaam");
                Date geboortedatum = Date.valueOf(String.valueOf(rsltst.getDate("geboortedatum")));

                System.out.println(reiziger_id);
                System.out.println(voorletters);
                System.out.println(tussenvoegsel);
                System.out.println(achternaam);
                System.out.println(geboortedatum);
            }
            return aantalreizigers;
        } catch (SQLException sqlException) {
            System.err.println("De gegevens van deze geboortedatum kunnen niet opgehaald worden, probeer het later opnieuw!");
        }
        return null;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsltst = stmt.executeQuery("SELECT* FROM reiziger");

        ArrayList<Reiziger> aantalreizigers = new ArrayList<Reiziger>();

        while (rsltst.next()) {

            int reiziger_id = rsltst.getInt("reiziger_id");
            String voorletters = rsltst.getString("voorletters");
            String tussenvoegsel = rsltst.getString("tussenvoegsel");
            String achternaam = rsltst.getString("achternaam");
            Date geboortedatum = rsltst.getDate("geboortedatum");

            Reiziger reiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);
            aantalreizigers.add(reiziger);
        }
        return aantalreizigers;
    }
}

package domein;

import dao.OVChipkaartDAO;
import dao.ReizigerDAO;
import dao.AdresDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {

    private Connection conn;
    private AdresDAO adrdao;
    private OVChipkaartDAO ovrdao;

    public ReizigerDAOPsql(Connection connection) {
        this.conn = connection;

    }

    public void setAdrdao(AdresDAO adrdao) {
        this.adrdao = adrdao;
    }


    @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement prepstmt = conn.prepareStatement("INSERT INTO reiziger (reiziger_id , voorletters , tussenvoegsel , achternaam, geboortedatum, adres_id, kaart_nummer) VALUES (?,?,?,?,?,?,?)");
            prepstmt.setInt(1, reiziger.getId());
            prepstmt.setString(2, reiziger.getVoorletters());
            prepstmt.setString(3, reiziger.getTussenvoegsel());
            prepstmt.setString(4, reiziger.getAchternaam());
            prepstmt.setDate(5, reiziger.getGeboortedatum());
            prepstmt.setInt(6, reiziger.getAdres().getId());
            prepstmt.setArray(7, (Array) reiziger.getOvChipkaart());

            prepstmt.executeUpdate();
            prepstmt.close();

        } catch (SQLException sqlException) {
            System.out.println("De gegevens konden niet opgeslagen worden.");

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
            prepstmt.close();

        } catch (SQLException sqlException) {
            System.out.println("De gegevens konden niet verwijderd worden.");

        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel =?, achternaam=?, geboortedatum=? WHERE reiziger_id=?";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            prepstmt.setString(1, reiziger.getVoorletters());
            prepstmt.setString(2, reiziger.getTussenvoegsel());
            prepstmt.setString(3, reiziger.getAchternaam());
            prepstmt.setDate(4, reiziger.getGeboortedatum());
            prepstmt.setInt(5, reiziger.getId());

            prepstmt.executeUpdate();
            prepstmt.close();

        } catch (SQLException sqlException) {
            System.out.println("De gegevens konden niet ge-update worden.");

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
                Adres adres_id = (Adres) rsltst.getRowId("adres_id");
                int kaartnummer = rsltst.getInt("kaart_nummer");

                System.out.println(reiziger_id);
                System.out.println(voorletters);
                System.out.println(tussenvoegsel);
                System.out.println(achternaam);
                System.out.println(geboortedatum);
                System.out.println(adres_id);

                return new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum, adres_id, (List<OVChipkaart>) ovrdao.findByKaartnummer(kaartnummer));
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
            rsltst.close();
            prepstmt.close();
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
            Adres adres_id = (Adres) rsltst.getRowId("adres_id");
            int kaartnummer = rsltst.getInt("kaart_nummer");

            Reiziger reiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum, adres_id, (List<OVChipkaart>) ovrdao.findByKaartnummer(kaartnummer));
            aantalreizigers.add(reiziger);
            rsltst.close();
            stmt.close();
        }

        return aantalreizigers;
    }
}

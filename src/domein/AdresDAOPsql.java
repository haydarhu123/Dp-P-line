package domein;

import dao.ReizigerDAO;
import dao.AdresDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;
    private ReizigerDAO rdao;


    public AdresDAOPsql(Connection connection) {
        this.conn = connection;
    }

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id , postcode , huisnummer , straat, woonplaats, reiziger_id) VALUES (?,?,?,?,?,?)";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            prepstmt.setInt(1, adres.getId());
            prepstmt.setString(2, adres.getPostcode());
            prepstmt.setString(3, adres.getHuisnummer());
            prepstmt.setString(4, adres.getStraat());
            prepstmt.setString(5, adres.getWoonplaats());
            prepstmt.setInt(6, adres.getReiziger_Id().getId());

            prepstmt.executeUpdate();
            prepstmt.close();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adres_id = ?";

            PreparedStatement prpstmt = conn.prepareStatement(query);

            prpstmt.setInt(1, adres.getId());

            prpstmt.executeUpdate();
            prpstmt.close();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        try {
            String query = "UPDATE adres SET postcode = ? , huisnummer = ? , straat = ?, woonplaats = ?, reiziger_id = ? WHERE adres_id=?";

            PreparedStatement prepstmt = conn.prepareStatement(query);


            prepstmt.setString(1, adres.getPostcode());
            prepstmt.setString(2, adres.getHuisnummer());
            prepstmt.setString(3, adres.getStraat());
            prepstmt.setString(4, adres.getWoonplaats());
            prepstmt.setInt(5, adres.getReiziger_Id().getId());
            prepstmt.setInt(6, adres.getId());

            prepstmt.executeUpdate();
            prepstmt.close();


        } catch (SQLException exception) {
            System.out.println("De gegevens konden niet ge-update worden.");
            ;

        }
        return true;
    }


    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {

        try {
            PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM adres WHERE adres_id=?");
            prepstmt.setInt(1, reiziger.getId());

            ResultSet rsltst = prepstmt.executeQuery();
            while (rsltst.next()) {
                int adres_id = rsltst.getInt("adres_id");
                String postcode = rsltst.getString("postcode");
                String huisnummer = rsltst.getString("huisnummer");
                String straat = rsltst.getString("straat");
                String woonplaats = rsltst.getString("woonplaats");
                int reiziger_id = rsltst.getInt("reiziger_id");


                System.out.println(adres_id);
                System.out.println(postcode);
                System.out.println(huisnummer);
                System.out.println(straat);
                System.out.println(woonplaats);
                System.out.println(reiziger_id);

                return new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger);
            }
            rsltst.close();
            prepstmt.close();

        } finally {

        }

        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsltst = stmt.executeQuery("SELECT* FROM adres");

        ArrayList<Adres> aantaladressen = new ArrayList<Adres>();

        while (rsltst.next()) {

            int adres_id = rsltst.getInt("adres_id");
            String postcode = rsltst.getString("postcode");
            String huisnummer = rsltst.getString("huisnummer");
            String straat = rsltst.getString("straat");
            String woonplaats = rsltst.getString("woonplaats");
            int reiziger_id = rsltst.getInt("reiziger_id");

            Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, rdao.findById(reiziger_id));
            aantaladressen.add(adres);
            rsltst.close();
            stmt.close();

        }
        return aantaladressen;
    }

}


package org.example;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.*;

/**
 * Klasa sluzaca do wysylania zapytan sql do bazy danych
 */
public class Connect {

    Connection conn;

    /**
     * konstruktor klasy Connect
     */
    public Connect() {

    }

    //connect

    /**
     * Sluzy do polaczenia z baza danych
     * @param jdbcURL ustawia url jdbc
     * @param userid id uzytkownika
     * @param password haslo uzytkownika
     * @throws SQLException wyjatek
     */
    public void getDBConnection(String jdbcURL,String userid,String password) throws SQLException{
        try{
            OracleDataSource ds;

        ds = new OracleDataSource();
        ds.setURL(jdbcURL);
        conn = ds.getConnection(userid,password);
        System.out.println("polaczono z baza danych!!!!!!!!!!");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Metoda odpowiadajaca za to czy polaczenie z baza zostalo zamnkiete
     * @return prawde jezeli tak, falsz jeseli baza nie zostala zamknieta
     * @throws SQLException wyjatek
     */
    public boolean isClosedd() throws SQLException {
        if(conn.isClosed()==false){
            throw new IllegalArgumentException("baza nie zamknieta");
        }
        return true;
    }

    /**
     * Sluzy do wprowadzania danych do tabeli
     * @param secondName nazwisko
     * @param name imie
     * @param pesel pesel
     * @param miejscowo miejscowosc
     * @param street ulica
     * @param houseNumber numer domu
     * @throws SQLException wyjatek
     */
    public void insertClientsData(String secondName,String name,String pesel,String miejscowo, String street,String houseNumber) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "insert into klient (klient_id,nazwisko,imie,pesel,miejscowosc,ulica,numer_domu) values (klientdane.nextval,'"+secondName+"','"+name+"','"+pesel+"','"+miejscowo+"','"+street+"','"+houseNumber+"')";
            myStmt.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do wprowadzania danych do tabeli
     * @param secondName nazwisko
     * @param name imie
     * @param earnings zarobki
     * @param position stanowisko
     * @throws SQLException wyjatek
     */
    public void insertWorkersData(String secondName,String name,String earnings,String position) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "insert into pracownicy (pracownik_id,nazwisko,imie,zarobki,stanowisko) values " + "(pracownicydane.nextval,'"+secondName+"','"+name+"',"+earnings+",'"+position+"')";
            myStmt.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Sluzy do wprowadzania danych do tabeli
     * @param carMake marka
     * @param carModel model
     * @param vinNumber numer vin
     * @param prodYear rok produkcji
     * @param price cena
     * @throws SQLException wyjatek
     */
    public void insertCarData(String carMake,String carModel,String vinNumber,String prodYear,String price) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "insert into samochod (samochod_id,marka,model,vin,rocznik,cena) values " + "(samochoddane.nextval,'"+carMake+"','"+carModel+"','"+vinNumber+"',"+prodYear+","+price+")";
            myStmt.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do wprowadzenia danych do tabeli
     * @param clientID id klienta
     * @param workerID id pracownika
     * @param carID id auta
     * @param paymentMethod metoda platnosci
     * @param yearOrder rok produkcji
     * @param monthOrder miesiac prdukcji
     * @param dayOrder dzien produkcji
     * @param priceOrder cena
     * @throws SQLException wyjatek
     */
    public void insertOrderData(String clientID,String workerID,String carID,int paymentMethod,String yearOrder,String monthOrder,String dayOrder,String priceOrder) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "insert into zamowienie (zamowienie_id,klient_id,pracownik_id,samochod_id,platnosc_id,rok,miesiac,dzien,koszt) values " + "(zamowieniadane.nextval,"+clientID+","+workerID+","+carID+","+paymentMethod+","+yearOrder+","+monthOrder+","+dayOrder+","+priceOrder+")";
            myStmt.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do wyswietlania danych z tabeli
     * @param clientID id klienta
     * @throws SQLException wyjatek
     */
    public void selectClientTable (String clientID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "SELECT * FROM klient WHERE klient_id=" + clientID;
            myStmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sluzy do wyswietlania danych z tabeli
     * @param workerID id pracownika
     * @throws SQLException wyjatek
     */
    public void selectWorkerTable (String workerID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "SELECT * FROM pracownicy WHERE pracownik_id=" + workerID;
            myStmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sluzy do wyswietlania danych z tabeli
     * @param carID id samochodu
     * @throws SQLException wyjatek
     */
    public void selectCarTable (String carID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "SELECT * FROM samochod WHERE samochod_id=" + carID;
            myStmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sluzy do wyswietlania danych z tabeli
     * @param orderID id zamowienia
     * @throws SQLException wyjatek
     */
    public void selectOrderTable (String orderID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "SELECT * FROM zamowienie WHERE zamowienie_id=" + orderID;
            myStmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sluzy do usuniecia danych z tabeli
     * @param clientID id klienta
     * @throws SQLException wyjatek
     */
    public void deleteClientData(String clientID) throws SQLException {

        try {
            Statement myStmt = conn.createStatement();
            Statement myStmtt = conn.createStatement();
            String query = "delete from zamowienie where klient_id="+clientID;
            String queryy = "delete from klient where klient_id="+clientID;
            myStmt.executeQuery(query);
            myStmtt.executeQuery(queryy);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do usuniecia danych z tabeli
     * @param workerID id pracownika
     * @throws SQLException wyjatek
     */
    public void deleteWorkerData(String workerID) throws SQLException {

        try {
            Statement myStmt = conn.createStatement();
            Statement myStmtt = conn.createStatement();
            String query = "delete from zamowienie where pracownik_id="+workerID;
            String queryy = "delete from pracownicy where pracownik_id="+workerID;
            myStmt.executeQuery(query);
            myStmtt.executeQuery(queryy);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do usuniecia danych z tabeli
     * @param carID id samochodu
     * @throws SQLException wyjatek
     */
    public void deleteCarData(String carID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            Statement myStmtt = conn.createStatement();
            String query = "delete from zamowienie where samochod_id="+carID;
            String queryy = "delete from samochod where samochod_id="+carID;
            myStmt.executeQuery(query);
            myStmtt.executeQuery(queryy);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Sluzy do usuniecia danych z tabeli
     * @param orderID id zamowienia
     * @throws SQLException wyjatek
     */
    public void deleteOrderData(String orderID) throws SQLException {
        try {
            Statement myStmt = conn.createStatement();
            String query = "delete from zamowienie where zamowienie_id="+orderID;
            myStmt.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    //disconnect t

    /**
     * Sluzy do rozwiazania polaczenia z baza
     */
    public void disconnect(){

        try {
            conn.close();
            if(conn.isClosed())
            System.out.println("rozlaczono z baza danych");

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }


    }




}

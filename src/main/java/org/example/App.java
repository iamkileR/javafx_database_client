package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
//modeltable
/**
 * Klasa glowna aplikacji
 */
public class App extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene scene,scene1,scene2,scene3,sceneCar,sceneOrder;
    Scene mainDelete,delete1,delete2,delete3,delete4;
    Scene mainView,view1,view2,view3,view4;
    //elo jestem podłączony
    static Connect connect = null;


    @Override
    public void start(Stage stage) {
        window = stage;

        // wyswietlanie tabel
        VBox view = new VBox(20);
        view.setAlignment(Pos.CENTER);
        Label labelek = new Label("Co chcesz wyswietlic?");
        ChoiceBox<String> choiceBoxxx = new ChoiceBox<>();
        choiceBoxxx.getItems().add("Klienta");
        choiceBoxxx.getItems().add("Pracownika");
        choiceBoxxx.getItems().add("Samochod");
        choiceBoxxx.getItems().add("Zamowienie");
        Button backButttt = new Button("Powrot");
        backButttt.setOnAction(e->changeScene(window,scene));
        view.getChildren().addAll(labelek,choiceBoxxx,backButttt);
        choiceBoxxx.setOnAction(e->{
            int selectedIndexxxxxx = choiceBoxxx.getSelectionModel().getSelectedIndex();
            try {
                switch (selectedIndexxxxxx) {
                    case 0:
                        changeScene(window, view1);
                        break;
                    case 1:
                        changeScene(window, view2);
                        break;
                    case 2:
                        changeScene(window,view3);
                        break;
                    case 3:
                        changeScene(window,view4);
                        break;
                    default:
                        AlertBox.display("Nieznana opcja","Nie dokonano wyboru");
                }
            } catch (Exception excc) { excc.printStackTrace(); }
        });

        mainView = new Scene(view,800,800);

// wyswietlanie tabeli klient

        VBox showClient = new VBox(20);
        showClient.setAlignment(Pos.CENTER);
        Label txt = new Label("Dane jakiego klienta chcesz wyświetlić?");
        TextField clientID2 = new TextField();
        clientID2.setMaxWidth(100);

        Button showButton = new Button("Wyświetl");
        Button backButton = new Button("Powrót");
        backButton.setOnAction(e->changeScene(window,mainView));
        showButton.setOnAction(e->{try {
            connect.selectClientTable(clientID2.getText());
            AlertBox.display("Wyświetlanie danych klienta","Wyświetlono");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        showClient.getChildren().addAll(clientID2,showButton,backButton);
        view1 = new Scene(showClient,800, 800);


// wyswietlanie tabeli pracownikow

        VBox showWorker = new VBox(20);
        showWorker.setAlignment(Pos.CENTER);
        Label txt1 = new Label("Dane jakiego pracownika chcesz wyświetlić?");
        TextField workerID2 = new TextField();
        workerID2.setMaxWidth(100);

        Button showButt = new Button("Wyświetl");
        Button backButt = new Button("Powrót");
        backButton.setOnAction(e->changeScene(window,mainView));

        showButt.setOnAction(e->{try {
            connect.selectWorkerTable(workerID2.getText());
            AlertBox.display("Wyświetlanie danych pracownika","Wyświetlono");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        showWorker.getChildren().addAll(workerID2,showButt,backButt);
        view2 = new Scene(showWorker,800, 800);

// wyswietlanie tabeli samochod

        VBox showCar = new VBox(20);
        showCar.setAlignment(Pos.CENTER);
        Label txt2 = new Label("Dane jakiego samochodu chcesz wyświetlić?");
        TextField carID2 = new TextField();
        carID2.setMaxWidth(100);

        Button showClick = new Button("Wyświetl");
        Button backClick = new Button("Powrót");
        backClick.setOnAction(e->changeScene(window,mainView));

        showClick.setOnAction(e->{try {
            connect.selectCarTable(carID2.getText());
            AlertBox.display("Wyświetlanie danych samochodu","Wyświetlono");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        showCar.getChildren().addAll(carID2,showClick,backClick);
        view3 = new Scene(showCar,800, 800);







        VBox mainDelet = new VBox(20);
        mainDelet.setAlignment(Pos.CENTER);
        Label text = new Label("Co chcesz usunac?");
        ChoiceBox<String> choiceBoxx = new ChoiceBox<>();
        choiceBoxx.getItems().add("Klienta");
        choiceBoxx.getItems().add("Pracownika");
        choiceBoxx.getItems().add("Samochod");
        choiceBoxx.getItems().add("Zamowienie");
        Button backBut = new Button("Powrot");
        backBut.setOnAction(e->changeScene(window,scene));
        mainDelet.getChildren().addAll(text,choiceBoxx,backBut);
        choiceBoxx.setOnAction(e->{
            int selectedIndex = choiceBoxx.getSelectionModel().getSelectedIndex();
            try {
                switch (selectedIndex) {
                    case 0:
                        changeScene(window, delete1);
                        break;
                    case 1:
                        changeScene(window, delete2);
                        break;
                    case 2:
                        changeScene(window,delete3);
                        break;
                    case 3:
                        changeScene(window,delete4);
                        break;
                    default:
                        System.out.println("nie dokonano wyboru");
                }
            } catch (Exception exc) { exc.printStackTrace(); }
        });
        mainDelete = new Scene(mainDelet,800, 800);


//----------- usuwanie klientow

        //delete1 - wybrana opcja usuwania klientow
        VBox klient = new VBox(15);
        klient.setAlignment(Pos.CENTER);

        Label klienID = new Label("Numer identyfikacyjny klienta: ");
        TextField klienID1 = new TextField();
        klienID1.setMaxWidth(100);


        Button del = new Button("Usun rekord");
        Button backOp = new Button("Powrót");
        backOp.setOnAction(e->changeScene(window,mainDelete));

        del.setOnAction(e->{try {
            connect.deleteClientData(klienID1.getText());
            AlertBox.display("Usuwanie klienta","usunięto pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        klient.getChildren().addAll(klienID,klienID1,del,backOp);
        delete1 = new Scene(klient,800,800);
//-------------------------

//----------- usuwanie pracownika

        //delete2 - wybrana opcja usuwania pracownika
        VBox prac = new VBox(15);
        prac.setAlignment(Pos.CENTER);

        Label pracID = new Label("Numer identyfikacyjny pracownika: ");
        TextField pracID1 = new TextField();
        pracID1.setMaxWidth(100);


        Button dell = new Button("Usun rekord");
        Button backOpp = new Button("Powrót");
        backOpp.setOnAction(e->changeScene(window,mainDelete));

        dell.setOnAction(e->{try {
            connect.deleteWorkerData(pracID1.getText());
            AlertBox.display("Usuwanie pracownika","usunięto pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        prac.getChildren().addAll(pracID,pracID1,dell,backOpp);
        delete2 = new Scene(prac,800,800);

//----------- usuwanie samochodu

        //delete3 - wybrana opcja usuwania samochodu
        VBox sam = new VBox(15);
        sam.setAlignment(Pos.CENTER);
        Label carID = new Label("Numer identyfikacyjny pracownika: ");
        TextField carId1 = new TextField();
        carId1.setMaxWidth(100);
        Button delll = new Button("Usun rekord");
        Button backOppp = new Button("Powrót");
        backOppp.setOnAction(e->changeScene(window,mainDelete));

        delll.setOnAction(e->{try {
            connect.deleteCarData(carId1.getText());
            AlertBox.display("Usuwanie samochodu","usunięto pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        sam.getChildren().addAll(carID,carId1,delll,backOppp);
        delete3 = new Scene(sam,800,800);
//----------- usuwanie zamowienia
        //delete4 - wybrana opcja usuwania zamowienia
        VBox order = new VBox(15);
        order.setAlignment(Pos.CENTER);
        Label orderID = new Label("Numer identyfikacyjny zamowienia: ");
        TextField orderID1 = new TextField();
        orderID1.setMaxWidth(100);
        Button dellll = new Button("Usun rekord");
        Button backOpppp = new Button("Powrót");
        backOpppp.setOnAction(e->changeScene(window,mainDelete));

        dellll.setOnAction(e->{try {
            connect.deleteOrderData(orderID1.getText());
            AlertBox.display("Usuwanie zamowienia","usunięto pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        order.getChildren().addAll(orderID,orderID1,dellll,backOpppp);
        delete4 = new Scene(order,800,800);








        //--------------------------------------------------------------------------------------------------------------------------------------------
        Button buttonAdd = new Button("Dodaj nowy rekord");
        Button buttonDelete = new Button("Usun rekordy");
        Button buttonVieww = new Button("Wyswietl rekordy");
        buttonDelete.setOnAction(e-> changeScene(window,mainDelete));
        buttonAdd.setOnAction(e -> changeScene(window,scene1));
        buttonVieww.setOnAction(e -> changeScene(window,mainView));
        //layout - gdzie sa ukladane elementy w pionowych kolumnach
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttonAdd,buttonDelete,buttonVieww);
        scene = new Scene(layout,800, 800);


        //scena1- wybor nowego rekordu
        VBox nowyLad = new VBox(20);
        nowyLad.setAlignment(Pos.CENTER);
        Label napis = new Label("Co chcesz dodac?");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Klienta");
        choiceBox.getItems().add("Pracownika");
        choiceBox.getItems().add("Samochod");
        choiceBox.getItems().add("Zamowienie");
        choiceBox.setOnAction(e-> {
                    int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
                    Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
                    System.out.println("zmiana dokonana: ["+selectedIndex+"]"+selectedItem);
                    System.out.println("choiceBox.getValue():"+choiceBox.getValue());
                   // do {
                        try {
                            switch (selectedIndex) {
                                case 0:
                                    changeScene(window, scene2);
                                    break;
                                case 1:
                                    changeScene(window, scene3);
                                    break;
                                case 2:
                                    changeScene(window,sceneCar);
                                    break;
                                case 3:
                                    changeScene(window,sceneOrder);
                                    break;
                                default:
                                    System.out.println("nie dokonano wyboru");
                            }
                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }
                   // }while(choiceBox.isPressed());



        });

        Button backOption = new Button("Powrot");
        backOption.setOnAction(e-> changeScene(window,scene));
        nowyLad.setSpacing(25);
        nowyLad.getChildren().addAll(napis,choiceBox,backOption);
        scene1 = new Scene(nowyLad,800,800);


        //scena2 - wybrana opcja
        VBox klienci = new VBox(15);
        klienci.setAlignment(Pos.CENTER);
        Label imi = new Label("Imie: ");
        TextField imie1 = new TextField();
        imie1.setMaxWidth(100);

        Label nazwisk = new Label("Nazwisko: ");
        TextField nazwisko1 = new TextField();
        nazwisko1.setMaxWidth(100);

        Label pes = new Label("Pesel: ");
        TextField pesel1 = new TextField();
        pesel1.setMaxWidth(100);

        Label miejsc = new Label("Miejscowosc: ");
        TextField miejscowosc1 = new TextField();
        miejscowosc1.setMaxWidth(100);

        Label ulic = new Label("Ulica: ");
        TextField ulica1 = new TextField();
        ulica1.setMaxWidth(100);

        Label nrdom = new Label("Numer domu: ");
        TextField nrdomu1 = new TextField();
        nrdomu1.setMaxWidth(100);


        Button add = new Button("Dodaj rekord");
        Button backOptio = new Button("Powrot");
        backOptio.setOnAction(e->changeScene(window,scene1));

        add.setOnAction(e->{try {
            connect.insertClientsData(nazwisko1.getText(),imie1.getText(), pesel1.getText(),miejscowosc1.getText(),ulica1.getText(),nrdomu1.getText());
            AlertBox.display("Dodawanie klienta","dodano pomyślnie");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } });
        klienci.getChildren().addAll(imi,imie1,nazwisk,nazwisko1,pes,pesel1,miejsc,miejscowosc1,ulic,ulica1,nrdom,nrdomu1,add,backOptio);
        scene2 = new Scene(klienci,800,800);





        //scene3 - odpowiedzialna za utworzenie formularza dodawania nowego pracownika
        VBox pracownik = new VBox(15);
        pracownik.setAlignment(Pos.CENTER);
        Label prImie = new Label("Imie: ");
        TextField prImie1 = new TextField();
        prImie1.setMaxWidth(100);

        Label prNazwisk = new Label("Nazwisko: ");
        TextField prNazwisko1 = new TextField();
        prNazwisko1.setMaxWidth(100);

        Label prZarobki = new Label("Zarobki: ");
        TextField prZarobki1 = new TextField();
        prZarobki1.setMaxWidth(100);

        Label prStanowisko = new Label("Stanowisko: ");
        TextField prStanowisko1 = new TextField();
        prStanowisko1.setMaxWidth(100);

        Button addPr = new Button("Dodaj pracownika");
        Button back = new Button("Powrot");
        pracownik.getChildren().addAll(prImie,prImie1,prNazwisk,prNazwisko1,prZarobki,prZarobki1,prStanowisko,prStanowisko1,
                addPr,back);
        addPr.setOnAction(e->{
            {try {
                     connect.insertWorkersData(prNazwisko1.getText(),prImie1.getText(),prZarobki1.getText(),prStanowisko1.getText());
                     AlertBox.display("Dodawanie pracownika","dodano pomyślnie");
                 } catch (SQLException throwables) {
                       throwables.printStackTrace();
                                                    }
            }                });
        back.setOnAction(e->{
            changeScene(window,scene1);
        });

        scene3 = new Scene(pracownik,800,800);




        //scena4 - wybrana opcja dodania samochodu
        VBox car = new VBox(15);
        car.setAlignment(Pos.CENTER);
        Label carMake = new Label("Marka: ");
        TextField carMake1 = new TextField();
        carMake1.setMaxWidth(100);

        Label carModel = new Label("Model: ");
        TextField carModel1 = new TextField();
        carModel1.setMaxWidth(100);

        Label vinNumber = new Label("Numer VIN: ");
        TextField vinNumber1 = new TextField();
        vinNumber1.setMaxWidth(100);

        Label prodYear = new Label("Rok produkcji auta: ");
        TextField prodYear1 = new TextField();
        prodYear1.setMaxWidth(100);

        Label carPrice = new Label("Cena: ");
        TextField carPrice1 = new TextField();
        carPrice1.setMaxWidth(100);

        Button addd = new Button("Dodaj samochod");
        Button backOpti = new Button("Powrot");
        backOpti.setOnAction(e->changeScene(window,scene1));
        addd.setOnAction(e->{try {
            connect.insertCarData(carMake1.getText(),carModel1.getText(), vinNumber1.getText(),prodYear1.getText(),carPrice1.getText());
            AlertBox.display("Dodawanie auta","dodano pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        car.getChildren().addAll(carMake,carMake1,carModel,carModel1,vinNumber,vinNumber1,prodYear,prodYear1,carPrice,carPrice1,addd,backOpti);
        sceneCar = new Scene(car,800,800);






        //scena5 - wybrana opcja dodania zamowienia
        VBox order1 = new VBox(15);
        order1.setAlignment(Pos.CENTER);
        Label clientID = new Label("Identyfikator klienta: ");
        TextField clientID1 = new TextField();
        clientID1.setMaxWidth(100);

        Label workerID = new Label("Identyfikator pracownika:");
        TextField workerID1 = new TextField();
        workerID1.setMaxWidth(100);

        Label cr = new Label("Identyfikator samochodu: ");
        TextField car1 = new TextField();
        car1.setMaxWidth(100);
        AtomicInteger selectedIndexx = new AtomicInteger();
        Label paymentMethod = new Label("Identyfikator platnosci:");
        ComboBox paymentMethod1 = new ComboBox();
        paymentMethod1.getItems().addAll("karta","gotowka","przelew");


        paymentMethod1.setOnAction(e->{
            int selectedIndexxx = paymentMethod1.getSelectionModel().getSelectedIndex();
            System.out.println("pierwszy sop:"+(selectedIndexxx+1));
        });


        Label yearOrder = new Label("Rok zamowienia: ");
        TextField yearOrder1 = new TextField();
        yearOrder1.setMaxWidth(100);
        Label monthOrder = new Label("Miesiac zamowienia: ");
        TextField monthOrder1 = new TextField();
        monthOrder1.setMaxWidth(100);
        Label dayOrder = new Label("Dzien zamowienia: ");
        TextField dayOrder1 = new TextField();
        dayOrder1.setMaxWidth(100);
        Label priceOrder = new Label("Koszt zamowienia: ");
        TextField priceOrder1 = new TextField();
        priceOrder1.setMaxWidth(100);


        Button adddd = new Button("Dodaj zamowienie");
        Button backOpt = new Button("Powrot");
        backOpt.setOnAction(e->changeScene(window,scene1));
        adddd.setOnAction(e->{try {
            connect.insertOrderData(clientID1.getText(),workerID1.getText(), car1.getText(), (paymentMethod1.getSelectionModel().getSelectedIndex()+1),yearOrder1.getText(),monthOrder1.getText(),dayOrder1.getText(),priceOrder1.getText());
            AlertBox.display("Dodawanie nowego zamowienia","dodano pomyślnie");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } });
        order1.getChildren().addAll(clientID,clientID1,workerID,workerID1,cr,car1,paymentMethod,paymentMethod1,yearOrder,yearOrder1,monthOrder,monthOrder1, dayOrder,dayOrder1,priceOrder,priceOrder1,adddd,backOpt);
        sceneOrder = new Scene(order1,800,800);








        changeScene(window,scene);
        window.show();
        window.setTitle("Klient bazy danych");
      /*  window.setOnCloseRequest(e -> {
            e.consume();
            //e.close();
            //do dorobienia zamykanie programu
        });*/

    }



    @Override
    public void handle(ActionEvent event) {

    }

    /**
     * Funkcja sluzaca do wyboru sceny
     * @param choiceBox pole do wyboru
     * @param window okienko/scena
     * @param scena scena aplikacji
     * @param scena2 scena aplikacji 2
     */
    public void sceneChoice(ChoiceBox<String> choiceBox,Stage window,Scene scena,Scene scena2){

           if(choiceBox.getValue().equals("Klienta")) {
               changeScene(window, scena);
           }
        /*   if(choiceBox.getValue().equals("Pracownika")){
               changeScene(window, scena2);
           }*/

    }


    /**
     * Funkcja sluzaca do wyboru sceny
     * @param window okienko
     * @param scena scena aplikacji
     */
    public void changeScene(Stage window,Scene scena){
        window.setScene(scena);
    }

    /**
     * Funkcja sluzaca do zamkniecia okienka
     */
    public void close(){
       /* if (window.close()){

        }*/

    }

    /**
     * Funkcja sluzaca do wyboru
     * @param choiceBox okno sluzace do wyboru textu
     */
    public void getChoice(ChoiceBox<String> choiceBox){
        String answer = choiceBox.getValue();
        System.out.println(answer);
    }

    /**
     * Glowna metoda aplikacji
     * @param args argumenty aplikacji
     * @throws SQLException wyjatek
     */
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String userid = "humba";
        String password = "12345";

        connect = new Connect();
        connect.getDBConnection(jdbcURL,userid,password);
        launch();
        connect.disconnect();


    }

}

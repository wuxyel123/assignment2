package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    MenuItem panino1 = new MenuItem(MenuItem.ItemType.Panino, "Panino 1", 5);
    MenuItem panino2 = new MenuItem(MenuItem.ItemType.Panino, "Panino 2", 4);
    MenuItem fritto1 = new MenuItem(MenuItem.ItemType.Fritto, "Fritto 1", 6);
    MenuItem fritto2 = new MenuItem(MenuItem.ItemType.Fritto, "Fritto 2", 2);
    MenuItem bevanda1 = new MenuItem(MenuItem.ItemType.Bevande, "Bevanda 1", 3);
    MenuItem bevanda2 = new MenuItem(MenuItem.ItemType.Bevande, "Bevanda 2", 1);
    private ArrayList<MenuItem> itemsOrdered;
    private App app;
    public AppTest() {
        app = new App();
    }

    @Test
    public void ordineConPiuDi30ElementiGestitoCheckMessaggioErrore() {
        itemsOrdered = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            itemsOrdered.add(panino1);
        }
        try {
            app.getOrderPrice(itemsOrdered);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
            assertEquals("Non è possibile avere un’ordinazione con più di 30 elementi",e.getMessage());
        }
    }

    @Test
    public void ordineConPiuDi30ElementiThrowato() throws TakeAwayBillException{
        itemsOrdered = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            itemsOrdered.add(panino1);
        }
            app.getOrderPrice(itemsOrdered);
    }

    @Test
    public void ordineVuoto() throws TakeAwayBillException{
        itemsOrdered = new ArrayList<>();
        assertEquals(0D, app.getOrderPrice(itemsOrdered), 0);
    }

    @Test
    public void totMinoreDi10EuroCommissione50cent() throws TakeAwayBillException {
        itemsOrdered = new ArrayList<>();
        itemsOrdered.add(panino1);
        assertEquals(5.50D, app.getOrderPrice(itemsOrdered), 0);
    }

    @Test
    public void ordineConPiuDi5PaniniSconto50percSulMenoCaro() throws TakeAwayBillException {
        itemsOrdered = new ArrayList<>();
        itemsOrdered.add(panino1);
        itemsOrdered.add(panino1);
        itemsOrdered.add(panino1);
        itemsOrdered.add(panino1);
        itemsOrdered.add(panino1);
        itemsOrdered.add(panino2);
        assertEquals(27D, app.getOrderPrice(itemsOrdered), 0);
    }


    @Test
    public void totaleBevandeEscluseMaggiore50EuroSconto10perc() throws TakeAwayBillException {
        itemsOrdered = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemsOrdered.add(fritto1);
        }
        itemsOrdered.add(bevanda1);
        itemsOrdered.add(bevanda1);
        itemsOrdered.add(bevanda1);
        itemsOrdered.add(bevanda2);
//Totale senza bevande=60 Con Bevande=70
        assertEquals(63D, app.getOrderPrice(itemsOrdered), 0);
    }


}

////////////////////////////////////////////////////////////////////
// Alessandro Discalzi 1169739
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;
import java.util.OptionalDouble;

public class App implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double tot = 0.0;//Totale prezzo da pagare
        double totNoDrink = 0.0;//Totale senza bebande
        /* Non è possibile avere un’ordinazione con più di 30 elementi
         * (se accade prevedere un messaggio
         * d’errore)
         */
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("Non è possibile avere un’ordinazione con più di 30 elementi");
        }

//Inizializzazione tot e totNoDrink
        tot = itemsOrdered.stream().mapToDouble(MenuItem::getPrice).sum();
        totNoDrink = itemsOrdered.stream().filter(item -> item.getType() != MenuItem.ItemType.Bevande)
                .mapToDouble(MenuItem::getPrice).sum();


        /* Se l’importo totale è inferiore a 10 € viene aggiunta una commissione di 0,50 €
         *
         */
        if (tot > 0 && tot < 10) {
            tot += 0.5;
        }

        /* Se vengono ordinati più di 5 Panini viene fatto uno sconto
         * del 50% sul prezzo del panino meno caro
         */
        long nPanini = itemsOrdered.stream().filter(m -> m.getType() == MenuItem.ItemType.Panino).count();
        OptionalDouble paninoMenoCostoso = itemsOrdered.stream()
                .filter(m -> m.getType() == MenuItem.ItemType.Panino)
                .mapToDouble(MenuItem::getPrice).min();

        if (nPanini > 5) {
            tot -= paninoMenoCostoso.getAsDouble() / 2;
        }

        /* Se l’importo totale delle ordinazioni (Panini e Fritti)
         *  supera i 50 euro viene fatto il 10% di sconto
         */
        if (totNoDrink > 50) {
            tot *= 0.9;
        }

        return tot;
    }

}

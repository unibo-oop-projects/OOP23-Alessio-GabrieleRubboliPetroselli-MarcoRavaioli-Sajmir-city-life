package unibo.citysimulation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class SimulazioneTimer {

    // Numero totale di giorni da simulare
    static int giorniTotali = 7 * 4; // Supponiamo una simulazione di 4 settimane

    // Durata in millisecondi per ciascun giorno (modifica per accelerare o rallentare)
    static long durataGiornoMillisecondi = 1000; // 1 secondo per ogni giorno

    // Contatore per tenere traccia delle ore nel giorno
    static int oreNelGiorno = 0;

    public static void main(String[] args) {
        // Inizializzazione di now con l'ora mezzanotte
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int giornoCorrente = 1;

            @Override
            public void run() {
                if (giornoCorrente <= giorniTotali) {
                    // Aggiornamento dell'ora corrente
                    oreNelGiorno++;
                    if (oreNelGiorno >= 24) {
                        oreNelGiorno = 0;
                    }
                    LocalDateTime oraCorrente = now.plusHours(oreNelGiorno); // Aggiorna l'ora corrente
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formattedDateTime = oraCorrente.format(formatter);
                    System.out.println("Giorno " + giornoCorrente + ", Ora: " + formattedDateTime);
                    
                    // Controllo se è passato un giorno intero
                    if (oreNelGiorno == 0) {
                        giornoCorrente++;
                    }
                } else {
                    timer.cancel();
                }
            }
        };

        // Avvio del timer che esegue il task ogni durataGiornoMillisecondi millisecondi
        timer.scheduleAtFixedRate(task, 0, durataGiornoMillisecondi);
    }
}


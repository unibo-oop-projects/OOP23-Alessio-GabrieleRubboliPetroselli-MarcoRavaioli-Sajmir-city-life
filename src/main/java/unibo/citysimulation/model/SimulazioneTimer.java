package unibo.citysimulation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class SimulazioneTimer {

    private int giorniTotali; // Numero totale di giorni da simulare
    private long durataGiornoMillisecondi = 1000; // Durata in millisecondi per ciascun giorno
    private int oreNelGiorno = 0; // Contatore per tenere traccia delle ore nel giorno
    private Timer timer;
    int giornoCorrente = 1;
    String formattedDateTime;

    public SimulazioneTimer(int giorniTotali) {
        this.giorniTotali = giorniTotali;
        this.timer = new Timer();
    }

    public void startSimulation() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                if (giornoCorrente <= giorniTotali) {
                    oreNelGiorno++;
                    if (oreNelGiorno >= 24) {
                        oreNelGiorno = 0;
                    }
                    LocalDateTime oraCorrente = now.plusHours(oreNelGiorno);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    formattedDateTime = oraCorrente.format(formatter);
                    System.out.println("Giorno " + giornoCorrente + ", Ora: " + formattedDateTime);

                    if (oreNelGiorno == 0) {
                        giornoCorrente++;
                    }
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, durataGiornoMillisecondi);
    }

    public void stopSimulation() {
        timer.cancel();
    }
}

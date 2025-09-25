package br.com.joaocarloslima;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {

    private static final int LADO = 13;
    private static final int CELL = 50;

    private final Fazenda fazenda = new Fazenda(200);
    private final List<ImageView> imageTerrenos = new ArrayList<>(LADO * LADO);
    private volatile int sleepTime = 3000;

    @FXML private GridPane grid;
    @FXML private ToggleButton botaoBatata;
    @FXML private ToggleButton botaoCenoura;
    @FXML private ToggleButton botaoMorango;
    @FXML private ToggleButton botaoColher;
    @FXML private ProgressBar ocupacaoDoCeleiro;
    @FXML private CheckBox ckbAcelerar;

    public void acelerar() { sleepTime = ckbAcelerar.isSelected() ? 1000 : 3000; }

    // Crescimento controlado no Controller (funciona com os getters de conveniência do Terreno)
    private void ciclo() {
        for (int x = 0; x < LADO; x++) {
            for (int y = 0; y < LADO; y++) {
                Terreno t = fazenda.getTerreno(x, y);
                if (t.getBatata() != null)      t.getBatata().crescer();
                else if (t.getCenoura() != null) t.getCenoura().crescer();
                else if (t.getMorango() != null) t.getMorango().crescer();
            }
        }
        atualizar();
    }

    private void atualizar() {
        botaoBatata.setText("Batata x " + fazenda.getCeleiro().getQtdeBatatas());
        botaoCenoura.setText("Cenoura x " + fazenda.getCeleiro().getQtdeCenouras());
        botaoMorango.setText("Morango x " + fazenda.getCeleiro().getQtdeMorangos());

        double occ = fazenda.getCeleiro().getOcupacao();
        ocupacaoDoCeleiro.setProgress(occ);
        ocupacaoDoCeleiro.setTooltip(new Tooltip(
            "Ocupação: " + (int)(occ * 100) + "%\n" +
            "Espaço livre: " + fazenda.getCeleiro().getEspacoDisponivel() + "\n" +
            "Batatas: " + fazenda.getCeleiro().getQtdeBatatas() +
            " | Cenouras: " + fazenda.getCeleiro().getQtdeCenouras() +
            " | Morangos: " + fazenda.getCeleiro().getQtdeMorangos()
        ));

        for (int x = 0; x < LADO; x++) {
            for (int y = 0; y < LADO; y++) {
                Terreno t = fazenda.getTerreno(x, y);
                ImageView cell = imageTerrenos.get(x * LADO + y);

                if (t.getBatata() != null) {
                    cell.setImage(new Image(getClass().getResourceAsStream(t.getBatata().getImagem())));
                } else if (t.getCenoura() != null) {
                    cell.setImage(new Image(getClass().getResourceAsStream(t.getCenoura().getImagem())));
                } else if (t.getMorango() != null) {
                    cell.setImage(new Image(getClass().getResourceAsStream(t.getMorango().getImagem())));
                } else {
                    cell.setImage(null);
                }
            }
        }
    }

    private void clockThread() {
        Thread th = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                    Platform.runLater(this::ciclo);
                } catch (InterruptedException ignored) {}
            }
        });
        th.setDaemon(true);
        th.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int x = 0; x < LADO; x++) {
            for (int y = 0; y < LADO; y++) {
                ImageView iv = new ImageView();
                iv.setFitWidth(CELL);
                iv.setFitHeight(CELL);
                grid.add(iv, x, y);
                imageTerrenos.add(iv);
            }
        }

        grid.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / CELL);
            int y = (int) (e.getY() / CELL);
            if (x < 0 || x >= LADO || y < 0 || y >= LADO) return;

            try {
                if (botaoColher.isSelected() && fazenda.getCeleiro().getEspacoDisponivel() < 2) {
                    new Alert(Alert.AlertType.WARNING, "Celeiro cheio! Esvazie antes de colher.").showAndWait();
                    return;
                }

                if (botaoBatata.isSelected())      fazenda.plantarBatata(x, y);
                else if (botaoCenoura.isSelected()) fazenda.plantarCenoura(x, y);
                else if (botaoMorango.isSelected()) fazenda.plantarMorango(x, y);
                else if (botaoColher.isSelected())  fazenda.colher(x, y);

                atualizar();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(ex.getMessage());
                alert.showAndWait();
            }
        });

        atualizar();
        clockThread();
    }
}

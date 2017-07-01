/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.Mapa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.media.opengl.GL;
import trabalhofinal.BolasAssassinasNervosas.BolasAssassinasNervosas;
import trabalhofinal.Main;

/**
 *
 * @author henrique
 */
public class Level {

    Mapa map;
    private int level = 1;
    private ArrayList<Mapa> setores;
    private ArrayList<Pixel> caminhavel;
    private ArrayList<Pixel> setorFinal;
    private BolasAssassinasNervosas[] bans;
    private boolean colidiu;
    private boolean passou;
    private Timer timer;
    private TimerTask tarefa;
    private long horaInicio;
    private String tempoGasto;
    private HashMap<Integer,String> temposFinais;

    public HashMap<Integer,String> getTemposFinais() {
        return temposFinais;
    }

    public Level() {
        level = 0;
        setores = new ArrayList<>();
        map = new Mapa();
        caminhavel = new ArrayList<>();
        bans = new BolasAssassinasNervosas[20];
        setorFinal = new ArrayList<>();
        for (int i = 0; i < bans.length; i++) {
            bans[i] = new BolasAssassinasNervosas();
        }
        colidiu = false;
        temposFinais = new HashMap<>();
    }

    public String getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(String tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    public void iniciaCronometro() {
        timer = null;
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (timer == null) {
            timer = new Timer();
            horaInicio = System.currentTimeMillis();
            tarefa = new TimerTask() {
                public void run() {
                    try {
                        Date data = new Date();
                        data.setTime(System.currentTimeMillis() - horaInicio);
//                        System.out.println("Hora: " + format.format(data.getTime()));
                        setTempoGasto(format.format(data.getTime()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            };
            timer.scheduleAtFixedRate(tarefa, 0, 1000);
        }
    }

    private void salvaCronometro(int index) {
        temposFinais.put(index,tempoGasto);
        timer.cancel();
        tarefa.cancel();
    }

    public boolean passou() {
        return passou;
    }

    public boolean colidiu() {
        return colidiu;
    }

    public void setColidiu(boolean colidiu) {
        this.colidiu = colidiu;
    }

    public ArrayList<Pixel> getCaminhavel() {
        return caminhavel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        iniciaCronometro();
        this.level = level;
        switch (level) {
            case 1:
                Main.agente.setPosx(-90);
                Main.agente.setPosy(40);
                bans[0].setPosx(-56);
                bans[0].setPosy(46);
                bans[1].setPosx(-56);
                bans[1].setPosy(37);
                bans[2].setPosx(46);
                bans[2].setPosy(27);
                bans[3].setPosx(-56);
                bans[3].setPosy(16);
                bans[4].setPosx(46);
                bans[4].setPosy(6);
                bans[5].setPosx(46);
                bans[5].setPosy(-3);
                break;
            case 2:
                Main.agente.setPosx(-100);
                Main.agente.setPosy(20);
                bans[0].setPosx(-72);
                bans[0].setPosy(56);
                bans[1].setPosx(-61);
                bans[1].setPosy(-26);
                bans[2].setPosx(-50);
                bans[2].setPosy(56);
                bans[3].setPosx(-39);
                bans[3].setPosy(-26);
                bans[4].setPosx(-28);
                bans[4].setPosy(56);
                bans[5].setPosx(-17);
                bans[5].setPosy(-26);
                bans[6].setPosx(-6);
                bans[6].setPosy(56);
                bans[7].setPosx(7);
                bans[7].setPosy(-26);
                bans[8].setPosx(18);
                bans[8].setPosy(56);
                bans[9].setPosx(29);
                bans[9].setPosy(-26);
                bans[10].setPosx(40);
                bans[10].setPosy(56);
                bans[11].setPosx(51);
                bans[11].setPosy(-26);
                bans[12].setPosx(62);
                bans[12].setPosy(56);
                bans[13].setPosx(73);
                bans[13].setPosy(-26);
                break;
            case 3:
                Main.agente.setPosx(-100);
                Main.agente.setPosy(20);
                bans[0].setPosx(-72);
                bans[0].setPosy(56);
                bans[1].setPosx(-61);
                bans[1].setPosy(-26);
                bans[2].setPosx(-50);
                bans[2].setPosy(56);
                bans[3].setPosx(-39);
                bans[3].setPosy(-26);
                bans[4].setPosx(-28);
                bans[4].setPosy(56);
                bans[5].setPosx(-17);
                bans[5].setPosy(-26);
                bans[6].setPosx(-6);
                bans[6].setPosy(56);
                bans[7].setPosx(7);
                bans[7].setPosy(-26);
                bans[8].setPosx(18);
                bans[8].setPosy(56);
                bans[9].setPosx(29);
                bans[9].setPosy(-26);
                bans[10].setPosx(40);
                bans[10].setPosy(56);
                bans[11].setPosx(51);
                bans[11].setPosy(-26);
                bans[12].setPosx(62);
                bans[12].setPosy(56);
                bans[13].setPosx(73);
                bans[13].setPosy(-26);
                bans[14].setPosx(-76);
                bans[14].setPosy(53);
                bans[15].setPosx(-76);
                bans[15].setPosy(30);
                bans[16].setPosx(-76);
                bans[16].setPosy(-1);
                bans[17].setPosx(-76);
                bans[17].setPosy(-24);
                bans[18].setPosx(76);
                bans[18].setPosy(-13);
                bans[19].setPosx(76);
                bans[19].setPosy(42);
                break;
            case 4:
                Main.agente.setPosx(-100);
                Main.agente.setPosy(-40);
                bans[0].setPosx(-72);
                bans[0].setPosy(-20);
                bans[1].setPosx(-58);
                bans[1].setPosy(-26);
                bans[2].setPosx(-72);
                bans[2].setPosy(30);
                bans[3].setPosx(-56);
                bans[3].setPosy(10);
                bans[4].setPosx(-75);
                bans[4].setPosy(74);
                bans[5].setPosx(-50);
                bans[5].setPosy(75);
                bans[6].setPosx(-40);
                bans[6].setPosy(75);
                bans[7].setPosx(35);
                bans[7].setPosy(60);
                bans[8].setPosx(-30);
                bans[8].setPosy(75);
                bans[9].setPosx(35);
                bans[9].setPosy(40);
                bans[10].setPosx(20);
                bans[10].setPosy(20);
                bans[11].setPosx(95);
                bans[11].setPosy(6);
                bans[12].setPosx(75);
                bans[12].setPosy(-20);
                break;
        }
    }

    public void movimentaBolas() {
        switch (level) {
            case 1:
                bans[0].setVelocidade(0.5f + Main.dif);
                if (!bans[0].indo()) {
                    bans[0].setPosx(bans[0].getPosx() + bans[0].getVelocidade());
                    bans[1].setPosx(bans[0].getPosx() + bans[0].getVelocidade());
                    bans[3].setPosx(bans[0].getPosx() + bans[0].getVelocidade());
                    if (bans[0].getPosx() >= 46) {
                        bans[0].setIndo(true);
                    }
                } else {
                    bans[0].setPosx(bans[0].getPosx() - bans[0].getVelocidade());
                    bans[1].setPosx(bans[0].getPosx() - bans[0].getVelocidade());
                    bans[3].setPosx(bans[0].getPosx() - bans[0].getVelocidade());
                    if (bans[0].getPosx() <= -56) {
                        bans[0].setIndo(false);
                    }
                }
                if (!bans[2].indo()) {
                    bans[2].setPosx(bans[2].getPosx() - bans[0].getVelocidade());
                    bans[4].setPosx(bans[2].getPosx() - bans[0].getVelocidade());
                    bans[5].setPosx(bans[2].getPosx() - bans[0].getVelocidade());
                    if (bans[2].getPosx() <= -56) {
                        bans[2].setIndo(true);
                    }
                } else {
                    bans[2].setPosx(bans[2].getPosx() + bans[0].getVelocidade());
                    bans[4].setPosx(bans[2].getPosx() + bans[0].getVelocidade());
                    bans[5].setPosx(bans[2].getPosx() + bans[0].getVelocidade());
                    if (bans[2].getPosx() >= 46) {
                        bans[2].setIndo(false);
                    }
                }
                break;
            case 2:
                bans[0].setVelocidade(0.2f + Main.dif);
                if (!bans[0].indo()) {
                    for (int i = 0; i < 13; i += 2) {
                        bans[i].setPosy(bans[0].getPosy() + bans[0].getVelocidade());
                    }
                    if (bans[0].getPosy() >= 56) {
                        bans[0].setIndo(true);
                    }
                } else {
                    for (int i = 0; i < 13; i += 2) {
                        bans[i].setPosy(bans[0].getPosy() - bans[0].getVelocidade());
                    }
                    if (bans[0].getPosy() <= -26) {
                        bans[0].setIndo(false);
                    }
                }
                if (!bans[1].indo()) {
                    for (int i = 1; i < 14; i += 2) {
                        bans[i].setPosy(bans[1].getPosy() - bans[0].getVelocidade());
                    }
                    if (bans[1].getPosy() <= -26) {
                        bans[1].setIndo(true);
                    }
                } else {
                    for (int i = 1; i < 14; i += 2) {
                        bans[i].setPosy(bans[1].getPosy() + bans[0].getVelocidade());
                    }
                    if (bans[1].getPosy() >= 56) {
                        bans[1].setIndo(false);
                    }
                }
                break;
            case 3:
                bans[0].setVelocidade(0.3f + Main.dif);
                if (!bans[0].indo()) {
                    for (int i = 0; i < 13; i += 2) {
                        bans[i].setPosy(bans[0].getPosy() + bans[0].getVelocidade());
                    }
                    if (bans[0].getPosy() >= 56) {
                        bans[0].setIndo(true);
                    }
                } else {
                    for (int i = 0; i < 13; i += 2) {
                        bans[i].setPosy(bans[0].getPosy() - bans[0].getVelocidade());
                    }
                    if (bans[0].getPosy() <= -26) {
                        bans[0].setIndo(false);
                    }
                }
                if (!bans[1].indo()) {
                    for (int i = 1; i < 14; i += 2) {
                        bans[i].setPosy(bans[1].getPosy() - bans[0].getVelocidade());
                    }
                    if (bans[1].getPosy() <= -26) {
                        bans[1].setIndo(true);
                    }
                } else {
                    for (int i = 1; i < 14; i += 2) {
                        bans[i].setPosy(bans[1].getPosy() + bans[0].getVelocidade());
                    }
                    if (bans[1].getPosy() >= 56) {
                        bans[1].setIndo(false);
                    }
                }
                bans[14].setVelocidade(0.1f + Main.dif);
                if (!bans[14].indo()) {
                    for (int i = 14; i < 18; i++) {
                        bans[i].setPosx(bans[14].getPosx() + bans[14].getVelocidade());
                    }
                    if (bans[14].getPosx() >= 76) {
                        bans[14].setIndo(true);
                    }
                } else {
                    for (int i = 14; i < 18; i++) {
                        bans[i].setPosx(bans[14].getPosx() - bans[14].getVelocidade());
                    }
                    if (bans[14].getPosx() <= -76) {
                        bans[14].setIndo(false);
                    }
                }
                if (!bans[18].indo()) {
                    for (int i = 18; i < 20; i++) {
                        bans[i].setPosx(bans[18].getPosx() - bans[14].getVelocidade());
                    }
                    if (bans[18].getPosx() <= -76) {
                        bans[18].setIndo(true);
                    }
                } else {
                    for (int i = 18; i < 20; i++) {
                        bans[i].setPosx(bans[18].getPosx() + bans[14].getVelocidade());
                    }
                    if (bans[18].getPosx() >= 76) {
                        bans[18].setIndo(false);
                    }
                }
                break;
            case 4:
                bans[0].setVelocidade(0.6f + Main.dif);
                if (!bans[0].indo()) {
                    bans[0].setPosy(bans[0].getPosy() - bans[0].getVelocidade());
                    if (bans[0].getPosy() <= -43) {
                        bans[0].setIndo(true);
                    }
                } else {
                    bans[0].setPosy(bans[0].getPosy() + bans[0].getVelocidade());
                    if (bans[0].getPosy() >= -0) {
                        bans[0].setIndo(false);
                    }
                }

                bans[1].setVelocidade(0.2f + Main.dif);
                if (!bans[1].indo()) {
                    bans[1].setPosx(bans[1].getPosx() - bans[1].getVelocidade());
                    if (bans[1].getPosx() <= -75) {
                        bans[1].setIndo(true);
                    }
                } else {
                    bans[1].setPosx(bans[1].getPosx() + bans[1].getVelocidade());
                    if (bans[1].getPosx() >= -54) {
                        bans[1].setIndo(false);
                    }
                }

                bans[2].setVelocidade(0.2f + Main.dif);
                if (!bans[2].indo()) {
                    bans[2].setPosx(bans[2].getPosx() - bans[2].getVelocidade());
                    bans[3].setPosx(bans[3].getPosx() + bans[2].getVelocidade());
                    if (bans[2].getPosx() <= -75) {
                        bans[2].setIndo(true);
                    }
                } else {
                    bans[2].setPosx(bans[2].getPosx() + bans[2].getVelocidade());
                    bans[3].setPosx(bans[3].getPosx() - bans[2].getVelocidade());
                    if (bans[2].getPosx() >= -54) {
                        bans[2].setIndo(false);
                    }
                }

                bans[4].setVelocidade(0.2f + Main.dif);
                if (!bans[4].indo()) {
                    bans[4].setPosx(bans[4].getPosx() + bans[4].getVelocidade());
                    bans[4].setPosy(bans[4].getPosy() - bans[4].getVelocidade());
                    if (bans[4].getPosx() >= -55) {
                        bans[4].setIndo(true);
                    }
                } else {
                    bans[4].setPosx(bans[4].getPosx() - bans[4].getVelocidade());
                    bans[4].setPosy(bans[4].getPosy() + bans[4].getVelocidade());
                    if (bans[4].getPosx() <= -75) {
                        bans[4].setIndo(false);
                    }
                }

                bans[5].setVelocidade(0.1f + Main.dif);
                if (!bans[5].indo()) {
                    bans[5].setPosy(bans[5].getPosy() - bans[5].getVelocidade());
                    bans[6].setPosy(bans[6].getPosy() - bans[5].getVelocidade());
                    if (bans[5].getPosy() <= 55) {
                        bans[5].setIndo(true);
                    }
                } else {
                    bans[5].setPosy(bans[5].getPosy() + bans[5].getVelocidade());
                    bans[6].setPosy(bans[6].getPosy() + bans[5].getVelocidade());
                    if (bans[5].getPosy() >= 75) {
                        bans[5].setIndo(false);
                    }
                }

                bans[7].setVelocidade(0.3f + Main.dif);
                if (!bans[7].indo()) {
                    bans[7].setPosx(bans[7].getPosx() - bans[7].getVelocidade());
                    bans[8].setPosx(bans[8].getPosx() + bans[7].getVelocidade());
                    if (bans[7].getPosx() <= -30) {
                        bans[7].setIndo(true);
                    }
                } else {
                    bans[7].setPosx(bans[7].getPosx() + bans[7].getVelocidade());
                    bans[8].setPosx(bans[8].getPosx() - bans[7].getVelocidade());
                    if (bans[7].getPosx() >= 36) {
                        bans[7].setIndo(false);
                    }
                }

                bans[9].setVelocidade(0.3f + Main.dif);
                if (!bans[9].indo()) {
                    bans[9].setPosy(bans[9].getPosy() - bans[9].getVelocidade());
                    if (bans[9].getPosy() <= 5) {
                        bans[9].setIndo(true);
                    }
                } else {
                    bans[9].setPosy(bans[9].getPosy() + bans[9].getVelocidade());
                    if (bans[9].getPosy() >= 45) {
                        bans[9].setIndo(false);
                    }
                }

                bans[10].setVelocidade(0.3f + Main.dif);
                if (!bans[10].indo()) {
                    bans[10].setPosx(bans[10].getPosx() - bans[10].getVelocidade());
                    bans[11].setPosx(bans[11].getPosx() + bans[10].getVelocidade());
                    if (bans[10].getPosx() <= 20) {
                        bans[10].setIndo(true);
                    }
                } else {
                    bans[10].setPosx(bans[10].getPosx() + bans[10].getVelocidade());
                    bans[11].setPosx(bans[11].getPosx() - bans[10].getVelocidade());
                    if (bans[10].getPosx() >= 90) {
                        bans[10].setIndo(false);
                    }
                }

                bans[12].setVelocidade(0.3f + Main.dif);
                if (!bans[12].indo()) {
                    bans[12].setPosy(bans[12].getPosy() - bans[12].getVelocidade());
                    if (bans[12].getPosy() <= -25) {
                        bans[12].setIndo(true);
                    }
                } else {
                    bans[12].setPosy(bans[12].getPosy() + bans[12].getVelocidade());
                    if (bans[12].getPosy() >= 20) {
                        bans[12].setIndo(false);
                    }
                }
                break;
        }
    }

    public ArrayList<Mapa> getSetores() {
        return setores;
    }

    public void percorreLvl() {
        caminhavel.clear();
        for (int r = 0; r < setores.size(); r++) {
            for (int j = setores.get(r).getPoint(0).getPosx(); j < setores.get(r).getPoint(2).getPosx(); j += 1) {
                for (int i = setores.get(r).getPoint(0).getPosy(); i > setores.get(r).getPoint(2).getPosy(); i -= 1) {
                    caminhavel.add(new Pixel(j, i));
                }
            }
        }
    }

    public void percorreSetorFinal() {
        setorFinal.clear();
        for (int j = setores.get(1).getPoint(0).getPosx(); j < setores.get(1).getPoint(2).getPosx(); j += 1) {
            for (int i = setores.get(1).getPoint(0).getPosy(); i > setores.get(1).getPoint(2).getPosy(); i -= 1) {
                setorFinal.add(new Pixel(j, i));
            }
        }
    }

    public void drawLvl(GL gl) {
        switch (this.level) {
            case 1:
                setores.clear();
                map = new Mapa();
                Mapa setor1 = new Mapa();
                Mapa setor2 = new Mapa();
                Mapa setor3 = new Mapa();
                Mapa setor4 = new Mapa();
                Mapa setor5 = new Mapa();

                Ponto p1 = new Ponto(-60, 50);
                Ponto p2 = new Ponto(40, 50);
                Ponto p3 = new Ponto(40, 60);
                Ponto p4 = new Ponto(100, 60);
                Ponto p5 = new Ponto(100, -20);
                Ponto p6 = new Ponto(60, -20);
                Ponto p7 = new Ponto(60, 50);
                Ponto p8 = new Ponto(50, 50);
                Ponto p9 = new Ponto(50, -10);
                Ponto p10 = new Ponto(-50, -10);
                Ponto p11 = new Ponto(-50, -20);
                Ponto p12 = new Ponto(-110, -20);
                Ponto p13 = new Ponto(-110, -20);
                Ponto p14 = new Ponto(-110, 60);
                Ponto p15 = new Ponto(-70, 60);
                Ponto p16 = new Ponto(-70, -10);
                Ponto p17 = new Ponto(-60, -10);

                map.addPoint(p1);
                map.addPoint(p2);
                map.addPoint(p3);
                map.addPoint(p4);
                map.addPoint(p5);
                map.addPoint(p6);
                map.addPoint(p7);
                map.addPoint(p8);
                map.addPoint(p9);
                map.addPoint(p10);
                map.addPoint(p11);
                map.addPoint(p12);
                map.addPoint(p13);
                map.addPoint(p14);
                map.addPoint(p15);
                map.addPoint(p16);
                map.addPoint(p17);

                setor1.addPoint(p1);
                setor1.addPoint(p8);
                setor1.addPoint(p9);
                setor1.addPoint(p17);

                setor2.addPoint(p3);
                setor2.addPoint(new Ponto(60, 60));
                setor2.addPoint(p7);
                setor2.addPoint(p2);

                setor3.addPoint(new Ponto(60, 60));
                setor3.addPoint(p4);
                setor3.addPoint(p5);
                setor3.addPoint(p6);

                setor4.addPoint(new Ponto(p16.getPosx() - 1, p16.getPosy() + 1));
                setor4.addPoint(new Ponto(p10.getPosx(), p10.getPosy() + 1));
                setor4.addPoint(p11);
                setor4.addPoint(new Ponto(-71, -20));

                setor5.addPoint(p14);
                setor5.addPoint(p15);
                setor5.addPoint(new Ponto(-70, -20));
                setor5.addPoint(p13);

                setores.add(setor5);
                setores.add(setor3);
                setores.add(setor1);
                setores.add(setor2);
                setores.add(setor4);

                setor1.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1);
                setor2.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1);
                setor3.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                setor4.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1);
                setor5.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                map.drawMap(gl, gl.GL_LINE_LOOP, 0, 0, 0);

                for (int i = 0; i < 6; i++) {
                    bans[i].drawBolas(gl, bans[i].getPosx(), bans[i].getPosy());
                }
                movimentaBolas();
                if (Main.agente.mesmaPos(bans, 6)) {
                    colidiu = true;
//                    salvaCronometro(1);
                }
                if (!setorFinal.isEmpty()) {
                    if (Main.agente.verificaFinal(setorFinal)) {
                        Main.fase = 2;
                        Main.inicializa = true;
                        trabalhofinal.Listener.clique = false;
                        salvaCronometro(1);
                    }
                }
                break;
            case 2:
                setores.clear();
                map = new Mapa();
                Mapa setor6 = new Mapa();
                Mapa setor7 = new Mapa();
                Mapa setor8 = new Mapa();

                Ponto p18 = new Ponto(-80, 60);
                Ponto p19 = new Ponto(80, 60);
                Ponto p20 = new Ponto(80, 30);
                Ponto p21 = new Ponto(110, 30);
                Ponto p22 = new Ponto(110, 0);
                Ponto p23 = new Ponto(80, 0);
                Ponto p24 = new Ponto(80, -30);
                Ponto p25 = new Ponto(-80, -30);
                Ponto p26 = new Ponto(-80, 0);
                Ponto p27 = new Ponto(-110, 0);
                Ponto p28 = new Ponto(-110, 30);
                Ponto p29 = new Ponto(-80, 30);

                map.addPoint(p18);
                map.addPoint(p19);
                map.addPoint(p20);
                map.addPoint(p21);
                map.addPoint(p22);
                map.addPoint(p23);
                map.addPoint(p24);
                map.addPoint(p25);
                map.addPoint(p26);
                map.addPoint(p27);
                map.addPoint(p28);
                map.addPoint(p29);

                setor6.addPoint(p18);
                setor6.addPoint(p19);
                setor6.addPoint(p24);
                setor6.addPoint(p25);

                setor7.addPoint(p20);
                setor7.addPoint(p21);
                setor7.addPoint(p22);
                setor7.addPoint(p23);

                setor8.addPoint(p28);
                setor8.addPoint(p27);
                setor8.addPoint(p26);
                setor8.addPoint(p29);

                setores.add(setor8);
                setores.add(setor7);
                setores.add(setor6);

                setor6.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1);
                setor7.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                setor8.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                map.drawMap(gl, gl.GL_LINE_LOOP, 0, 0, 0);

                for (int i = 0; i < 14; i++) {
                    bans[i].drawBolas(gl, bans[i].getPosx(), bans[i].getPosy());
                }
                movimentaBolas();

                if (Main.agente.mesmaPos(bans, 14)) {
                    colidiu = true;
//                    salvaCronometro(2);
                }
                if (!setorFinal.isEmpty()) {
                    if (Main.agente.verificaFinal(setorFinal)) {
                        Main.fase = 3;
                        Main.inicializa = true;
                        trabalhofinal.Listener.clique = false;
                        salvaCronometro(2);
                    }
                }
                break;
            case 3:
                setores.clear();
                map = new Mapa();
                Mapa setor9 = new Mapa();
                Mapa setor10 = new Mapa();
                Mapa setor11 = new Mapa();

                Ponto p30 = new Ponto(-80, 60);
                Ponto p31 = new Ponto(80, 60);
                Ponto p32 = new Ponto(80, 30);
                Ponto p33 = new Ponto(110, 30);
                Ponto p34 = new Ponto(110, 0);
                Ponto p35 = new Ponto(80, 0);
                Ponto p36 = new Ponto(80, -30);
                Ponto p37 = new Ponto(-80, -30);
                Ponto p38 = new Ponto(-80, 0);
                Ponto p39 = new Ponto(-110, 0);
                Ponto p40 = new Ponto(-110, 30);
                Ponto p41 = new Ponto(-80, 30);

                map.addPoint(p30);
                map.addPoint(p31);
                map.addPoint(p32);
                map.addPoint(p33);
                map.addPoint(p34);
                map.addPoint(p35);
                map.addPoint(p36);
                map.addPoint(p37);
                map.addPoint(p38);
                map.addPoint(p39);
                map.addPoint(p40);
                map.addPoint(p41);

                setor9.addPoint(p30);
                setor9.addPoint(p31);
                setor9.addPoint(p36);
                setor9.addPoint(p37);

                setor10.addPoint(p32);
                setor10.addPoint(p33);
                setor10.addPoint(p34);
                setor10.addPoint(p35);

                setor11.addPoint(p40);
                setor11.addPoint(p39);
                setor11.addPoint(p38);
                setor11.addPoint(p41);

                setores.add(setor11);
                setores.add(setor10);
                setores.add(setor9);

                setor9.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1);
                setor10.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                setor11.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                map.drawMap(gl, gl.GL_LINE_LOOP, 0, 0, 0);

                for (int i = 0; i < 20; i++) {
                    bans[i].drawBolas(gl, bans[i].getPosx(), bans[i].getPosy());
                }
                movimentaBolas();

                if (Main.agente.mesmaPos(bans, 20)) {
                    colidiu = true;
//                    salvaCronometro(3);
                }
                if (!setorFinal.isEmpty()) {
                    if (Main.agente.verificaFinal(setorFinal)) {
                        Main.fase = 4;
                        Main.inicializa = true;
                        trabalhofinal.Listener.clique = false;
                        salvaCronometro(3);
                    }
                }
                break;
            case 4:
                setores.clear();
                map = new Mapa();

                Mapa setor12 = new Mapa();
                Mapa setor13 = new Mapa();
                Mapa setor14 = new Mapa();
                Mapa setor15 = new Mapa();
                Mapa setor16 = new Mapa();
                Mapa setor17 = new Mapa();
                Mapa setor18 = new Mapa();

                Ponto p42 = new Ponto(-120, -20);
                Ponto p43 = new Ponto(-80, -20);
                Ponto p44 = new Ponto(-80, 80);
                Ponto p45 = new Ponto(40, 80);
                Ponto p46 = new Ponto(40, 30);
                Ponto p47 = new Ponto(100, 30);
                Ponto p48 = new Ponto(100, -30);
                Ponto p49 = new Ponto(50, -30);
                Ponto p50 = new Ponto(50, -10);
                Ponto p51 = new Ponto(70, -10);
                Ponto p52 = new Ponto(70, 0);
                Ponto p53 = new Ponto(10, 0);
                Ponto p54 = new Ponto(10, 50);
                Ponto p55 = new Ponto(-50, 50);
                Ponto p56 = new Ponto(-50, -50);
                Ponto p57 = new Ponto(-120, -50);

                map.addPoint(p42);
                map.addPoint(p43);
                map.addPoint(p44);
                map.addPoint(p45);
                map.addPoint(p46);
                map.addPoint(p47);
                map.addPoint(p48);
                map.addPoint(p49);
                map.addPoint(p50);
                map.addPoint(p51);
                map.addPoint(p52);
                map.addPoint(p53);
                map.addPoint(p54);
                map.addPoint(p55);
                map.addPoint(p56);
                map.addPoint(p57);

                setor12.addPoint(p42);
                setor12.addPoint(p43);
                setor12.addPoint(new Ponto(-80, -50));
                setor12.addPoint(p57);

                setor13.addPoint(p44);
                setor13.addPoint(new Ponto(-50, 80));
                setor13.addPoint(p56);
                setor13.addPoint(new Ponto(-80, -50));

                setor14.addPoint(p44);
                setor14.addPoint(p45);
                setor14.addPoint(new Ponto(40, 50));
                setor14.addPoint(new Ponto(-80, 50));

                setor15.addPoint(new Ponto(10, 80));
                setor15.addPoint(p45);
                setor15.addPoint(new Ponto(40, -00));
                setor15.addPoint(p53);

                setor16.addPoint(new Ponto(10, 30));
                setor16.addPoint(p47);
                setor16.addPoint(new Ponto(100, 00));
                setor16.addPoint(p53);

                setor17.addPoint(new Ponto(70, 30));
                setor17.addPoint(p47);
                setor17.addPoint(p48);
                setor17.addPoint(new Ponto(70, -30));

                setor18.addPoint(p50);
                setor18.addPoint(new Ponto(70, -10));
                setor18.addPoint(new Ponto(70, -30));
                setor18.addPoint(p49);

                setores.add(setor12);
                setores.add(setor18);
                setores.add(setor14);
                setores.add(setor15);
                setores.add(setor16);
                setores.add(setor17);
                setores.add(setor13);

                setor12.drawMap(gl, gl.GL_QUADS, 0, 1f, 0.5f);
                setor13.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1f);
                setor14.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1f);
                setor15.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1f);
                setor16.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1f);
                setor17.drawMap(gl, gl.GL_QUADS, 0, 0.5f, 1f);
                setor18.drawMap(gl, gl.GL_QUADS, 0, 1, 0.5f);
                map.drawMap(gl, gl.GL_LINE_LOOP, 0, 0, 0);

                for (int i = 0; i < 13; i++) {
                    bans[i].drawBolas(gl, bans[i].getPosx(), bans[i].getPosy());
                }
                movimentaBolas();

                if (Main.agente.mesmaPos(bans, 13)) {
                    colidiu = true;
//                    salvaCronometro(4);
                }
                if (!setorFinal.isEmpty()) {
                    if (Main.agente.verificaFinal(setorFinal)) {
                        Main.fase = 666;
                        Main.inicializa = true;
                        trabalhofinal.Listener.clique = false;
                        salvaCronometro(4);
                    }
                }
                break;
            case 666:
                Main.iniciou = false;
                Main.fim = true;
                break;
        }
    }
}

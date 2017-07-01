/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import trabalhofinal.Mapa.Pixel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author henrique
 */
public class Listener implements KeyListener, MouseListener {

    public static boolean clique;
    public static KeyEvent e;
    public static List<String> pressedList = new ArrayList<>();

    static void clique() {
        if (clique) {
            if (pressedList.isEmpty()) {
                cliqueDuplo(e.getKeyCode());
            } else if (pressedList.size() == 1) {
                cliqueDuplo(Integer.valueOf(pressedList.get(0)));
            } else if (pressedList.size() > 1) {
                cliqueDuplo(Integer.valueOf(pressedList.get(0)));
                cliqueDuplo(Integer.valueOf(pressedList.get(1)));
            }
        }
    }

    private static void cliqueDuplo(Integer e) {
        Pixel p;
        if (e == KeyEvent.VK_RIGHT) {
            p = new Pixel(Main.agente.getPosx() + Main.agente.getLateral() + 1, Main.agente.getPosy());
            if (p.contains(Main.lvl.getCaminhavel())) {
                Main.agente.setPosx(Main.agente.getPosx() + 2);
            }
        }
        if (e == KeyEvent.VK_LEFT) {
            p = new Pixel(Main.agente.getPosx() + -Main.agente.getLateral() - 1, Main.agente.getPosy());
            if (p.contains(Main.lvl.getCaminhavel())) {
                Main.agente.setPosx(Main.agente.getPosx() - 2);
            }
        }
        if (e == KeyEvent.VK_UP) {
            p = new Pixel(Main.agente.getPosx(), Main.agente.getPosy() + Main.agente.getLateral() + 1);
            if (p.contains(Main.lvl.getCaminhavel())) {
                Main.agente.setPosy(Main.agente.getPosy() + 2);
            }
        }
        if (e == KeyEvent.VK_DOWN) {
            p = new Pixel(Main.agente.getPosx(), Main.agente.getPosy() + -Main.agente.getLateral() - 1);
            if (p.contains(Main.lvl.getCaminhavel())) {
                Main.agente.setPosy(Main.agente.getPosy() - 2);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Main.menu) {
            if (e.getKeyCode() == KeyEvent.VK_2) {
                Main.escolheFaze = true;
                Main.menu = false;
                Main.tempos = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_1) {
                Main.dificuldade = true;
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.fase = 1;
                Main.tempos = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_3) {
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.tempos = true;
                Main.dificuldade = false;
            }
        } else if (Main.escolheFaze) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                Main.dificuldade = true;
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.fase = 1;
            } else if (e.getKeyCode() == KeyEvent.VK_2) {
                Main.dificuldade = true;
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.fase = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_3) {
                Main.dificuldade = true;
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.fase = 3;
            } else if (e.getKeyCode() == KeyEvent.VK_4) {
                Main.dificuldade = true;
                Main.iniciou = false;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.fase = 4;
            }
        } else if (Main.dificuldade) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                Main.dificuldade = false;
                Main.iniciou = true;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.dif = 0;
                Main.difSalvar = "Fácil";
            } else if (e.getKeyCode() == KeyEvent.VK_2) {
                Main.dificuldade = false;
                Main.iniciou = true;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.dif = 0.5f;
                Main.difSalvar = "Médio";
            } else if (e.getKeyCode() == KeyEvent.VK_3) {
                Main.dificuldade = false;
                Main.iniciou = true;
                Main.escolheFaze = false;
                Main.menu = false;
                Main.dif = 0.7f;
                Main.difSalvar = "Difícil";
            }
        } else {
            if (pressedList.size() <= 2) {
                if (!pressedList.contains(String.valueOf(e.getKeyCode()))) {
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        pressedList.add(String.valueOf(e.getKeyCode()));
                    }
                }
            }
            this.e = e;
            clique = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.dificuldade = false;
            Main.iniciou = false;
            Main.escolheFaze = false;
            Main.menu = true;
            Main.inicializa = true;
            Main.lvl.setColidiu(false);
            Main.tempos = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!pressedList.isEmpty()) {
            pressedList.remove(String.valueOf(e.getKeyCode()));
        }
        if (pressedList.isEmpty()) {
            clique = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Main.menu) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getX() >= 270 && e.getX() <= 481) {
                    if (e.getY() >= 212 && e.getY() <= 249) {
                        Main.dificuldade = true;
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.fase = 1;
                        Main.tempos = false;
                    }
                }
                if (e.getX() >= 271 && e.getX() <= 484) {
                    if (e.getY() >= 268 && e.getY() <= 303) {
                        Main.escolheFaze = true;
                        Main.menu = false;
                        Main.tempos = false;
                    }
                }
                if (e.getX() >= 271 && e.getX() <= 382) {
                    if (e.getY() >= 324 && e.getY() <= 363) {
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.tempos = true;
                    }
                }
            }
        } else if (Main.escolheFaze) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getX() >= 376 && e.getX() <= 392) {
                    if (e.getY() >= 139 && e.getY() <= 161) {
                        Main.dificuldade = true;
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.fase = 1;
                    }
                }
                if (e.getX() >= 376 && e.getX() <= 392) {
                    if (e.getY() >= 177 && e.getY() <= 199) {
                        Main.dificuldade = true;
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.fase = 2;
                    }
                }
                if (e.getX() >= 376 && e.getX() <= 392) {
                    if (e.getY() >= 215 && e.getY() <= 236) {
                        Main.dificuldade = true;
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.fase = 3;
                    }
                }
                if (e.getX() >= 376 && e.getX() <= 392) {
                    if (e.getY() >= 252 && e.getY() <= 275) {
                        Main.dificuldade = true;
                        Main.iniciou = false;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.fase = 4;
                    }
                }
            }
        } else if (Main.dificuldade) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getX() >= 336 && e.getX() <= 420) {
                    if (e.getY() >= 139 && e.getY() <= 161) {
                        Main.dificuldade = false;
                        Main.iniciou = true;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.dif = 0;
                        Main.difSalvar = "Fácil";
                    }
                }
                if (e.getX() >= 336 && e.getX() <= 425) {
                    if (e.getY() >= 177 && e.getY() <= 199) {
                        Main.dificuldade = false;
                        Main.iniciou = true;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.dif = 0.5f;
                        Main.difSalvar = "Médio";
                    }
                }
                if (e.getX() >= 336 && e.getX() <= 440) {
                    if (e.getY() >= 215 && e.getY() <= 236) {
                        Main.dificuldade = false;
                        Main.iniciou = true;
                        Main.escolheFaze = false;
                        Main.menu = false;
                        Main.dif = 0.7f;
                        Main.difSalvar = "Difícil";
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

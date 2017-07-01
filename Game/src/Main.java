package trabalhofinal;

import trabalhofinal.Mapa.Tempo;
import java.awt.BorderLayout;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import trabalhofinal.Agente.Agente;
import trabalhofinal.Mapa.Level;

public class Main extends GLCanvas implements GLEventListener {
    
    
    private int fps = 60;

    GLU glu = new GLU();
    public static boolean menu = true;
    public static boolean escolheFaze = false;
    public static boolean iniciou = false;
    public static int fase = 1;
    public static boolean inicializa = true;
    public static boolean tempos = false;
    public static boolean fim = false;
    public static boolean dificuldade = false;
    public static float dif = 1;
    public static String difSalvar;

    int cont = 0;
    boolean salvar = true;

    private static ArrayList<Tempo> temposSalvos;

    @Override
    public void display(GLAutoDrawable drawable) {
        if (!animator.isAnimating()) {
            return;
        }
        final GL gl = drawable.getGL();
        frame.setTitle("O Jogo Mais Difícil do Mundo -- FPS:" + (calcFPS()));
        // Clear screen.
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        if (cont == 0) {
            Listener.clique();
            cont++;
        } else if (cont == 1) {
            cont = 0;
        }
        // Set camera.
        setCamera(gl, glu, 300);
        if (menu) {
            salvar = true;
            gl.glClearColor(1f, 1f, 1f, 0f);
            gl.glColor3f(0f, 0f, 0f);
            gl.glRasterPos2i(-100, 70);
            for (int i = 0; i < sbTitulo.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, sbTitulo.charAt(i));
            }
            gl.glRasterPos2i(-60, -30);
            for (int i = 0; i < sbFaze.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, sbFaze.charAt(i));
            }
            gl.glRasterPos2i(-60, 0);
            for (int i = 0; i < sbInicio.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, sbInicio.charAt(i));
            }
            gl.glRasterPos2i(-60, -60);
            for (int i = 0; i < sbTempos.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, sbTempos.charAt(i));
            }
        } else if (!tempos) {
            if (escolheFaze) {
                gl.glColor3f(0f, 0f, 0f);
                gl.glRasterPos2i(-50, 70);
                for (int i = 0; i < sbEscolheFaze.length(); i++) {
                    glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, sbEscolheFaze.charAt(i));
                }
                gl.glRasterPos2i(-10, 40);
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, '1');
                gl.glRasterPos2i(-10, 20);
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, '2');
                gl.glRasterPos2i(-10, 00);
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, '3');
                gl.glRasterPos2i(-10, -20);
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, '4');
            }
            if (iniciou) {
                if (inicializa) {
                    Listener.clique = false;
                    Listener.pressedList.clear();
                    lvl.setLevel(fase);
                    lvl.drawLvl(gl);
                    lvl.percorreLvl();
                    lvl.percorreSetorFinal();
                    inicializa = false;
                    cont1 = 0;
                } else {
                    if (!lvl.colidiu()) {
                        lvl.drawLvl(gl);
                        lvl.movimentaBolas();
                        agente.drawAgente(gl, agente.getPosx(), agente.getPosy());

                        gl.glColor3f(0f, 0f, 0f);
                        gl.glRasterPos2i(60, 105);
                        StringBuilder tempo = new StringBuilder(lvl.getTempoGasto());
                        for (int i = 0; i < tempo.length(); i++) {
                            glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, tempo.charAt(i));
                        }

                        gl.glRasterPos2i(-120, 105);
                        StringBuilder numFaze = new StringBuilder("Fase: " + fase);
                        for (int i = 0; i < numFaze.length(); i++) {
                            glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, numFaze.charAt(i));
                        }
                    } else {
                        gl.glClearColor(1f, 0f, 0f, 0f);
                        gl.glColor3f(0f, 0f, 0f);
                        gl.glRasterPos2i(-30, 30);
                        for (int i = 0; i < sbErro.length(); i++) {
                            glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, sbErro.charAt(i));
                        }
                        gl.glRasterPos2i(-65, 0);

                        for (int i = 0; i < sbVolta.length(); i++) {
                            glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, sbVolta.charAt(i));
                        }
                        if (cont1 == 30) {
                            if (salvar) {
                                salvar = false;
                                salvar();
                                cont1 = 0;
                            }
                        } else {
                            cont1++;
                        }
                    }
                }
            } else if (fim) {
                if (cont1 == 30) {
                    JOptionPane.showMessageDialog(null, "Fim!!\nParabéns!!");
                    salvar();
                    Main.iniciou = false;
                    Main.escolheFaze = false;
                    Main.menu = true;
                    Main.inicializa = true;
                    Main.lvl.setColidiu(false);
                    Main.tempos = false;
                    Main.cont1 = 0;
                } else {
                    cont1++;
                }
            }
        } else if (tempos) {
            if (GUItempos == null) {
                GUItempos = new GUITempos();
                ArrayList<Tempo> t = lerArquivo();
                for (Tempo tempo : t) {
                    GUItempos.preencheTabela(tempo);
                }
                GUItempos.setVisible(true);
            } else {
                Main.menu = true;
                Main.tempos = false;
                GUItempos = null;
            }
        }
        if (dificuldade) {
            gl.glColor3f(0f, 0f, 0f);
            gl.glRasterPos2i(-60, 70);
            for (int i = 0; i < sbEscolheDif.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, sbEscolheDif.charAt(i));
            }
            StringBuilder dif = new StringBuilder("1 - Fácil");
            gl.glRasterPos2i(-30, 40);
            for (int i = 0; i < dif.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, dif.charAt(i));
            }
            dif = new StringBuilder("2 - Médio");
            gl.glRasterPos2i(-30, 20);
            for (int i = 0; i < dif.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, dif.charAt(i));
            }
            dif = new StringBuilder("3 - Difícil");
            gl.glRasterPos2i(-30, 00);
            for (int i = 0; i < dif.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_9_BY_15, dif.charAt(i));
            }
        }
    }

    public static void salvar() {
        Tempo t = new Tempo();
        t.setNome(JOptionPane.showInputDialog("Digite seu nome"));
        if (t.getNome() == null) {
            return;
        }
        t.setDificuldade(difSalvar);
        ArrayList<String> al = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < lvl.getTemposFinais().size();) {
            if (lvl.getTemposFinais().containsKey(j)) {
                al.add(lvl.getTemposFinais().get(j));
                j++;
                i++;
            } else {
                al.add("");
                j++;
            }
        }
        t.setTempos(al);
        temposSalvos.add(t);
        salvarArquivo();
    }

    GUITempos GUItempos = null;
    public static int cont1 = 0;

    private static void salvarArquivo() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("./tempos.har")));
            oos.writeObject(temposSalvos);
        } catch (Exception ex) {
            System.out.println("Erro ao gravar arquivo");
        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar arquivo");
            }
        }
    }

    private ArrayList<Tempo> lerArquivo() {
        ObjectInputStream ois = null;
        ArrayList<Tempo> tempo = new ArrayList<>();
        try {
            File file = new File("./tempos.har");
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                tempo = (ArrayList<Tempo>) ois.readObject();
                ois.close();
            }
        } catch (Exception ex) {
            System.out.println("Erro ao ler arquivo");
        }
        return tempo;
    }

    public static Level lvl;
    public static Agente agente;

    private static final long serialVersionUID = 1L;
    /**
     * Quagentedros por segundo.
     */
    /**
     * O OpenGL agentenimagentetor.
     */
    private FPSAnimator animator;

    /**
     * Novagentes definições
     *
     * cagentepagentebilities The GL cagentepagentebilities. width
     * Lagenterguragente dagente jagentenelagente. height Alturagente dagente
     * jagentenelagente.
     */
    public Main(GLCapabilities capabilities, int width, int height) {
        addGLEventListener(this);
    }

    private static GLCapabilities createGLCapabilities() {
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setRedBits(8);
        capabilities.setBlueBits(8);
        capabilities.setGreenBits(8);
        capabilities.setAlphaBits(8);
        return capabilities;
    }

    StringBuilder sbTitulo;
    StringBuilder sbFaze;
    StringBuilder sbInicio;
    StringBuilder sbErro;
    StringBuilder sbEscolheFaze;
    StringBuilder sbVolta;
    StringBuilder sbTempos;
    StringBuilder sbEscolheDif;

    /**
     * Definições dagente Jagentenelagente.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        sbVolta = new StringBuilder("Pressione Esc para voltar");
        sbEscolheFaze = new StringBuilder("Escolha uma fase");
        sbTitulo = new StringBuilder("O JOGO MAIS DIFÍCIL DO MUNDO");
        sbFaze = new StringBuilder("2 - Escolher uma fase");
        sbInicio = new StringBuilder("1 - Começar do início");
        sbTempos = new StringBuilder("3 - Tempos");
        sbErro = new StringBuilder("Perdeu!!!");
        sbEscolheDif = new StringBuilder("Escolha uma dificuldade");
        drawable.setGL(new DebugGL(drawable.getGL()));
        final GL gl = drawable.getGL();

        // Enable z- (depth) buffer for hidden surface removal. 
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);

        // Enable smooth shading.
        gl.glShadeModel(GL.GL_SMOOTH);

        // Define "clear" color.
        gl.glClearColor(1f, 1f, 1f, 0f);

        // We want agente nice perspective.
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        // Create GLU.
        glu = new GLU();

        lvl = new Level();
        agente = new Agente();
        temposSalvos = new ArrayList<>();

        temposSalvos = lerArquivo();

        // Start animator.
        animator = new FPSAnimator(this, fps);
        animator.start();
    }
    GLUT glut = new GLUT();

    /**
     * Redefinir o Tagentemnho dagente Jagentenelagente/Telagente
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL gl = drawable.getGL();
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        throw new UnsupportedOperationException("Não é suportado ainda.");
    }

    /**
     * distagentence => Distâncis dagente telagente.
     */
    private void setCamera(GL gl, GLU glu, float distance) {
        //Chams agente Matriz de projecao.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Define agente perspectiva.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    private static JFrame frame;

    public final static void main(String[] args) {
        int altura = 800, largura = 500;
        GLCapabilities capabilities = createGLCapabilities();
        Main canvas = new Main(capabilities, altura, largura);
        canvas.addKeyListener(new Listener());
        canvas.addMouseListener(new Listener());
        frame = new JFrame("O Jogo Mais Difícil do Mundo");
        frame.setResizable(false);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.setSize(altura, largura);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        canvas.requestFocus();
    }
    /*
     *Fonte: http://stackoverflow.com/questions/87304/calculating-frames-per-second-in-a-game
     */
    private static long ONE_SECOND = 1000000L * 1000L; //1 second is 1000ms which is 1000000ns
    LinkedList<Long> frames = new LinkedList<>(); //List of frames within 1 second

    public int calcFPS() {
        long time = System.nanoTime(); //Current time in nano seconds
        frames.add(time); //Add this frame to the list
        while (true) {
            long f = frames.getFirst(); //Look at the first element in frames
            if (time - f > ONE_SECOND) { //If it was more than 1 second ago
                frames.remove(); //Remove it from the list of frames
            } else {
                break;
            }
            /*If it was within 1 second we know that all other frames in the list
             * are also within 1 second
             */
        }
        return frames.size(); //Return the size of the list
    }
}

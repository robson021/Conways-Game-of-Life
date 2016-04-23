import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private DrawingPanel drawingPanel;
    private JTextField textFieldX, textFieldY;
    private static JLabel infoLabel;
    private Thread lifeThread = null;
    private boolean isThreadRunning = false;
    private JButton startButton;

    public MainFrame() {
        super("Game of Life");
        this.setLayout(new BorderLayout());
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        JButton addButton = new JButton("Add new life");
        addButton.addActionListener(new AddButtonListener());
        JButton killButton = new JButton("Kill cell");
        killButton.addActionListener(new KillCellAction());

        southPanel.add(startButton);
        southPanel.add(killButton);
        southPanel.add(addButton);
        textFieldX = new JTextField(2);
        southPanel.add(new JLabel("x: "));
        southPanel.add(textFieldX);
        textFieldY = new JTextField(2);
        southPanel.add(new JLabel("y: "));
        southPanel.add(textFieldY);

        this.add(southPanel, BorderLayout.SOUTH);

        infoLabel = new JLabel("Nothing happend yet.");

        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(infoLabel);

        this.add(northPanel, BorderLayout.NORTH);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textFieldY.getText().equals("") || textFieldX.getText().equals("")) return;
            int x = Integer.parseInt(textFieldX.getText());
            int y = Integer.parseInt(textFieldY.getText());
            if (drawingPanel.addNewLife(x, y)) {
                drawingPanel.getFieldsAt(x, y).setBackground(Color.GREEN);
            } else {
                infoLabel.setText("Given coordinates are not correct!");
            }
        }
    }

    private class KillCellAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textFieldY.getText().equals("") || textFieldX.getText().equals("")) return;
            int x = Integer.parseInt(textFieldX.getText());
            int y = Integer.parseInt(textFieldY.getText());
            drawingPanel.killLife(x, y);
        }
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lifeThread == null || !lifeThread.isAlive()) {
                lifeThread = new Thread(new LifeRunnable());
                lifeThread.start();
                startButton.setText("Stop");
                System.out.println("Thread started.");
            } else {
                isThreadRunning = false;
                startButton.setEnabled(false);
            }
            //startButton.repaint();
        }
    }

    public static void updateInfo(String text) {
        infoLabel.setText(text);
    }

    private class LifeRunnable implements Runnable {
        public LifeRunnable() {
            isThreadRunning = true;
        }
        @Override
        public void run() {
            while (isThreadRunning) {
                CellPane cellPane;
                CellPane[][] cellPanes = drawingPanel.getCells();
                for (int j, i = 0; i < DrawingPanel.SIZE; i++) {
                    for (j = 0; j < DrawingPanel.SIZE; j++) {
                        cellPane = cellPanes[i][j];
                        cellPane.checkNeighbourhood();
                        try {
                            Thread.sleep(35); // sleep for slower screen update
                        } catch (InterruptedException e) {
                            System.out.println("Error - thread sleep try.");
                        }
                    }
                    System.out.println("\tAlive cells:");
                    System.out.println(getAliveCellsCords());
                }

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //isThreadRunning = false;
            }

            System.out.println("Thread stopped.");
            System.out.println("\n*******************************************");
            System.out.println("\n\tAlive cells:");
            System.out.println(getAliveCellsCords());
            startButton.setText("Start");
            startButton.setEnabled(true);
        }
    }

    private String getAliveCellsCords() {
        StringBuilder sb = new StringBuilder();
        int total = 0;
        CellPane[][] cells = DrawingPanel.getPanel().getCells();
        for (int j, i = 0; i < DrawingPanel.SIZE; i++)
            for (j = 0; j < DrawingPanel.SIZE; j++) {
                if (cells[i][j].isAlive()) {
                    ++total;
                    sb.append(cells[i][j].getCords());
                    sb.append("\n");
                }
            }
        sb.append("\tTotal: " + total);
        if (total == 0) isThreadRunning = false;
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame mainFrame = new MainFrame();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLocationRelativeTo(null);
                //mainFrame.setLocationByPlatform(true);
                mainFrame.setResizable(false);
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        });

    }


}

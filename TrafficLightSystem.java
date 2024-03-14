import javax.swing.*;
import java.awt.*;


class TrafficLight {
    enum Color { RED, YELLOW, GREEN }

    private Color color;

    public TrafficLight() {
        this.color = Color.RED;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

class PedestrianSignal {
    enum Color { WAIT, WALK }

    private Color color;

    public PedestrianSignal() {
        this.color = Color.WAIT;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

class TrafficController {
    private TrafficLight northSouthLight;
    private TrafficLight eastWestLight;

    private PedestrianSignal northSouthPedestrianSignal;
    private PedestrianSignal eastWestPedestrianSignal;

    // GUI Components
    private StopLightPanel northSouthLightPanel;
    private StopLightPanel eastWestLightPanel;
    private SimplePedestrianSignalPanel northSouthPedestrianSignalPanel;
    private SimplePedestrianSignalPanel eastWestPedestrianSignalPanel;

    public TrafficController(StopLightPanel northSouthLightPanel, StopLightPanel eastWestLightPanel, 
                             SimplePedestrianSignalPanel northSouthPedestrianSignalPanel, 
                             SimplePedestrianSignalPanel eastWestPedestrianSignalPanel) {
        this.northSouthLight = new TrafficLight();
        this.eastWestLight = new TrafficLight();
        this.northSouthPedestrianSignal = new PedestrianSignal();
        this.eastWestPedestrianSignal = new PedestrianSignal();

        this.northSouthLightPanel = northSouthLightPanel;
        this.eastWestLightPanel = eastWestLightPanel;
        this.northSouthPedestrianSignalPanel = northSouthPedestrianSignalPanel;
        this.eastWestPedestrianSignalPanel = eastWestPedestrianSignalPanel;
    }


public void runSimulation() {
    int greenDuration = 7; // seconds
    int yellowDuration = 3; // seconds
    int redDuration = 10;    

    while (true) {
        // North/South lights are green, East/West lights are red
        SwingUtilities.invokeLater(() -> {
            setLights(TrafficLight.Color.GREEN, TrafficLight.Color.RED);
            setPedestrianSignals(PedestrianSignal.Color.WALK, PedestrianSignal.Color.WAIT);
            updateGUI();
        });
        sleep(greenDuration);

        // Transition to yellow for North/South
        SwingUtilities.invokeLater(() -> {
            setLights(TrafficLight.Color.YELLOW, TrafficLight.Color.RED);
            setPedestrianSignals(PedestrianSignal.Color.WAIT, PedestrianSignal.Color.WAIT);
            updateGUI();
        });
        sleep(yellowDuration);

        // Transition to red for North/South, green for East/West
        SwingUtilities.invokeLater(() -> {
            setLights(TrafficLight.Color.RED, TrafficLight.Color.GREEN);
            setPedestrianSignals(PedestrianSignal.Color.WAIT, PedestrianSignal.Color.WALK);
            updateGUI();
        });
        sleep(redDuration - yellowDuration); 

        // Transition to yellow for East/West
        SwingUtilities.invokeLater(() -> {
            setLights(TrafficLight.Color.RED, TrafficLight.Color.YELLOW);
            setPedestrianSignals(PedestrianSignal.Color.WAIT, PedestrianSignal.Color.WAIT);
            updateGUI();
        });
        sleep(yellowDuration);
    }
}


private void updateGUI() {
    northSouthLightPanel.setLightColor(northSouthLight.getColor());
    eastWestLightPanel.setLightColor(eastWestLight.getColor());
    northSouthPedestrianSignalPanel.setSignal(northSouthPedestrianSignal.getColor());
    eastWestPedestrianSignalPanel.setSignal(eastWestPedestrianSignal.getColor());
}

    private void setLights(TrafficLight.Color northSouthColor, TrafficLight.Color eastWestColor) {
        northSouthLight.setColor(northSouthColor);
        eastWestLight.setColor(eastWestColor);
    }

    private void setPedestrianSignals(PedestrianSignal.Color northSouthColor, PedestrianSignal.Color eastWestColor) {
        northSouthPedestrianSignal.setColor(northSouthColor);
        eastWestPedestrianSignal.setColor(eastWestColor);
    }

    
    private void sleep(int seconds) {
            try {
                Thread.sleep(seconds*1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
    }
    
}

 class StopLightPanel extends JPanel {

    StopLightDrawing light = new StopLightDrawing();

    public StopLightPanel(){
        setLayout(new BorderLayout()); // Ensure the light component fills the panel
        add(light, BorderLayout.CENTER);
        setPreferredSize(new Dimension(160, 260)); // This should set the preferred size of the panel, not the light
    }

    public void setLightColor(TrafficLight.Color color) {
        light.setLightColor(color);
    }

}



 class StopLightDrawing extends JComponent {
    private Color go = Color.gray;
    private Color slow = Color.gray;
    private Color stop = Color.red; // Initial state is red

    public StopLightDrawing() {
        setPreferredSize(new Dimension(30, 50)); // This sets the preferred size for the drawing area
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 30, 50);

        // Border
        g.setColor(Color.black);
        g.drawRect(0, 0, 30, 50);

        // Red light
        g.setColor(stop);
        g.fillOval(10, 5, 10, 10);
        g.setColor(Color.black);
        g.drawOval(10, 5, 10, 10);

        // Yellow light
        g.setColor(slow);
        g.fillOval(10, 20, 10, 10);
                g.setColor(Color.black);
        g.drawOval(10, 20, 10, 10);

        // Green light
        g.setColor(go);
        g.fillOval(10, 35, 10, 10);
                g.setColor(Color.black);
        g.drawOval(10, 35, 10, 10);
    }

    public void setLightColor(TrafficLight.Color color) {
        // Reset all lights to gray
        stop = Color.gray;
        slow = Color.gray;
        go = Color.gray;

        // Set the specified light to its color
        switch (color) {
            case RED:
                stop = Color.red;
                break;
            case YELLOW:
                slow = Color.yellow;
                break;
            case GREEN:
                go = Color.green;
                break;
        }
        repaint(); 
    }
}

 class SimplePedestrianSignalPanel extends JPanel {
    private JLabel signalLabel;
    private boolean isWaitState;

    public SimplePedestrianSignalPanel() {
        // Initialize the signal in the "Wait" state
        isWaitState = true;

        // Create a label with the text "Wait"
        signalLabel = new JLabel("Wait", SwingConstants.CENTER);
        signalLabel.setOpaque(true);
        signalLabel.setBackground(Color.RED);
        signalLabel.setForeground(Color.WHITE);
        signalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(signalLabel);

        // Set the size of the panel
        this.setPreferredSize(new Dimension(50, 30));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    private void toggleSignal() {
        // Toggle the isWaitState boolean
        isWaitState = !isWaitState;

        // Update the label text and background color based on the state
        if (isWaitState) {
            signalLabel.setText("Wait");
            signalLabel.setBackground(Color.RED);
        } else {
            signalLabel.setText("Walk");
            signalLabel.setBackground(Color.GREEN);
        }
    }

    // based on isWaitState
    private void updateSignalDisplay() {
        if (isWaitState) {
            signalLabel.setText("Wait");
            signalLabel.setBackground(Color.RED);
        } else {
            signalLabel.setText("Walk");
            signalLabel.setBackground(Color.GREEN);
        }
        repaint();
    }


    public void setSignal(PedestrianSignal.Color color) {
        isWaitState = color == PedestrianSignal.Color.WAIT;
        updateSignalDisplay(); 
    }


}


class IntersectionPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawIntersection(g);
    }

    private void drawIntersection(Graphics g) {
        // Draw the roads
        g.setColor(Color.GRAY);
        g.fillRect(getWidth()/2-100, 0, 200, getHeight()); // ns road
        g.fillRect(0, getHeight()/2-100, getWidth(), 200); // ew road

        // Draw the pedestrian crossings
        g.setColor(Color.WHITE);
        g.fillRect(getWidth()/2-100, getHeight()/2-105, 200, 10); // East-West pedestrian crossing
        g.fillRect(getWidth()/2-100, getHeight()/2+95, 200, 10); // East-West pedestrian crossing
        g.fillRect(getWidth()/2-105, getHeight()/2-100, 10, 200); // North-South pedestrian crossing
        g.fillRect(getWidth()/2+95, getHeight()/2-100, 10, 200); // North-South pedestrian crossing
    }
}

      

        public class TrafficLightSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));

        IntersectionPanel intersectionPanel = new IntersectionPanel();
        intersectionPanel.setBounds(0, 0, 800, 800);
        layeredPane.add(intersectionPanel, JLayeredPane.DEFAULT_LAYER);

        // Directly create and keep references to the light and signal panels
        StopLightPanel northSouthLight = new StopLightPanel();
        StopLightPanel eastWestLight = new StopLightPanel();
        SimplePedestrianSignalPanel northSouthPedSignal = new SimplePedestrianSignalPanel();
        SimplePedestrianSignalPanel eastWestPedSignal = new SimplePedestrianSignalPanel();

        // Wrap panels with labels
        JPanel northSouthLightPanel = createLabeledPanel(northSouthLight, "N/S");
        JPanel eastWestLightPanel = createLabeledPanel(eastWestLight, "E/W");
        JPanel eastWestPedSignalPanel = createLabeledPanel(northSouthPedSignal, "E/W");
        JPanel northSouthPedSignalPanel = createLabeledPanel(eastWestPedSignal, "N/S");

        // Adjust bounds and add labeled panels to layeredPane
        northSouthLightPanel.setBounds(800/2-100,230 , 30, 65); 
        eastWestLightPanel.setBounds(800/2-135, 300, 30, 65); 
        northSouthPedSignalPanel.setBounds(450, 245, 50, 50); 
        eastWestPedSignalPanel.setBounds(505, 300, 50, 50); 

        layeredPane.add(northSouthLightPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(eastWestLightPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(northSouthPedSignalPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(eastWestPedSignalPanel, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
        frame.setVisible(true);

        // Initialize TrafficController with direct references
        TrafficController controller = new TrafficController(northSouthLight, eastWestLight, northSouthPedSignal, eastWestPedSignal);
        new Thread(controller::runSimulation).start();
    }

    private static JPanel createLabeledPanel(JComponent component, String labelText) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        panel.setOpaque(false); 
        component.setOpaque(false); 
        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }
}

        

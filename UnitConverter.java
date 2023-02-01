import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class UnitConverter extends JFrame {

	private String[] DistanceUnits = {"Metres (m)", "Yard (yd)", "Pieds (ft)", "Pouces (in)", "Kilometres (km)", "Centimetres (cm)", "Millimetres (mm)"};
	private String[] VitesseUnits = {"km/h", "m/s"};
	private String[] FrequenceUnits = {"Hertz (hz)", "Kilohertz (Khz)", "Megahertz (Mhz)", "Gigahertz (Ghz)"};
	private String[] DataUnits = {"Bits (bit)", "Octets (o)", "KiloOctets (Ko)", "MegaOctets (Mo)", "GigaOctets (Go)"};
	private String[] TimeUnits = {"Minutes (min)", "Secondes (s)", "Millisecondes (ms)", "Heures (h)", "Jours (jr)", "Semaines"};
	private JComboBox DistanceUnitsBox1 = new JComboBox<>(DistanceUnits);
	private JComboBox DistanceUnitsBox2 = new JComboBox<>(DistanceUnits);
	private JComboBox VitesseUnitsBox1 = new JComboBox<>(VitesseUnits);
	private JComboBox VitesseUnitsBox2 = new JComboBox<>(VitesseUnits);
	private JComboBox FrequenceUnitsBox1 = new JComboBox<>(FrequenceUnits);
	private JComboBox FrequenceUnitsBox2 = new JComboBox<>(FrequenceUnits);
	private JComboBox DataUnitsBox1 = new JComboBox<>(DataUnits);
	private JComboBox DataUnitsBox2 = new JComboBox<>(DataUnits);
	private JComboBox TimeUnitsBox1 = new JComboBox<>(TimeUnits);
	private JComboBox TimeUnitsBox2 = new JComboBox<>(TimeUnits);
	private JTextField DisttextField1 = new JTextField();
	private JTextField DisttextField2 = new JTextField();
	private JTextField VittextField1 = new JTextField();
	private JTextField VittextField2 = new JTextField();
	private JTextField FreqtextField1 = new JTextField();
	private JTextField FreqtextField2 = new JTextField();
	private JTextField DatatextField1 = new JTextField();
	private JTextField DatatextField2 = new JTextField();
	private JTextField TimetextField1 = new JTextField();
	private JTextField TimetextField2 = new JTextField();
	private JButton DistResetBtn = new JButton("Réinitialiser");
	private JButton VitResetBtn = new JButton("Réinitialiser");
	private JButton FreqResetBtn = new JButton("Réinitialiser");
	private JButton DataResetBtn = new JButton("Réinitialiser");
	private JButton TimeResetBtn = new JButton("Réinitialiser");
	private String Distancechoice1 = "Metres (m)";
	private String Distancechoice2 = "Metres (m)";
	private String Vitessechoice1 = "km/h";
	private String Vitessechoice2 = "km/h";
	private String Frequencechoice1 = "Hertz (hz)";
	private String Frequencechoice2 = "Hertz (hz)";
	private String Datachoice1 = "Bits (bit)";
	private String Datachoice2 = "Bits (bit)";
	private String Timechoice1 = "Minutes (min)";  
	private String Timechoice2 = "Minutes (min)"; 
	private JPanel p1; 
	
	public UnitConverter(JPanel p1){
		this.p1 = p1;
	}
	
    public JPanel unitconverterPanel(){
		
		this.p1.setLayout(new BorderLayout());
		this.p1.setBackground(Color.DARK_GRAY);

		JTabbedPane ucOnglets = new JTabbedPane();

		ucOnglets.addTab("Distance", CreateDistCalculZone());
		ucOnglets.addTab("Vitesse", CreateVitCalculZone());
		ucOnglets.addTab("Frequence", CreateFreqCalculZone());
		ucOnglets.addTab("Stockage Numérique", CreateDataCalculZone());
		ucOnglets.addTab("Temps", CreateTimeCalculZone());

		this.p1.add(ucOnglets);

		return this.p1;
    }
	
	//Méthodes de récupération des choix
	public String getDistanceChoice1(){ return Distancechoice1; }
	public String getDistanceChoice2(){ return Distancechoice2; }
	public String getVitesseChoice1(){ return Vitessechoice1; }
	public String getVitesseChoice2 (){ return Vitessechoice2;	}
	public String getFrequenceChoice1(){ return Frequencechoice1; }
	public String getFrequenceChoice2(){ return Frequencechoice2; }
	public String getDataChoice1(){ return Datachoice1; }
	public String getDataChoice2(){ return Datachoice2; }
	public String getTimeChoice1(){ return Timechoice1; }
	public String getTimeChoice2(){ return Timechoice2; }

	// Méthode de Création des zones de calcul (incluant les deux zones de texte)
	private JPanel CreateDistCalculZone(){
		JPanel DistCalculZone = new JPanel(new GridLayout(3, 1));

		DistCalculZone.setBackground(Color.DARK_GRAY);

		DistCalculZone.add(CreateDistCalculZone1()); 
		DistCalculZone.add(CreateDistCalculZone2());
		DistCalculZone.add(CreateDistButtonZone());

		return DistCalculZone;
	}
	private JPanel CreateVitCalculZone(){
		JPanel VitCalculZone = new JPanel(new GridLayout(3, 1));

		VitCalculZone.setBackground(Color.DARK_GRAY);

		VitCalculZone.add(CreateVitCalculZone1()); 
		VitCalculZone.add(CreateVitCalculZone2());
		VitCalculZone.add(CreateVitButtonZone());
		
		return VitCalculZone;
	}
	private JPanel CreateFreqCalculZone(){
		JPanel FreqCalculZone = new JPanel(new GridLayout(3, 1));

		FreqCalculZone.setBackground(Color.DARK_GRAY);

		FreqCalculZone.add(CreateFreqCalculZone1()); 
		FreqCalculZone.add(CreateFreqCalculZone2());
		FreqCalculZone.add(CreateFreqButtonZone());
		
		return FreqCalculZone;
	}
	private JPanel CreateDataCalculZone(){
		JPanel DataCalculZone = new JPanel(new GridLayout(3, 1));

		DataCalculZone.setBackground(Color.DARK_GRAY);

		DataCalculZone.add(CreateDataCalculZone1()); 
		DataCalculZone.add(CreateDataCalculZone2());
		DataCalculZone.add(CreateDataButtonZone());
		
		return DataCalculZone;
	}
	private JPanel CreateTimeCalculZone(){
		JPanel TimeCalculZone = new JPanel(new GridLayout(3, 1));

		TimeCalculZone.setBackground(Color.DARK_GRAY);

		TimeCalculZone.add(CreateTimeCalculZone1()); 
		TimeCalculZone.add(CreateTimeCalculZone2());
		TimeCalculZone.add(CreateTimeButtonZone());
		
		return TimeCalculZone;
	}

	private JPanel CreateDistCalculZone1(){
		JPanel DistCalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		DistCalculZone1.setBackground(Color.DARK_GRAY);
		DistanceUnitsBox1.setBackground(Color.DARK_GRAY);
		DistanceUnitsBox1.setFont(new Font("Serif", Font.BOLD, 14));
		DistanceUnitsBox1.setPreferredSize(new Dimension(180, 40));
		DisttextField1.setHorizontalAlignment(JTextField.RIGHT);
		DisttextField1.setBackground(Color.DARK_GRAY);
		DisttextField1.setForeground(Color.WHITE);
		DisttextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		DisttextField1.setFont(new Font("Serif", Font.BOLD, 14));
		DisttextField1.setPreferredSize(new Dimension(220, 50));

		DistanceUnitsBox1.addActionListener(e -> Distancechoice1 = (String) DistanceUnitsBox1.getSelectedItem());

		DisttextField1.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { DisttextField1Calcul(DisttextField1.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		DistCalculZone1.add(DisttextField1);
		DistCalculZone1.add(DistanceUnitsBox1);

		return DistCalculZone1;
	}
	private JPanel CreateDistCalculZone2(){
		JPanel DistCalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		DistCalculZone2.setBackground(Color.DARK_GRAY);
		DistanceUnitsBox2.setBackground(Color.DARK_GRAY);
		DistanceUnitsBox2.setFont(new Font("Serif", Font.BOLD, 14));
		DistanceUnitsBox2.setPreferredSize(new Dimension(180, 40));
		DisttextField2.setHorizontalAlignment(JTextField.RIGHT);
		DisttextField2.setBackground(Color.DARK_GRAY);
		DisttextField2.setForeground(Color.WHITE);
		DisttextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		DisttextField2.setFont(new Font("Serif", Font.BOLD, 14));
		DisttextField2.setPreferredSize(new Dimension(220, 50));

		DistanceUnitsBox2.addActionListener(e -> Distancechoice2 = (String) DistanceUnitsBox2.getSelectedItem());

		DisttextField2.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { DisttextField2Calcul(DisttextField2.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		DistCalculZone2.add(DisttextField2);
		DistCalculZone2.add(DistanceUnitsBox2);

		return DistCalculZone2;
	}
	
	private JPanel CreateVitCalculZone1(){
		JPanel VitCalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		VitCalculZone1.setBackground(Color.DARK_GRAY);
		VitesseUnitsBox1.setBackground(Color.DARK_GRAY);
		VitesseUnitsBox1.setFont(new Font("Serif", Font.BOLD, 14));
		VitesseUnitsBox1.setPreferredSize(new Dimension(180, 40));
		VittextField1.setHorizontalAlignment(JTextField.RIGHT);
		VittextField1.setBackground(Color.DARK_GRAY);
		VittextField1.setForeground(Color.WHITE);
		VittextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		VittextField1.setFont(new Font("Serif", Font.BOLD, 14));
		VittextField1.setPreferredSize(new Dimension(220, 50));

		VitesseUnitsBox1.addActionListener(e -> Vitessechoice1 = (String) VitesseUnitsBox1.getSelectedItem());

		VittextField1.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { VittextField1Calcul(VittextField1.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		VitCalculZone1.add(VittextField1);
		VitCalculZone1.add(VitesseUnitsBox1);

		return VitCalculZone1;
	}
	private JPanel CreateVitCalculZone2(){
		JPanel VitCalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		VitCalculZone2.setBackground(Color.DARK_GRAY);
		VitesseUnitsBox2.setBackground(Color.DARK_GRAY);
		VitesseUnitsBox2.setFont(new Font("Serif", Font.BOLD, 14));
		VitesseUnitsBox2.setPreferredSize(new Dimension(180, 40));
		VittextField2.setHorizontalAlignment(JTextField.RIGHT);
		VittextField2.setBackground(Color.DARK_GRAY);
		VittextField2.setForeground(Color.WHITE);
		VittextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		VittextField2.setFont(new Font("Serif", Font.BOLD, 14));
		VittextField2.setPreferredSize(new Dimension(220, 50));

		VitesseUnitsBox2.addActionListener(e -> Vitessechoice2 = (String) VitesseUnitsBox2.getSelectedItem());

		VittextField2.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { VittextField2Calcul(VittextField2.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		VitCalculZone2.add(VittextField2);
		VitCalculZone2.add(VitesseUnitsBox2);

		return VitCalculZone2;
	}
	
	private JPanel CreateFreqCalculZone1(){
		JPanel FreqCalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		FreqCalculZone1.setBackground(Color.DARK_GRAY);
		FrequenceUnitsBox1.setBackground(Color.DARK_GRAY);
		FrequenceUnitsBox1.setFont(new Font("Serif", Font.BOLD, 14));
		FrequenceUnitsBox1.setPreferredSize(new Dimension(180, 40));
		FreqtextField1.setHorizontalAlignment(JTextField.RIGHT);
		FreqtextField1.setBackground(Color.DARK_GRAY);
		FreqtextField1.setForeground(Color.WHITE);
		FreqtextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		FreqtextField1.setFont(new Font("Serif", Font.BOLD, 14));
		FreqtextField1.setPreferredSize(new Dimension(220, 50));

		FrequenceUnitsBox1.addActionListener(e -> Frequencechoice1 = (String) FrequenceUnitsBox1.getSelectedItem());

		FreqtextField1.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { FreqtextField1Calcul(FreqtextField1.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		FreqCalculZone1.add(FreqtextField1);
		FreqCalculZone1.add(FrequenceUnitsBox1);

		return FreqCalculZone1;
	}
	private JPanel CreateFreqCalculZone2(){
		JPanel FreqCalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		FreqCalculZone2.setBackground(Color.DARK_GRAY);
		FrequenceUnitsBox2.setBackground(Color.DARK_GRAY);
		FrequenceUnitsBox2.setFont(new Font("Serif", Font.BOLD, 14));
		FrequenceUnitsBox2.setPreferredSize(new Dimension(180, 40));
		FreqtextField2.setHorizontalAlignment(JTextField.RIGHT);
		FreqtextField2.setBackground(Color.DARK_GRAY);
		FreqtextField2.setForeground(Color.WHITE);
		FreqtextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		FreqtextField2.setFont(new Font("Serif", Font.BOLD, 14));
		FreqtextField2.setPreferredSize(new Dimension(220, 50));

		FrequenceUnitsBox2.addActionListener(e -> Frequencechoice2 = (String) FrequenceUnitsBox2.getSelectedItem());

		FreqtextField2.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { FreqtextField2Calcul(FreqtextField2.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		FreqCalculZone2.add(FreqtextField2);
		FreqCalculZone2.add(FrequenceUnitsBox2);

		return FreqCalculZone2;
	}
	
	private JPanel CreateDataCalculZone1(){
		JPanel DataCalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		DataCalculZone1.setBackground(Color.DARK_GRAY);
		DataUnitsBox1.setBackground(Color.DARK_GRAY);
		DataUnitsBox1.setFont(new Font("Serif", Font.BOLD, 14));
		DataUnitsBox1.setPreferredSize(new Dimension(180, 40));
		DatatextField1.setHorizontalAlignment(JTextField.RIGHT);
		DatatextField1.setBackground(Color.DARK_GRAY);
		DatatextField1.setForeground(Color.WHITE);
		DatatextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		DatatextField1.setFont(new Font("Serif", Font.BOLD, 14));
		DatatextField1.setPreferredSize(new Dimension(220, 50));

		DataUnitsBox1.addActionListener(e -> Datachoice1 = (String) DataUnitsBox1.getSelectedItem());

		DatatextField1.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { DatatextField1Calcul(DatatextField1.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		DataCalculZone1.add(DatatextField1);
		DataCalculZone1.add(DataUnitsBox1);

		return DataCalculZone1;
	}
	private JPanel CreateDataCalculZone2(){
		JPanel DataCalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		DataCalculZone2.setBackground(Color.DARK_GRAY);
		DataUnitsBox2.setBackground(Color.DARK_GRAY);
		DataUnitsBox2.setFont(new Font("Serif", Font.BOLD, 14));
		DataUnitsBox2.setPreferredSize(new Dimension(180, 40));
		DatatextField2.setHorizontalAlignment(JTextField.RIGHT);
		DatatextField2.setBackground(Color.DARK_GRAY);
		DatatextField2.setForeground(Color.WHITE);
		DatatextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		DatatextField2.setFont(new Font("Serif", Font.BOLD, 14));
		DatatextField2.setPreferredSize(new Dimension(220, 50));

		DataUnitsBox2.addActionListener(e -> Datachoice2 = (String) DataUnitsBox2.getSelectedItem());

		DatatextField2.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { DatatextField2Calcul(DatatextField2.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		DataCalculZone2.add(DatatextField2);
		DataCalculZone2.add(DataUnitsBox2);

		return DataCalculZone2;
	}
	
	private JPanel CreateTimeCalculZone1(){
		JPanel TimeCalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		TimeCalculZone1.setBackground(Color.DARK_GRAY);
		TimeUnitsBox1.setBackground(Color.DARK_GRAY);
		TimeUnitsBox1.setFont(new Font("Serif", Font.BOLD, 14));
		TimeUnitsBox1.setPreferredSize(new Dimension(180, 40));
		TimetextField1.setHorizontalAlignment(JTextField.RIGHT);
		TimetextField1.setBackground(Color.DARK_GRAY);
		TimetextField1.setForeground(Color.WHITE);
		TimetextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		TimetextField1.setFont(new Font("Serif", Font.BOLD, 14));
		TimetextField1.setPreferredSize(new Dimension(220, 50));

		TimeUnitsBox1.addActionListener(e -> Timechoice1 = (String) TimeUnitsBox1.getSelectedItem());

		TimetextField1.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { TimetextField1Calcul(TimetextField1.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		TimeCalculZone1.add(TimetextField1);
		TimeCalculZone1.add(TimeUnitsBox1);

		return TimeCalculZone1;
	}
	private JPanel CreateTimeCalculZone2(){
		JPanel TimeCalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));

		TimeCalculZone2.setBackground(Color.DARK_GRAY);
		TimeUnitsBox2.setBackground(Color.DARK_GRAY);
		TimeUnitsBox2.setFont(new Font("Serif", Font.BOLD, 14));
		TimeUnitsBox2.setPreferredSize(new Dimension(180, 40));
		TimetextField2.setHorizontalAlignment(JTextField.RIGHT);
		TimetextField2.setBackground(Color.DARK_GRAY);
		TimetextField2.setForeground(Color.WHITE);
		TimetextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		TimetextField2.setFont(new Font("Serif", Font.BOLD, 14));
		TimetextField2.setPreferredSize(new Dimension(220, 50));

		TimeUnitsBox2.addActionListener(e -> Timechoice2 = (String) TimeUnitsBox2.getSelectedItem());

		TimetextField2.addKeyListener(new KeyListener(){
			
			public void keyPressed(KeyEvent e) { };
			public void keyReleased(KeyEvent e) { TimetextField2Calcul(TimetextField2.getText()); };
			public void keyTyped(KeyEvent e) { };
			
		});

		TimeCalculZone2.add(TimetextField2);
		TimeCalculZone2.add(TimeUnitsBox2);

		return TimeCalculZone2;
	}

	// Méthodes de Création des boutons "Réinitialiser" pour chaque onglets
	private JPanel CreateDistButtonZone(){
		JPanel DistButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		DistButton.setBackground(Color.DARK_GRAY);
		DistResetBtn.setBackground(Color.RED);
		DistResetBtn.setForeground(Color.WHITE);
		DistResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

		DistResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DisttextField1.setText("");
				DisttextField2.setText("");
			}
		});

		DistButton.add(DistResetBtn);

		return DistButton;
	}
	private JPanel CreateVitButtonZone(){
		JPanel VitButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		VitButton.setBackground(Color.DARK_GRAY);
		VitResetBtn.setBackground(Color.RED);
		VitResetBtn.setForeground(Color.WHITE);
		VitResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

		VitResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VittextField1.setText("");
				VittextField2.setText("");
			}
		});
		VitButton.add(VitResetBtn);

		return VitButton;
	}
	private JPanel CreateFreqButtonZone(){
		JPanel FreqButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		FreqButton.setBackground(Color.DARK_GRAY);
		FreqResetBtn.setBackground(Color.RED);
		FreqResetBtn.setForeground(Color.WHITE);
		FreqResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

		FreqResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				FreqtextField1.setText("");
				FreqtextField2.setText("");
			}
		});

		FreqButton.add(FreqResetBtn);

		return FreqButton;
	}
	private JPanel CreateDataButtonZone(){
		JPanel DataButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		DataButton.setBackground(Color.DARK_GRAY);
		DataResetBtn.setBackground(Color.RED);
		DataResetBtn.setForeground(Color.WHITE);
		DataResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

		DataResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DatatextField1.setText("");
				DatatextField2.setText("");
			}
		});

		DataButton.add(DataResetBtn);

		return DataButton;
	}
	private JPanel CreateTimeButtonZone(){
		JPanel TimeButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		TimeButton.setBackground(Color.DARK_GRAY);
		TimeResetBtn.setBackground(Color.RED);
		TimeResetBtn.setForeground(Color.WHITE);
		TimeResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

		TimeResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TimetextField1.setText("");
				TimetextField2.setText("");
			}
		});

		TimeButton.add(TimeResetBtn);

		return TimeButton;
	}
	
	private void DisttextField1Calcul(String text) {

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			DisttextField2.setText("");
		}
		else if(text.equals("-")){
			return;
		}
		else{
			try{
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					DisttextField2.setText("NaN");
				}
				else{
					if (getDistanceChoice1() == "Metres (m)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 0.9144f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (1 / 0.3048f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (1 / 0.0254f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.001f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (100f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (1000f) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else if (getDistanceChoice1() == "Yard (yd)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (0.9144f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (3f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (36f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.0009144f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (91.44f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (914.4f) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else if (getDistanceChoice1() == "Pieds (ft)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (0.3048f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 3f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (12f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.0003048f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (30.48f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (304.8f) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else if (getDistanceChoice1() == "Pouces (in)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (0.0254f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 36f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (1 / 12f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.0000254f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (2.54f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (25.4f) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else if (getDistanceChoice1() == "Kilometres (km)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (1000f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 0.0009144f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (1 / 0.0003048f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (1 / 0.0000254f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (100000f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (1000000f) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else if (getDistanceChoice1() == "Centimetres (cm)") {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (0.01f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 91.44f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (1 / 30.48f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (1 / 2.54f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.00001f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = (10) * Floattext;

							DisttextField2.setText(resultat + "");

						}

					} else {

						if (getDistanceChoice2() == "Metres (m)") {

							resultat = (0.001f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Yard (yd)") {

							resultat = (1 / 914.4f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pieds (ft)") {

							resultat = (1 / 304.8f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Pouces (in)") {

							resultat = (1 / 25.4f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Kilometres (km)") {

							resultat = (0.000001f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Centimetres (cm)") {

							resultat = (0.1f) * Floattext;

							DisttextField2.setText(resultat + "");

						} else if (getDistanceChoice2() == "Millimetres (mm)") {

							resultat = Floattext;

							DisttextField2.setText(resultat + "");

						}

					}
				}
			}
			catch (NumberFormatException e){ return; }	
		}
	}
	private void DisttextField2Calcul(String text) {

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			DisttextField1.setText("");
		}
		else if(text.equals("-")){
			DisttextField1.setText("NaN");;
		}
		else{
			try{	
				Floattext = Float.parseFloat(text);			
				if(Floattext < 0){
					return;
				}
				else{
					if (getDistanceChoice2() == "Metres (m)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 0.9144f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (1 / 0.3048f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (1 / 0.0254f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.001f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (100f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (1000f) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else if (getDistanceChoice2() == "Yard (yd)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (0.9144f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (3f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (36f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.0009144f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (91.44f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (914.4f) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else if (getDistanceChoice2() == "Pieds (ft)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (0.3048f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 3f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (12f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.0003048f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (30.48f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (304.8f) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else if (getDistanceChoice2() == "Pouces (in)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (0.0254f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 36f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (1 / 12f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.0000254f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (2.54f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (25.4f) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else if (getDistanceChoice2() == "Kilometres (km)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (1000f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 0.0009144f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (1 / 0.0003048f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (1 / 0.0000254f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (100000f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (1000000f) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else if (getDistanceChoice2() == "Centimetres (cm)") {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (0.01f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 91.44f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (1 / 30.48f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (1 / 2.54f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.00001f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = (10) * Floattext;

							DisttextField1.setText(resultat + "");

						}

					} else {

						if (getDistanceChoice1() == "Metres (m)") {

							resultat = (0.001f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Yard (yd)") {

							resultat = (1 / 914.4f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pieds (ft)") {

							resultat = (1 / 304.8f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Pouces (in)") {

							resultat = (1 / 25.4f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Kilometres (km)") {

							resultat = (0.000001f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Centimetres (cm)") {

							resultat = (0.1f) * Floattext;

							DisttextField1.setText(resultat + "");

						} else if (getDistanceChoice1() == "Millimetres (mm)") {

							resultat = Floattext;

							DisttextField1.setText(resultat + "");

						}

					}
				}
			}
			catch (NumberFormatException e){ return; }
		}	
	
	}
	
	private void VittextField1Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			VittextField2.setText("");
		}
		else if(text.equals("-")){
			VittextField2.setText("NaN");
		}
		else{
			try {
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getVitesseChoice1() == "km/h") {

						if (getVitesseChoice2() == "km/h") {

							resultat = Floattext;

							VittextField2.setText(resultat + "");

						} else if (getVitesseChoice2() == "m/s") {

							resultat = (1 / 3.6f) * Floattext;

							VittextField2.setText(resultat + "");

						}

					} else {

						if (getVitesseChoice2() == "km/h") {

							resultat = (3.6f) * Floattext;

							VittextField2.setText(resultat + "");

						} else if (getVitesseChoice2() == "m/s") {

							resultat = Floattext;

							VittextField2.setText(resultat + "");

						}

					}
				}				
			} 
			catch (NumberFormatException e) { return; }
		}

	}
	private void VittextField2Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			VittextField1.setText("");
		}
		else if(text.equals("-")){
			VittextField1.setText("NaN");
		}
		else{
			try {
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getVitesseChoice2() == "km/h") {

						if (getVitesseChoice1() == "km/h") {

							resultat = Floattext;

							VittextField1.setText(resultat + "");

						} else if (getVitesseChoice1() == "m/s") {

							resultat = (1 / 3.6f) * Floattext;

							VittextField1.setText(resultat + "");

						}

					} else {

						if (getVitesseChoice1() == "km/h") {

							resultat = (3.6f) * Floattext;

							VittextField1.setText(resultat + "");

						} else if (getVitesseChoice1() == "m/s") {

							resultat = Floattext;

							VittextField1.setText(resultat + "");

						}

					}
				}				
			} 
			catch (NumberFormatException e) { return; }
		}

	}
	
	private void FreqtextField1Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			FreqtextField2.setText("");
		}
		else if(text.equals("-")){
			FreqtextField2.setText("NaN");
		}
		else{
			try{
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getFrequenceChoice1() == "Hertz (hz)") {

						if (getFrequenceChoice2() == "Hertz (hz)") {

							resultat = Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

							resultat = (0.001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Megahertz (Mhz)") {

							resultat = (0.000001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else {

							resultat = (0.000000001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						}

					} else if (getFrequenceChoice1() == "Kilohertz (Khz)") {

						if (getFrequenceChoice2() == "Hertz (hz)") {

							resultat = (1000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

							resultat = Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Megahertz (Mhz)") {

							resultat = (0.001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else {

							resultat = (0.000001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						}

					} else if (getFrequenceChoice1() == "Megahertz (Mhz)") {

						if (getFrequenceChoice2() == "Hertz (hz)") {

							resultat = (1000000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

							resultat = (1000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Megahertz (Mhz)") {

							resultat = Floattext;

							FreqtextField2.setText(resultat + "");

						} else {

							resultat = (0.001f) * Floattext;

							FreqtextField2.setText(resultat + "");

						}

					} else {

						if (getFrequenceChoice2() == "Hertz (hz)") {

							resultat = (1000000000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

							resultat = (1000000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Megahertz (Mhz)") {

							resultat = (1000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else {

							resultat = Floattext;

							FreqtextField2.setText(resultat + "");

						}

					}
				}
			}
			catch(NumberFormatException e){
				return;
			}
		}
	}
	private void FreqtextField2Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			FreqtextField1.setText("");
		}
		else if(text.equals("-")){
			FreqtextField1.setText("NaN");
		}
		else{
			try{
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getFrequenceChoice2() == "Hertz (hz)") {

						if (getFrequenceChoice2() == "Hertz (hz)") {

							resultat = Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

							resultat = (0.001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice2() == "Megahertz (Mhz)") {

							resultat = (0.000001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else {

							resultat = (0.000000001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						}

					} else if (getFrequenceChoice2() == "Kilohertz (Khz)") {

						if (getFrequenceChoice1() == "Hertz (hz)") {

							resultat = (1000f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Kilohertz (Khz)") {

							resultat = Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Megahertz (Mhz)") {

							resultat = (0.001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else {

							resultat = (0.000001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						}

					} else if (getFrequenceChoice2 () == "Megahertz (Mhz)") {

						if (getFrequenceChoice1() == "Hertz (hz)") {

							resultat = (1000000f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Kilohertz (Khz)") {

							resultat = (1000f) * Floattext;

							FreqtextField1.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Megahertz (Mhz)") {

							resultat = Floattext;

							FreqtextField1.setText(resultat + "");

						} else {

							resultat = (0.001f) * Floattext;

							FreqtextField1.setText(resultat + "");

						}

					} else {

						if (getFrequenceChoice1() == "Hertz (hz)") {

							resultat = (1000000000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Kilohertz (Khz)") {

							resultat = (1000000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else if (getFrequenceChoice1() == "Megahertz (Mhz)") {

							resultat = (1000f) * Floattext;

							FreqtextField2.setText(resultat + "");

						} else {

							resultat = Floattext;

							FreqtextField2.setText(resultat + "");

						}

					}
				}
			}
			catch(NumberFormatException e){ return;	}
		}
	}

	private void DatatextField1Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			DatatextField2.setText("");
		}
		else if(text.equals("-")){
			DatatextField2.setText("NaN");
		}
		else{
			try{
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getDataChoice1() == "Bits (bit)") {

						if (getDataChoice2() == "Bits (bit)") {

							resultat = Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "Octets (o)") {

							resultat = (1 / 8f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "KiloOctets (Ko)") {

							resultat = (1f / (8 * 1024f)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "MegaOctets (Mo)") {

							resultat = (1f / (8 * (float) Math.pow(1024, 2))) * Floattext;

							DatatextField2.setText(resultat + "");

						} else {

							resultat = (1f / (8* (float) Math.pow(1024, 3))) * Floattext;

							DatatextField2.setText(resultat + "");

						}

					} else if (getDataChoice1() == "Octets (o)") {

						if (getDataChoice2() == "Bits (bit)") {

							resultat = (8f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "Octets (o)") {

							resultat = Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "KiloOctets (Ko)") {

							resultat = (1 / 1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "MegaOctets (Mo)") {

							resultat = (1 / (float) Math.pow(1024, 2)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else {

							resultat = (1 / (float) Math.pow(1024, 3)) * Floattext;

							DatatextField2.setText(resultat + "");

						}

					} else if (getDataChoice1() == "KiloOctets (Ko)") {

						if (getDataChoice2() == "Bits (bit)") {

							resultat = (8 * 1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "Octets (o)") {

							resultat = (1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "KiloOctets (Ko)") {

							resultat = Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "MegaOctets (Mo)") {

							resultat = (1 / 1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else {

							resultat = (1 / (float) Math.pow(1024, 2)) * Floattext;

							DatatextField2.setText(resultat + "");

						}

					} else if (getDataChoice1() == "MegaOctets (Mo)") {

						if (getDataChoice2() == "Bits (bit)") {

							resultat = (8 * (float) Math.pow(1024, 2)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "Octets (o)") {

							resultat = ((float) Math.pow(1024, 2)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "KiloOctets (Ko)") {

							resultat = (1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "MegaOctets (Mo)") {

							resultat = Floattext;

							DatatextField2.setText(resultat + "");

						} else {

							resultat = (1 / 1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						}

					} else {

						if (getDataChoice2() == "Bits (bit)") {

							resultat = (8 * (float) Math.pow(1024, 3)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "Octets (o)") {

							resultat = ((float) Math.pow(1024, 3)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "KiloOctets (Ko)") {

							resultat = ((float) Math.pow(1024, 2)) * Floattext;

							DatatextField2.setText(resultat + "");

						} else if (getDataChoice2() == "MegaOctets (Mo)") {

							resultat = (1024f) * Floattext;

							DatatextField2.setText(resultat + "");

						} else {

							resultat = Floattext;

							DatatextField2.setText(resultat + "");

						}

					}
				}
			} catch (NumberFormatException e) {
				return;
			}
		}
	}
	private void DatatextField2Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			DatatextField1.setText("");
		}
		else if(text.equals("-")){
			DatatextField1.setText("NaN");
		}
		else{
			try{
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getDataChoice2() == "Bits (bit)") {

						if (getDataChoice1() == "Bits (bit)") {

							resultat = Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "Octets (o)") {

							resultat = (1 / 8f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "KiloOctets (Ko)") {

							resultat = (1 / 8 * 1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "MegaOctets (Mo)") {

							resultat = (1 / 8 * (float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else {

							resultat = (1 / 8 * (float) Math.pow(1024, 3)) * Floattext;

							DatatextField1.setText(resultat + "");

						}

					} else if (getDataChoice2() == "Octets (o)") {

						if (getDataChoice1() == "Bits (bit)") {

							resultat = (8f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "Octets (o)") {

							resultat = Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "KiloOctets (Ko)") {

							resultat = (1 / 1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "MegaOctets (Mo)") {

							resultat = (1 / (float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else {

							resultat = (1 / (float) Math.pow(1024, 3)) * Floattext;

							DatatextField1.setText(resultat + "");

						}

					} else if (getDataChoice2() == "KiloOctets (Ko)") {

						if (getDataChoice1() == "Bits (bit)") {

							resultat = (8 * 1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "Octets (o)") {

							resultat = (1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "KiloOctets (Ko)") {

							resultat = Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "MegaOctets (Mo)") {

							resultat = (1 / 1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else {

							resultat = (1 / (float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						}

					} else if (getDataChoice2() == "MegaOctets (Mo)") {

						if (getDataChoice1() == "Bits (bit)") {

							resultat = (8 * (float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "Octets (o)") {

							resultat = ((float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "KiloOctets (Ko)") {

							resultat = (1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "MegaOctets (Mo)") {

							resultat = Floattext;

							DatatextField1.setText(resultat + "");

						} else {

							resultat = (1 / 1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						}

					} else {

						if (getDataChoice1() == "Bits (bit)") {

							resultat = (8 * (float) Math.pow(1024, 3)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "Octets (o)") {

							resultat = ((float) Math.pow(1024, 3)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "KiloOctets (Ko)") {

							resultat = ((float) Math.pow(1024, 2)) * Floattext;

							DatatextField1.setText(resultat + "");

						} else if (getDataChoice1() == "MegaOctets (Mo)") {

							resultat = (1024f) * Floattext;

							DatatextField1.setText(resultat + "");

						} else {

							resultat = Floattext;

							DatatextField1.setText(resultat + "");

						}

					}
				}
			} catch (NumberFormatException e) {
				return;
			}
		}
	}

	private void TimetextField1Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			TimetextField1.setText("");
		}
		else if(text.equals("-")){
			TimetextField1.setText("NaN");
		}
		else{
			try {
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getTimeChoice1() == "Minutes (min)") {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = (60f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = (60000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = (1 / 60f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = (1 / 1444f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = (1 / 1080f) * Floattext;

							TimetextField2.setText(resultat + "");

						}

					} else if (getTimeChoice1() == "Secondes (s)") {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = (1 / 60f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = (1000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = (1 / 3600f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = (1 / 86400f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = (1 / 604800f) * Floattext;

							TimetextField2.setText(resultat + "");

						}

					} else if (getTimeChoice1() == "Millisecondes (ms)") {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = (1 / 60000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = (0.001f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = (1 / 3600000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = (1 / 86400000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = (1 / 604800000) * Floattext;

							TimetextField2.setText(resultat + "");

						}

					} else if (getTimeChoice1() == "Heures (h)") {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = (60f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = (3600f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = (3600000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = (1 / 24f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = (1 / 168f) * Floattext;

							TimetextField2.setText(resultat + "");

						}

					} else if (getTimeChoice1() == "Jours (jr)") {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = (1440f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = (86400f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = (86400000f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = (24f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = (1 / 7f) * Floattext;

							TimetextField2.setText(resultat + "");

						}

					} else {

						if (getTimeChoice2() == "Minutes (min)") {

							resultat = (10080) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Secondes (s)") {

							resultat = (604800) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Millisecondes (ms)") {

							resultat = (604800000) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Heures (h)") {

							resultat = (168f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else if (getTimeChoice2() == "Jours (jr)") {

							resultat = (7f) * Floattext;

							TimetextField2.setText(resultat + "");

						} else {

							resultat = Floattext;

							TimetextField2.setText(resultat + "");

						}

					}
				}
			} catch (NumberFormatException e) { return; }
		}

	}
	private void TimetextField2Calcul(String text){

		Float Floattext;
		Float resultat;

		if(text.isEmpty()){
			TimetextField1.setText("");
		}
		else if(text.equals("-")){
			TimetextField1.setText("NaN");
		}
		else{
			try {
				Floattext = Float.parseFloat(text);
				if(Floattext < 0){
					return;
				}
				else{
					if (getTimeChoice2() == "Minutes (min)") {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = (60f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = (60000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = (1 / 60f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = (1 / 1444f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = (1 / 1080f) * Floattext;

							TimetextField1.setText(resultat + "");

						}

					} else if (getTimeChoice2() == "Secondes (s)") {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = (1 / 60f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = (1000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = (1 / 3600f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = (1 / 86400f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = (1 / 604800f) * Floattext;

							TimetextField1.setText(resultat + "");

						}

					} else if (getTimeChoice2() == "Millisecondes (ms)") {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = (1 / 60000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = (0.001f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = (1 / 3600000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = (1 / 86400000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = (1 / 604800000) * Floattext;

							TimetextField1.setText(resultat + "");

						}

					} else if (getTimeChoice2() == "Heures (h)") {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = (60f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = (3600f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = (3600000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = (1 / 24f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = (1 / 168f) * Floattext;

							TimetextField1.setText(resultat + "");

						}

					} else if (getTimeChoice2() == "Jours (jr)") {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = (1440f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = (86400f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = (86400000f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = (24f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = (1 / 7f) * Floattext;

							TimetextField1.setText(resultat + "");

						}

					} else {

						if (getTimeChoice1() == "Minutes (min)") {

							resultat = (10080) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Secondes (s)") {

							resultat = (604800) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Millisecondes (ms)") {

							resultat = (604800000) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Heures (h)") {

							resultat = (168f) * Floattext;

							TimetextField1.setText(resultat + "");

						} else if (getTimeChoice1() == "Jours (jr)") {

							resultat = (7) * Floattext;

							TimetextField1.setText(resultat + "");

						} else {

							resultat = Floattext;

							TimetextField1.setText(resultat + "");

						}

					}
				}
			} catch (NumberFormatException e) { return; }
		}

	}

}
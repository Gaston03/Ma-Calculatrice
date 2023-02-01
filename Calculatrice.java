import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Calculatrice extends JFrame implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private String[] etiquette = {
			"C", "n^b", "n!", "÷", "",
			"7", "8", "9", "√", "*", 
			"4", "5", "6", "log", "-", 
			"1", "2", "3", "ln", "+",
			"0", ",", "%", "|n|", "="
	};
	private JButton[] Boutons = new JButton[etiquette.length];
	private JTextField txt1 = new JTextField();
	private JTextField txt2 = new JTextField();
	private String Sop = "";
    private String operande = "";
    private ArrayList<Character> operateurs = new ArrayList<Character>();
    private ArrayList<Float> operandes = new ArrayList<Float>();
    private float Foperande;
	private StringBuilder Txt1Content;
	private String STxt1Content;
	private String label;
	private int taille;
	private int j = 0;
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();

	/* Constructeur de la class Calculatrice */
	public Calculatrice() {
		this.setTitle("Calculatrice Scientifique");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(460, 495);
		this.setMinimumSize(new Dimension(395, 495));
		this.setResizable(false); // Fenêtre non redimensionable
		this.setLocationRelativeTo(null); //Centrer la fenêtre par rapport à l'écran (null)
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);

		JTabbedPane onglets = new JTabbedPane();

		BaseConverter baseconverter = new BaseConverter(p1);
		UnitConverter unitconverter = new UnitConverter(p2);

		onglets.addTab("Calcul basique", calculatricePanel());
		onglets.addTab("Convertisseur de bases", baseconverter.baseconverterPannel());
		onglets.addTab("Convertisseur d'unités ", unitconverter.unitconverterPanel());

		this.add(onglets);
		this.setVisible(true);
		
	}

	public JPanel calculatricePanel(){
		JPanel conteneur = new JPanel(); //Création d'un conteneur de type JPanel
		conteneur.setLayout(new BorderLayout());

		conteneur.setBackground(Color.DARK_GRAY);
		conteneur.add(CreateZoneText(), BorderLayout.NORTH); // Ajout des zones de textes
		conteneur.add(CreateTouche()); // Ajout des différentes touches
		
		return conteneur;

	}

	/* Création des zones de texte de saisie et d'affichage */
	private JPanel CreateZoneText() {
		
		JPanel TxtZoneConteneur = new JPanel(new FlowLayout());
		TxtZoneConteneur.setBackground(Color.DARK_GRAY);
		
		txt1.setPreferredSize(new Dimension(315, 55));
		txt1.setFont(new Font("Serif", Font.BOLD, 14));
		txt1.setBackground(Color.DARK_GRAY);
		txt1.setForeground(Color.WHITE);
		txt1.setBorder(BorderFactory.createEmptyBorder());
		TxtZoneConteneur.add(txt1);
		
		txt1.addKeyListener(this);
		
		txt2.setEditable(false);
		txt2.setPreferredSize(new Dimension(130, 55));
		txt2.setFont(new Font("Serif", Font.BOLD, 14));
		txt2.setBackground(Color.DARK_GRAY);
		txt2.setForeground(Color.WHITE);
		txt2.setBorder(BorderFactory.createEmptyBorder());
		TxtZoneConteneur.add(txt2);
		
		return TxtZoneConteneur;
	}
	
	/* Création des Touches de la calculatrice */
	private JPanel CreateTouche() {
		
		JPanel ToucheConteneur = new JPanel(new GridLayout(5, 4));
		ToucheConteneur.setBackground(Color.DARK_GRAY);

		for(int i=0; i<etiquette.length; i++) {
			if(etiquette[i].equals("")){
				Boutons[i] = new JButton(new ImageIcon("clear.png"));
				Boutons[i].setBackground(Color.red);
			}
			else if(etiquette[i].equals("=")){
				Boutons[i] = new JButton(etiquette[i]);
				Boutons[i].setFont(new Font("Serif", Font.BOLD, 18));
				Boutons[i].setBackground(Color.GREEN);
				Boutons[i].setForeground(Color.WHITE);
			}
			else if(etiquette[i].equals("+") || etiquette[i].equals("-") || etiquette[i].equals("*") || etiquette[i].equals("%") ||
					etiquette[i].equals("÷") || etiquette[i].equals("n!") || etiquette[i].equals("|n|") || etiquette[i].equals("ln") ||
					etiquette[i].equals("log") || etiquette[i].equals("√") || etiquette[i].equals("n^b") ){
				Boutons[i] = new JButton(etiquette[i]);
				Boutons[i].setFont(new Font("Serif", Font.BOLD, 18));
				Boutons[i].setBackground(Color.DARK_GRAY);
				Boutons[i].setForeground(Color.GREEN);
			}
			else{
				Boutons[i] = new JButton(etiquette[i]);
				Boutons[i].setFont(new Font("Serif", Font.BOLD, 20));
				Boutons[i].setBackground(Color.DARK_GRAY);
				Boutons[i].setForeground(Color.WHITE);
			}
			Boutons[i].addActionListener(this);
			ToucheConteneur.add(Boutons[i]);
		}
		
		return ToucheConteneur;
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode(); // Pour obtenir le KeyCode
		Txt1Content = new StringBuilder(txt1.getText()); // Pour obtenir le contenu de la zone de text txt1
		taille = Txt1Content.length();   // Calcul le nombre de carateres contenus dans la zone de saisie
		if(keycode == KeyEvent.VK_ENTER){

			STxt1Content = Txt1Content.toString().toLowerCase();
			calculate(STxt1Content);
			
		}
		else if(keycode == KeyEvent.VK_DIVIDE){ txt1.setText(Txt1Content.toString() + '÷');	}
	}
	@Override
	public void keyReleased(KeyEvent event) { }
	@Override
	public void keyTyped(KeyEvent event) { }
	@Override
	public void actionPerformed(ActionEvent e) {
		label = e.getActionCommand(); // Pour obtenir le label du bouton cliqué 
		Txt1Content = new StringBuilder(txt1.getText()); // Pour obtenir le contenu de la zone de text txt1
		taille = Txt1Content.length();   // Calcul le nombre de carateres contenus dans la zone de saisie
		if(label.equals("C")){
			txt1.setText("");
			txt2.setText("");
			operandes.clear();
			operateurs.clear();
		}
		else if(label.equals("")){
			try {
				txt1.setText(Txt1Content.deleteCharAt(taille-1).toString());
			txt2.setText("");
			operandes.clear();
			operateurs.clear();
			} catch (StringIndexOutOfBoundsException e1) { return; }
		}
		else if(label.equals("=")){

			STxt1Content = Txt1Content.toString().toLowerCase();
			calculate(STxt1Content);

		}
		else{
			if(label.equals("n^b")){
				txt1.setText(Txt1Content.toString() + "^");
			}
			else if(label.equals("n!")){
				txt1.setText(Txt1Content.toString() + "!");
			}
			else if(label.equals("|n|")){
				txt1.setText(Txt1Content.toString() + "abs(");
			}
			else{
				txt1.setText(Txt1Content.toString() + label);
		
			}
		}
	}

	public void calculate(String expression){

		for(int i = 0; i<expression.length(); i++){

			if(i == expression.length()-1){
				operande += expression.charAt(i);
				try {
					DecimalFormat decimal = new DecimalFormat("#");
					Foperande = decimal.parse(operande).floatValue();
				}
				catch(ParseException e1){
					JOptionPane.showMessageDialog(Calculatrice.this, "Expression Incorrecte", "Avertissement", JOptionPane.OK_OPTION);
					Sop = "";
					operande = "";
					operandes.clear();
					operateurs.clear();
					return;
				}
				if(expression.charAt(i) == '!'){
					if(Foperande >= 0){
						if(Sop.equals("√")){
							operandes.add((float) Math.sqrt(factorielle(Foperande)));
						}
						else if(Sop.equals("ln")){
							if(Foperande != 0){
								operandes.add((float) Math.log(factorielle(Foperande)));
							}
							else{
								JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
							}
						}
						else if(Sop.equals("log")){
							if(Foperande != 0){
								operandes.add((float) Math.log10(factorielle(Foperande)));
							}
							else{
								JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
							}
						}
						else if(Sop.equals("abs")){
							operandes.add(Math.abs(factorielle(Foperande)));
						}
						else{
							operandes.add(factorielle(Foperande));
						}
					}
					else{
						operandes.add(-1*factorielle(-1*Foperande));
					}
				}
				else{                  
					if(Sop.equals("√")){
						operandes.add((float) Math.sqrt(Foperande));
					}
					else if(Sop.equals("ln")){
						if(Foperande != 0){
							operandes.add((float) Math.log((Foperande)));
						}
						else{
							JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
						}
					}
					else if(Sop.equals("log")){
						if(Foperande != 0){
							operandes.add((float) Math.log10((Foperande)));
						}
						else{
							JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
						}
					}
					else if(Sop.equals("abs")){
						operandes.add(Math.abs(Foperande));
					}
					else{
						operandes.add(Foperande);
					}
				}
			}
			else{
				if( expression.charAt(i) == '√' ||
					expression.charAt(i) == 'l' || 
					expression.charAt(i) == 'n' || 
					expression.charAt(i) == 'o' || 
					expression.charAt(i) == 'g' || 
					expression.charAt(i) == 'a' ||
					expression.charAt(i) == 'b' ||
					expression.charAt(i) == 's'){
					if(i>0 && (Character.isDigit(expression.charAt(i-1)) || expression.charAt(i-1) == '!')){
						operateurs.add('*');
	
						try {
							DecimalFormat decimal = new DecimalFormat("#");
							Foperande = decimal.parse(operande).floatValue();
						}
						catch(ParseException e1){
							JOptionPane.showMessageDialog(Calculatrice.this, "Expression Incorrecte", "Avertissement", JOptionPane.OK_OPTION);
							Sop = "";
							operande = "";
							operandes.clear();
							operateurs.clear();
							return;
						}
	
						if(expression.charAt(i-1) == '!'){
							Sop += expression.charAt(i);
							operandes.add(factorielle(Foperande));;
						}
						else{
							
							if(Sop.equals("√")){
								operandes.add((float) Math.sqrt(Foperande));
							}
							else if(Sop.equals("ln")){
								operandes.add((float) Math.log(Foperande));
							}
							else if(Sop.equals("log")){
								operandes.add((float) Math.log10(Foperande));
							}
							else{
								operandes.add(Foperande);
							}
			
							Sop = "";
							Sop += expression.charAt(i);
							
						}
						operande = "";
					}
					else{
						Sop += expression.charAt(i);
					}
				}
				else if(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == ',' || expression.charAt(i) == '.' ){
					if(expression.charAt(i) == '.'){
						operande += ',';
					}
					else{
						operande += expression.charAt(i);
					}
					
				}
				else if(expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '%' || expression.charAt(i) == '^' || expression.charAt(i) == '÷' || expression.charAt(i) == '!'){
									
					if(expression.charAt(i) == '!'){
						continue;
					}
					else if(expression.charAt(i) == '-'){
						if(Sop.equals("abs")){
							if(j == 0){
								operande += expression.charAt(i);
								j++;
							}
							else if(j >= 1){
								try {
									DecimalFormat decimal = new DecimalFormat("#");
									Foperande = decimal.parse(operande).floatValue();
								}
								catch(ParseException e1){
									JOptionPane.showMessageDialog(Calculatrice.this, "Expression Incorrecte", "Avertissement", JOptionPane.OK_OPTION);
									Sop = "";
									operande = "";
									operandes.clear();
									operateurs.clear();
									return;
								}
								
								operateurs.add(expression.charAt(i));
			
								if(expression.charAt(i-1) == '!'){
									operandes.add(factorielle(Foperande));
								}
								else{
									if(Sop.equals("√")){
										operandes.add((float) Math.sqrt(Foperande));
									}
									else if(Sop.equals("ln")){
										if(Foperande != 0){
											operandes.add((float) Math.log(Foperande));
										}
										else{
											JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
										}
									}
									else if(Sop.equals("log")){
										if(Foperande != 0){
											operandes.add((float) Math.log10(Foperande));
										}
										else{
											JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
										}
									}
									else if(Sop.equals("abs")){
										operandes.add(Math.abs(Foperande));
									}
									else{
										operandes.add(Foperande);
									}
				
									Sop = "";
								}
			
								operande = "";
								
							}
						}
						else if(i == 0){
							operande += expression.charAt(i);
						}
						else{
							operateurs.add(expression.charAt(i));

							try {
								DecimalFormat decimal = new DecimalFormat("#");
								Foperande = decimal.parse(operande).floatValue();
							}
							catch(ParseException e1){
								JOptionPane.showMessageDialog(Calculatrice.this, "Expression Incorrecte", "Avertissement", JOptionPane.OK_OPTION);
								Sop = "";
								operande = "";
								operandes.clear();
								operateurs.clear();
								return;
							}
								
							if(expression.charAt(i-1) == '!'){
								if(Sop.equals("√")){
									operandes.add((float) Math.sqrt(factorielle(Foperande)));
								}
								else if(Sop.equals("ln")){
									if(Foperande != 0){
										operandes.add((float) Math.log(factorielle(Foperande)));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("log")){
									if(Foperande != 0){
										operandes.add((float) Math.log10(factorielle(Foperande)));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("abs")){
									operandes.add(Math.abs(factorielle(Foperande)));
								}
								else{
									if(Foperande >= 0){
										operandes.add(factorielle(Foperande));
									}
									else{
										operandes.add(-1*factorielle(-1*Foperande));
									}
								}
								Sop = "";
							}
							else{
								if(Sop.equals("√")){
									operandes.add((float) Math.sqrt(Foperande));
								}
								else if(Sop.equals("ln")){
									if(Foperande != 0){
										operandes.add((float) Math.log(Foperande));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("log")){
									if(Foperande != 0){
										operandes.add((float) Math.log10(Foperande));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("abs")){
									operandes.add(Math.abs(Foperande));
								}
								else{
									operandes.add(Foperande);
								}
				
								Sop = "";
							}

							operande = "";
						}
					}
					else{

						operateurs.add(expression.charAt(i));

						try {
							DecimalFormat decimal = new DecimalFormat("#");
							Foperande = decimal.parse(operande).floatValue();
						}
						catch(ParseException e1){
							JOptionPane.showMessageDialog(Calculatrice.this, "Expression Incorrecte", "Avertissement", JOptionPane.OK_OPTION);
							Sop = "";
							operande = "";
							operandes.clear();
							operateurs.clear();
							return;
						}
							
						if(expression.charAt(i-1) == '!'){
							if(Foperande >= 0){
								if(Sop.equals("√")){
									operandes.add((float) Math.sqrt(factorielle(Foperande)));
								}
								else if(Sop.equals("ln")){
									if(Foperande != 0){
										operandes.add((float) Math.log(factorielle(Foperande)));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("log")){
									if(Foperande != 0){
										operandes.add((float) Math.log10(factorielle(Foperande)));
									}
									else{
										JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
									}
								}
								else if(Sop.equals("abs")){
									operandes.add(Math.abs(factorielle(Foperande)));
								}
								else{
									operandes.add(factorielle(Foperande));
								}
							}
							else{
								operandes.add(-1*factorielle(-1*Foperande));
							}
							Sop = "";
						}
						else{
							if(Sop.equals("√")){
								operandes.add((float) Math.sqrt(Foperande));
							}
							else if(Sop.equals("ln")){
								if(Foperande != 0){
									operandes.add((float) Math.log(Foperande));
								}
								else{
									JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
								}
							}
							else if(Sop.equals("log")){
								if(Foperande != 0){
									operandes.add((float) Math.log10(Foperande));
								}
								else{
									JOptionPane.showMessageDialog(Calculatrice.this, "Le logarithme de zéro n'est pas défini", "Avertissement", JOptionPane.OK_OPTION);
								}
							}
							else if(Sop.equals("abs")){
								operandes.add(Math.abs(Foperande));
							}
							else{
								operandes.add(Foperande);
							}
			
							Sop = "";
						}
		
						operande = "";
							
					}
						   
				}
		   } 
		
		}

		while (operateurs.size() != 0) {
			
			int j;
			for(int i = 0; i<operateurs.size(); ){

				if(operateurs.get(i) == '^'){
					operandes.add(i, (float) Math.pow(operandes.get(i),operandes.get(i+1)));
					operandes.remove(i+1);
					operandes.remove(i+1);
					operateurs.remove(i);
				}
				else{
					i++;
				}

			}
			for(int i = 0; i<operateurs.size(); ){

				if(operateurs.get(i) == '%'){

					operandes.add(i, operandes.get(i)%operandes.get(i+1));
					operandes.remove(i+1);
					operandes.remove(i+1);
					operateurs.remove(i);
				}
				else{
					i++;
				}

			}
			for(int i = 0; i<operateurs.size(); ){

				if(operateurs.get(i) == '*'){
					operandes.add(i, operandes.get(i)*operandes.get(i+1));
					operandes.remove(i+1);
					operandes.remove(i+1);
					operateurs.remove(i);
				}
				else if(operateurs.get(i) == '÷'){
					if(operandes.get(i+1) == 0){
						JOptionPane.showMessageDialog(Calculatrice.this, "Division par 0: impossible", "Avertissement", JOptionPane.OK_OPTION);
						return;
					}
					operandes.add(i, operandes.get(i)/operandes.get(i+1));
					operandes.remove(i+1);
					operandes.remove(i+1);
					operateurs.remove(i);
				}
				else{
					i++;
				}

			}
			for(j = 0; j<operateurs.size(); ){

				if(operateurs.get(j) == '+'){
					operandes.add(j, operandes.get(j)+operandes.get(j+1));
					operandes.remove(j+1);
					operandes.remove(j+1);
					operateurs.remove(j);
				}
				else if(operateurs.get(j) == '-'){
					operandes.add(j, operandes.get(j)-operandes.get(j+1));
					operandes.remove(j+1);
					operandes.remove(j+1);
					operateurs.remove(j);
				}

			}
			
		}

		txt2.setText(Float.toString(operandes.get(0)));
		operandes.clear();
		Sop = "";
		operande = "";
		j = 0;

	}
	
	public float factorielle(float x){
		if(x == 0){
			return 1;
		}
		return x*factorielle(x-1);
	}

	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(new NimbusLookAndFeel()); // Look Nimbus: plus stylé

		new Calculatrice();
	}
}

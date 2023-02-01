import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseConverter extends JFrame{

	private String[] NbreFormat = {"Notation Naturelle", "Complément à 2 (8 bits)", "Complément à 2 (16 bits)", "Norme IEEE 754 (32 bits)"};
	private JComboBox formatBox = new JComboBox<>(NbreFormat);
	private JTextField txtField1 = new JTextField();
	private JTextField txtField2 = new JTextField(); 
	private JTextField txtField3 = new JTextField();
	private JTextField txtField4 = new JTextField();
	private JButton ResetBtn = new JButton("Réinitialiser");
	private String formatchoice = "Notation Naturelle";
	private float FloattxtField1Content;
	private int InttxtField1Content;
	private int InttxtField2Content;
	private int InttxtField3Content;
	private int InttxtField4Content;
	private JPanel p;

	public BaseConverter(JPanel p){
		this.p = p;
	} 

    public JPanel baseconverterPannel() {

        this.p.setLayout(new GridLayout(6, 1));
		this.p.setBackground(Color.DARK_GRAY);

		this.p.add(CreateComboBox());
		this.p.add(CreateCalculZone1());
		this.p.add(CreateCalculZone2());
		this.p.add(CreateCalculZone3());
		this.p.add(CreateCalculZone4());
		this.p.add(CreateCalculButton());

		return this.p;

	}

	private JPanel CreateComboBox(){
		JPanel comboBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));

		JLabel format = new JLabel("Format : ");
		comboBox.setBackground(Color.DARK_GRAY);
		format.setForeground(Color.WHITE);
		format.setFont(new Font("Serif", Font.BOLD, 14));
		formatBox.setBackground(Color.DARK_GRAY);
		formatBox.setFont(new Font("Serif", Font.BOLD, 14));
		formatBox.setPreferredSize(new Dimension(320, 40));
		formatBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				formatchoice = (String) formatBox.getSelectedItem();

				if(formatchoice == "Notation Naturelle") {
					txtField2.setEditable(true);
					txtField3.setEditable(true);
					txtField4.setEditable(true);
				}
				else if(formatchoice== "Complément à 2 (8 bits)" || formatchoice == "Complément à 2 (16 bits)" || formatchoice == "Norme IEEE 754 (32 bits)"){
					txtField2.setEditable(false);
					txtField3.setEditable(false);
					txtField4.setEditable(false);
				}

			}
		});

		comboBox.add(format);
		comboBox.add(formatBox);

		return comboBox;
	}
	private JPanel CreateCalculZone1(){
		JPanel CalculZone1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));

		JLabel DecimalLabel = new JLabel("Decimal : ");
		DecimalLabel.setForeground(Color.WHITE);
		DecimalLabel.setFont(new Font("Serif", Font.BOLD, 14));
		CalculZone1.setBackground(Color.DARK_GRAY);
		txtField1.setBackground(Color.DARK_GRAY);
		txtField1.setForeground(Color.WHITE);
		txtField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		txtField1.setFont(new Font("Serif", Font.BOLD, 14));
		txtField1.setPreferredSize(new Dimension(320, 40));
		
		txtField1.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent ke) { txtField1Calcul(txtField1.getText(), getformatchoice()); }
			@Override
			public void keyReleased(KeyEvent ke) { txtField1Calcul(txtField1.getText(), getformatchoice());	}
			@Override
			public void keyTyped(KeyEvent ke) { txtField1Calcul(txtField1.getText(), getformatchoice()); }

			public void txtField1Calcul(String text, String selectedItem){
				
				if(selectedItem == "Notation Naturelle"){
					try{
						if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: text (String) -> InttxtField1Content (Décimal)
							InttxtField1Content = Integer.parseInt(text); 
							if(InttxtField1Content >= 0){
								/*
								* Conversion: InttxtField1Content (Décimal) -> Binaire
								* 								  			-> Octal
								* 							      	 		-> Hexadécimal
								*/
								txtField2.setText(Integer.toBinaryString(InttxtField1Content)); 
								txtField3.setText(Integer.toOctalString(InttxtField1Content));
								txtField4.setText(Integer.toHexString(InttxtField1Content).toUpperCase());
							}
							else{
								txtField2.setText("-" + Integer.toBinaryString((-1)*InttxtField1Content)); 
								txtField3.setText("-" + Integer.toOctalString((-1)*InttxtField1Content));
								txtField4.setText("-" + Integer.toHexString((-1)*InttxtField1Content).toUpperCase());
							}
						}
					}
					catch (NumberFormatException e1)	{
						if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							if(InttxtField1Content >= 0){
								txtField2.setText(Integer.toBinaryString(InttxtField1Content)); 
								txtField3.setText(Integer.toOctalString(InttxtField1Content));
								txtField4.setText(Integer.toHexString(InttxtField1Content).toUpperCase());
							}
							else{
								txtField2.setText("-" + Integer.toBinaryString((-1)*InttxtField1Content)); 
								txtField3.setText("-" + Integer.toOctalString((-1)*InttxtField1Content));
								txtField4.setText("-" + Integer.toHexString((-1)*InttxtField1Content).toUpperCase());
							}
						}
					}
				}
				// conversion en complément à 2 sur 8 bits à sens unique: Décimal -> binaire - octal - Hexadecimal
				else if(selectedItem == "Complément à 2 (8 bits)"){
					try{
						if(text.isEmpty()){
							txtField2.setText("");
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: txtField1Content (String) -> InttxtField1Content (Décimal)
							InttxtField1Content = Integer.parseInt(text);
							if(InttxtField1Content >= 0){
								if(InttxtField1Content < 128){
									/*
									* Conversion: InttxtField1Content (Décimal) -> Binaire
									* 								  			-> Octal
									* 							      	 		-> Hexadécimal			
									*/
									txtField2.setText(String.format("%" + 8 + "s", Integer.toBinaryString(InttxtField1Content)).replace(' ', '0')); 
									txtField3.setText(String.format("%" + 3 + "s", Integer.toOctalString(InttxtField1Content)).replace(' ', '0'));
									txtField4.setText(String.format("%" + 2 + "s", Integer.toHexString(InttxtField1Content)).replace(' ', '0').toUpperCase());
								}
								else{
									txtField2.setText("");
									txtField3.setText("");
									txtField4.setText("");
								}
							}
							else{
								int complement = (int) Math.pow(2, 8) + InttxtField1Content;
								if(InttxtField1Content > -128){
									txtField2.setText(Integer.toBinaryString(complement)); 
									txtField3.setText(Integer.toOctalString(complement));
									txtField4.setText(Integer.toHexString(complement).toUpperCase());
								}
								else{
									txtField2.setText(""); 
									txtField3.setText("");
									txtField4.setText("");
								}
							}
						}
					}
					catch (NumberFormatException e1)	{
						if(text.isEmpty()){
							txtField2.setText("");
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							if(InttxtField1Content >= 0){
								if(InttxtField1Content < 128){
									txtField2.setText(String.format("%" + 8 + "s", Integer.toBinaryString(InttxtField1Content)).replace(' ', '0')); 
									txtField3.setText(String.format("%" + 3 + "s", Integer.toOctalString(InttxtField1Content)).replace(' ', '0'));
									txtField4.setText(String.format("%" + 2 + "s", Integer.toHexString(InttxtField1Content)).replace(' ', '0').toUpperCase());
								}
								else{
									txtField2.setText("");
									txtField3.setText("");
									txtField4.setText("");
								}
							}
							else{
								int complement = (int) Math.pow(2, 8) + InttxtField1Content;
								if(InttxtField1Content > -128){
									txtField2.setText(Integer.toBinaryString(complement)); 
									txtField3.setText(Integer.toOctalString(complement));
									txtField4.setText(Integer.toHexString(complement).toUpperCase());
								}
								else{
									txtField2.setText(""); 
									txtField3.setText("");
									txtField4.setText("");
								}
							}
						}
					}
				}
				// conversion en complément à 2 sur 16 bits à sens unique: Décimal -> binaire - octal - Hexadecimal
				else if(selectedItem == "Complément à 2 (16 bits)"){
					try{
						if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: txtField1Content (String) -> InttxtField1Content (Décimal)
							InttxtField1Content = Integer.parseInt(text);
							if(InttxtField1Content >= 0){
								if(InttxtField1Content < 32768){
									/*
									* Conversion: InttxtField1Content (Décimal) -> Binaire
									* 								  			-> Octal
									* 							      	 		-> Hexadécimal			
									*/
									txtField2.setText(String.format("%" + 16 + "s", Integer.toBinaryString(InttxtField1Content)).replace(' ', '0')); 
									txtField3.setText(String.format("%" + 5 + "s", Integer.toOctalString(InttxtField1Content)).replace(' ', '0'));
									txtField4.setText(String.format("%" + 4 + "s", Integer.toHexString(InttxtField1Content)).replace(' ', '0').toUpperCase());
								}
								else{
									JOptionPane.showMessageDialog(BaseConverter.this, "Impossible d'exprimer ce nombre sur 16 bits !", "Erreur !", JOptionPane.OK_OPTION);
								}
							}	
							else{
								if(InttxtField1Content > -32768){
									int complement = (int) Math.pow(2, 16) + InttxtField1Content;
									txtField2.setText(Integer.toBinaryString(complement)); 
									txtField3.setText(Integer.toOctalString(complement));
									txtField4.setText(Integer.toHexString(complement).toUpperCase());
								}
								else{
									JOptionPane.showMessageDialog(BaseConverter.this, "Impossible d'exprimer ce nombre sur 16 bits !", "Erreur !", JOptionPane.OK_OPTION);
								}	
							}
						}
					}
					catch (NumberFormatException e1)	{
						if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else{
							if(InttxtField1Content >= 0){
								if(InttxtField1Content < 32768){
									txtField2.setText(String.format("%" + 16 + "s", Integer.toBinaryString(InttxtField1Content)).replace(' ', '0')); 
									txtField3.setText(String.format("%" + 5 + "s", Integer.toOctalString(InttxtField1Content)).replace(' ', '0'));
									txtField4.setText(String.format("%" + 4 + "s", Integer.toHexString(InttxtField1Content)).replace(' ', '0').toUpperCase());
								}
								else{
									JOptionPane.showMessageDialog(BaseConverter.this, "Impossible d'exprimer ce nombre sur 16 bits !", "Erreur !", JOptionPane.OK_OPTION);
								}
							}	
							else{
								if(text.equals("-")){
									return;
								}
								else{
									if(InttxtField1Content > -32768){
										int complement = (int) Math.pow(2, 16) + InttxtField1Content;
										txtField2.setText(Integer.toBinaryString(complement)); 
										txtField3.setText(Integer.toOctalString(complement));
										txtField4.setText(Integer.toHexString(complement).toUpperCase());
									}
									else{
										JOptionPane.showMessageDialog(BaseConverter.this, "Impossible d'exprimer ce nombre sur 16 bits !", "Erreur !", JOptionPane.OK_OPTION);
									}
								}
							}
						}
					}
				}
				// conversion en norme IEEE 754 simple précision à sens unique: Décimal -> binaire - octal - Hexadecimal
				else{
					try {
						if(text.equals("-")){
							return;
						}
						else if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else{
							// Conversion: txtField1Content (String) -> FloattxtField1Content (Float)
							FloattxtField1Content = Float.parseFloat(text);
							// Conversion: FloattxtField1Content (Float) -> bFloattxtField1Content (Binaire norme IEE 754)
							int bFloattxtField1Content = Float.floatToIntBits(FloattxtField1Content);
		
							/*
							*  Conversion: bFloattxtField1Content (Binaire norme IEE 754) -> Octal
							*    														   -> Hexadecimal
							*/
							txtField2.setText(Integer.toBinaryString(bFloattxtField1Content)); 
							txtField3.setText(Integer.toOctalString(bFloattxtField1Content));
							txtField4.setText(Integer.toHexString(bFloattxtField1Content).toUpperCase());
						}
					}
					catch (NumberFormatException e) {
						if(text.equals("-")){
							return;
						}
						else if(text.isEmpty()){
							txtField2.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else{
							int bFloattxtField1Content = Float.floatToIntBits(FloattxtField1Content);
		
							txtField2.setText(Integer.toBinaryString(bFloattxtField1Content)); 
							txtField3.setText(Integer.toOctalString(bFloattxtField1Content));
							txtField4.setText(Integer.toHexString(bFloattxtField1Content).toUpperCase());
						}
					}
				}
			}
		
		});

		CalculZone1.add(DecimalLabel);
		CalculZone1.add(txtField1);

		return CalculZone1;
	}
	private JPanel CreateCalculZone2(){
		JPanel CalculZone2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));

		JLabel binaireLabel = new JLabel("Binaire : ");
		binaireLabel.setForeground(Color.WHITE);
		binaireLabel.setFont(new Font("Serif", Font.BOLD, 14));
		CalculZone2.setBackground(Color.DARK_GRAY);
		txtField2.setBackground(Color.DARK_GRAY);
		txtField2.setForeground(Color.WHITE);
		txtField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		txtField2.setFont(new Font("Serif", Font.BOLD, 14));
		txtField2.setPreferredSize(new Dimension(320, 40));
		
		txtField2.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent ke) { txtField2Calcul(txtField2.getText(), getformatchoice()); }
			@Override
			public void keyReleased(KeyEvent ke) { txtField2Calcul(txtField2.getText(), getformatchoice());	}
			@Override
			public void keyTyped(KeyEvent ke) { txtField2Calcul(txtField2.getText(), getformatchoice()); }

			public void txtField2Calcul(String text, String SelectedItem){
		
				if(SelectedItem.equals("Notation Naturelle")){
		
					try{
						if(text.isEmpty()){
							txtField1.setText(""); 
							txtField3.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: txtField2Content (String) -> InttxtField2Content (Binaire naturel)
							InttxtField2Content = Integer.parseInt(text, 2);
							if(InttxtField2Content >= 0){
								/*  Conversion: InttxtField2Content (Binaire signé) -> Décimal
								*  					 							-> Octal naturel
								*   												-> Hexadécimal naturel
								*/
					
								txtField1.setText(Integer.toString(InttxtField2Content));
								txtField3.setText(Integer.toOctalString(InttxtField2Content));
								txtField4.setText(Integer.toHexString(InttxtField2Content).toUpperCase());
							}
							else{
								txtField1.setText("-" + Integer.toString(-1*InttxtField2Content));
								txtField3.setText("-" + Integer.toOctalString(-1*InttxtField2Content));
								txtField4.setText("-" + Integer.toHexString(-1*InttxtField2Content).toUpperCase());
							}
						}						
					}
					catch (NumberFormatException e1)	{
						if(InttxtField2Content >= 0){
							txtField1.setText(Integer.toString(InttxtField2Content));
							txtField3.setText(Integer.toOctalString(InttxtField2Content));
							txtField4.setText(Integer.toHexString(InttxtField2Content).toUpperCase());
						}
						else{
							txtField1.setText("-" + Integer.toString(-1*InttxtField2Content));
							txtField3.setText("-" + Integer.toOctalString(-1*InttxtField2Content));
							txtField4.setText("-" + Integer.toHexString(-1*InttxtField2Content).toUpperCase());
						}
					}
		
				}
				
			}
	
		});

		CalculZone2.add(binaireLabel);
		CalculZone2.add(txtField2);

		return CalculZone2;
	}
	private JPanel CreateCalculZone3(){
		JPanel CalculZone3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));

		JLabel OctalLabel = new JLabel("Octal : ");
		OctalLabel.setForeground(Color.WHITE);
		OctalLabel.setFont(new Font("Serif", Font.BOLD, 14));
		CalculZone3.setBackground(Color.DARK_GRAY);
		txtField3.setBackground(Color.DARK_GRAY);
		txtField3.setForeground(Color.WHITE);
		txtField3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		txtField3.setFont(new Font("Serif", Font.BOLD, 14));
		txtField3.setPreferredSize(new Dimension(320, 40));

		txtField3.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) { txtField3Calcul(txtField3.getText() , getformatchoice()); };
			@Override
			public void keyReleased(KeyEvent ke) { txtField3Calcul(txtField3.getText() , getformatchoice()); };
			@Override
			public void keyTyped(KeyEvent ke) { txtField3Calcul(txtField3.getText() , getformatchoice()); };
			
			public void txtField3Calcul(String text, String selectedItem){
		
				if(selectedItem.equals("Notation Naturelle")){
					try{
						if(text.isEmpty()){
							txtField1.setText(""); 
							txtField2.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: text (String) -> InttxtField3Content (Octal signé)
							InttxtField3Content = Integer.parseInt(text, 8);
							if(InttxtField3Content >= 0){
								/*  Conversion: InttxtField3Content (Octal signé) -> Décimal signé
								*   											  -> Binaire signé
								*     											  -> Hexadécimal signé
								*/
								txtField1.setText(Integer.toString(InttxtField3Content));	
								txtField2.setText(Integer.toBinaryString(InttxtField3Content));
								txtField4.setText(Integer.toHexString(InttxtField3Content).toUpperCase());
							}
							else{
								txtField1.setText("-" + Integer.toString(-1*InttxtField3Content));	
								txtField2.setText("-" + Integer.toBinaryString(-1*InttxtField3Content));
								txtField4.setText("-" + Integer.toHexString(-1*InttxtField3Content).toUpperCase());
							}
						}
						
					}
					catch (NumberFormatException e1)	{
						if(InttxtField3Content >= 0){
							txtField1.setText(Integer.toString(InttxtField3Content));	
							txtField2.setText(Integer.toBinaryString(InttxtField3Content));
							txtField4.setText(Integer.toHexString(InttxtField3Content).toUpperCase());
						}
						else{
							txtField1.setText("-" + Integer.toString(-1*InttxtField3Content));	
							txtField2.setText("-" + Integer.toBinaryString(-1*InttxtField3Content));
							txtField4.setText("-" + Integer.toHexString(-1*InttxtField3Content).toUpperCase());
						}
					}
				}
				
			}
		
		});

		CalculZone3.add(OctalLabel);
		CalculZone3.add(txtField3);

		return CalculZone3;
	}
	private JPanel CreateCalculZone4(){
		JPanel CalculZone4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));

		JLabel hexLabel = new JLabel("Hexadecimal :");
		hexLabel.setForeground(Color.WHITE);
		hexLabel.setFont(new Font("Serif", Font.BOLD, 14));
		CalculZone4.setBackground(Color.DARK_GRAY);
		txtField4.setBackground(Color.DARK_GRAY);
		txtField4.setForeground(Color.WHITE);
		txtField4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		txtField4.setFont(new Font("Serif", Font.BOLD, 14));
		txtField4.setPreferredSize(new Dimension(320, 40));

		txtField4.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) { txtField4Calcul(txtField4.getText() , getformatchoice()); };
			@Override
			public void keyReleased(KeyEvent ke) { txtField4Calcul(txtField4.getText() , getformatchoice()); };
			@Override
			public void keyTyped(KeyEvent ke) { txtField4Calcul(txtField4.getText() , getformatchoice()); };
		
			public void txtField4Calcul(String text, String selectedItem){
		
				if(selectedItem.equals("Notation Naturelle")){
					try{
						if(text.isEmpty()){
							txtField1.setText(""); 
							txtField2.setText("");
							txtField4.setText("");
						}
						else if(text.equals("-")){
							return;
						}
						else{
							// Conversion: text (String) -> InttextField4Content (Hexadécimal signé)
							InttxtField4Content = Integer.parseInt(text, 16);	
							if(InttxtField4Content >= 0){
								/*  Conversion: InttextField4Content (Hexadécimal signé) -> Décimal signé
								*       												 -> Binaire signé
								*        												 -> Octale signé
								*/
								
								txtField1.setText(Integer.toString(InttxtField4Content));
								txtField2.setText(Integer.toBinaryString(InttxtField4Content));
								txtField3.setText(Integer.toOctalString(InttxtField4Content));
							}	
							else{
								txtField1.setText("-" + Integer.toString(-1*InttxtField4Content));
								txtField2.setText("-" + Integer.toBinaryString(-1*InttxtField4Content));
								txtField3.setText("-" + Integer.toOctalString(-1*InttxtField4Content));
							}	
						}
												
					}
					catch (NumberFormatException e1)	{
						InttxtField4Content = Integer.parseInt(text, 16);	
							if(InttxtField4Content >= 0){
								txtField1.setText(Integer.toString(InttxtField4Content));
								txtField2.setText(Integer.toBinaryString(InttxtField4Content));
								txtField3.setText(Integer.toOctalString(InttxtField4Content));
							}	
							else{
								txtField1.setText("-" + Integer.toString(-1*InttxtField4Content));
								txtField2.setText("-" + Integer.toBinaryString(-1*InttxtField4Content));
								txtField3.setText("-" + Integer.toOctalString(-1*InttxtField4Content));
							}
					}
				}
				
			}
		
		});

		CalculZone4.add(hexLabel);
		CalculZone4.add(txtField4);

		return CalculZone4;
	}
	private JPanel CreateCalculButton(){
		JPanel CalculButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		CalculButton.setBackground(Color.DARK_GRAY);
		ResetBtn.setBackground(Color.RED);
		ResetBtn.setForeground(Color.WHITE);
		ResetBtn.setFont(new Font("Serif", Font.BOLD, 14));

	 	
		ResetBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtField1.setText("");
				txtField2.setText("");
				txtField3.setText("");
				txtField4.setText("");
			}

		});

		CalculButton.add(ResetBtn);

		return CalculButton;
	}

	private String getformatchoice(){ return formatchoice; }
	
}

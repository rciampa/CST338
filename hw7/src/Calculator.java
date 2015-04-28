/***************************************************************************************
 *                         hw5/Calculate.java - Copyright rciampa
 *                         
 * Title: Homework #7 Swing GUI Calculator
 * 
 * Abstract: A simple calculator with a swing GUI. The user is allowed to enter two
 * 			 numbers and choose addition, subtraction and take the sin of a number or
 * 			 final value from a calculation. 
 *
 * @author rciampa
 * ID: 7470
 * Date: Wed April 13 : 20:13:03
 * File: hw7/Calculator.java
 * Requires: n/a
 *
 * This application created for the CST338 course at CSU Monterey Bay SP:2015 Semester.
 * This software is not licensed or warranted for any other purpose than the CSUMB
 * scholastic 2015 semester with Dr. Byun.
 *
 * Heading.java
 * /home/rciampa/Documents/Umbrello/headings/heading.java
 *
 ***************************************************************************************/

//package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RichardD
 */
public class Calculator {

	/**
	 * Public constructor
	 */
    public Calculator() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame calcWindow = new CalcWindow();
        calcWindow.setVisible(true);
    }
}

/**
 * 
 * @author rciampa
 *
 */
class CalcWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CalcEventHandler calcEvents = new CalcEventHandler();
	
	public JTextField calcDisplay, histDisplay;
	double numOne, numTwo, result;
	boolean hasPoint;
	String currentOperator;
	
	/**
	 * Default constructor
	 */
    CalcWindow() {
        super("Calculator");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(225, 300);
        this.setResizable(false);

        //Add the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(200, 20);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setPreferredSize(new Dimension(45, 10));
        fileMenu.setEnabled(true);
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("exit");
        exitMenuItem.addActionListener(calcEvents);
        
        fileMenu.add(exitMenuItem);
        
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setPreferredSize(new Dimension(45, 10));
        editMenu.setEnabled(false);
        
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setActionCommand("about_menu_item");
        aboutItem.addActionListener(calcEvents);
        //Add items to the help menu
        helpMenu.add(aboutItem);
        
        //Add all the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 5;
        //Add all the components to the window
        this.add(menuBar, c);
        
        
        c.gridx = 0; c.gridy = 1;
        c.gridwidth = 6;
        JPanel funcPanel = new CalcFunctionPanel();
        this.add(funcPanel, c);
        
        this.pack();
        this.setVisible(true);

    }
    
    /**
     * Updates the calculator display
     * 
     * @param val
     */
    public void updateCalcDisplay(String val) {
    	calcDisplay.setText(calcDisplay.getText() + val);
    }
    
    /**
     * Clears the calculator display
     */
    public void clearCalcDisplay(){
    	calcDisplay.setText("");
    	histDisplay.setText("");
    	numOne = 0; numTwo = 0; result = 0;
    	hasPoint = false;
    	currentOperator = "";
    }
    
    /**
     * Is called any time a function key is pressed
     * 
     * @param operator The operator symbol
     */
    public void funcKeyPressed(String operator){
    	String val = calcDisplay.getText();
    	if(val.length() > 0){
    		numOne = Double.parseDouble(val);
    		updateHistDisplay(operator, false);
    		calcDisplay.setText("");
    	}
    	currentOperator = operator;
    	
    }
    
    /**
     * Inserts a decimal point into the number
     */
    public void insertPoint(){
    	calcDisplay.setText(calcDisplay.getText() + ".");
    	hasPoint = true;
    }
    
    /**
     * Gives the negative of the number
     */
    public void funcNegKeyPressed(){
    	if(!calcDisplay.getText().isEmpty()){
    		calcDisplay.setText("-" + calcDisplay.getText());
    	}
    }
    
    /**
     * Evaluates the operation and gives the results
     */
    public void evaluateOperator(){
    	switch(currentOperator){
    	case "+":
    		if(calcDisplay.getText().length() > 0){
    			numTwo = Double.parseDouble(calcDisplay.getText());
    			updateHistDisplay(currentOperator, true);
    		}
    		evaluateSum();
    		break;
    	case "-":
    		if(calcDisplay.getText().length() > 0){
    			numTwo = Double.parseDouble(calcDisplay.getText());
    			updateHistDisplay(currentOperator, true);
    		}
    		evaluateSubtraction();
    		break;
    	case "sin":
    		updateHistDisplay(currentOperator, true);
    		evaluateSin();
    		break;
    	}
    }
    
    /**
     * Evaluates two numbers for the sum
     */
    private void evaluateSum(){
    	this.result = numOne + numTwo;
    	printResult();
    }
    
    /**
     * Evaluates two numbers via subtraction
     */
    private void evaluateSubtraction(){
    	this.result = numOne - numTwo;
    	printResult();
    }
    
    /**
     * Provides the sin of a number or the result
     */
    private void evaluateSin(){
    	if(currentOperator == "" && !calcDisplay.getText().isEmpty()){
    		updateHistDisplay("sin", true);
    	calcDisplay.setText(String.format("%1f RAD",
    			Math.sin(Double.parseDouble(calcDisplay.getText()))));
    	}else if(calcDisplay.getText().isEmpty()){
			calcDisplay.setText("error");
		}else{
			updateHistDisplay("sin", true);
			calcDisplay.setText(String.format("%1f RAD", Math.sin(this.result)));
		}
    }
    
    /**
     * Prints the result to the calculator display
     */
    private void printResult(){
    	if(hasPoint){
    		calcDisplay.setText(String.format("%1.1f", this.result));
    	}else{
    		calcDisplay.setText(String.format("%1.0f", this.result));
    	}
    }
    
    /**
     * Updates the history display during calculations
     * 
     * @param operator The symbol that represents the operator
     * @param eval True if the event is an evaluation, false  otherwise
     */
    private void updateHistDisplay(String operator, boolean eval){
    	if(!eval){
    	histDisplay.setText(calcDisplay.getText() + " " + operator + " ");
    	}else{
    		if(operator == "sin"){
    			histDisplay.setText("sin("+ calcDisplay.getText() + ") ");
    		}else{
    			histDisplay.setText(histDisplay.getText() + calcDisplay.getText() + " = ");
    		}
    	}
    }
    
    
    /**
     * 
     * @author rciampa
     *
     */
    class CalcFunctionPanel extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JButton func1, func2, func3, func4, func5,
                func6, func7, func8, func9, func10,
                func11, func12, func13, func14, func15,
                func16, func17, func18, func19, func20,
                func21,func22, func23, func24, func25,
                func26, func27, func28;
        
        /**
         * Default constructor
         */
        public CalcFunctionPanel() {
            super();
            setLayout(new GridBagLayout());
            setSize(225, 300);
            setLocationRelativeTo(null);
            addCalcDisplay();
            createFunctionKeys();
        }
        
        /**
         * Adds the calculator displays
         */
        private void addCalcDisplay(){
            GridBagConstraints c = new GridBagConstraints();
            
            calcDisplay = new JTextField(15);
            calcDisplay.setHorizontalAlignment(JTextField.RIGHT);
            calcDisplay.setEditable(false);
            calcDisplay.setBackground(new Color(255,255,255));
            calcDisplay.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
            calcDisplay.setBorder(new EmptyBorder(0, 0, 0, 0));
            c.gridx = 0; c.gridy = 3;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 15;
            c.gridwidth = 5;
            c.weightx =  0.5;
            this.add(calcDisplay, c);
            
            histDisplay = new JTextField(15);
            histDisplay.setHorizontalAlignment(JTextField.RIGHT);
            histDisplay.setEditable(false);
            histDisplay.setBackground(new Color(255,255,255));
            histDisplay.setFont(new Font(Font.MONOSPACED,Font.BOLD,14));
            histDisplay.setBorder(new EmptyBorder(0, 0, 0, 0));
            c.gridx = 0; c.gridy = 2;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 8;
            c.gridwidth = 5;
            c.weightx =  0;
            this.add(histDisplay, c);
            
        }

        /**
         * Creates all the calculator keys
         */
        private void createFunctionKeys() {
        	
        	Dimension prefDim = new Dimension(60,40);
        	Font calcFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
            
        	/**
        	 * Row one --unneeded
        	 */
        	
//            func1 = new JButton();
//            func1.setPreferredSize(prefDim);
//            this.add(func1, constraints(10, 15, 0, 3, 0.5, 1, 1,
//            		GridBagConstraints.BOTH));
//            
//            func2 = new JButton();
//            func2.setPreferredSize(prefDim);
//            this.add(func2, constraints(10, 15, 1, 3, 0.5, 1, 1,
//            		GridBagConstraints.BOTH));
//            
//            func3 = new JButton();
//            func3.setPreferredSize(prefDim);
//            this.add(func3, constraints(10, 15, 2, 3, 0.5, 1, 1,
//            		GridBagConstraints.BOTH));
//            
//            func4 = new JButton();
//            func4.setPreferredSize(prefDim);
//            this.add(func4, constraints(10, 15, 3, 3, 0.5, 1, 1,
//            		GridBagConstraints.BOTH));
//          
//            func5 = new JButton();
//            func5.setPreferredSize(prefDim);
//            this.add(func5, constraints(10, 15, 4, 3, 0.5, 1, 1,
//            		GridBagConstraints.BOTH));
            
            /**
             * Row two
             */
            
            func6 = new JButton();
            func6.setPreferredSize(prefDim);
            this.add(func6, constraints(10, 15, 0, 4, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func7 = new JButton();
            func7.setPreferredSize(prefDim);
            this.add(func7, constraints(10, 15, 1, 4, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func8 = new JButton("C");
            func8.setActionCommand("c");
            func8.setFont(calcFont);
            func8.setPreferredSize(prefDim);
            func8.addActionListener(calcEvents);
            this.add(func8, constraints(10, 15, 2, 4, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func9 = new JButton("Neg");
            func9.setActionCommand("neg");
            func9.setFont(calcFont);
            func9.setPreferredSize(prefDim);
            func9.addActionListener(calcEvents);
            this.add(func9, constraints(10, 15, 3, 4, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func10 = new JButton();
            func10.setPreferredSize(prefDim);
            this.add(func10, constraints(10, 15, 4, 4, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            /**
             * Row three
             */
            
            func11 = new JButton("7");
            func11.setActionCommand("7");
            func11.setFont(calcFont);
            func11.setPreferredSize(prefDim);
            func11.addActionListener(calcEvents);
            this.add(func11, constraints(10, 15, 0, 5, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func12 = new JButton("8");
            func12.setActionCommand("8");
            func12.setFont(calcFont);
            func12.setPreferredSize(prefDim);
            func12.addActionListener(calcEvents);
            this.add(func12, constraints(10, 15, 1, 5, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func13 = new JButton("9");
            func13.setActionCommand("9");
            func13.setFont(calcFont);
            func13.setPreferredSize(prefDim);
            func13.addActionListener(calcEvents);
            this.add(func13, constraints(10, 15, 2, 5, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func14 = new JButton("Sin");
            func14.setActionCommand("sin");
            func14.setFont(calcFont);
            func14.setPreferredSize(prefDim);
            func14.addActionListener(calcEvents);
            this.add(func14, constraints(10, 15, 3, 5, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func15 = new JButton();
            func15.setPreferredSize(prefDim);
            this.add(func15, constraints(10, 15, 4, 5, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            /**
             * Row four
             */
            func16 = new JButton("4");
            func16.setActionCommand("4");
            func16.setFont(calcFont);
            func16.setPreferredSize(prefDim);
            func16.addActionListener(calcEvents);
            this.add(func16, constraints(10, 15, 0, 6, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func17 = new JButton("5");
            func17.setActionCommand("5");
            func17.setFont(calcFont);
            func17.setPreferredSize(prefDim);
            func17.addActionListener(calcEvents);
            this.add(func17, constraints(10, 15, 1, 6, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func18 = new JButton("6");
            func18.setActionCommand("6");
            func18.setFont(calcFont);
            func18.setPreferredSize(prefDim);
            func18.addActionListener(calcEvents);
            this.add(func18, constraints(10, 15, 2, 6, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func19 = new JButton();
            func19.setPreferredSize(prefDim);
            this.add(func19, constraints(10, 15, 3, 6, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func20 = new JButton();
            func20.setPreferredSize(prefDim);
            this.add(func20, constraints(10, 15, 4, 6, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            /**
             * Row five
             */
            func21 = new JButton("1");
            func21.setActionCommand("1");
            func21.setFont(calcFont);
            func21.setPreferredSize(prefDim);
            func21.addActionListener(calcEvents);
            this.add(func21, constraints(10, 15, 0, 7, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func22 = new JButton("2");
            func22.setActionCommand("2");
            func22.setFont(calcFont);
            func22.setPreferredSize(prefDim);
            func22.addActionListener(calcEvents);
            this.add(func22, constraints(10, 15, 1, 7, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func23 = new JButton("3");
            func23.setActionCommand("3");
            func23.setFont(calcFont);
            func23.setPreferredSize(prefDim);
            func23.addActionListener(calcEvents);
            this.add(func23, constraints(10, 15, 2, 7, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func24 = new JButton("-");
            func24.setActionCommand("-");
            func24.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            func24.setPreferredSize(prefDim);
            func24.addActionListener(calcEvents);
            this.add(func24, constraints(10, 15, 3, 7, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            
            /**
             * Row six
             */
            func25 = new JButton("0");
            func25.setActionCommand("0");
            func25.setFont(calcFont);
            func25.setPreferredSize(new Dimension(120,40));
            func25.addActionListener(calcEvents);
            this.add(func25, constraints(10, 15, 0, 8, 0.5, 2, 1,
            		GridBagConstraints.BOTH));
            
            
            func26 = new JButton(".");
            func26.setActionCommand(".");
            func26.setPreferredSize(prefDim);
            func26.addActionListener(calcEvents);
            this.add(func26, constraints(10, 15, 2, 8, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func27 = new JButton("+");
            func27.setActionCommand("+");
            func27.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            func27.setPreferredSize(prefDim);
            func27.addActionListener(calcEvents);
            this.add(func27, constraints(10, 15, 3, 8, 0.5, 1, 1,
            		GridBagConstraints.BOTH));
            
            func28 = new JButton("=");
            func28.setActionCommand("=");
            func28.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            func28.setPreferredSize(prefDim);
            func28.addActionListener(calcEvents);
            this.add(func28, constraints(10, 15, 4, 7, 0.5, 1, 2,
    		GridBagConstraints.BOTH));

        }

        /**
         * Sets the grid bag constraints
         * 
         * @param ipadx
         * @param ipady
         * @param gridx
         * @param gridy
         * @param weightx
         * @param gridwidth
         * @param gridheight
         * @param fill
         * @return
         */
        private GridBagConstraints constraints(int ipadx, int ipady, int gridx,
        		int gridy, double weightx, int gridwidth, int gridheight,
        		int fill){
        	
        	GridBagConstraints c = new GridBagConstraints();
        	
        	c.fill = fill;
        	c.gridx = gridx;
        	c.gridy = gridy;
        	c.ipadx = ipadx;
        	c.ipady = ipady;
        	c.weightx = weightx;
        	c.gridwidth = gridwidth;
        	c.gridheight = gridheight;
        	c.insets = new Insets(3, 3, 3, 3);
        	
        	return c;
        }
    }
    
    
    /**
     * 
     * @author rciampa
     *
     */
    class CalcEventHandler implements ActionListener{

    	AboutWindow aboutWin;
    	
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "about_menu_item":
                	aboutWin = new AboutWindow();
                	aboutWin.setVisible(true);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "0":
                    updateCalcDisplay("0");
                	break;
                case "1":
                	updateCalcDisplay("1");
                	break;
                case "2":
                	 updateCalcDisplay("2");
                	break;
                case "3":
                	updateCalcDisplay("3");
                	break;
                case "4":
                	updateCalcDisplay("4");
                	break;
                case "5":
                	updateCalcDisplay("5");
                	break;
                case "6":
                	updateCalcDisplay("6");
                	break;
                case "7":
                	updateCalcDisplay("7");
                	break;
                case "8":
                	updateCalcDisplay("8");
                	break;
                case "9":
                	updateCalcDisplay("9");
                	break;
                case "+":
                	funcKeyPressed("+");
                	break;
                case "-":
                	funcKeyPressed("-");
                	break;
                case ".":
                	insertPoint();
                	break;
                case "sin":
                	evaluateSin();
                	break;
                case "neg":
                	funcNegKeyPressed();
                	break;
                case "=":
                	evaluateOperator();
                	break;
                case "c":
                	clearCalcDisplay();
                	break;
            }
        }

    }
    
    /**
     * 
     * @author rciampa
     *
     */
    class AboutWindow extends JFrame implements ActionListener {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JFrame aboutWindow;
    	CalcEventHandler calcEvents = new CalcEventHandler();

        public AboutWindow() {
        	setTitle("About Calculator");
            this.setSize(260, 200);
            this.setLayout(new BorderLayout());
            JButton okButton = new JButton("OK");
            okButton.setActionCommand("about_ok");
            okButton.addActionListener(this);
            this.add(okButton, BorderLayout.SOUTH);
            this.setVisible(false);
            JLabel label = new JLabel("Calculator");
            JLabel labelName = new JLabel("By: Richard D Ciampa");
            label.setHorizontalAlignment(JLabel.CENTER);
            labelName.setHorizontalAlignment(JLabel.CENTER);
            this.add(labelName, BorderLayout.CENTER);
            this.add(label, BorderLayout.NORTH);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);;
        }
        
        
		@Override
		public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
            case "about_ok":
            	this.dispose();
            	break;
            }
			
		}
    }
}





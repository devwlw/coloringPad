package test;
/*
	Spirograph Applet

	File: Spiro.java
	Author: Anu Garg http://wordsmith.org/anu
	Email:  gargATwordsmith.org (replace AT with @)

	A spirograph is a curve defined by the equations:

	x = (R+r)*cos(t) - O*cos(((R+r)/r)*t)
	y = (R+r)*sin(t) - O*sin(((R+r)/r)*t)
   when moving circle is outside the fixed circle.

	x = (R-r)*cos(t) + O*cos(((R-r)/r)*t)
	y = (R-r)*sin(t) - O*sin(((R-r)/r)*t)
   when moving circle is inside the fixed circle.

	Where R and r are the radii of fixed and moving circle,
	and O is the offset of the drawing point in the moving circle.

*/

import java.awt.*;
import java.applet.*;
import java.lang.*;
import java.net.*;
import java.awt.event.*;

//===========================Class: spiro=========================

public class Spiro extends Applet{

	private int R = 99, r = 3, O = 100;  // Radii of fixed & moving circles, and offset
	private int I, degreeI;                // Number of revolutions in radians, in degrees
	private boolean movingCircleOut = false; // moving circle is inside the fixed circle or outside

	Scrollbar sliderR, sliderr, sliderO, sliderI;
	String sliderTextR = "Fixed circle radius (1 - 100): ";
	String sliderTextr = "Moving circle radius (1 - 100): ";
	String sliderTextO = "Moving circle offset (0 - 100): ";
	String sliderTextC = "Moving circle: ";
	String sliderTextI = "Revolutions in radians: ";
	Label sliderLabelR, sliderLabelr, sliderLabelO, labelColor, sliderLabelC, sliderLabelI, titleLabel;

	Panel fields;

	Scrollbar redSlider,blueSlider,greenSlider;
	Label redLabel, greenLabel, blueLabel;
	private int redBits = 90, greenBits = 246, blueBits = 255;

	String colorMode = "single"; // possible values: single, multi, random

	String sliderTextRed = "Red (0 - 255): ";
	String sliderTextGreen = "Green (0 - 255): ";
	String sliderTextBlue = "Blue (0 - 255): ";
	Panel colorPanel, buttonPanel;

	CheckboxGroup CircleInOutGroup; // moving circile is inside or outside the fixed circle?
	Checkbox insideCheckbox, outsideCheckbox;
	Checkbox singleColorCheckbox, multiColorCheckbox, randomColorCheckbox;

	CheckboxGroup LinePointGroup;	// Plot lines or point?
	Checkbox lineCheckbox, pointCheckbox;

	Checkbox soundCheckbox;	// Play sound?
	Checkbox bgCheckbox;	// Background black (or white)?

	int appletHeight=400, appletWidth=680;

	Font font;
	Panel controlPanel, optionPanelColor, optionPanel0, optionPanel1, optionPanel2; // for radio buttons and checkboxes
	String audioScroll = "bell.au";
	String audioButton = "chirp.au";

	Button okButton;

	// Get parameters (for radii, color, etc)
	public void getParams()
	{
		if(getParameter("R") !=  null)
		{
			R = new Integer(new String(getParameter("R"))).intValue();
			if(R < 1)
				R = 1;
			else if(R > 100)
				R = 100;
		}

		if(getParameter("smallr") !=  null)
		{
			r = new Integer(new String(getParameter("smallr"))).intValue();
			if(r < 1)
				r = 1;
			else if(r > 100)
				r = 100;
		}

		I = revolutions(R, r); // Adjust I based on new R and r

		if(getParameter("O") !=  null)
		{
			O = new Integer(new String(getParameter("O"))).intValue();
			if(O < 1)
				O = 1;
			else if(O > 100)
				O = 100;
		}

		if(getParameter("movingCircleOut") !=  null)
		{
			movingCircleOut = new Boolean(new String(getParameter("movingCircleOut"))).booleanValue();
		}

		if(getParameter("redBits") !=  null)
		{
			redBits = new Integer(new String(getParameter("redBits"))).intValue();
			if(redBits < 0)
				redBits = 0;
			else if(redBits > 255)
				redBits = 255;
		}

		if(getParameter("greenBits") !=  null)
		{
			greenBits = new Integer(new String(getParameter("greenBits"))).intValue();
			if(greenBits < 0)
				greenBits = 0;
			else if(greenBits > 255)
				greenBits = 255;
		}

		if(getParameter("blueBits") !=  null)
		{
			blueBits = new Integer(new String(getParameter("blueBits"))).intValue();
			if(blueBits < 0)
				blueBits = 0;
			else if(blueBits > 255)
				blueBits = 255;
		}

		if(getParameter("colorMode") !=  null)
		{
			colorMode = new String(getParameter("colorMode")); // possible values: single, multi, random
		}

		if(getParameter("height") !=  null)
		{
			appletHeight = new Integer(new String(getParameter("height"))).intValue();
			if(appletHeight < 1)
				appletHeight = 400;
		}
		if(getParameter("width") !=  null)
		{
			appletWidth = new Integer(new String(getParameter("width"))).intValue();
			if(appletWidth < 1)
				appletWidth = 680;
		}

	}

	public void init()
	{
		getParams();
		//resize(appletWidth, appletHeight);
		showStatus("Spirograph by Anu Garg (gargATwordsmith.org) http://wordsmith.org/anu");

		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new BorderLayout());

		controlPanel = new Panel();
		controlPanel.setLayout(new GridLayout(27,1));
		add("East", controlPanel);

		titleLabel = new Label("S  P  I  R  O  G  R  A  P  H", 1); //1 = CENTER
		font = new Font("Helvetica", Font.BOLD, 13);
		titleLabel.setFont(font);
		titleLabel.setBackground(Color.gray);
		titleLabel.setForeground(Color.white);
		controlPanel.add(titleLabel);

		controlPanel.add(new Label(" "));

		sliderLabelR = new Label(sliderTextR + Integer.toString(R));
		// textFont = new Font("Helvetica", Font.PLAIN, 11);
		// sliderLabelR.setFont(textFont);
		controlPanel.add(sliderLabelR);
		sliderR = new Scrollbar(Scrollbar.HORIZONTAL, R, 1, 1, 101);
		controlPanel.add(sliderR);

		sliderLabelr = new Label(sliderTextr + Integer.toString(r));
		controlPanel.add(sliderLabelr);
		sliderr = new Scrollbar(Scrollbar.HORIZONTAL, r, 1, 1, 101);
		controlPanel.add(sliderr);

		sliderLabelO = new Label(sliderTextO + Integer.toString(O));
		controlPanel.add(sliderLabelO);
		sliderO = new Scrollbar(Scrollbar.HORIZONTAL, O, 1, 0, 101);
		controlPanel.add(sliderO);

		controlPanel.add(new Label(" "));
		// Moving circle is outside or inside the fixed circle?
		sliderLabelC = new Label(sliderTextC);

		CheckboxGroup CircleInOutGroup = new CheckboxGroup ();
		insideCheckbox = new Checkbox("inside",  CircleInOutGroup, !movingCircleOut);
		outsideCheckbox = new Checkbox("outside", CircleInOutGroup, movingCircleOut);

		optionPanel0 = new Panel();
		optionPanel0.setLayout(new GridLayout(1, 3));
		optionPanel0.add(sliderLabelC);
		optionPanel0.add(insideCheckbox);
		optionPanel0.add(outsideCheckbox);
		controlPanel.add("North", optionPanel0);

		controlPanel.add(new Label(" "));

		redLabel = new Label(sliderTextRed + Integer.toString(redBits));
		controlPanel.add(redLabel);
		redSlider = new Scrollbar(Scrollbar.HORIZONTAL, redBits, 1, 0, 256);
		controlPanel.add(redSlider);

		greenLabel = new Label(sliderTextGreen + Integer.toString(greenBits));
		controlPanel.add(greenLabel);
		greenSlider = new Scrollbar(Scrollbar.HORIZONTAL, greenBits, 1, 0, 256);
		controlPanel.add(greenSlider);

		blueLabel = new Label(sliderTextBlue + Integer.toString(blueBits));
		controlPanel.add(blueLabel);
		blueSlider = new Scrollbar(Scrollbar.HORIZONTAL, blueBits, 1, 0, 256);
		controlPanel.add(blueSlider);


		controlPanel.add(new Label(" "));
		CheckboxGroup colorGroup = new CheckboxGroup ();
		singleColorCheckbox = new Checkbox("single",  colorGroup, colorMode.equals("single"));
		multiColorCheckbox = new Checkbox("multi", colorGroup, colorMode.equals("multi"));
		randomColorCheckbox = new Checkbox("random", colorGroup, colorMode.equals("random"));

		optionPanelColor = new Panel();
		optionPanelColor.setLayout(new GridLayout(1, 4));
		labelColor = new Label("Color: ");
		optionPanelColor.add(labelColor);
		optionPanelColor.add(singleColorCheckbox);
		optionPanelColor.add(multiColorCheckbox);
		optionPanelColor.add(randomColorCheckbox);
		controlPanel.add("North", optionPanelColor);

		controlPanel.add(new Label(" "));

		sliderLabelI = new Label(sliderTextI + Integer.toString(I));
		controlPanel.add(sliderLabelI);
		sliderI = new Scrollbar(Scrollbar.HORIZONTAL, I, 1, 0, 400);
		controlPanel.add(sliderI);

		controlPanel.add(new Label(" "));

		optionPanel1 = new Panel();
		optionPanel1.setLayout(new GridLayout(1, 2));
		optionPanel2 = new Panel();
		optionPanel2.setLayout(new GridLayout(1, 2));

		CheckboxGroup LinePointGroup = new CheckboxGroup ();
		lineCheckbox = new Checkbox("line",  LinePointGroup, true);
		pointCheckbox = new Checkbox("point", LinePointGroup, false);

		soundCheckbox = new Checkbox("sound effects", true);
		bgCheckbox = new Checkbox("dark canvas", true);

		optionPanel1.add(lineCheckbox);
		optionPanel1.add(soundCheckbox);
		optionPanel2.add(pointCheckbox);
		optionPanel2.add(bgCheckbox);

		controlPanel.add("North", optionPanel1);
		controlPanel.add("North", optionPanel2);

		controlPanel.add(new Label(" "));

		buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1,2,4,0));
		buttonPanel.add(new Button("Random"));
		buttonPanel.add(new Button("About"));
		controlPanel.add("North", buttonPanel);
	}

	public boolean handleEvent(Event e)
	{
		if (e.target instanceof Scrollbar) {
			if (soundCheckbox.getState()) play(getCodeBase(), audioScroll); // Not getDocumentBase()
			int value = ((Scrollbar)e.target).getValue();
			if (e.target == sliderR) {
				sliderLabelR.setText(sliderTextR + Integer.toString(value));
				R = value;

				I = revolutions(R, r); // Adjust I based on new R and r
				sliderLabelI.setText(sliderTextI + Integer.toString(I));
				sliderI.setValue(I);

			} else if (e.target == sliderr) {
				sliderLabelr.setText(sliderTextr + Integer.toString(value));
				r = value;
				if (r == 0) r++;

				I = revolutions(R, r); // Adjust I based on new R and r
				sliderLabelI.setText(sliderTextI + Integer.toString(I));
				sliderI.setValue(I);

			} else if (e.target == sliderO) {
				sliderLabelO.setText(sliderTextO + Integer.toString(value));
				O = value;
			} else if (e.target == redSlider) {
				redLabel.setText(sliderTextRed + Integer.toString(value));
				redBits=value;
			} else if (e.target == greenSlider) {
				greenLabel.setText(sliderTextGreen + Integer.toString(value));
				greenBits=value;
			} else if (e.target == blueSlider) {
				blueLabel.setText(sliderTextBlue + Integer.toString(value));
				blueBits=value;
			} else if (e.target == sliderI) {
				sliderLabelI.setText(sliderTextI + Integer.toString(value));
				I = value;
			}

			repaint();
			return true;
		}
		return super.handleEvent(e);
	}

	int revolutions(int R, int r){
		return (int)(r/gcd(R,r))*7; // radians
	}

/*
	int rad2degree(int i){
		return (int)(180*7*i/22);
	}
*/

	public boolean action(Event e, Object arg)
	{
		if("Random".equals(arg)){
			if (soundCheckbox.getState()) play(getCodeBase(), audioButton);

			R = (int)(Math.random()*100+1);
			r = (int)(Math.random()*100+1);
			if (r == 0) r++;
			O = (int)(Math.random()*100+1);
			sliderLabelR.setText(sliderTextR + Integer.toString(R));
			sliderLabelr.setText(sliderTextr + Integer.toString(r));
			sliderLabelO.setText(sliderTextO + Integer.toString(O));
			sliderR.setValue(R);
			sliderr.setValue(r);
			sliderO.setValue(O);

			I = revolutions(R, r); // Adjust I based on new R and r
			sliderLabelI.setText(sliderTextI + Integer.toString(I));
			sliderI.setValue(I);

			redBits = (int)(Math.random()*255);
			blueBits = (int)(Math.random()*255);
			greenBits = (int)(Math.random()*255);

/*
			int bumpFactor = 50; //value by which increase/decrease color values to compensate for background
			if (bgCheckbox.getState() && (redBits+blueBits+greenBits)<400) { // dark background
				if (redBits   + bumpFactor < 255) redBits   += bumpFactor;
				if (blueBits  + bumpFactor < 255) blueBits  += bumpFactor;
				if (greenBits + bumpFactor < 255) greenBits += bumpFactor;
			}
			if (!bgCheckbox.getState() && (redBits+blueBits+greenBits)>400) { // light background
				if (redBits   - bumpFactor >= 0) redBits   -= bumpFactor;
				if (blueBits  - bumpFactor >= 0) blueBits  -= bumpFactor;
				if (greenBits - bumpFactor >= 0) greenBits -= bumpFactor;
			}
*/
			redLabel.setText(sliderTextRed + Integer.toString(redBits));
			greenLabel.setText(sliderTextGreen + Integer.toString(greenBits));
			blueLabel.setText(sliderTextBlue + Integer.toString(blueBits));
			redSlider.setValue(redBits);
			greenSlider.setValue(greenBits);
			blueSlider.setValue(blueBits);

			repaint();
			return true;
		}

		if("About".equals(arg)){
			AboutDialog ab = new AboutDialog(this);
			ab.pack();
			ab.show();
			return true;
		}

		if (e.target == lineCheckbox || e.target == pointCheckbox || e.target == bgCheckbox || e.target == singleColorCheckbox || e.target == multiColorCheckbox || e.target == randomColorCheckbox || e.target == insideCheckbox || e.target == outsideCheckbox) {
		   repaint();
		   return true;
		}
		return true;

	}

    public int gcd(int x, int y){
        if (x%y == 0) return y;
        return gcd(y,x%y);
    }

	public void paint(Graphics g)
	{
		((Graphics2D)g).setRenderingHint (RenderingHints.KEY_ANTIALIASING,
   		RenderingHints.VALUE_ANTIALIAS_ON);

		int x = 0;
		int y = 0;
		int prevx = 0;
		int prevy = 0;

		double s = .004 * Math.sqrt(r);	// controls the step size; low values of r (e.g. 2) make jagged pattern, so s will be small for low value of r; the effect is not directly proportinal, but sqrt (arbitrary)

		double t;
		double rSum, rDiff, exprResult;
		int Offset = appletHeight/2;

		if (bgCheckbox.getState()) {
			g.fillRoundRect(0,0,appletHeight,appletHeight, 25, 25);
		} else {
			g.drawRoundRect(0,0,appletHeight,appletHeight-1, 25, 25);
		}
		g.clipRect(0,0,appletHeight-1,appletHeight-1);

		rSum  = R + r;
		rDiff = R - r;

		// for multiColor, step to change for each color 
		int redStep = 1;
		int greenStep = 1;
		int blueStep = 1;

		for(t=0; t<=I; t+=s){
			prevx = x;
			prevy = y;

			if (multiColorCheckbox.getState()) { // multi color
				redBits = 200;
				int whichColor = (int)(Math.random()*3); // randomly change only one color in each iteration; makes more interesting patterns
				switch (whichColor){
					case 0:
						if (redBits < 1) redStep = 1;
						if (redBits > 254) redStep = -1;
						redBits += redStep;
					case 1:
						if (greenBits < 1) greenStep = 1;
						if (greenBits > 254) greenStep = -1;
						greenBits += greenStep;
					case 2:
						if (blueBits < 1) blueStep = 1;
						if (blueBits > 254) blueStep = -1;
						blueBits+= blueStep;
				}
			} else {
				if (randomColorCheckbox.getState()) { // random color
					redBits = (int)(Math.random()*255);
					blueBits = (int)(Math.random()*255);
					greenBits = (int)(Math.random()*255);
				}
			}
			g.setColor(new Color(redBits, greenBits, blueBits));

			if (outsideCheckbox.getState()){
				exprResult = (rSum * t) / (double) r;
				x = (int)((rSum)*Math.cos((double)t) - (O)*Math.cos(exprResult) + Offset);
				y = (int)((rSum)*Math.sin((double)t) - (O)*Math.sin(exprResult) + Offset);
			} else {	// moving circle is INSIDE the fixed circle
				exprResult = (rDiff * t) / (double) r;
				x = (int)((rDiff)*Math.cos((double)t) + (O)*Math.cos(exprResult) + Offset);
				y = (int)((rDiff)*Math.sin((double)t) - (O)*Math.sin(exprResult) + Offset);
			}
			if (lineCheckbox.getState()){
				if (t > 0)
					g.drawLine(prevx, prevy, x, y); // line
			} else {
				g.drawLine(x, y, x, y);				// point
			}
		}
	}
}

//--------------------------------------------------------------------------------------------------

class AboutDialog extends Dialog {
	 AboutDialog (Applet app){
	    super (new Frame(), "About Spirograph", true);
	    setFont(new Font("Helvetica", Font.BOLD, 13));

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(10,1));
		p1.add(new Label(" "));
	    p1.add(new Label("Spirograph"));
	    p1.add(new Label("by"));
	    p1.add(new URLLabel(app, "http://wordsmith.org/anu/", "Anu Garg"));
	    p1.add(new URLLabel(app, "mailto:gargATwordsmith.org", "gargATwordsmith.org"));
	    p1.add(new Label(" "));
	    p1.add(new Label("How to put this Spirograph on your Web site:"));
	    p1.add(new URLLabel(app, "http://wordsmith.org/anu/java/spirograph.html", "http://wordsmith.org/anu/java/spirograph.html"));
		add("Center", p1);

		Panel p2 = new Panel();
	    Button OK = new Button ("OK");
	    p2.add ("Center", OK);
	    add("South", p2);
	    resize (300, 250);
	    setLocation(250,200);
	 }
	 public boolean action (Event ev, Object arg) {
	//    if ("OK".equals(arg)){
	// the above "if" commented out because we want the About window to be
	// closed whenever someone clicks on a link, not only on a button
        	hide ();
	        dispose ();
	        return true;
	  //  }
	  //  return true;
	 }
}

//--------------------------------------------------------------------------------------------------
class URLLabel extends Label  {
  private java.applet.Applet _applet;
  private URL _url;
  private String _target = "";
  private Color _unvisitedURL = Color.blue;
  private Color _visitedURL = Color.blue;

  public URLLabel(java.applet.Applet applet, String url, String text){
   this(applet, url, text, "_self");
   }

  public URLLabel(java.applet.Applet applet, String url, String text, String target){
   super(text);
   setForeground(_unvisitedURL);
   try {
    _applet = applet;
    _url = new URL(url);
    _target = target;
    addMouseListener( new Clicked() );
    }
   catch (Exception e) {
    e.printStackTrace();
    }
   }

 public void paint(Graphics g) {
  Rectangle r;
  super.paint(g);
  r = g.getClipBounds();
  g.drawLine
   (0,
    r.height - this.getFontMetrics(this.getFont()).getDescent(),
    this.getFontMetrics(this.getFont()).stringWidth(this.getText()),
    r.height - this.getFontMetrics(this.getFont()).getDescent());
  }

 public void setUnvisitedURLColor(Color c) {
  _unvisitedURL = c;
  }

 public void setVisitedURLColor(Color c) {
  _visitedURL = c;
  }

 class Clicked extends MouseAdapter{
  public void mouseClicked(MouseEvent me){
   setForeground(_visitedURL);
   _applet.getAppletContext().showDocument(_url, _target);
   }
  }
}

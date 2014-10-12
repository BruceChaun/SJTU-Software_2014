package EasyBBK_Swing.gui;

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Cursor;  
import java.awt.Font;  
import java.awt.Frame;  
import java.awt.GraphicsEnvironment;  
import java.awt.Insets;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
import java.util.HashMap;  
import java.util.Map;  

import javax.swing.JButton;  
import javax.swing.JColorChooser;  
import javax.swing.JComboBox;  
import javax.swing.JDialog;  
import javax.swing.JLabel;  
import javax.swing.JList;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTextField;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;
 
@SuppressWarnings("serial")
public class FontChooser extends JPanel 
{  
  
    // ���ý�����  
    {  
        try {  
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    //[start] �������  
    private String current_fontName = "Arial";//��ǰ����������,Ĭ��Time New Roman.  
    private String showStr = "I'm handsome";//չʾ������  
    private int current_fontStyle = Font.PLAIN;//��ǰ������,Ĭ�ϳ���.  
    private int current_fontSize = 9;//��ǰ�����С,Ĭ��9��.  
    private Color current_color = Color.BLACK;//��ǰ��ɫ,Ĭ�Ϻ�ɫ.  
    private JDialog dialog; //������ʾģ̬�Ĵ���  
    private JLabel lblFont; //ѡ�������LBL  
    private JLabel lblStyle; //ѡ�����͵�LBL  
    private JLabel lblSize; //ѡ���ִ�С��LBL  
    private JLabel lblColor; //ѡ��Color��label  
    private JLabel otherColor; //������ɫ  
    private JTextField txtFont; //��ʾѡ�������TEXT  
    private JTextField txtStyle; //��ʾѡ�����͵�TEXT  
    private JTextField txtSize; //��ʾѡ���ִ�С��TEXT  
    private JTextField showTF; //չʾ�������  
    @SuppressWarnings("rawtypes")
	private JList lstFont; //ѡ��������б�.  
    @SuppressWarnings("rawtypes")
	private JList lstStyle; //ѡ�����͵��б�.  
    @SuppressWarnings("rawtypes")
	private JList lstSize; //ѡ�������С���б�.  
    @SuppressWarnings("rawtypes")
	private JComboBox cbColor; //ѡ��Color��������.  
    private JButton ok, cancel; //"ȷ��","ȡ��"��ť.  
    private JScrollPane spFont;  
    private JScrollPane spSize;
    private JPanel showPan; //��ʾ��.  
    @SuppressWarnings("rawtypes")
	private Map sizeMap; //�ֺ�ӳ���.  
    @SuppressWarnings("rawtypes")
	private Map colorMap; //����ɫӳ���.  
    private Font selectedfont; //�û�ѡ�������  
    private Color selectedcolor; //�û�ѡ�����ɫ  
    public String styleString="Normal";
    //[end]  
    
    //�޲γ�ʼ��  
    public FontChooser()
    {  
    	this.selectedfont = null;  
    	this.selectedcolor = null;  
    	/* ��ʼ������ */  
    	init(null,null);  
    }  
      
    //���ع��죬�вεĳ�ʼ�� ���ڳ�ʼ���������  
    public FontChooser(Font font, Color color)
    {  
        if (font != null) 
        {  
        	this.selectedfont = font;  
            this.selectedcolor = color;  
            this.current_fontName = font.getName();  
            this.current_fontSize = font.getSize();  
            this.current_fontStyle = font.getStyle();  
            this.current_color = color;  
            /* ��ʼ������ */  
            init(font,color);  
        }
        else
        {  
            JOptionPane.showMessageDialog(this, "û�б�ѡ��Ŀؼ�", "����", JOptionPane.ERROR_MESSAGE);  
        }  
    }  
  
    //�ɹ��ⲿ���õķ���  
    public Font getSelectedfont() 
    {  
        return selectedfont;  
    }  
  
    public void setSelectedfont(Font selectedfont) 
    {  
        this.selectedfont = selectedfont;  
    }  
  
    public Color getSelectedcolor() 
    {  
        return selectedcolor;  
    }  
  
    public void setSelectedcolor(Color selectedcolor) 
    {  
        this.selectedcolor = selectedcolor;  
    }  
    
    public void setSelectedstyle(String styleString) 
    {  
        this.styleString = styleString;  
    }
    
    public String getSelectedstyle()
    {
    	return styleString;
    }
      
    /*��ʼ������*/   
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(Font font,Color color) 
    {  
        //ʵ��������  
        lblFont = new JLabel("Font:");  
        lblStyle = new JLabel("Style:");  
        lblSize = new JLabel("Size:");  
        lblColor = new JLabel("Color:");  
        otherColor = new JLabel("<html><U>Other Colors</U></html>");  
        txtFont = new JTextField("Arial");  
        txtStyle = new JTextField("Normal");  
        txtSize = new JTextField("9");
                 
        //ȡ�õ�ǰ������������.  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] fontNames = ge.getAvailableFontFamilyNames();  
  
        lstFont = new JList(fontNames);
          
        //����.  
        lstStyle = new JList(new String[]{"Normal", "Bold" ,"Italic", "BoldItalic"});  
          
        //�ֺ�.  
        String[] sizeStr = new String[]{  
            "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};  
        int sizeVal[] = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};  
        sizeMap = new HashMap();  
        for (int i = 0; i < sizeStr.length; ++i) 
        {  
            sizeMap.put(sizeStr[i], sizeVal[i]);  
        }  
        lstSize = new JList(sizeStr);  
        spFont = new JScrollPane(lstFont);  
        spSize = new JScrollPane(lstSize);  
  
        //��ɫ  
        String[] colorStr = new String[]{  
            "Black", "Blue", "Cyan", "DarkGray", "Gray", "Green", "LightGray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow"  };  
        Color[] colorVal = new Color[]{  
            Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW  
        };  
        colorMap = new HashMap();  
        for (int i = 0; i < colorStr.length; i++) 
        {  
            colorMap.put(colorStr[i], colorVal[i]);  
        }  
        cbColor = new JComboBox(colorStr);  
        showPan = new JPanel();  
        ok = new JButton("OK");  
        cancel = new JButton("Cancel"); 
        cancel.setMargin(new Insets(0,0,0,0));
          
          
        //���ֿؼ�  
        //�����  
        this.setLayout(null);   //���ò��ֹ�����  
        add(lblFont);  
        lblFont.setBounds(12, 10, 30, 20);  
        txtFont.setEditable(false);  
        add(txtFont);  
        txtFont.setBounds(10, 30, 155, 20);  
        txtFont.setText("Arial");  
        lstFont.setSelectedValue("Arial", true);  
        if (font != null) 
        {  
            txtFont.setText(font.getName());  
            lstFont.setSelectedValue(font.getName(), true);  
        }  
          
        add(spFont);  
        spFont.setBounds(10, 50, 155, 100);  
  
        //��ʽ  
        add(lblStyle);  
        lblStyle.setBounds(175, 10, 50, 20);  
        txtStyle.setEditable(false);  
        add(txtStyle);  
        txtStyle.setBounds(175, 30, 130, 20);  
        lstStyle.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));  
        add(lstStyle);  
        lstStyle.setBounds(175, 50, 130, 100);  
        txtStyle.setText("Normal"); //��ʼ��ΪĬ�ϵ���ʽ  
        lstStyle.setSelectedValue("Normal",true);   //��ʼ��ΪĬ�ϵ���ʽ  
        styleString = "Normal";
        if(font != null)
        {  
            lstStyle.setSelectedIndex(font.getStyle()); //��ʼ����ʽlist  
            if (font.getStyle() == 0) 
            {  
                txtStyle.setText("Normal");  
            } 
            else if (font.getStyle() == 1) 
            {  
                txtStyle.setText("Bold");  
            } 
            else if (font.getStyle() == 2) 
            {  
                txtStyle.setText("Italic");  
            } 
            else if (font.getStyle() == 3) 
            {  
                txtStyle.setText("BoldItalic");  
            }  
        }  
  
  
        //��С  
        add(lblSize);  
        lblSize.setBounds(320, 10, 30, 20);  
        txtSize.setEditable(false);  
        add(txtSize);  
        txtSize.setBounds(320, 30, 60, 20);  
        add(spSize);  
        spSize.setBounds(320, 50, 60, 100);  
        lstSize.setSelectedValue("9", false);  
        txtSize.setText("9");  
        if (font != null) {  
            lstSize.setSelectedValue(Integer.toString(font.getSize()), false);  
            txtSize.setText(Integer.toString(font.getSize()));  
        }  
  
        //��ɫ  
        add(lblColor);  
        lblColor.setBounds(18, 225, 50, 20);  
        cbColor.setBounds(18, 245, 100, 22);  
        cbColor.setMaximumRowCount(5);  
        add(cbColor);  
        otherColor.setForeground(Color.black);  
        otherColor.setBounds(130, 245, 120, 22);  
        otherColor.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        add(otherColor);  

        //չʾ��  
        showTF = new JTextField();  
        showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
        showTF.setBounds(10, 10, 300, 50);  
        showTF.setHorizontalAlignment(JTextField.CENTER);  
        showTF.setText(showStr);  
        showTF.setBackground(Color.white);  
        showTF.setEditable(false);  
        showPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Example"));  
        add(showPan);  
        showPan.setBounds(13, 150,370, 80);  
        showPan.setLayout(new BorderLayout());  
        showPan.add(showTF);  
        if (font != null) {  
            showTF.setFont(font); // ����ʾ���е����ָ�ʽ  
        }  
        if (font != null) 
        {  
            showTF.setForeground(color);  
        }  
  
        //ȷ����ȡ����ť  
        add(ok);  
        ok.setBounds(230, 245, 60, 20);  
        add(cancel);  
        cancel.setBounds(300, 245, 60, 20);  
        //���ֿؼ�_����  
  
        //listener.....  
        /*�û�ѡ������*/  
        lstFont.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) 
            {  
                current_fontName = (String) lstFont.getSelectedValue();  
                txtFont.setText(current_fontName);  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        /*�û�ѡ������*/  
        lstStyle.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                String value = (String) ((JList) e.getSource()).getSelectedValue();  
                if (value.equals("Normal")) 
                {  
                    current_fontStyle = Font.PLAIN;
                    styleString = "Normal";
                }  
                if (value.equals("Italic")) 
                {  
                    current_fontStyle = Font.ITALIC;  
                    styleString = "Italic";
                }  
                if (value.equals("Bold")) 
                {  
                    current_fontStyle = Font.BOLD;  
                    styleString = "Bold";
                }  
                if (value.equals("BoldItalic")) 
                {  
                    current_fontStyle = Font.BOLD | Font.ITALIC;  
                    styleString = "BoldItalic";
                }  
                txtStyle.setText(value);  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        /*�û�ѡ�������С*/  
        lstSize.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                current_fontSize = (Integer) sizeMap.get(lstSize.getSelectedValue());  
                txtSize.setText(String.valueOf(current_fontSize));  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        /*�û�ѡ��������ɫ*/  
        cbColor.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                current_color = (Color) colorMap.get(cbColor.getSelectedItem());  
                showTF.setForeground(current_color);  
            }  
        });  
        /*������ɫ*/  
        otherColor.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                @SuppressWarnings("static-access")
				Color col_temp = new JColorChooser().showDialog(null, null, Color.pink);  
                if (col_temp != null) {  
                    current_color = col_temp;  
                    showTF.setForeground(current_color);  
                    super.mouseClicked(e);  
                }  
            }  
        });  
        /*�û�ȷ��*/  
        ok.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                setSelectedfont(new Font(current_fontName, current_fontStyle, current_fontSize));    
                setSelectedcolor(current_color);
                setSelectedstyle(styleString);
                dialog.dispose();  
                dialog = null;  
            }  
        });  
  
        /*�û�ȡ��*/  
        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                dialog.dispose();  
                dialog = null;  
            }  
        });  
    }  
      
    /*��ʾ����ѡ�����Ի���(x,y��ʾ���������λ��)*/  
    public void showDialog(Frame parent,int x,int y) 
    {  
        String  title = "Font";  
        dialog = new JDialog(parent, title,true);  
        dialog.add(this);  
        dialog.setResizable(false);  
        dialog.setSize(400, 310);  
        //���ýӽ��������λ��  
        dialog.setLocation(x,y);  
        dialog.addWindowListener(new WindowAdapter() {  
  
            /*����ر�ʱ����*/  
            public void windowClosing(WindowEvent e) 
            {  
                dialog.removeAll();  
                dialog.dispose();  
                dialog = null;  
            }  
        });  
        dialog.setVisible(true);  
    }
} 


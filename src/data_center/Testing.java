package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.*;

import data_center.SketchComponent.*;

public class Testing  extends JFrame
{	
	/*
	adadadada
	JTable tableList =null;
	String[] cols={"name","type","author","enterDate","shortDesc","url"};
    String[][] rows=null;
    public Testing()
    {
    	BbkDatabaseConnector conncetor = new BbkDatabaseConnector();
    	//SearchResultList resultList = conncetor.search("B0034");
    	SearchResultList resultList = conncetor.search("jcbraff","part_author");
    	rows = resultList.showSearchResult();
    	JPanel jp=(JPanel)this.getContentPane();
    	tableList = new JTable(rows,cols);
		JScrollPane jsp_table=new JScrollPane(tableList);
		jp.add(jsp_table);
		this.setTitle("Biobrick Search");
		this.setSize(600,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    */
	public static void main(String[] args)
	{	
		//Test for Search
		//new Testing();
		
		//SearchResultList list
			//= BbkBlaster.blast_local(BbkBlaster.DEFAULT_INFILE, BbkBlaster.MODE_INPUT_FILE_PATH);
		//for (int i = 0; i < 100; ++i)
			//list.get(i).display();
		
		
		BbkDatabaseConnector connector = new BbkDatabaseConnector();
		
		//Test for ...
		
//		ArrayList<Component> componentList = new ArrayList<Component>();
//		componentList.add(new Label(0, "Lable text", 
//				new Point(5, 5), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
//		componentList.add(new BioBrick(1, BbkType.Sketch.BioBrick.PROMOTER, 
//				new Point(10, 10), null));
//		componentList.add(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
//				new Point(20, 20), Color.BLUE));
//		componentList.add(new BackBone(3, new Point(50, 50), new Point(100, 50)));
//		componentList.add(new Relation(4, BbkType.Sketch.Relation.SUPPRESS, 
//				new ArrayList<Point>(), new Color(50, 50, 50), 10));
//		componentList.add(new Vector(5, BbkType.Sketch.Vector.BACTERIA, 
//				new Point(300, 300), 3));
		// ������������ôһ��componentList����Ҫ�������б�д��XML�ļ��������ٴζ�ȡ��
		// ע�������Color�����ֱ�ʾ��ʽColor(x, x, x)����Color.BLACK������¼���ļ���Ӧ��
		// 		��һ������ʽ
		// ���ⲻͬcomponent���ͣ����б��е�1,2�������Label��BioBrick��Ҳ��Ҫ��¼������
		// 		���ݽṹ�м�¼�ڸ���component��primaryType��
		// ������ϸ��һ��SketchComponent����Ľṹ
		// ��������ȫ���ӱ��ļ��е���������~
	}
}

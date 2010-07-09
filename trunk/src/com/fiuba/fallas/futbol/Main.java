

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;


public class Main {
	private static String log;
	private JFrame mainFrame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Archivo");
	private JMenuItem openMenuItem = new JMenuItem("Abreiendo la Aplicacion");
	public static String getLog() {
		return log;
	}
	
	public static void setLog(String log) {
		Main.log = log;
	}
	
	private JMenuItem exitMenuItem = new JMenuItem("Salir");
	private BorderLayout borderLayout = new BorderLayout();
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
	private JPanel buttonPanel = new JPanel(flowLayout);
	private JButton solveButton = new JButton("Procesar");
	private JFileChooser fileChooser;
	private JTextArea textArea;
	private ArrayList<Maquina> maquinas;
	private JTable table;
	private Object[][] data;
	
	public Main() {
		maquinas = fillArray();
		mainFrame = new JFrame("Selección de maquinas");

		data = new Object[11][11];
		Maquina maquina;
		for (int j = 0; j < maquinas.size(); j++) {
			maquina = maquinas.get(j);
			data[j][0] = maquina.getNombre();
			data[j][1] = Integer.toString(maquina.getcosto());
			data[j][2] = Integer.toString(maquina.getVelocidad());
			data[j][3] = Integer.toString(maquina.getResistencia());
			data[j][4] = Integer.toString(maquina.getcostoMO());
			data[j][5] = Integer.toString(maquina.getamortizacion());
			data[j][6] = Integer.toString(maquina.getduracion());
			data[j][7] = Integer.toString(maquina.getcapacitacion());
			data[j][8] = Integer.toString(maquina.getmantenimiento());
			data[j][9] = Integer.toString(maquina.getExperiencia());
			data[j][10] = "No Definida";
		}

		String[] columnNames = { "terminal de Trabajo", "Costo", "Velocidad", "Resistencia", "CostoMO", "Amortizacion",
				"Duracion", "Capacitacion", "Def", "Experiencia", "Estado" };

		// se crea la Tabla
		textArea= new JTextArea(7,50);
		textArea.setEditable(false);
		table = new JTable(data, columnNames);

		table.setPreferredScrollableViewportSize(new Dimension(535, 250));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		for (int k = 1; k < 10; k++)
			table.getColumnModel().getColumn(k).setPreferredWidth(35);

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane scrollPane2 = new JScrollPane(textArea);

		// Agregamos el JScrollPane al contenedor
		mainFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		mainFrame.getContentPane().add(scrollPane2,BorderLayout.SOUTH);
		mainFrame.pack();
	
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (fileChooser == null)
		    {
		      fileChooser = new JFileChooser();
		    }		         
			try
			{
			  if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION)
			  {
			    System.out.println(fileChooser.getSelectedFile().getCanonicalPath());
			  }
			 }
			 catch (IOException ex)
			 {
			   ex.printStackTrace();
			  }
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setLayout(borderLayout);
		// buttonPanel.add(fireOneRuleButton);
		buttonPanel.add(solveButton);
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solveButton.setText("Procesar");
				solveButton.setEnabled(false);
				cargaBase();
			}
		});

		mainFrame.add(BorderLayout.SOUTH, buttonPanel);
		mainFrame.setSize(600, 500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	private void cargaBase() {
		// load up the knowledge base
		try {
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
			long startTime = System.currentTimeMillis();
			Maquina maquina;
			int lim =maquinas.size();
			maquinas.clear();

			for (int k = 0; k < lim; k++) {
			maquina = new Maquina(k,
			(String) table.getModel().getValueAt(k, 0), (Integer.parseInt((String) 
					table.getModel().getValueAt(k, 1))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 2))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 3))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 4))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 5))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 6))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 7))), (Integer.parseInt((String) table.getModel().getValueAt(
					k, 8))), (Integer.parseInt((String) table.getModel().getValueAt(k, 9))));
				maquinas.add(maquina);
				ksession.insert(maquina);
			}

			PosicionPosible pp;
			for (int i = 0; i < 11; i++) {
				pp = new PosicionPosible(i, "Maquina");
				ksession.insert(pp);
			}
			ksession.insert(this);
			ksession.fireAllRules();
			logger.close();
			
			long endTime = System.currentTimeMillis() - startTime;
			solveButton.setEnabled(true);
			solveButton.setText("Procesado (" + Long.toString(endTime)+ " msec )");
			updatePosicion();
			textArea.append("Procesado (" + Long.toString(endTime)+ " msec) \n");
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	private void updatePosicion() {
		for (int i = 0 ; i< maquinas.size();i++){
			//System.out.println("Final ("+maquinas.get(0).getNombre()+")-> posicion: " + maquinas.get(0).getPosicion());
			data[i][10] = maquinas.get(i).getestado();
			table.updateUI();		
		}
	}

	public static final void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("ElegirMaquina.drl"),ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	private ArrayList<Maquina> fillArray() {
		System.out.println("Cargando file...\n");
	    
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      //BufferedReader input =  new BufferedReader(new FileReader(new File("C:\\Users\\Nico\\Workspace\\TPFallasI\\src\\com\\fiuba\\fallas\\futbol\\a.txt")));
	    	BufferedReader input =  new BufferedReader(new FileReader(new File("C:\\Documents and Settings\\Administrador\\Escritorio\\eclipse\\workspace\\tpfallas\\src\\a.txt")));
	      try {
	        String line = null; //not declared within while loop
	       // line.
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	        	System.out.println(line);
	       //   contents.append(line);
	        //  contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
		// ACA PONER NOMBRES DE MAQUINAS!!!!!
		ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
		Maquina Maquina = new Maquina(1, "Maquina Sebastian", 6, 5, 2, 5, 7, 6, 2, 3,4);
		maquinas.add(Maquina);
		Maquina = new Maquina(2, "Maquina Nacho", 9, 6, 4, 10, 3, 5, 6, 4, 2);
		maquinas.add(Maquina);
		Maquina = new Maquina(3, "Maquina Rodolfo", 2, 5, 5, 9, 4, 5, 8, 4, 2);
		maquinas.add(Maquina);
		Maquina = new Maquina(4, "Maquina Marcos", 4, 6, 3, 6, 5, 5, 7, 2, 1);
		maquinas.add(Maquina);
		Maquina = new Maquina(5, "Maquina Martin", 5, 4, 7, 3, 6, 6, 7, 5, 4);
		maquinas.add(Maquina);
		Maquina = new Maquina(6, "Maquina German", 2, 6, 6, 2, 5, 4, 8, 6, 3);
		maquinas.add(Maquina);
		Maquina = new Maquina(7, "Maquina Roberto", 1, 7, 6, 5, 7, 6, 2, 7, 5);
		maquinas.add(Maquina);
		Maquina = new Maquina(8, "Maquina Bonifacio", 0, 8, 7, 6, 8, 5, 5, 8, 4);
		maquinas.add(Maquina);
		Maquina = new Maquina(9, "Maquina Matias", 2, 6, 4, 7, 6, 4, 6, 6, 2);
		maquinas.add(Maquina);
		Maquina = new Maquina(10, "Maquina Diego Armando", 4, 8, 8, 3, 7, 4, 7, 9, 3);
		maquinas.add(Maquina);
		Maquina = new Maquina(11, "Maquina Nicolas", 0, 9, 7, 1, 6, 6, 5, 8, 6);
		maquinas.add(Maquina);
		return maquinas;
	}
}
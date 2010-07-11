import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JMenuItem openMenuItem = new JMenuItem("Abrir..");
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

		data = new Object[12][12];
		Maquina maquina;
		for (int j = 0; j < maquinas.size(); j++) {
			maquina = maquinas.get(j);
			data[j][0] = maquina.getNombre();
			data[j][1] = Integer.toString(maquina.getVelocidad());
			data[j][2] = Integer.toString(maquina.getColores());
			data[j][3] = Double.toString(maquina.getDisponibilidad());
		}

		String[] columnNames = { "Terminal de trabajo", "Velocidad","Colores", "Disponibilidad"};

		// se crea la Tabla
		textArea= new JTextArea(7,50);
		textArea.setEditable(false);
		table = new JTable(data, columnNames);

		table.setPreferredScrollableViewportSize(new Dimension(1100, 250));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		for (int k = 1; k < 4; k++)
			table.getColumnModel().getColumn(k).setPreferredWidth(100);

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
			maquina = new Maquina
			(k,(String) table.getModel().getValueAt(k, 0), (Integer.parseInt((String) 
					table.getModel().getValueAt(k, 7))), (Integer.parseInt((String) 
					table.getModel().getValueAt(k, 8))), (Double.parseDouble((String)
					table.getModel().getValueAt(k, 9))));
			maquinas.add(maquina);
			ksession.insert(maquina);
			}

			Trabajo pp;
			for (int i = 0; i < 1; i++) {
				pp = new Trabajo(1,10000,4);
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
		System.out.println("Cargando Archivo..\n");
	    
		ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
		Maquina Maquina = new Maquina(1, "Heidelberg SS5067", 8, 6, 1);
		maquinas.add(Maquina);
		Maquina = new Maquina(2, "Cabrenta 170", 9, 2, 0.4);
		maquinas.add(Maquina);
		Maquina = new Maquina(3, "Cabrenta 2100", 2, 4, 0.3);
		maquinas.add(Maquina);
		Maquina = new Maquina(4, "Rolland Rollermaster 3000", 4, 4, 0.2);
		maquinas.add(Maquina);
		Maquina = new Maquina(5, "Heidelberg H100", 5, 2, 1);
		maquinas.add(Maquina);
		Maquina = new Maquina(6, "Heidelberg 120A", 2, 2, 1);
		maquinas.add(Maquina);
		Maquina = new Maquina(7, "Tyuith 235", 1, 5, 0.0);
		maquinas.add(Maquina);
		Maquina = new Maquina(8, "Rolland T650", 0, 5, 0.0);
		maquinas.add(Maquina);
		Maquina = new Maquina(9, "Heidelberg SS2210", 2, 4, 1);
		maquinas.add(Maquina);
		return maquinas;
	}
}
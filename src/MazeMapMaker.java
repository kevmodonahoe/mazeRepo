
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MazeMapMaker extends JFrame{
	static int rows = 20;
    static int columns = 20;
    int panelSize = 25;
    static int map[][] = new int[columns][rows];
    ArrayList<String> mapList = new ArrayList<String>();
    int level = 0;
    boolean levelsExistAlready = false;
    static JButton clearMaze = new JButton("Clear");
    JButton saveMaze = new JButton("Save");
    
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 490;
    int HEIGHT = 530;
    
    public MazeMapMaker(){
    	
    	clearMaze.setSize(menuWidth, menuHeight);
    	clearMaze.setLocation(450, 525);
    	this.add(clearMaze);
    	    	
    	saveMaze.setSize(menuWidth, menuHeight);
    	saveMaze.setLocation(350, 525);
    	this.add(saveMaze);
    	
    	saveMaze.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				boolean firstColExists = false;
				for(int r=0; r<rows-1; r++){
						if(map[0][r] == 1){
							firstColExists = true;
						}
				}
				if(firstColExists == false){
             		JOptionPane.showMessageDialog(null,"Please add valid tile in first column!","Invalid Board",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					saveMap();
					new MainMenu();
					dispose();
				}
				
				
				
			}    		
    		
    	});

    	
    	getMapList();
    	getLevelChoice();
    	if(level != -1){
    		
    		//EDIT A CURRENTLY EXISTING MAP
	        loadMap();
	        this.setResizable(false);
	        this.setSize((columns*panelSize)+50, (rows*panelSize)+80);
	        this.setTitle("Maze Map Maker");
	        this.setLayout(null);
	        
	        this.addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent e) {
	                saveMap();
	                new MainMenu();
	            }
	        });
	        
	        this.setLocationRelativeTo(null);
	        
	        for(int y = 0; y < columns; y++){	        	
	            for(int x = 0; x < rows; x++){
	            	
	            	
	            	MapMakerTile tile = new MapMakerTile(x, y);

	            	
	                tile.setSize(panelSize-1, panelSize-1);
	                tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
	                
	                
	                
	                if(map[x][y] == 0){
	                    tile.setBackground(Color.BLACK);
	                }else{
	                    tile.setBackground(Color.WHITE);
	                }
	                
	                tile.setVisible(true);
	                
	                this.add(tile);
	            }
	        }
	        
	        
	        this.setVisible(true);
    	}else{
    		new MainMenu();
    	}
    }
    
    public void getMapList(){
    	for(int i = 0; i < 99; i++){
    		File map = new File("./Map "+i);
    		if(map.exists()){
    			System.out.println("Map "+i+" exists");
    			mapList.add("Map "+i);
    			levelsExistAlready = true;
    		}
    	}
    }
    
    public void getLevelChoice(){
    	if(levelsExistAlready){
	    	String maps[] = new String[99];
	    	mapList.toArray(maps);
	    	
	    	
	    	maps[mapList.size()] = "New Map";
	    	
	    	
	    	String choice = (String)JOptionPane.showInputDialog(null, "Which map would you like to play?", "Maze Map Selector", JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);
	    	
	    	
	    	System.out.println(choice);
	    	
	    	
	    	
	    	if(choice != null && !choice.equals("New Map")){
	    		level = Integer.parseInt((choice.replace("Map ", "")));
	    	}else if(choice == null){
	    		level = -1;
	    	}else{
	    		level = mapList.size();
	    	}
    	}
    }
    
    public void saveMap(){
        try{
        PrintWriter writer = new PrintWriter("Map "+level, "UTF-8");
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                writer.print(map[x][y]);
            }
            writer.print("\r\n");
        }
        writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("Map "+level));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String mapStr = sb.toString();
            
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                        //System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        //System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    map[x][y] = 0;
                }
            }
        }
    }
}


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MapMakerTile extends JPanel{
    
	static int rows = 20;
    static int columns = 20;
	int x, y;
    JButton clearMaze = new JButton("Clear");
    
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 490;
    int HEIGHT = 530;
    
    
    
    public MapMakerTile(int x, int y){
        this.x = x;
        this.y = y;
        
        MazeMapMaker.clearMaze.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int r=0; r<MazeMapMaker.rows-1; r++){
					for(int c=0; c<MazeMapMaker.columns-1; c++){
						setBackground(Color.BLACK);
						MazeMapMaker.map[r][c] = 0;
					}
				}
			}
    		
    		
    	});
        
        
        addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                    	if(e.getButton() == MouseEvent.BUTTON1){
                    		
	                        setBackground(Color.CYAN);
	                        	                     
	                        //sets position to 1 if that tile is a path
	                        MazeMapMaker.map[x][y] = 1;
                    	}
                    	if(e.getButton() == MouseEvent.BUTTON3){
                    		
                    		
	                        setBackground(Color.BLACK);
	                        
	                        //sets position to 0 if that tile is a wall
	                        MazeMapMaker.map[x][y] = 0;
                    	}
                    }
                });
    }
}


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MapMakerTile extends JPanel{
    int x, y;
    
    public MapMakerTile(int x, int y){
        this.x = x;
        this.y = y;
        
        addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                    	if(e.getButton() == MouseEvent.BUTTON1){
                    		
                    		//FOR CHLOE: SET DIFFERENT TILE COLORS
	                        setBackground(Color.WHITE);
	                        
	                        //sets position to 1 if that tile is a path
	                        MazeMapMaker.map[x][y] = 1;
                    	}
                    	if(e.getButton() == MouseEvent.BUTTON3){
                    		
                    		
	                        setBackground(Color.GRAY);
	                        
	                        //sets position to 0 if that tile is a wall
	                        MazeMapMaker.map[x][y] = 0;
                    	}
                    }
                });
    }
}

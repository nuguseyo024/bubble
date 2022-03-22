package bubble.test.ex16;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class player :: new 가능한 애들! 게임에 존재할 수 있음. (추상 메서드를 가질 수 없다) 
@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

	private BubbleFrame mContext;
	
	// 위치 상태 
	private int x;
	private int y;

	// 적군의 방향 
	private EnemyWay enemyWay;
	
	// 움직임 상태 
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 적군 속도 상태 
	private final int SPEED = 3;
	private final int JUMPSPEED = 1; // up, down 의 스피드 
	
	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
	}


	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");

	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;
			
		enemyWay = EnemyWay.RIGHT;
		
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}


	private void initBackgroundEnemyService() { 
		//new Thread(new BackgroundEnemyService(this)).start();
	
	}

	
	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;		
		left = true;
		new Thread(()-> {
			while(left) {
				setIcon(enemyL);
				x = x - SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}).start();

	}
         
	@Override
	public void right() {
		enemyWay = EnemyWay.RIGHT;
		
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(enemyR);
				x = x + SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}).start();

	}
	
	// left + up, right + up
	@Override
	public void up() {
		//System.out.println("up");
		up = true;
		new Thread(()->{
			for(int i = 0 ; i < 130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x,y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {	
		//System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x,y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
		
	}

}

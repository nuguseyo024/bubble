package bubble.test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인스레드 : 바쁨 -- 키보드 이벤트 처리하느라
// 백그라운드에서 계속 관찰하기 위한 스레드 
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player; 
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/test.png"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		// 플레이어의 위치에 따른 색상 확인 
		Color color = new Color(image.getRGB(player.getX(), player.getY()));
		System.out.println("색상 : " + color);
		
	}
	
}

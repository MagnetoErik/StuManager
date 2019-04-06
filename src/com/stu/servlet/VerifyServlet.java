package com.stu.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
//"verify.jpeg?"+Math.random();

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 鍦ㄥ唴瀛樹腑鍒涘缓鍥捐薄
		int width = 72, height = 28;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 鑾峰彇鍥惧舰涓婁笅鏂�
		Graphics g = image.getGraphics();

		// 鐢熸垚闅忔満绫�
		Random random = new Random();

		// 璁惧畾鑳屾櫙鑹�
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 璁惧畾瀛椾綋
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		// 鍙栭殢鏈轰骇鐢熺殑璁よ瘉鐮�
		String sRand = "";
		int start = 0;
		int end = 0;
		String[] arr1 = { "+", "x", "-" };
		start = random.nextInt(10);
		end = random.nextInt(10);
		// 鐢熸垚绗竴涓瓧绗�
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		g.drawString(String.valueOf(start), 13 * 0 + 6, 22);
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		// 鐢熸垚绗簩涓瓧绗�
		if (start > end) {
			int index = random.nextInt(3);
			if (index == 0) {
				sRand = String.valueOf((start + end));
				g.drawString(arr1[index], 13 * 1 + 6, 22);
			} else if (index == 1) {
				sRand = String.valueOf((start * end));
				g.drawString(arr1[index], 13 * 1 + 6, 22);
			} else {
				sRand = String.valueOf((start - end));
				g.drawString(arr1[index], 13 * 1 + 6, 22);
			}
		} else {
			int index = random.nextInt(2);
			if (index == 1) {
				g.drawString(arr1[index], 13 * 1 + 6, 22);
				sRand = String.valueOf((start * end));
			} else {
				g.drawString(arr1[index], 13 * 1 + 6, 22);
				sRand = String.valueOf((start + end));
			}
		}
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		g.drawString(String.valueOf(end), 13 * 2 + 6, 22);
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		g.drawString("=", 13 * 3 + 6, 22);
		g.drawString("?", 13 * 4 + 6, 22);
		// 闅忔満浜х敓8鏉″共鎵扮嚎锛屼娇鍥捐薄涓殑璁よ瘉鐮佷笉鏄撹鍏跺畠绋嬪簭鎺㈡祴鍒�
		for (int i = 0; i < 8; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 灏嗚璇佺爜瀛樺叆SESSION
		request.getSession(true).setAttribute("rand", sRand);
		// 鍥捐薄鐢熸晥
		g.dispose();
		// 杈撳嚭鍥捐薄鍒伴〉闈�
		ServletOutputStream out = response.getOutputStream();
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(bi);
		ImageIO.write(image, "JPEG", response.getOutputStream());
		out.flush();
		out.close();
	}
    
	
	private  Color interLine(int Low, int High){
        if(Low > 255)
            Low = 255;
        if(High > 255)
            High = 255;
        if(Low < 0)
            Low = 0;
        if(High < 0)
            High = 0;
        int interval = High - Low;
        int r = Low + (int)(Math.random() * interval);
        int g = Low + (int)(Math.random() * interval);
        int b = Low + (int)(Math.random() * interval);
        return new Color(r, g, b);
      }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		super.destroy();
	}

	public Color getRandColor(int fc, int bc) {// 缁欏畾鑼冨洿鑾峰緱闅忔満棰滆壊
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}

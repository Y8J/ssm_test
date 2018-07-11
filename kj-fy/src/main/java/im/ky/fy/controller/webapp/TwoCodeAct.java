package im.ky.fy.controller.webapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成模拟类
 * @author yangjing
 *
 */
@RequestMapping("code")
@Controller
public class TwoCodeAct {

	private static final long serialVersionUID = 1261007771190467333L;
	/** 定义二维码的宽度 */
	private static final int WIDTH = 200;
	/** 定义二维码的高度 */
	private static final int HEIGHT = 200;
	/** 定义中间图片的宽度 */
	private static final int LOGO_WIDTH = 50;
	/** 定义中间图片的高度 */
	private static final int LOGO_HEIGHT = 50;
	/** 定义二维码中的内容 URL */
	private String url;

	/**
	 * 生成二维码方法
	 * @param request
	 * @param response
	 * @param model
	 * @throws WriterException
	 * @throws IOException
	 */
	@RequestMapping("data.do")
	public void goData(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws WriterException, IOException{
		
		/** 判断URL */
		if (url == null || "".equals(url)){
			url = "http://www.hao123.com";
		}
		
		/** 设置响应的内容类型与编码 */
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		/** 定义Map集合来封装二维码需要配置信息 */
		Map<EncodeHintType, Object> hints = new HashMap<>();
		/** 设置二维码内容的编码 */
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		/** 设置二维码的上下左右的边距 */
		hints.put(EncodeHintType.MARGIN, 0);
		/** 设置二维码生成的纠错级别 */
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		
		/** 创建二维码字节转换对象 */
		/**
		 * 第一个参数：内容
		 * 第二个参数：二维码格式器
		 * 第三个参数：二维码图片的宽度
		 * 第四个参数：二维码图片的高度
		 * 第五个参数：生成二维码需要的配置信息
		 */
		BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE,
				WIDTH, HEIGHT, hints);
		
		/** 获取二维码的宽度 */
		int barcode_width = bitMatrix.getWidth();
		/** 获取二维码的高度 */
		int barcode_height = bitMatrix.getHeight();
		/** 把BitMatrix字节转换对象 转换成BufferedImage对象 */
		BufferedImage image = new BufferedImage(barcode_width, barcode_height, BufferedImage.TYPE_INT_RGB);
		/** 循环二维码图片中的所有像素点 */
		for (int x = 0; x < barcode_width; x++){
			for (int y = 0; y < barcode_height; y++){
				/** 返回true这一点是黑色的，false是白色 */
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000: 0xFFFFFF);
			}
		}
		
		/** 放在二维码中间的图片 */
		BufferedImage logo = ImageIO.read(new File("D:/download/image/1.jpg"));
		/** 获取画笔 */
		Graphics2D g = (Graphics2D)image.getGraphics();
		/** 绘制中间的图片 */
		g.drawImage(logo, (barcode_width - LOGO_WIDTH) / 2, (barcode_height - LOGO_HEIGHT) / 2, LOGO_WIDTH, LOGO_HEIGHT, null);
		/** 设置消除锯齿 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/** 设置画笔颜色 */
		g.setColor(Color.WHITE);
		/** 设置画笔粗细 */
		g.setStroke(new BasicStroke(3.5f));
		/** 绘制圆角矩形 */
		g.drawRoundRect((barcode_width - LOGO_WIDTH) / 2, (barcode_height - LOGO_HEIGHT) / 2, LOGO_WIDTH, LOGO_HEIGHT, 15, 15);
		/** 向浏览器输出二维码 */
		//MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		ImageIO.write(image, "png", response.getOutputStream());
		response.getOutputStream().flush();   
        response.getOutputStream().close();
	}
	
	/**
	 * 进入请求二维码页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gohtml.html")
	public String gohtml(HttpServletRequest request, HttpServletResponse response,ModelMap model){
	
		return "codeImage";
	}
}

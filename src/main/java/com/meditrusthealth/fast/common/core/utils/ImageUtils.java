package com.meditrusthealth.fast.common.core.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public final class ImageUtils {
	protected static Logger log = LoggerFactory.getLogger(ImageUtils.class);

	public ImageUtils() {
	}

	public static final void pressImage(String pressImg, String targetImg, int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth((ImageObserver) null);
			int height = src.getHeight((ImageObserver) null);
			BufferedImage image = new BufferedImage(wideth, height, 1);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, (ImageObserver) null);
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth((ImageObserver) null);
			int height_biao = src_biao.getHeight((ImageObserver) null);
			g.setComposite(AlphaComposite.getInstance(10, alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao,
					(ImageObserver) null);
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception var14) {
			log.error("error", var14);
		}

	}

	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth((ImageObserver) null);
			int height = src.getHeight((ImageObserver) null);
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, (ImageObserver) null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(10, alpha));
			g.drawString(pressText, (width - getLength(pressText) * fontSize) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception var15) {
			log.error("error", var15);
		}

	}

	public static String resize(String filePath, String fileName, int height, int width, boolean bb) throws Exception {
		try {
			double ratio = 0.0D;
			File file = new File(filePath + File.separator + fileName);
			String resultName = "s_" + fileName;
			File resultFile = new File(filePath + File.separator + resultName);
			FileUtils.copyTo(file, resultFile);
			BufferedImage bi = ImageIO.read(resultFile);
			Image itemp = bi.getScaledInstance(width, height, 4);
			if (bi.getHeight() > height || bi.getWidth() > width) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / (double) bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / (double) bi.getWidth();
				}

				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio),
						(RenderingHints) null);
				itemp = op.filter(bi, (BufferedImage) null);
			}

			if (bb) {
				BufferedImage image = new BufferedImage(width, height, 1);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == ((Image) itemp).getWidth((ImageObserver) null)) {
					g.drawImage((Image) itemp, 0, (height - ((Image) itemp).getHeight((ImageObserver) null)) / 2,
							((Image) itemp).getWidth((ImageObserver) null),
							((Image) itemp).getHeight((ImageObserver) null), Color.white, (ImageObserver) null);
				} else {
					g.drawImage((Image) itemp, (width - ((Image) itemp).getWidth((ImageObserver) null)) / 2, 0,
							((Image) itemp).getWidth((ImageObserver) null),
							((Image) itemp).getHeight((ImageObserver) null), Color.white, (ImageObserver) null);
				}

				g.dispose();
				itemp = image;
			}

			ImageIO.write((BufferedImage) itemp, "jpg", resultFile);
			return resultName;
		} catch (Exception var14) {
			log.error("error", var14);
			throw var14;
		}
	}

	private static int getLength(String text) {
		int length = 0;

		for (int i = 0; i < text.length(); ++i) {
			if ((new String(text.charAt(i) + "")).getBytes().length > 1) {
				length += 2;
			} else {
				++length;
			}
		}

		return length / 2;
	}

	public static void getThumbImage(String filePath, String targetPath, int width, int height, float quality) {
		try {
			Thumbnails.of(new String[] { filePath }).size(width, height).outputQuality(1.0F).toFile(targetPath);
		} catch (IOException var6) {
			var6.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private static BufferedImage getBufferedImage(String path) throws IOException {
		BufferedImage result = null;
		File file = new File(path);
		if (file.exists()) {
			result = ImageIO.read(file);
		} else {
			System.out.println("img not exists!");
		}

		return result;
	}

	@SuppressWarnings("unused")
	public static String loadImageFile(String srcFile, String filePath, String fileName, int width, int height)
			throws Exception {
		byte[] data = null;
		FileImageInputStream input = null;

		try {
			String resizeSrcFile = resizeImage(filePath, fileName, height, width);
			boolean isResize = false;
			if (!resizeSrcFile.equals(srcFile)) {
				isResize = true;
			}

			input = new FileImageInputStream(new File(resizeSrcFile));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			boolean var11 = false;

			int numBytesRead;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}

			// byte[] data = output.toByteArray();
			data = output.toByteArray();
			output.close();
			input.close();
			if (isResize) {
				FileUtils.remove(resizeSrcFile);
			}

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		} catch (Exception var13) {
			log.error("error", var13);
			throw var13;
		}
	}

	public static String resizeImage(String filePath, String fileName, int width, int height) throws Exception {
		try {
			String srcImgPath = filePath + fileName;
			String resultName = "press_" + fileName;
			String resizeFilePath = filePath + resultName;
			File srcFile = new File(srcImgPath);
			if ((new Double((double) srcFile.length() / 1000.0D)).doubleValue() < 2000.0D) {
				return srcImgPath;
			} else {
				Image srcImg = ImageIO.read(srcFile);
				BufferedImage buffImg = null;
				buffImg = new BufferedImage(width, height, 1);
				buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, 4), 0, 0, (ImageObserver) null);
				ImageIO.write(buffImg, "PNG", new File(resizeFilePath));
				return resizeFilePath;
			}
		} catch (Exception var10) {
			log.error("error", var10);
			throw var10;
		}
	}

	public static void main(String[] args) throws Exception {
		pressImage("F:\\Code\\yiyaobao\\logo.png", "C:\\Users\\GaoWenChao\\Pictures\\Camera Roll\\10135394.jpeg", 0, 0,
				0.5F);
	}
}

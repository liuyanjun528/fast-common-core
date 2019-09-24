/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2017年12月26日 下午3:39:07
 * @version 1.0.0
 */
public final class CaptchaUtilsTest {

	public static void main(String[] args) throws IOException {
		int width = 200, height = 80;
		for (int i = 0; i < 1; i++) {
			String captcha = CaptchaUtils.generateCaptcha(4);
			BufferedImage image = CaptchaUtils.generateImage(width, height, captcha);
			File imageFile = new File("./src/test/java/" + captcha + "." + CaptchaUtils.JPEG_SUFFIX);
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			ImageIO.write(image, CaptchaUtils.JPEG_SUFFIX, fos);
			fos.close();
		}

	}

}

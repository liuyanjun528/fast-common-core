/**
 *  www.meditrusthealth.com Copyright © MediTrust Health 2017
 */
package com.meditrusthealth.fast.common.core.utils;

/**
 * <p>
 * </p>
 *
 * @author xiaoyu.wang
 * @date 2018年1月12日 下午2:15:39
 * @version 1.0.0
 */
public class QRCodeUtilsTest {

	public static void main(String[] args) throws Exception {
		// 生成二维码
		String text = "500000M26620180110000510";
		String src = FastCodeUtils.encrypt(text);
		System.out.println(src);
		String imagePath = "//Users//xiaoyu.wang//logo.jpg";
		String destPath = "//Users//xiaoyu.wang/2018.jpg";
		QRCodeUtils.encode(src, imagePath, destPath, true);
		String result = QRCodeUtils.decode(destPath);
		System.out.println(result);

		// 验证图片是否含有二维码
		// String destPath1 = "D:/image/output/53541299.jpg";
		// try {
		// String result = QRCodeUtils.decode(destPath1);
		// System.out.println(result);
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println(destPath1 + "不是二维码");
		// }
		//
		// // 一维码生成
		// String imgPath = "D://image/data/";
		// String contents = "100100000001";
		// String filePath = imgPath + contents + ".png";
		// int width = 105, height = 50;
		// QRCodeUtils.encodeBarcode(contents, width, height, filePath);
		// System.out.println("Michael ,you have finished zxing EAN13 encode.");
		// // 一维码识别
		// String msg = QRCodeUtils.decodeBarcode(filePath);
		// System.out.println("barcode:" + msg);
	}

}

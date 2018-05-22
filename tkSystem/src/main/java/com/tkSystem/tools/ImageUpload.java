package com.tkSystem.tools;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class ImageUpload {
	
	static Base64Image baseImage=new Base64Image();
	public static final String pictureAddress="http://"+getAddress()+":8080/wanyi/static/img/";
	
	public static final String orderShopFirstAddress="http://www.wanyi168.cn/data/upload/shop/store/goods/";
	
	
	public static String getAddress(){//获取本机IP
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	/**
	 * base64编码
	 * parameter spid:商铺ID
	 *           fwid:上架的服务ID
	 *           storage：放在img下面的那个文件夹，没有会自动创建
	 *           imgBase:base64编码
	 *           imgNames:图片名称
	 * return    图片名称
	 * */
	//一般用于服务发布图片上传
	public static List<String> uploadImg(String adddres,String[] imgBaseList){
		List<String> imgNameList=new ArrayList();
		try {
			for (int i = 0; i < imgBaseList.length; i++) {
				new File(adddres).mkdirs();//生成文件夹
				String	imgName=i+".PNG";
				Boolean bu=baseImage.GenerateImage(imgBaseList[i],adddres+"/"+imgName);
				imgNameList.add(imgName);
			}
			return imgNameList;
		} catch (Exception e){
			return imgNameList;
		}	
	} 
	
	//一般用于用户头像上传
	public String uploadImg(String adddres,String fileName_img,String imgBase){
		try {
			String dz=adddres+"\\"+fileName_img+"\\";
			new File(dz).mkdirs();//生成文件夹
			String	imgName=fileName_img+".PNG";
			Boolean bu=baseImage.GenerateImage(imgBase,dz+imgName);
			return dz+imgName;
		} catch (Exception e) {
			return null;
		}	
	}
	
	
/*	public List<String> uploadImg(String storage,List<String> imgBase){
		List<String> imgNameList=new ArrayList();
		try {
			String cjlj="/"+storage+"/";
			String path="src/main/webapp/static/img"+cjlj;//图片存放地址
			new File(path).mkdirs();//生成文件夹
			Integer convertNum=0;//转换数量
			for (String bm : imgBase) {
				convertNum++;
				String imgName=convertNum+".PNG";
				baseImage.GenerateImage(bm, path+imgName);
				String retName= cjlj+imgName;
				imgNameList.add(retName);
			}
			return imgNameList;
		} catch (Exception e) {
			return imgNameList;
		}	
	}*/
	
}

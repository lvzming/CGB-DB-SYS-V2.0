package com.tedu.cgb.team.sys.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cgb.team.common.vo.SysUserDeptVo;
import com.tedu.cgb.team.sys.dao.SysUserDAO;





@Controller
@RequestMapping("/excel")
public class ExcelController {
	@Autowired
	private SysUserDAO sysUserDAO;
	
	
	@ResponseBody
	 @RequestMapping(value = "/exportStatisticsNum/{excelName}")
	 public void exportWhiteList(@PathVariable("excelName") String excelName,
	                            HttpServletResponse response) {
	     HSSFWorkbook wb = new HSSFWorkbook();
	     //创建工作表
	     HSSFSheet sheet = wb.createSheet();
	     //创建行列
	     HSSFRow nRow = sheet.createRow(0);
	     HSSFCell nCell = nRow.createCell(0);
	     //控制行号列号
	     int rowNo = 0;
	     int colNo = 0;
	     //列标题
	     String[] title;
	     title = new String[]{"ID", "用户名", "部门名称","email","手机号码","是否启用","创建时间","修改时间","修改人"};
	     //设置标题到第一行的列中
	     nRow = sheet.createRow(rowNo++);
	     for (int i = 0; i < title.length; i++) {
	         nCell = nRow.createCell(i);
	         nCell.setCellValue(title[i]);
	     }
	     try {
	         /*
	          * 调用逻辑层函数查询
	          */

			/*
			 * List<Area> list = new LinkedList<Area>(); list.add(new
			 * Area("广东省","广州市","番禺区")); list.add(new Area("广东省","广州市","南沙区")); list.add(new
			 * Area("广东省","广州市","BB区")); list.add(new Area("广东省","广州市","AA区"));
			 */
	    	 
	    	 List<SysUserDeptVo> list=sysUserDAO.findAllUser();
	    	 for (SysUserDeptVo sysUserDeptVo : list) {
				System.out.println(sysUserDeptVo);
			}
	         //遍历并且创建行列
	         for (SysUserDeptVo allUser : list) {
	             //控制列号
	             colNo = 0;
	             //每遍历一次创建一行
	             nRow = sheet.createRow(rowNo++);
	             //ID
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getId());
	             //用户名
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getUsername());
	             //部门名称
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getSysDept().getName());
	            //email
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getEmail());
	             //手机号码
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getMobile());
	             //是否启用
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getValid()==1?"启用":"禁用");
	             
	             
	             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             //String cTime=sdf.format(allUser.getModifiedTime());
	            // System.out.println(cTime);
	             //创建时间
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getCreatedTime()==null?"":sdf.format(allUser.getCreatedTime()));
	             //修改时间
	             nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getModifiedTime()==null?"":sdf.format(allUser.getModifiedTime()));
	             //修改人
             	nCell = nRow.createCell(colNo++);
	             nCell.setCellValue(allUser.getModifiedUser()==null?"":allUser.getModifiedUser());
	             
	         }
	         loadResponse(excelName, response, wb);
	     } catch (Exception e) {
	     }
	 }

	 /**
	  * 设置Excel相关参数
	  *
	  * @param excelName
	  * @param response
	  * @param wb
	  * @throws IOException
	  */
	 private void loadResponse(String excelName, HttpServletResponse response, HSSFWorkbook wb) throws IOException {
		 
		 
		 //到这里，excel就已经生成了，然后就需要通过流来写出去
	     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	     //将excel写入流
	     wb.write(byteArrayOutputStream);
	     //设置文件标题
	     //String dateTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
	     String outFile = excelName + ".xls";
	     //设置返回的文件类型
	     response.setContentType("application/vnd.ms-excel;charset=utf-8");
	     //对文件编码
	     outFile = response.encodeURL(new String(outFile.getBytes("gb2312"), "iso8859-1"));
	     //使用Servlet实现文件下载的时候，避免浏览器自动打开文件
	     response.addHeader("Content-Disposition", "attachment;filename=" + outFile);
	     //设置文件大小
	     response.setContentLength(byteArrayOutputStream.size());
	     //创建Cookie并添加到response中
		/*
		 * Cookie cookie = new Cookie("fileDownload", "true"); cookie.setPath("/");
		 * response.addCookie(cookie);
		 */
	     
	     
	     
	     //将流写进response输出流中
	     ServletOutputStream outputstream = response.getOutputStream();
	     byteArrayOutputStream.writeTo(outputstream);

	     byteArrayOutputStream.close();
	     outputstream.flush();
	 }
	}
	
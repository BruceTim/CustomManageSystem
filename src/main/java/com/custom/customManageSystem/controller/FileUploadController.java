package com.custom.customManageSystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.custom.customManageSystem.model.Custom;
import com.custom.customManageSystem.model.User;
import com.custom.customManageSystem.service.ICustomService;
import com.custom.customManageSystem.util.ExcelUtils;
import com.custom.customManageSystem.util.StringUtils;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Resource
	private ICustomService customService;
	/**
	 * 
	 * @param files
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	public String fileUpload(HttpServletResponse response, HttpSession session, @RequestParam("file") CommonsMultipartFile[] files, Model model) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			User user = (User) session.getAttribute("loginuser");
			if(user == null){
				out.println("<script>alert('你还没有登录或登录已过期，请登录！');location='../login';</script>");
				return null;
			}
			String path = session.getServletContext().getRealPath("file/");
			for(int i=0;i<files.length;i++){
				CommonsMultipartFile file = files[i];
				if(!file.isEmpty()){
					long startTime = System.currentTimeMillis();
					String filename = file.getOriginalFilename();
					String name = StringUtils.getRandomString(5) + System.currentTimeMillis() +"-"+filename;
					File newFile = new File(path, name);
					FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
					List<Custom> customs = readExcel(path + File.separator + name);
					boolean flag = true;
					List<Custom> queryCustom = new ArrayList<Custom>();
					Custom cus = null;
					for(Custom custom:customs){
						String licenseplates1 = custom.getLicenseplates();
						String carframecode1 = custom.getCarframecode();
						if("".equals(licenseplates1)){
							if("".equals(carframecode1)){
								continue;
							}
							queryCustom = customService.checkSingle(null, carframecode1);
						}else {
							queryCustom = customService.checkSingle(licenseplates1, null);
							if(queryCustom.size() == 0 && !"".equals(carframecode1)){
								queryCustom = customService.checkSingle(null, carframecode1);
							}
						}
						if(queryCustom.size() > 0){
							cus = queryCustom.get(0);
							custom.setCustomid(cus.getCustomid());
							String licenseplates2 = cus.getLicenseplates();
							String carframecode2 = cus.getCarframecode();
							if(licenseplates2 !=null && !"".equals(licenseplates2)){
								custom.setLicenseplates(licenseplates2);
							}
							if(carframecode2 !=null && !"".equals(carframecode2)){
								custom.setCarframecode(carframecode2);
							}
							flag = flag && customService.updateByPrimaryKey(custom) > 0;
						} else {
							flag = flag && customService.insert(custom) > 0;
						}
					}
					if(flag) {
						out.println("<script>alert('导入成功');</script>");
						FileUtils.deleteQuietly(newFile);
						return "redirect:../custom/showCustom";
					}else {
						out.println("<script>alert('导入失败，请重试！');</script>");
						FileUtils.deleteQuietly(newFile);
						return "redirect:../custom/showCustom";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:../500";
		}
		return "redirect:../custom/showCustom";
	}
	
	public List<Custom> readExcel(String path){
		List<Custom> customs = ExcelUtils.readExcel(path);
		return customs;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("upload2")
	public String fileUpload(HttpSession session, HttpServletRequest request, Model model) throws IOException{
		User loginUser = (User) session.getAttribute("loginuser");
		if(loginUser == null){
			return "redirect:../login";
		}
		String path = session.getServletContext().getRealPath("file/");
		CommonsMultipartResolver multipartResovler = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResovler.isMultipart(request)){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while(iter.hasNext()){
				long startTime = System.currentTimeMillis();
				MultipartFile file = multipartRequest.getFile(iter.next());
				String filename = file.getOriginalFilename();
				String name = StringUtils.getRandomString(5) + System.currentTimeMillis() +"-"+filename;
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, name));
				long endTime = System.currentTimeMillis();
				long time = endTime - startTime;
				System.out.println(filename  + time + " ms");
				model.addAttribute("uphoto", request.getContextPath() + "../resources/img/users/" + name );
			}
		}
		return "redirect:../custom/showCustom";
	}
	
	@RequestMapping("/download/{filename}")
	public String download(@PathVariable("filename") String fileName, HttpSession session, HttpServletResponse response){
        OutputStream os = null;
        try {
        	os = response.getOutputStream();
        	response.reset();
        	response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");  
        	response.setContentType("application/octet-stream; charset=utf-8");  
        	String path = session.getServletContext().getRealPath("file/");
			File file = new File(path, fileName + ".xls");
			os.write(FileUtils.readFileToByteArray(file));  
			FileUtils.deleteQuietly(file);
        } catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:custom/outCustom";
	}
}

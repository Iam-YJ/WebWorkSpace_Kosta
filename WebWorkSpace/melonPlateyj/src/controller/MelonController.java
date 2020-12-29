package controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.Melon;
import model.dto.Menu;
import model.service.MelonService;
import net.sf.json.JSONArray;

@WebServlet("/melon")
public class MelonController extends HttpServlet implements Controller {
	private static final long serialVersionUID = 1L;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	public ModelAndView selectByGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("selectByGrade ȣ��");
		List<Melon> list = MelonService.selectByGrade();
		request.setAttribute("list", list);
		System.out.println("con list : " + list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("grid-listing-filterscol-map.jsp");

		return mv;
	}

	public ModelAndView selectByHits(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("selectByHits ȣ��");
		List<Melon> list = MelonService.selectByHits();
		request.setAttribute("list", list);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");

		return mv;
	}

	public ModelAndView selectByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("selectByType ȣ��");
		String type = request.getParameter("type");
		List<Melon> list = MelonService.selectByType(type);
		request.setAttribute("list", list);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("html/grid-listing-filterscol-map.jsp");

		return mv;
	}

	public ModelAndView updateHits(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("updateHits ȣ��");
		String resNo = request.getParameter("resNo");
		int result = MelonService.updateHits(Integer.parseInt(resNo));

		ModelAndView mv = new ModelAndView();
		mv.setRedirect(true);
		mv.setViewName("index.jsp");

		return mv;
	}

	public ModelAndView selectByArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("MelonoController�� selectByArea Controller ��û");

		List<Melon> list = MelonService.selectByArea("����");

		ModelAndView mv = new ModelAndView();

		request.setAttribute("list", list);

		mv.setViewName("/html/admin_section/deleteRestaurant.jsp");

		return mv;
	}

	public ModelAndView selectByResName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("selectByResName ȣ��");
		String resName = request.getParameter("resName");
		System.out.println(resName);
		List<Melon> list = MelonService.selectByResName(resName);
		request.setAttribute("list", list);
		System.out.println("Con list : " + list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("html/grid-listing-filterscol-map.jsp");

		return mv;
	}

	public ModelAndView selectByPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���� ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
		System.out.println("selectByPrice ȣ��");
		String price = request.getParameter("price");
		List<Melon> list = MelonService.selectByResName(price);
		request.setAttribute("list", list);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");

		return mv;
	}

	public ModelAndView selectDetailRes(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("selectDetailRes ȣ��");
		String resNo = request.getParameter("resNo");
		Melon melon = MelonService.selectDetailRes(Integer.parseInt(resNo));
		List<Menu> menu = MelonService.selectMenu(Integer.parseInt(resNo));
		request.setAttribute("melon", melon);
		request.setAttribute("menu", menu);

		System.out.println("melon : " + melon);
		System.out.println("menu : " + menu);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("html/detail-restaurant.jsp");
		return mv;
	}

	public ModelAndView selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("selectAll ȣ��");
		List<Melon> list = MelonService.selectAll();
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();

		mv.setViewName("html/grid-listing-filterscol-map.jsp");

		return mv;
	}
	
	public ModelAndView selectAll2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("selectAll2 ȣ��");
		List<Melon> list = MelonService.selectAll();
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/html/admin_section/deleteRestaurant.jsp");

		return mv;
	}

	public void selectRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("selectRecommend ȣ��.");

		List<Melon> list = MelonService.selectRecommend();
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter out = response.getWriter();

		out.println(arr);
	}
}
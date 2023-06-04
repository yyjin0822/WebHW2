package pj;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/guest.nhn")

public class guestController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private guestDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "pj/guestList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new guestDAO();
		ctx = getServletContext();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		dao = new guestDAO();
		
		Method m;
		String view = null;
		
		if(action == null) {
			action = "listGuest";
		}
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음 !!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다!!");
			view = START_PAGE;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
	
	public String addGuest(HttpServletRequest request) {
	    guest n = new guest();
	    
	    // 사용자가 입력한 데이터를 가져와서 guest 객체에 설정
	    n.setUser(request.getParameter("user"));
	    n.setEmail(request.getParameter("email"));
	    n.setTitle(request.getParameter("title"));
	    n.setPw(request.getParameter("pw"));
	    n.setContent(request.getParameter("content"));
	    
	    try {
	        dao.addGuest(n);
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("방명록 과정에서 문제 발생!!");
	        request.setAttribute("error", "방명록이 정상적으로 등록되지 않았습니다!!");
	        return listGuest(request);
	    }
	    
	    return "redirect:/guest.nhn?action=listGuest";
	}

	
	public String deleteGuest(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delGuest(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("방명록 삭제 과정에서 문제발생 !!!! ");
			request.setAttribute("error", "방명록이 정상적으로 삭제되지 않았습니다!!");
			return listGuest(request);
		}
		return "redirect:/guest.nhn?action=listGuest";
	}
	
	public String listGuest(HttpServletRequest request) {
		List<guest> list;
		try {
			list = dao.getAll();
			request.setAttribute("guestlist", list);
		} catch(Exception e) {
			e.printStackTrace();
			ctx.log("방명록 목록 생성 과정에서 문제 발생!! ");
			request.setAttribute("error", "방명록 목록이 정상적으로 처리되지 않았습니다. " );
		}
		return "pj/guestList.jsp";
	}
	
	public String getGuest(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			guest n = dao.getGuest(aid);
			request.setAttribute("guest", n);
		} catch(SQLException e) {
			e.printStackTrace();
			ctx.log("방명록을 가져오는 과정에서 문제 발생!!. ");
			request.setAttribute("error", "방명록을 정상적으로 가져오지 못 했습니다. ");
		}
		return "pj/guestUpdate.jsp";
	}
	
	public String updateGuest(HttpServletRequest request) {
	    guest n = new guest();
	    
	    // 사용자가 입력한 데이터를 가져와서 guest 객체에 설정
	    n.setAid(Integer.parseInt(request.getParameter("aid")));
	    n.setUser(request.getParameter("user"));
	    n.setEmail(request.getParameter("email"));
	    n.setTitle(request.getParameter("title"));
	    n.setPw(request.getParameter("pw"));
	    n.setContent(request.getParameter("content"));
	    
	    try {
	        dao.updateGuest(n);
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("방명록 수정 과정에서 문제 발생!!");
	        request.setAttribute("error", "방명록이 정상적으로 수정되지 않았습니다!!");
	        return listGuest(request);
	    }
	    
	    return "redirect:/guest.nhn?action=listGuest";
	}
	

}

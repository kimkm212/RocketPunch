package net.member.action.friend;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberFriendDAO;
import net.member.db.dto.MemberFriend;

public class FriendAcceptAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		MemberFriend mf = new MemberFriend();
		MemberFriendDAO mfdao = new MemberFriendDAO();
		
		//세션아이디는 친구 신청을 수락하는 맴버
		Integer fromId = (Integer)request.getSession().getAttribute("id");
		mf.setMemberId1(fromId);
		
		//파라미터로 넘오온 id는 친구신청을 한 맴버
		Integer toId = Integer.parseInt(request.getParameter("toId"));
		mf.setMemberId2(toId);
		
		//친구관계는 relation값이 2임
		mf.setRelation("2");
		
		int result = mfdao.acceptFriend(mf);
		
		//결과값을 리턴해 ajax처리함
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();
		return null;
	}

}

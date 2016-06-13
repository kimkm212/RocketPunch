package net.member.action.friend;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberFriendDAO;
import net.member.db.dto.MemberFriend;

public class FriendListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberFriend mf = new MemberFriend();
		MemberFriendDAO mfdao = new MemberFriendDAO();
		
		//세션아이디는 조회할 아이디
		Integer fromId = (Integer)request.getSession().getAttribute("id");
		mf.setMemberId1(fromId);
	
		//친구신청상태 relation 2인 사람만 조회
		mf.setRelation("2");
		
		List<HashMap<String, Object>> list = mfdao.getFriends(mf);
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/member_friend_list.jsp");
		
		return forward;
	}

}

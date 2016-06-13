<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String sessionName = (String)session.getAttribute("name");
  Integer sessionId = (Integer)session.getAttribute("id");
  Integer sessionC_Id = (Integer)session.getAttribute("c_id");
  String sessionP_Img = (String)session.getAttribute("pro_img");
  if(sessionP_Img==null){
    sessionP_Img = "/imgs/def_pro.jpg";
  }

%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>RocketPunch</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/index.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script src="/js/index.js"></script>
    <script>var sessionId=<%=sessionId%>; </script>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-defualt container">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
              <i class="fa fa-bars fa-2x" aria-hidden="true"></i>
            </button>
            <img src="/imgs/rocketpunch_bi.png" alt="" class="rocketImg"/>
            <a class="navbar-brand" href="/">RocketPunch</a>
          </div>
          <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
              <li class="active"><a href="/">Home</a></li>
              <li><a href="/CompanyList.co">기업</a></li>
              <li><a href="/MemberList.me">사람</a></li>
              <li><a href="/RecruitList.co">채용</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
              <%
              if(sessionId==null){
              %>
                <li><a href="" data-toggle="modal" data-target="#singup_modal" ><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
                <li><a href="" data-toggle="modal" data-target="#login_modal"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
              <%
              }else{
              %>
               <li class="dropdown" id="my_pro">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <img src="<%=sessionP_Img%>" alt="내 사진"/><p><%=sessionName%></p>
                    <span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu">
                    <li><a href="/MemberInfoAction.me?id=<%=sessionId%>">내 프로필</a></li>
                    <li><a href="/FriendList.me">내 친구</a></li>
                  <%if(sessionC_Id!=0){%>
                    <li><a href="/CompanyInfo.co?id=<%=sessionC_Id%>">내 회사</a></li>
                    <li><a href="/RecruitAdmin.co?id=<%=sessionC_Id%>">내 회사 채용정보</a></li>
                  <%} %>
                    <li data-toggle="modal" data-target="#drop_modal"><a>회원탈퇴</a></li>
                    <li><a href="/MemberLogoutAction.me" ><span class="glyphicon glyphicon-log-out"></span>로그아웃</a></li>
                  </ul>
                </li>
              <%
              }
              %>
            </ul>
          </div>
        </div>
      </nav>
    </header>

  <!--회원가입 모달 -->
  <div id="singup_modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로켓펀치 회원가입</h4>
        </div>
        <div class="modal-body">
          <p>SNS 계정으로 빠르게 가입할 수 있습니다</p>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>FaceBook</button>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-git-square fa-2x" aria-hidden="true"></i>GitHub</button>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-google-plus-square fa-2x" aria-hidden="true"></i>Google+</button>
        </div>
        <div class="modal-footer">
          <div class="col-md-9 col-md-offset-3">
            <p>Email로 가입하기</p>
            <form class="col-xs-12" action="/MemberJoinAction.me" method="post">
              <div class="form-group">
                <input type="email" class="form-control" name="email" autocomplete="off" placeholder="이메일" required>
              </div>
              <div class="form-group form-inline">
                <input type="text" class="form-control" name="first_name_k" autocomplete="off" placeholder="성" required>
                <input type="text" class="form-control" name="last_name_k" autocomplete="off" placeholder="이름" required>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" name="pwd" autocomplete="off" placeholder="비밀번호" required>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" autocomplete="off" placeholder="비밀번호 확인" required>
              </div>
               <!--버튼 제어-->
               <button type="submit" class="btn btn-success btn-block">회원가입</button>
             </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!--로그인 모달 -->
  <div id="login_modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로켓펀치 로그인</h4>
        </div>
        <div class="modal-body">
          <p>SNS 계정으로 쉽고 간편하게 로그인 할 수 있습니다</p>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>FaceBook</button>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-git-square fa-2x" aria-hidden="true"></i>GitHub</button>
          <button type="button" class="btn btn-primary btn-md"><i class="fa fa-google-plus-square fa-2x" aria-hidden="true"></i>Google+</button>
        </div>
        <div class="modal-footer">
          <div class="col-md-offset-6">
            <p>Email로그인</p>
            <form action="/MemberLoginAction.me" method="post">
              <div class="form-group">
                <input type="email" class="form-control" name="email" autocomplete="off" placeholder="이메일" required>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" name="pwd" autocomplete="off" placeholder="비밀번호" required>
              </div>
               <button type="submit" class="btn btn-success btn-block">로그인</button>
             </form>
             <p>비밀번호 찾기</p>
          </div>
        </div>
      </div>
    </div>
  </div>

<!--회원 탈퇴 모달-->
  <div id="drop_modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로켓펀치 회원탈퇴</h4>
        </div>
        <div class="modal-body">
          <form action="/MemberDelete.me" method="post">
            <div class="form-group">
              <input type="password" class="form-control" name="pwd" autocomplete="off" placeholder="비밀번호" required>
            </div>
             <button type="submit" class="btn btn-success btn-block">탈퇴</button>
           </form>
        </div>
        <div class="modal-footer">

        </div>
      </div>
    </div>
  </div>


//친구 관계  AJAX 컨트롤러
$(document).ready(function(){

  //DOM TREE 갱신해야 함......
  //친구추가
  $('.btn_friends').on('click','.addFriend',function(){
    var toId = $(this).parent('.btn_friends').attr('id');
    var area = $(this).parent('.btn_friends');
    $.post("/FriendRequest.me",{toId : toId},function(data, status){
      if(data==1){
        alert('친구 신청 완료');
        area.empty();
        area.append('<button class="btn btn-primary btn-sm cancelFriend">친구신청 철회</button>');
      }else{
        alert('오류발생');
      }
    });
  });

  //친구 수락
  $('.btn_friends').on('click','.acceptFriend',function(){
    var toId = $(this).parent('.btn_friends').attr('id');
    var area = $(this).parent('.btn_friends');
    $.post("/FriendAccept.me",{toId : toId},function(data, status){
      if(data==1){
        alert('친구가 되었습니다.');
        area.empty();
        area.append('<button class="btn btn-default btn-sm">친구</button>');
      }else{
        alert('오류발생');
      }
    });
  });

  //친구 요청 취소
  $('.btn_friends').on('click','.cancelFriend',function(){
    var toId = $(this).parent('.btn_friends').attr('id');
    var area = $(this).parent('.btn_friends');
    $.post("/FriendDelete.me",{toId : toId},function(data, status){
      if(data==1){
        alert('친구요청이 취소됨.');
        area.empty();
        area.append('<button class="btn btn-warning btn-sm addFriend">친구추가</button>');
      }else{
        alert('오류발생');
      }
    });
  });

  //친구 요청 거절
  $('.btn_friends').on('click','.rejectFriend',function(){
    var toId = $(this).parent('.btn_friends').attr('id');
    var area = $(this).parent('.btn_friends');
    $.post("/FriendDelete.me",{toId : toId},function(data, status){
      if(data==1){
        alert('친구요청 거절함.');
        area.empty();
        area.append('<button class="btn btn-warning btn-sm addFriend">친구추가</button>');
      }else{
        alert('오류발생');
      }
    });
  });

  //회사 구성원 가입하기
  $('#join_co').click(function(){
    var a = confirm('가입하시겠습니까?');
    if(a==true){
      var c_id = $(this).val();
      var thisButton = $(this);
      $.post("/MemberJoinCompany.me",{c_id : c_id},function(data, status){
        if(data==1){
          alert('가입했습니다.');
          thisButton.remove();
        }else{
          alert('오류발생');
        }
      });
    }
  });



});

		function ItemList(){
	    let f = document.createElement('form');
	    f.setAttribute('method', 'get');
	    f.setAttribute('action', '/ItemList');
	    document.body.appendChild(f);
	    f.submit();
	}

	$(document).ready(function(){
		$(".buttonA").on("click", function(){
    	   let itemId =  $(this).parent("li").parent("ul").find("input[name='itemId']").val();
    	   let count = 1;

    	       $.ajax({
                     type: "get",
                     url : "/user/cart",
                     data: {
                         "itemId" : itemId,
                         "num" : count,
                     },
                     success: function(res) {
                       if(res.indexOf('로그인') == -1){
                           alert("장바구니담기 성공!", res);
                           history.go(0);
                       }else{
                           alert("로그인이 필요합니다.", res);
                           location.href = "/login";
                       }
                     },
                     error: function(err){
                         alert("장바구니담기 실패!", err);
                     }
                 });
    	});
	})

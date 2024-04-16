
	$(".buttonB").on("click", function(){
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
                     alert("장바구니담기 성공!", res);
                     history.go(0);
                 },
                 error: function(err){
                     alert("장바구니담기 실패!", err);
                 }
             });
	});

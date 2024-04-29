// 페이지 진입 시 기본 값
window.addEventListener('DOMContentLoaded', function() {
    showContent('A');
  });

  $( document ).ready( function() {

    let numcount = $("#num").val();
    let numprice = $("#totalprice").val();
    let totalPrice = numcount * numprice;
    $(".result_price").text(totalPrice + " 원");
    document.getElementById("allprice").value = totalPrice;
    var btn = document.getElementById("requestDiv"); 	
    var con = document.getElementById("productArea"); 	
    var name = $("#sessionUsername").val();

    if(name == 'admin'){
        $(".requestDiv").on('click',function(){
            $(this).parent(".inquiry").find(".productArea").css("display", "flex");
            console.log("g");
        });
    }

    $("#num").change(function(){ 
      if(numcount >= 1){
        numprice = $("#totalprice").val();
        numcount = $("#num").val();
        
        console.log(numcount);
        console.log(numprice);
        
        totalPrice = numcount * numprice;
        $(".result_price").text(totalPrice + " 원");
        document.getElementById("allprice").value = totalPrice;
      }
    });

  });

  
  function showContent(content) {
    var contentA = document.getElementById("contentA");
    var contentB = document.getElementById("contentB");
    var contentC = document.getElementById("contentC");
    var contentD = document.getElementById("contentD");
  
    // 내용 숨김
    contentA.style.display = "none";
    contentB.style.display = "none";
    contentC.style.display = "none";
    contentD.style.display = "none";
  
    // 선택한 내용 보이기
    if (content === "A") {
      contentA.style.display = "block";
    } else if (content === "B") {
      contentB.style.display = "block";
    } else if (content === "C") {
      contentC.style.display = "block";
    } else if (content === "D") {
      contentD.style.display = "block";
    }
  };

  const carts = () => {
      const itemId = document.getElementById("itemId").value;
      const count = document.getElementById("num").value;
      console.log("itemId", itemId);
      console.log("count", count);

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
                  location.href = "/item/"+itemId+"/detail";
              }else{
                  alert("로그인이 필요합니다.", res);
                  location.href = "/login";
              }

          },
          error: function(err){
              alert("장바구니담기 실패!", err);
          }
      });
  }

    const comment = () => {
        const itemId = document.getElementById("itemId").value;
        const review = document.getElementById("Product Review").value;
        console.log("itemId", itemId);

        if(review == ""){

            alert("내용을 입력해주세요.");
            return;
        }

        $.ajax({
            type: "get",
            url : "/user/addreviews",
            data: {
                "itemId" : itemId,
                "review" : review,
            },
            success: function(res) {
                if(res.indexOf('로그인') == -1){
                    alert("상품평 완료!", res);
                    location.href = "/item/"+itemId+"/detail";
                }else{
                    alert("로그인이 필요합니다.", res);
                    location.href = "/login";
                }
            },
            error: function(err){
                alert("상품평 실패!", err);
            }
        });
    }

 var IMP = window.IMP;
     IMP.init("imp61074715");

     var today = new Date();
     var hours = today.getHours(); // 시
     var minutes = today.getMinutes();  // 분
     var seconds = today.getSeconds();  // 초
     var milliseconds = today.getMilliseconds();
     var makeMerchantUid = hours +  minutes + seconds + milliseconds;

     function requestPay() {
     let itemId = document.getElementById("itemId").value;
     let totalprice = document.getElementById("allprice").value;
     let num = document.getElementById("num").value;

         IMP.request_pay({
             pg : 'kakaopay',
             merchant_uid: "IMP"+makeMerchantUid,
             name : '당근 10kg',
             amount : 1004,
             buyer_email : 'Iamport@chai.finance',
             buyer_name : '아임포트 기술지원팀',
             buyer_tel : '010-1234-5678',
             buyer_addr : '서울특별시 강남구 삼성동',
             buyer_postcode : '123-456'
         }, function (rsp) { // callback
             if (rsp.success) {
                 console.log(rsp);
                 var msg = '결제가 완료되었습니다.';
                 msg += '결제 금액 : ' + rsp.paid_amount;
                 orderForm();
             } else {
                 console.log(rsp);
                 var msg = '결제에 실패하였습니다.';
                 msg += '에러내용 : ' + rsp.error_msg;
             }
             alert(msg);
         });
     }

     function orderForm() {
      var form = document.createElement("form");
      form.setAttribute("method", "get");

      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "itemId");
      hiddenField.setAttribute("value", itemId);
      form.appendChild(hiddenField);

      hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "totalprice");
      hiddenField.setAttribute("value", totalprice);
      form.appendChild(hiddenField);

      hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "num");
      hiddenField.setAttribute("value", num);
      form.appendChild(hiddenField);

      form.setAttribute("action", "/user/order");
      document.body.appendChild(form);
      form.submit();
      }




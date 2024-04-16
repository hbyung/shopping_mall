
  $( document ).ready( function() {

    let price = $(".price").val();
    let delivery =0;
    let totalPrice = 0;

    	if(price >= 30000){
    		delivery = 0;
            totalPrice = parseInt(price) + parseInt(delivery);
            console.log(totalPrice);
            $(".totalPrice").text(totalPrice+"원");
            document.getElementById('orderPrice').value = totalPrice;
    	} else if(price ==0) {
    		delivery = 0;
    		totalPrice = 0;
    		$(".totalPrice").text(totalPrice+"원");
    	} else {
    		delivery = 3000;
    		totalPrice = parseInt(price) + parseInt(delivery);
    		console.log(totalPrice);
    		$(".totalPrice").text(totalPrice+"원");
    		document.getElementById('orderPrice').value = totalPrice;
    	}

  });

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
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
     let itemName = document.getElementById("itemName").value;
     let memberId = document.getElementById("memberId").value;
     let memberName = document.getElementById("memberName").value;
     let num = document.getElementById("num").value;
     let postcode = document.getElementById("postcode").value;
     let address = document.getElementById("address").value;
     let detailAddress = document.getElementById("detailAddress").value;
     let orderPrice = document.getElementById("orderPrice").value;

      if(address == "" || detailAddress == ""){
         alert("주소를 입력해주세요");
         return;
      }

         IMP.request_pay({
             pg : 'kakaopay',
             merchant_uid: "IMP"+makeMerchantUid,
             name : itemName,
             amount : orderPrice,
             buyer_email : 'Iamport@chai.finance',
             buyer_name : memberName,
             buyer_tel : '010-1234-5678',
             buyer_addr : address,
             buyer_postcode : '123-456'
         }, function (rsp) { // callback
             if (rsp.success) {
                 console.log(rsp);
                 var msg = '결제가 완료되었습니다.';
                 msg += '결제 금액 : ' + rsp.paid_amount;
                 order();

             } else {
                 console.log(rsp);
                 var msg = '결제에 실패하였습니다.';
                 msg += '에러내용 : ' + rsp.error_msg;
             }
             alert(msg);
         });

      function order() {
          var form = document.createElement("form");
          form.setAttribute("method", "post");

          var hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "itemId");
          hiddenField.setAttribute("value", itemId);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "memberId");
          hiddenField.setAttribute("value", memberId);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "num");
          hiddenField.setAttribute("value", num);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "postcode");
          hiddenField.setAttribute("value", postcode);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "address");
          hiddenField.setAttribute("value", address);
          form.appendChild(hiddenField);

          hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "detailAddress");
          hiddenField.setAttribute("value", detailAddress);
          form.appendChild(hiddenField);

          form.setAttribute("action", "/order");
          document.body.appendChild(form);
          form.submit();
      }

     }


$(document).ready(function(){
	setTotalInfo();

	$(".plus_btn").on("click", function(){
		let quantity = $(this).parent("li").find("input").val();
		$(this).parent("li").find("input").val(++quantity);
		setTotalInfo($(".shopping"));
		let price = $(this).parent("li").parent("ul").find("input[name='realPrice']").val();
		let onePrice = quantity * price;
		$(this).parent("li").parent("ul").children('li:eq(5)').text(onePrice+" 원");

	});

	$(".minus_btn").on("click", function(){
		let quantity = $(this).parent("li").find("input").val();
		if(quantity > 1){
			$(this).parent("li").find("input").val(--quantity);
			setTotalInfo($(".shopping"));
		let price = $(this).parent("li").parent("ul").find("input[name='realPrice']").val();
		let onePrice = quantity * price;
		$(this).parent("li").parent("ul").children('li:eq(5)').text(onePrice+" 원");
		}
	});


});

$(".shopping").on("change", function(){

	setTotalInfo($(".shopping"));
});

function setTotalInfo(){
	let price = $(".realPrice").val();
	let totalPrice = 0;
	let delivery =0;
	let final_price =0;
	let finalTotalPrice = 0;
	let totalCount = 0;

	$(".shoppingLiST").each(function(index, element){
		if($(element).find(".shoppingCheck").is(":checked") === true){	//체크여부
		// 총 가격
		totalPrice = parseInt($(element).find(".realPrice").val());
		// 총 갯수
		totalCount = parseInt($(element).find(".number").val());

		final_price += totalCount * totalPrice;

		}

	});

	if(final_price >= 30000){
		delivery = 0;
	} else if(final_price ==0) {
		delivery = 0;
	} else {
		delivery = 3000;
	}

	finalTotalPrice = final_price + delivery;

	$(".finalall_price").text(final_price+" 원");
	// 총 갯수
	$(".delivery").text(delivery+ " 원");
	// 최종 가격(총 가격 + 배송비)
	$(".finalTotalPrice").text(finalTotalPrice + " 원");
	document.getElementById("finalTotalPrice").value = finalTotalPrice;
};

function selectAll(selectAll)  {
	const checkboxes 
	   = document.querySelectorAll('input[type="checkbox"]');
	
	checkboxes.forEach((checkbox) => {
	  checkbox.checked = selectAll.checked
	})
};

function Ordercarts() {
    console.log("실행");
    let itemName= [];
    let realPrice= [];
    let number= [];
    finalTotalPrice = document.getElementById("finalTotalPrice").value;

    	$(".shoppingLiST").each(function(index, element){
    		if($(element).find(".shoppingCheck").is(":checked") === true){	//체크여부
            itemName.push($(element).find(".itemName").val());
            console.log(itemName);
            realPrice.push(parseInt($(element).find(".realPrice").val()));
            console.log(realPrice);
            number.push(parseInt($(element).find(".number").val()));
            console.log(number);
    		}
    	});
    	console.log(itemName);

         var form = document.createElement("form");
         form.setAttribute("method", "get");

         var hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "itemName");
         hiddenField.setAttribute("value", itemName);
         form.appendChild(hiddenField);

         hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "itemPrice");
         hiddenField.setAttribute("value", realPrice);
         form.appendChild(hiddenField);

         hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "number");
         hiddenField.setAttribute("value", number);
         form.appendChild(hiddenField);

         hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "finalTotalPrice");
         hiddenField.setAttribute("value", finalTotalPrice);
         form.appendChild(hiddenField);

         form.setAttribute("action", "/cart/Order");
         document.body.appendChild(form);
         form.submit();

};

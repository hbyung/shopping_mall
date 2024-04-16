$(document).ready(function(){
$("#mainFile").on('change',function(){
    let maxSize = 5 * 1024 * 1024;; //* 5MB 사이즈 제한
	let fileSize = this.files[0].size; //업로드한 파일용량

    if(fileSize > maxSize){
		alert("파일첨부 사이즈는 5MB 이내로 가능합니다.");
		$(this).val(''); //업로드한 파일 제거
		return; 
	}
    var fileName = $("#mainFile").val();
    $(".upload-mainname").val(fileName);
    
  });


// $("#file").on('change',function(){

//     var fileName = {};
//     fileName = $("#file").val();
//     console.log(fileName);
//     for(const key in fileName){
//         $(".upload-name").val(key);
//     }

// });  

$('#file').each(function(){
    $('#file').change(function (e) {
        let maxSize = 5 * 1024 * 1024;; //* 5MB 사이즈 제한
        let fileSize = this.files[0].size; //업로드한 파일용량
    
        if(fileSize > maxSize){
            alert("파일첨부 사이즈는 5MB 이내로 가능합니다.");
            $(this).val(''); //업로드한 파일 제거
            return; 
        }
        var index = $(this).index();
        var files = e.target.files.length
        var fileName = "";
  
        $(".upload-name").val(files + "개");

    });
});

$("select[name=list]").on('change',function(){
    category = document.querySelector("select[name=list] option:checked").value;
    if(category == "음식"){
        $(".bookTab, .machineTab,.shoesTab").css("display", "none");
        $(".foodTab").css("display", "table-row");
    }else if(category == "전자"){
        $(".bookTab, .shoesTab, .foodTab").css("display", "none");
        $(".machineTab").css("display", "table-row");
    }else if(category == "신발"){
        $(".bookTab, .machineTab,.foodTab").css("display", "none");
        $(".shoesTab").css("display", "table-row");
    }else if(category == "책"){
        $(".machineTab,.shoesTab, .foodTab").css("display", "none");
        $(".bookTab").css("display", "table-row");
    };

});

});




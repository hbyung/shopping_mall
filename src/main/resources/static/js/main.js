    const searchicons = document.querySelector('.search-icons');
    const searchbox = document.querySelector('.search-box');
    const icons = document.querySelector('.navbar_icons');


    searchicons.addEventListener('click', () => {
      searchbox.classList.toggle('active');
    });

          const btn_menu = document.querySelector(".btn-menu");
          const side_bar = document.querySelector(".sidebar");

          btn_menu.addEventListener("click", function () {
            side_bar.classList.toggle("expand");
            changebtn();
          });

          function changebtn() {
            if (side_bar.classList.contains("expand")) {
              btn_menu.classList.replace("bx-menu", "bx-menu-alt-right");

              
              
            } else {
              btn_menu.classList.replace("bx-menu-alt-right", "bx-menu");

            }
          }

    // 스크롤 이벤트 리스너
window.addEventListener('scroll', () => {
  // 스크롤 위치가 100px 이상일 때 위로 가기 버튼을 보이게 함
  if (
    document.body.scrollTop > 100 ||
    document.documentElement.scrollTop > 20
  ) {
    document.getElementById('btn-back-to-top').style.display = 'block';
  } else {
    document.getElementById('btn-back-to-top').style.display = 'none';
  }
});

// 클릭 시 페이지 맨 위로 스크롤 (애니메이션 효과 추가)
function backToTop() {
  const position =
    document.documentElement.scrollTop || document.body.scrollTop;
  if (position) {
    window.requestAnimationFrame(() => {
      window.scrollTo(0, position - position / 10);
      backToTop();
    });
  }
};      

function ex() {
  const ex1 = document.querySelectorAll(".exprice");
  ex1.forEach((item)=>{
    console.log(item);
})
}






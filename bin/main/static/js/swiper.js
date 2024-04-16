const swiper1 = new Swiper('.swheader', {

   speed : 1000,
   loop: true,
   spaceBetween: 0,
    autoplay:{
      delay:1500,
      disableOnInteraction: false
    },

   // If we need pagination
   pagination: {
     el: '.swiper-pagination',
   },

   // Navigation arrows
   navigation: {
     nextEl: '.swiper-button-next',
     prevEl: '.swiper-button-prev',
   },

   // And if we need scrollbar

 });

  var swiper2 = new Swiper(".mySwiper", {
    slidesPerView: 3,
    grid: {
    rows: 2,
    },
    spaceBetween: 30,
    pagination: {
    el: ".swiper-pagination",
    clickable: true,
    },

       // Navigation arrows
   navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
});

const div = document.getElementById('.swiper-wrapper');

$(".mySwiper")
  .mouseover(function () {
  $(this).find(".swiper-button-prev, .swiper-button-next").css("visibility", "visible");

});

$(".mySwiper")
  .mouseout(function () {
    $(this).find(".swiper-button-prev, .swiper-button-next").css("visibility", "hidden");

});

$(".swheader")
  .mouseover(function () {
  $(this).find(".swiper-button-prev, .swiper-button-next").css("visibility", "visible");

});

$(".swheader")
  .mouseout(function () {
    $(this).find(".swiper-button-prev, .swiper-button-next").css("visibility", "hidden");

});


window.onload= function(){
    const input_id = document.querySelector('input[type=text]')
    const input_pw = document.querySelector('input[type=password]')
    const input_name = document.querySelector('.user_name')
    const input_phone = document.querySelector('.phone_name')
    const id_error = document.querySelector('.id_error')
    const pw_error = document.querySelector('.pw_error')
    const name_error = document.querySelector('.name_error')
    const phone_error = document.querySelector('.phone_error')

    input_id.addEventListener('click',function(){
        id_error.style.display = 'block'
    })

    input_pw.addEventListener('click',function(){
        pw_error.style.display = 'block'
    })

    input_name.addEventListener('click',function(){
        name_error.style.display = 'block'
    })

    input_phone.addEventListener('click',function(){
        phone_error.style.display = 'block'
    })

}
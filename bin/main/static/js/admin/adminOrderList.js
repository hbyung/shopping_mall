 function cancel(id) {
 var form = document.createElement("form");
 form.setAttribute("method", "post");
 form.setAttribute("action", "/admin/" + id + "/cancel");
 document.body.appendChild(form);
 form.submit();
 }
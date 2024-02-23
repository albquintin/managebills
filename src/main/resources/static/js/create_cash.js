$(document).ready(function(){
 setValuesToZero();
 $(this).click(function() {
    setValuesToZero();
    var activeElement = document.activeElement;
    if(activeElement.value == "0"){
        activeElement.value = '';
    }
  });
});
function setValuesToZero(){
    if(document.getElementById("totalPrice").value == ''){
        document.getElementById("totalPrice").value = "0";
    }
    if(document.getElementById("iva21base").value == ''){
        document.getElementById("iva21base").value = "0";
    }
    if(document.getElementById("iva21amount").value == ''){
        document.getElementById("iva21amount").value = "0";
    }
    if(document.getElementById("iva10base").value == ''){
        document.getElementById("iva10base").value = "0";
    }
    if(document.getElementById("iva10amount").value == ''){
        document.getElementById("iva10amount").value = "0";
    }
}

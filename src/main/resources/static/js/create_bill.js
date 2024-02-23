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
    if(document.getElementById("iva10base").value == ''){
        document.getElementById("iva10base").value = "0";
    }
    if(document.getElementById("iva5base").value == ''){
        document.getElementById("iva5base").value = "0";
    }
    if(document.getElementById("iva4base").value == ''){
        document.getElementById("iva4base").value = "0";
    }
    if(document.getElementById("iva0").value == ''){
        document.getElementById("iva0").value = "0";
    }
    if(document.getElementById("retention").value == ''){
        document.getElementById("retention").value = "0";
    }
}

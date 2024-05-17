$(document).ready(function(){
 setValuesToZero();
 $(this).click(function() {
    setValuesToZero();
    var activeElement = document.activeElement;
    if(activeElement.value == "0"){
        activeElement.value = '';
    }
  });
 $("#codiasa").change(function() {
     if(this.checked) {
        $('#base10IvaColumn').css("display", "none");
        $('#base21IvaColumn').css("display", "");
     }else{
        $('#base10IvaColumn').css("display", "");
        $('#base21IvaColumn').css("display", "none");
     }
 });
});
function setValuesToZero(){
    if(document.getElementById("iva21base").value == ''){
        document.getElementById("iva21base").value = "0";
    }
    if(document.getElementById("iva10base").value == ''){
        document.getElementById("iva10base").value = "0";
    }
}

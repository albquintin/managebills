$(document).ready(function(){
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